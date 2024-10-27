
在本文中，我们继续来讲 SQL 优化的真实案例，旨在让大家尽量多地进行 case 储备，以活学活用的方式应对面试官。

当然，本文案例的难度也会适当提升一些。话不多说，show me the case。




## 示例二



### 业务背景

某中型电商平台，商品中心服务需要根据排序规则，返回符合查询条件 20 个商品，目前的排序规则有：按销量排序、按价格高低排序。

有一天，产品经理提了一个新的需求，希望新增一种排序方式，即：按照商品的好评率进行排序。因为这样对优质的商家更为友好。

研发人员经过需求评审后，认领了这个需求，正式启动开发。





### 慢查场景

在这里，我们先简单说明下分库分表中的“垂直拆分”（后面对应章节会详细讲解）。

垂直拆分，就是在业务主表中，把一些不常用的字段或大字段拆分出去，拆分后的业务主表的数据记录更小，这样一个  16k 的数据页就可以存储更多数据。例如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/421e4c750be849819cb15b975ee1b612~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=611&h=383&s=50187&e=png&b=ffffff" alt=""  /></p>


而我们系统中的商品表也是做过垂直拆分的，把一些商品名称、价格、商品总库存、当前库存、分类、状态、所属商家、首图链接、重要售卖属性等核心字段放在 `product` 表中，把商品描述、多图链接、多种规格参数、商品创建人、好评率、退货率等大字段或不常用的字段，放到了 `product_detail` 表中。

product 和 product_detail，这两张表中的数据是`一对一`的关系，总数据量均为 65 万多。

`product` 的表结构如下：

```SQL
CREATE TABLE product (
	id bigint UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(64) NOT NULL,
	price int NOT NULL,
	merchant_id int NOT NULL,
	total_inventory int NOT NULL,
	current_inventory int NOT NULL,
	first_img_url varchar(256) NOT NULL,
	class_id smallint NOT NULL,
	status tinyint NOT NULL DEFAULT 0,
	allow_refund tinyint NOT NULL DEFAULT 0,
	24h_shipping tinyint NOT NULL DEFAULT 0,
	create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	INDEX idx_price USING BTREE (price) comment '',
	INDEX idx_merchant_id USING BTREE (merchant_id) comment '',
	INDEX idx_total_inventory USING BTREE (total_inventory) comment '',
	INDEX idx_current_inventory USING BTREE (current_inventory) comment '',
	INDEX idx_class_id USING BTREE (class_id) comment '',
	INDEX idx_create_time USING BTREE (create_time) comment '',
	INDEX idx_update_time USING BTREE (update_time) comment ''
) ENGINE=InnoDB AUTO_INCREMENT=1802131 DEFAULT CHARSET=utf8;
```



`product_detail` 的表结构如下：

```SQL
CREATE TABLE product_detail (
	id bigint NOT NULL AUTO_INCREMENT,
	description varchar(2048) NOT NULL,
	img_urls varchar(2048) NOT NULL,
	specs varchar(1024) NOT NULL,
	creator_id int NOT NULL,
	applause_rate tinyint UNSIGNED DEFAULT NULL,
	return_rate tinyint UNSIGNED DEFAULT NULL,
	create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
   INDEX idx_applause_rate USING BTREE (applause_rate) comment ''
	INDEX idx_create_time USING BTREE (create_time) comment '',
	INDEX idx_update_time USING BTREE (update_time) comment ''
) ENGINE=InnoDB AUTO_INCREMENT=1802131 DEFAULT CHARSET=utf8;
```

  


我们可以看到，好评率字段（applause_rate）是存储到 product_detail 表中的。

因此，在产品经理的“按照商品的好评率进行排序”的需求中，研发人员需要通过两表关联，才能实现排序操作。

SQL 如下：

```SQL
SELECT * FROM product p , product_detail pd WHERE p.id = pd.id AND p.`status` = 1 
ORDER BY pd.applause_rate DESC LIMIT 20;
```

功能实现起来也比较简单，只有一个过滤条件，即：status = 1（已发布上线的商品），再加上一个限制数量（limit）的排序操作。研发人员开发完成，测试也没有问题，于是发布上线。

岂料刚上线没半个小时，产品经理就跑过来说，功能确实没问题，但是运行起来特别慢，非常影响用户体验，让我们赶紧看看。

这块如果慢的话，只能慢在上述的 SQL 执行上，因为代码里面只是做了简单传参。于是，我们在生产环境上运行了一下这个 SQL，发现确实很慢，执行时间竟然达到了惊人的 8.42 秒。

如下图所示：

<p align=center><img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d622d48436a645038bdaf595b4495769~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1049&h=394&s=79789&e=png&b=f3f3f3" alt="image.png"  /></p>


然后，我们又看了一下执行计划：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5a69087d6110411f9bc2bf635f5b0538~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=986&h=295&s=36142&e=png&b=f9f9f9" alt=""  /></p>


不看不知道，一看吓一跳，product 表不仅仅出现了 `type = ALL`，而且还出现了 Using temporary 和 Using filesort，怪不得慢呢。






### 慢查思考

在这条慢查 SQL 中出现的 type = ALL，大家应该都清楚，是全表扫描的意思。那 Using temporary 和 Using filesort 又是什么呢？

- Using filesort：使用文件排序，意味着 SQL 语句在进行 order by 操作的时候，所对应的列没有用到索引。
- Using temporary：使用临时表，意味着 SQL 语句在执行 order by、group by、union 等操作的过程中，需要临时表来存储中间结果集。

看到上面的解释，我们明白了 Using temporary 和 Using filesort 出现的原因，是跟 SQL 语句中的 `ORDER BY pd.applause_rate DESC` 排序操作有关系的，从而导致了 SQL 语句执行缓慢。

我们进行一次试验，先把 SQL 语句中的排序操作部分删掉，也就是 ORDER BY pd.applause_rate DESC 。如果 SQL 执行时间仍然很长，那就证明不是这块的原因，反之则确认无疑。

SQL 语句如下：

```SQL
SELECT * FROM product p , product_detail pd WHERE p.id = pd.id AND p.`status` = 1 LIMIT 20;
```

如下图所示：

<p align=center><img src="https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/65c0a8774bad4b52a6d75d57bef19e58~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1042&h=404&s=74786&e=png&b=f7f7f7" alt="image.png"  /></p>

果然，执行时间直接降低到了几毫秒，快到 Navicat 都不显示数据了。我们再看看执行计划：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a4e23efbcb7c401bb2414d671b6084a9~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=834&h=237&s=30548&e=png&b=f7f7f7" alt=""  /></p>


Extra 里面的 Using temporary 和 Using filesort 都不见了。那我们就找到切入点了，只要解决了排序问题，就能完成这条 SQL 语句的优化工作。

我们继续顺藤摸瓜，分析一下为什么 Using temporary 和 Using filesort 会出现在执行计划行里面。

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d4a803585a85440382034e731fa41baa~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=986&h=295&s=36142&e=png&b=f9f9f9" alt=""  /></p>


查了一些资料发现，原来 SQL 语句在进行多表关联时，如果使用了非驱动表中的字段进行 order by 操作，就会在 Extra 列中出现 Using temporary 和 Using filesort。

驱动表就是在 SQL 语句执行过程中，优先被读取的表，那非驱动表就是没有被优化读取的表。

说到这里，我们的优化思路就出来了，`是不是把 product_detail 表从非驱动表变成驱动表就可以了？`接下来我们去落地实现它。





### 慢查优化

那么，如何才能在多表关联的时候，控制表的连接顺序，选择我们认为最优的驱动表呢？答案是：使用 `STRAIGHT_JOIN` 关键字。

MySQL 的官方文档是这样写的：

> STRAIGHT_JOIN is similar to JOIN, except that the left table is always read before the right table. This can be used for those (few) cases for which the join optimizer processes the tables in a suboptimal order.

翻译过来就是：

STRAIGHT_JOIN 与我们常用的 JOIN 非常相似，只不过其左边的表会在右边的表之前读取，可用于连接优化器未能给出最优解的少数情况。

需要注意的是，STRAIGHT_JOIN 只能用于 inner join。而在 left join 和 right join 的场景，已经知道了哪个表是驱动表，也就不再需要该功能了。

STRAIGHT_JOIN 的语法为：

```SQL
SELECT * FROM table1 STRAIGHT_JOIN table2 ON table1.id = table2.id
```

接下来，我们对刚才的慢查询 SQL 语句按照 STRAIGHT_JOIN 的方式进行改造，看看效果。

```SQL
SELECT * FROM product_detail pd STRAIGHT_JOIN product p WHERE p.id = pd.id AND p.`status` = 1 
ORDER BY pd.applause_rate DESC LIMIT 20;
```

执行效果如下：

<p align=center><img src="https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/750c5389006149f7a464d128226fd442~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1054&h=426&s=108461&e=png&b=f4f4f4" alt="image.png"  /></p>

果然优化成功了，从 8.42 秒一下子优化到了几毫秒，快到 Navicat 都不显示数据了，性能提升了差不多一千倍，飞一般的感觉有没有？我们继续看下现在 SQL 的执行计划：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/21e2e88eb928423c97abc77d01a89ba6~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=942&h=257&s=35799&e=png&b=f8f8f8" alt=""  /></p>


我们发现，果然 product_detail 表变成了驱动表，直接走的 idx_applause_rate 索引进行排序，Extra 中的 Using temporary 和 Using filesort 都没有了，取而代之的是 Backward index scan（MySQL 8.0 新增的反向索引扫描，从索引的末尾开始扫描）。

另外，一些喜欢刨根问底的同学可能会问，除了使用 STRAIGHT_JOIN 关键字，还有其他的优化方式吗？当然有！

我们想一想，上一章 SQL 优化中讲了什么？ 对，FORCE INDEX 关键字，强制指定索引。

我们通过 FORCE INDEX 关键字，强制指定使用 product_detail 中的 idx_applause_rate 索引，看看效果如何。

```SQL
SELECT * FROM product_detail pd FORCE INDEX(idx_applause_rate) , product p WHERE p.id = pd.id 
AND p.`status` = 1 ORDER BY pd.applause_rate DESC LIMIT 20;
```

执行效果如下：

<p align=center><img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/cca7bcbd221149968f4e6879dc41521f~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=1062&h=394&s=106034&e=png&b=f6f6f6" alt="image.png"  /></p>

从执行结果来看，性能一样非常非常快。我们看看执行计划是否有区别：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f474a88d6fac4832a0a21220e831ab1f~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=923&h=295&s=38006&e=png&b=f9f9f9" alt=""  /></p>


对比来看，执行计划也跟使用 STRAIGHT_JOIN 的完全一样，没有任何区别。

SQL 优化完毕！在这里再次吐槽一下 MySQL 的优化器，真的是昏招百出啊，远不如 Oracle 那么好用！




## 总结

本章我们还是以“`业务背景——>慢查场景——>慢查思考——>慢查优化`”的完整闭环链路，进行了第二个慢查 SQL 的优化。

相比较于上一章，本次慢查 SQL 优化 case 要复杂一些，是一个`两表关联查询 + 排序`的场景。我们不但通过 `STRAIGHT_JOIN` 关键字进行了一次优化，同时也用上一章节学到的 `FORCE INDEX` 关键字又进行了一次优化。

这里有个关键点需要提醒大家，**如果可以定位到慢查 SQL 的产生原因，那 SQL 优化工作也就完成至少 50% 了**。

在接下来的一篇文章里，继续增加难度，我会带着大家进行一个场景更加复杂的 SQL 优化。