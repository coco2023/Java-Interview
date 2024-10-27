这又是一个高频面试题，原因在于，很多候选人简历中的专业技能，会赫然写着“熟悉 MySQL 数据库，具备良好的数据库表设计能力，擅长 SQL 优化。”

而往往这最后半句话，激起了面试官的兴趣，于是他便提出这样的问题：“那你说说在过往项目中，都做过哪些 SQL 优化？”

对于这样的问题，如果我们想回答得颇具亮点，成为加分项，以下的几个环节是必不可少的：

1. 交待项目的业务场景，也就是说，你的慢 SQL 是在什么背景下产生的。
2. 慢 SQL 是如何产生的，库表中的数据量有多大，SQL 是如何写的，执行计划是什么，以及运行时长是多久。
3. 你对于这条慢 SQL 的思考路径是什么，打算用什么方式去进行优化。
4. 优化后的 SQL 以及对应的执行计划是什么样的，最终执行时长从多少优化到了多少。

另外就是，在回答该问题的时候，我们要尽量规避那些烂大街的优化方式，类似于：

- 在全表扫描的时候，给 where 语句后面的字段加个索引。
- 从 select * from table，优化为 select 具体字段 from table。
- limit 深分页场景，从偏移量的方式优化为 id > 10000 的方式。
- 在模糊查询场景中，百分号不要写在关键字的左侧，一定要写在关键字的右侧。
- 循环中的单次 insert 操作，改为一次批量 insert 操作。

上述这些虽然也算是 SQL 优化，但在面试中出现频率过高，因此并不建议继续使用。毕竟我们给出面试官的答案是有亮点的，而不是应付过去，对吧？

接下来我们举几个 SQL 优化的完整 case（限于篇幅原因，本文先着重剖析其中一个），以便于大家进行理解。




## 案例一

### 业务背景

某市的人口档案管理系统，支持对本市人口进行多维度的统计，统计维度包括但不限于姓氏、职业、年龄、所属行政区等。

### 慢查场景

该系统有一张 person 主表，数据量 500万+，表结构如下：

```SQL
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `sex` tinyint(4) NOT NULL COMMENT '性别，0：男，1：女',
  `age` smallint(6) NOT NULL COMMENT '年龄',
  `id_card_number` char(18) NOT NULL COMMENT '身份证号',
  `job_type` smallint(6) DEFAULT NULL COMMENT '工作类型',
  `job` varchar(50) DEFAULT NULL COMMENT '工作岗位',
  `district` varchar(50) DEFAULT NULL COMMENT '行政区',
  `address` varchar(300) DEFAULT NULL COMMENT '家庭住址',
  `phone` char(20) DEFAULT NULL COMMENT '电话',
  `status` tinyint(4) NOT NULL COMMENT '当前状态，0：在世，1：不在世',
  `description` varchar(500) DEFAULT NULL COMMENT '个人描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除，0：不删除，1：删除',
  PRIMARY KEY (`id`),
  KEY `idx_name_age` (`name`,`age`) USING BTREE,
  KEY `idx_age_name` (`age`,`name`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_modify_time` (`modify_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5900563 DEFAULT CHARSET=utf8;
```

统计人员希望通过系统获取的数据结果是：统计全市目前在世的、年龄最大的 100 个张姓居民的个人信息。目前系统需要执行将近 12 秒才能得到结果，于是希望研发人员对此进行优化。

我们看了一下，系统的代码逻辑很简单，就是从前端获取到请求后，将参数传给下面的 SQL 语句去执行获取结果：

``` SQL
SELECT * FROM person WHERE `name` LIKE '张%' and `status`= 0 ORDER BY age DESC LIMIT 100;
```

而且，确实是执行了 10 秒多才出结果。

<p align=center><img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b22c0d33f9f248af8faec1d9b9d74ee4~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1046&h=404&s=97610&e=png&b=f5f5f5" alt="image.png"  /></p>


这两个字段我们按照排列顺序，建了两个联合索引，按理说不应该执行这么久的。

```
KEY `idx_name_age` (`name`,`age`) USING BTREE,
KEY `idx_age_name` (`age`,`name`) USING BTREE,
```

继续看这条 SQL 对应的执行计划：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/183983c7a2244dfc9886bac0a15e124e~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1006&h=249&s=35682&e=png&b=f8f8f8" alt="image.png"  /></p>



我们发现，这条 SQL 的执行计划中的几个关键点：用到的索引是 idx_age_name，type = index，Extra 中有 Using where。

-   type = index，全索引扫描，需要遍历整个索引来查询匹配行，性能仅仅比全表扫描要好一些。
-   Using where，表示查询的列未被索引覆盖，所以需要回表读取数据。
-   idx_age_name，age 和 name 的联合索引，其中 age 为先导列。



分析到这里，我们就知道这条 SQL 是如何执行的了。

1.  先通过 `idx_age_name` 索引中的 age 列进行排序。
1.  排序后通过 name 列进行“`name` LIKE '张%'”的匹配。
1.  最后再回表进行`status`= 0 的匹配和 select * 的数据组织，当数据满 100 条后返回结果。








### 慢查思考

其实问题分析到这里，大家应该也看出了一些端倪，这条 SQL 是先进行的排序，而不是先进行的过滤。

我们上文已经说过，其实这张表中是创建了两个联合索引的，除了 `idx_age_name (age,name)`之外，还有一个 `idx_name_age (name,age)`索引，会不会走后面的这个索引好一些呢？

我们先用这条 SQL 进行一下验证：

```SQL
SELECT COUNT(*) AS '在世的张姓人数', (SELECT COUNT(*) FROM person) AS '城市总人数' FROM person WHERE 
`name`  LIKE '张%' and `status` = 0
```

执行效果如下：

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/59475901f9bd4600b30702042450a4d2~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=904&h=204&s=23400&e=png&b=f6f6f6)



通过这条 SQL 我们可以看出来，表中的总数据是 500 万，而我们过滤后的数据是 52 万，可以过滤 90% 的数据，效果还是可以的。

接下来，我们就沿着这个思路去进行优化。

### 慢查优化

如何才能让这条 SQL 语句走`idx_name_age (name,age)`索引呢？别忘了有个关键字叫 **FORCE INDEX**。

**FORCE INDEX，顾名思义，强制使用指定索引，但不敢保证 100% 生效。**

原因在于，MySQL 的查询优化器会自己选择索引，如果 FORCE INDEX 指定的索引出现在候选索引上，这时查询优化器会直接使用该索引；如果没出现在候选索引中，即使通过 FORCE INDEX 进行指定了，索引也不会生效。

我们先来用这条 SQL 试试：

```
SELECT * FROM person FORCE INDEX (idx_name_age) WHERE `name` LIKE '张%' and `status`= 0 ORDER BY 
age DESC LIMIT 100;
```

对应的执行计划如下，果然走了强制指定的索引。

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c58d30522f044535b5007640c0bfbb85~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1101&h=289&s=31920&e=png&b=f7f7f7)


执行效果如下：

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/853b9f8cec4644e789d6330bf122fb47~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=720&h=279&s=91188&e=png&b=f5f5f5)


果然生效了，而且查询时间缩短为原来的 1/8，优化效果非常明显。

对了，最后再聊一下，为什么通过 FORCE INDEX 关键字强制指定了，才会走`idx_name_age` (`name`,`age`)这个索引呢？明明通过这个索引的执行效果很好啊。

我的思考是，这是由于 MySQL 的查询优化器还不够“智能”导致的。

当 MySQL 的查询优化器接收到该条 SQL 语句，发现该 SQL 语句中 WHERE 条件的 name 字段有 LIKE 关键字时，认为如果选择走`idx_name_age` (`name`,`age`)索引效率不高，于是选择了它认为的效果更好的索引，`idx_age_name` (`age`,`name`)，结果聪明反被聪明误了。



## 总结

本文给大家梳理了，在应对“项目中的 SQL 优化”面试题的时候，如何以“业务背景——>慢查场景——>慢查思考——>慢查优化”的闭环路径，进行清晰有据的回答。

除此之外，我们还列举了一个颇具亮点的 SQL 优化 case，对该闭环路径进行实战套用，以便于加深大家的理解。

后续两篇写 SQL 优化的文章中，我们继续以真实场景示例的方式，手把手带着大家进行 SQL 优化，但在技术难度上会进行逐级递增，由单表的 SQL 优化变为多表关联的 SQL 优化。