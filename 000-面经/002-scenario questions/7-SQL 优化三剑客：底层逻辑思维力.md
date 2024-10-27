在本文中，我们继续讲一个难度更大些、会涉及到`多表关联`的 SQL 优化真实案例，同时也会讲到更多 SQL 优化的知识，用来加强大家的技术储备，秒杀面试官。

话不多说，正式搞起。





## 示例三



### 业务背景

某大型在线教育公司，产品经理提了一个新的需求，需求要达成的目标是：获知学生当前的学习情况，并对学生的学习行为进行激励，以此达到更好的学习效果。

因此，需求具体的落地方式是：学生每上完六次直播课，系统就会进行一次在线考试。考试分数最高的前 10 名学生，会给予文具大礼包奖励。

当然，这需要系统能自动把前 10 名学生的学习成绩计算出来。

BTW：在分数相同的情况下，年龄小的学生排名靠前。



### 慢查场景

在这个业务场景下，我们一共需要涉及三个数据库表：

1.  `学生表（student）`，存储的是学生信息数据，目前数据量为 65 万。
1.  `课程表（course）`，存储的是课程信息，数据量很小，只有 10 多条。
1.  `学生成绩表（student_score）`，存储的是学生历次考试成绩，是数据量最大的一张表，达到了 262 万。

相关表结构如下：

```SQL
CREATE TABLE `student` (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `sex` tinyint NOT NULL,
  `age` tinyint NOT NULL,
  `phone` varchar(20) NOT NULL,
  `city` smallint NOT NULL,
  `grade` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_phone` USING BTREE (`phone`) comment '',
  INDEX `idx_city` USING BTREE (`city`) comment '',
  INDEX `idx_create_time` USING BTREE (`create_time`) comment ''
) ENGINE=InnoDB AUTO_INCREMENT=1632232 DEFAULT CHARSET=utf8;
```

  


```SQL
CREATE TABLE `course` (
	`id` int UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(20) NOT NULL,
	`level` tinyint NOT NULL,
	`type` tinyint NOT NULL,
	`description` varchar(500) DEFAULT NULL,
	`price` int NOT NULL,
	`quantity` varchar(255) NOT NULL,
	`status` tinyint NOT NULL,
	`create_time` datetime NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
```

  


```SQL
CREATE TABLE `student_score` (
	`id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	`student_id` bigint NOT NULL,
	`course_id` int NOT NULL,
	`exam_id` bigint NOT NULL,
	`score` smallint NOT NULL,
	`status` tinyint NOT NULL,
	`create_time` datetime NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `idx_student_id` USING BTREE (`student_id`) comment '',
	INDEX `idx_course_id` USING BTREE (`course_id`) comment '',
	INDEX `idx_exam_id` USING BTREE (`exam_id`) comment '',
	INDEX `idx_score` USING BTREE (`score`) comment ''
) ENGINE=InnoDB AUTO_INCREMENT=12662254 DEFAULT CHARSET=utf8;
```

那么，把某场考试的前 10 名学生的学习成绩计算出来的 SQL 语句为：

```SQL
SELECT c.`name` AS course_name, s.`name` AS student_name, s.user_name, ss.exam_id, ss.score, s.age
FROM student s, student_score ss, course c
WHERE ss.student_id = s.id AND c.id = ss.course_id AND ss.exam_id = 10
AND ss.`status` = 1 AND c.`status` = 1 AND s.`status` = 1
ORDER BY ss.score DESC, s.age ASC LIMIT 10
```

写好了 SQL 语句之后，我们来执行一下：


![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a93c3a3e33ab40f985137e290ceb6924~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1063&h=467&s=79446&e=png&b=f8f8f8)


一执行吓了一跳，竟然执行了 14.2 秒才出来结果。

  


### 慢查思考

我们接着说，这个 SQL 语句逻辑很简单，按理说不应该这么慢的，我们来看下执行计划：


![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e95b1bba68944feea66da1fceadd32e1~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1074&h=346&s=59952&e=png&b=f9f9f9)



可以看到，虽然 course 表走了全表扫描，并在 Extra 里面出现了 `Using temporary; Using filesort`（临时表和文件排序），但 course 表的数据量很小，只有 10 条数据，因此不会成为性能瓶颈点。


再看下执行计划的第二行，student_score 表选择了 course_id 的索引，以此用来跟 course 表进行关联，而没有选择 exam_id 这个索引进行条件过滤，会不会瓶颈点在这里呢？

我们建立一个联合索引来优化试试：

```SQL
ALTER TABLE `student_score` DROP INDEX `idx_course_id`, 
ADD INDEX `idx_course_id_exam_id` USING BTREE (`course_id`, `exam_id`) comment '';
```

建完索引后，再次执行原来的 SQL 语句：


![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/81b09c56429e47058fd1148fb3400989~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1056&h=468&s=79347&e=png&b=f8f8f8)


果然快了一些，执行时间从之前的 14.2 秒降低到了 4.6 秒，来看下执行计划：


![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3674588507ef424b9c202335d06f208a~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=979&h=327&s=55981&e=png&b=f9f9f9)



执行计划中的索引换成了我们刚刚建立的联合索引，并且 rows 比之前也少了很多。但是，4.6 秒依然很慢，需要继续进行优化。

既然 SQL 中 where 条件中数据过滤的瓶颈点已经解决了，那会不会是慢在排序上呢？接下来我们优化一下试试。

我们在刚才 `idx_course_id_exam_id` 的索引上，把 status 过滤列和 score 排序列也加上，看看在 SQL 执行时间上会不会再缩短很多。

```SQL
ALTER TABLE `test`.`student_score` DROP INDEX `idx_course_id_exam_id`, 
ADD INDEX `idx_course_id_exam_id_status_score` USING BTREE 
(`course_id`, `exam_id`, `status`, `score`) comment '';
```

接下来再执行一下刚才的 SQL 语句：


![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c1c1d3f20bbf439f8bd1893a8e4af1a1~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1059&h=452&s=77943&e=png&b=f9f9f9)




![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/409a93497cbf444c818200714196cf6c~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=982&h=310&s=55412&e=png&b=f9f9f9)



把 status 过滤列和 score 排序列也加上之后，SQL 语句的执行时间从 4.6 秒降低到 4.36 秒，效果不明显。

既然 SQL 语句中的 where 条件和 order by 排序两方面都优化了，那接下来的优化点是不是可以放在 limit 10 这个方向上呢？

这里面最大的困难点在于，SQL 语句中的排序同时用到了两个表的两个不同字段，因此 SQL 语句执行中需要如下步骤：

1. 从 student_course 表中读取满足过滤条件的数据（exam_id = 10 and status = 1），然后跟 student 表中满足过滤条件的数据（status = 1）进行关联。

2. 按照 `ORDER BY ss.score DESC, s.age ASC` 条件进行排序。

3. 按照 LIMIT 10，取出前 10 条记录并返回结果。

如果我们可以把步骤 1 中，student_course 表的数据量进行大幅缩减，那 SQL 语句的执行时间肯定就快多了，接下来我们按照这个思路进行优化。





### 慢查优化

我们思考一下，是否需要 student_course 表中如此庞大的数据集（exam_id = 10 and status = 1），再跟 student 表进行关联和排序？

既然我们要取“考试分数最高的前 10 名学生，且在分数相同的情况下，年龄小的学生排名靠前”的数据，那最终的数据集一定是在“本次考试的分数，大于等于分数排名第 10”的学生里面。

上文说起来有些拗口，不太容易理解，还是一步一步看 SQL 语句吧：

```SQL
SELECT score FROM student_score ss WHERE ss.exam_id = 10
AND STATUS = 1 ORDER BY score DESC LIMIT 10
```

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ba192bffa6af4b1aa6919bcfa5f6c8ea~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=720&h=364&s=30007&e=png&b=f8f8f8)


通过这条 SQL 语句，我们获取了本次考试最高的十个分数，然后再获取其中的最低分：

```SQL
SELECT min(score) from (SELECT score FROM student_score WHERE exam_id = 10 AND 
`status` = 1 ORDER BY score DESC LIMIT 10) a 
```

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bd5d7691b12841eea61b1156876223db~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=865&h=223&s=18537&e=png&b=f7f7f7)


如图所示：92 分。

接下来，我们正式开始获取“本次考试的分数，大于等于分数排名第 10” 的学生 ID，SQL 语句如下：

```SQL
SELECT exam_id, student_id, score FROM student_score WHERE `status` = 1 AND exam_id = 10 
AND score >= (SELECT min(score) from (SELECT score FROM student_score WHERE exam_id = 10
AND `status` = 1 ORDER BY score DESC LIMIT 10) a ) ORDER BY score DESC
```


![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c4dfb2c84d2344ef867b278c3a77dc0f~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=874&h=533&s=66002&e=png&b=f4f4f4)


我们发现，“本次考试的分数，大于等于分数排名第 10” 的数据，一共有 12 条。这是因为排名第 10 的 92 分，一共有 3 条数据，最终会通过关联 student 表，从这 3 条数据中找到年龄最小的学生，被选入到考试分数最高第 10 名排名中。

接下来，我们看下完整的 SQL 语句，并重新执行一次。

```SQL
SELECT * FROM (

SELECT c.`name` AS course_name, s.`name` AS student_name, s.user_name, ss.exam_id, ss.score, s.age 
FROM student s, student_score ss, course c WHERE ss.student_id = s.id AND c.id = ss.course_id 
AND ss.exam_id = 10 AND ss.`status` = 1 AND c.`status` = 1 AND s.`status` = 1 AND ss.score >= 

(SELECT min(score) FROM (SELECT score FROM student_score WHERE exam_id = 10 AND `status` = 1 
ORDER BY score DESC LIMIT 10) a)
	
) b ORDER BY b.score DESC, b.age ASC LIMIT 10;
```


![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/322d706f94fd466a829448d3075f6acb~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1083&h=537&s=92746&e=png&b=f8f8f8)


果然，我们又将 SQL 优化到了几毫秒的级别，只不过 SQL 语句复杂一些。


![image.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b7b8e54805f44f95b2db76852667abeb~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=993&h=406&s=76872&e=png&b=f8f8f8)


执行计划非常容易理解，将子查询出来的 score 进行条件过滤，过滤后只剩下 12 条数据，然后再进行多表关联和排序操作。

其实，这次的 SQL 优化，用的依然是“**优先过滤原则**”，只不过通过其背后的底层逻辑思维力，重新将 SQL 语句进行了改写。




## 总结

至此，我们的 SQL 优化三部曲已经全部讲完了。

这里需要再次叮嘱大家的是，在应对“项目中的 SQL 优化”面试题的时候，**一定要以“业务背景——>慢查场景——>慢查思考——>慢查优化”的闭环路径，进行清晰有据的回答**。

另外，我写的这三个 case，大家不仅仅要知道其背后的技术特性，还可以进行拆解，并结合于自己的项目特性进行整合。