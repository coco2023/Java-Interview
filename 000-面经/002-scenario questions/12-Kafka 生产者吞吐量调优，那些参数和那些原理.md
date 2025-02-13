在技术面试中，如果跟面试官聊到 “Kafka 生产者提升吞吐量”这个话题，面试官往往会有如下夺命三连问：

1.  Kafka 在你项目中的使用场景是什么？是否需要进行提升吞吐量优化？
1.  Kafka 生产者的底层运行原理是什么？这块你清楚吗？
1.  你具体是如何优化落地的？最后达成的效果如何？


下面我们来一一进行分析讲解。



## 场景分析

### 异步场景

“异步”与“同步”相对应，可以解决同步场景下的较长等待时间问题。

我们先来讲讲同步。

**同步**，指一个进程（线程）在执行某个请求的时候，如果该请求需要执行一段时间，该进程（线程）会一直进行等待，直到收到返回信息才继续往下执行。

其最大弊端就是：整体等待时间较长。这里我们以“电商下单业务”的真实场景举例，如下图：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ce9a4479a1064a76b1b8d48a90339ec2~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=759&h=325&s=43612&e=png&b=fefefe" alt=""  /></p>

用户在点击下单按钮后，需要等待“下单支付、库存扣减、增加积分、通知物流、新客变老客”这横跨五个系统的一系列操作后，才能返回“下单成功”的结果。按上图标示的每个步骤的耗时来看，用户需要等待 250ms。

**异步**，指一个进程（线程）在执行某个请求的时候，不需要一直等待结果，而是可以继续执行后续的操作。这样做的意义是，把一些可异步化、非必须直接返回结果的步骤去掉，从而减少用户等待时间。

下图中，还是以“电商下单业务”的真实场景举例：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9ccc4edd69704d45bc9571de4dbbad5c~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=712&h=378&s=57240&e=png&b=ffffff" alt=""  /></p>


用户点击下单按钮后，只需等待库存扣减、下单支付这两步操作，即可返回“下单成功”的结果。而增加积分、通知物流、新客变老客这三步操作，通过 Kafka 进行异步化后，再由各系统分别执行即可。

与之前的同步流程相比，用户在异步流程下只需要等待 120ms 多点儿（生产者将消息发送给 Broker 的时间），减少了一半的等待时间。


### 消峰场景

上游系统在某个时间点，忽然在短时间内产生高出平时 N 倍量级的请求，我们需要将这波峰值进行消减，让系统按照自己能够承载的节奏去运行。

比如：在线教育 1v1 业务，周五中午 12 点开放预约下周的课程，那么百万用户都去抢着预约心仪老师的课程，这时如果不进行消峰的话，约课服务会由于系统过载而宕机。如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/36fcbd17a5b24f1da2caa61c0ca29274~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=415&h=431&s=39220&e=png&b=ffffff" alt=""  /></p>



该场景可以引入 Kafka 进行消峰缓冲，把用户的约课请求信息暂存到 Broker 中，下游消费者服务按照自己可控的节奏进行慢慢处理。如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/98e2fa95117d4479adac0f7490ad4221~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=670&h=409&s=57614&e=png&b=ffffff" alt=""  /></p>



### 解耦场景

解耦：把系统中不同的部分分离开来，使它们可以互相独立运行、互不干扰，这样系统开发和维护更容易，更具可扩展性。

假设一种业务场景，当某个商品全部卖光，库存数为 0 时，需要给对应商家打自动外呼电话，告诉其及时对库存进行增补。这种情况，如果让维护库存数据的商品中心去调用外呼平台，会使两者间增加没必要的耦合性。

如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d6062c873a534d0998fb7ef652b0f5eb~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=524&h=400&s=26423&e=png&b=ffffff" alt=""  /></p>


但如果接入 Kafka 的话，那商品中心和外呼平台就没有依赖关系，相互解耦了。此时，如果该场景需要增加发短信的话，短信平台自行接入即可，商品中心不需要进行任何代码改动。

如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0a70a5910ff5429e989c4d6164c5f951~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=592&h=336&s=32930&e=png&b=ffffff" alt=""  /></p>

  

通过上述分析，只有在其“消峰”场景下，是对 Kafka 生产者发送消息的吞吐量有要求的，毕竟我们不希望消息的生产者率先出现性能瓶颈。

如果我们在面试的时候，说通过生产者扩容的方式来解决的吞吐量问题的话，该答案起不到加分的效果。而将答案换成深入理解 Kafka 生产者的底层运行原理，并进行针对性地参数调优来解决的话，无疑会增色不少。

下面我们就来继续进行详析。




## 生产者运行原理

Kafka 生产者的消息发送动作，是由`主线程`和 `Sender 线程（发送线程）`相互配合完成的。

**主线程**

主线程负责消息创建，然后会依次经过拦截器、序列化器和分区器后，将消息缓存在消息累加器中。如下图：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1522031b7ef84c168d513c54e3b785b5~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=710&h=266&s=27564&e=png&b=ffffff" alt=""  /></p>



-   **拦截器**：此处为 onSend 拦截器，可对发送前的消息进行一些通用性的处理操作，如消息过滤、消息内容二次处理等。（该拦截器为可选。）

-   **序列化器：** 将消息对象序列化为字节数组。
-   **分区器：** 决定将消息发送至哪个分区。如果消息键值为 null，分区器默认的话，会使用轮询（Round Robin）算法决定分区归属；如果消息键值不为空，分区器默认的话，对键进行散列（Hash）来决定分区归属。
-   **消息累加器：** 我们每次发送给 Broker 的消息，不是单条发送的，而是打包批量发送的，这样性能会更高些。而消息累加器起到了聚合消息，以供后续发送的作用。如下图所示：

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/cafe2816bd1a4c5c8dc642b5f6ad9f9f~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=776&h=357&s=40426&e=png&b=ffffff)


可以看到，消息累加器内部有一个双端队列，以分区为维度，存储消息批次（ProducerBatch），主线程往队列中写入，Sender 线程从队列中读取。


**Sender 线程**

Sender 线程负责从消息累加器中读取消息，封装成 Request 对象，并缓存到 InFlightRequests 区域中，然后再发送到 Kafka 的 Broker 上。如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f97295ee5b654adeb75fc50ffd187bc0~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=413&h=434&s=28975&e=png&b=ffffff" alt=""  /></p>

InFlightRequests，顾名思义：飞行中的请求，也就是已经发出去、但还没有收到响应的消息请求，默认数量是 5，超出就不能再往这个 Broker 节点发送请求了。其设计初衷为，防止消息发送密集从而导致 Broker 节点负载过高。

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be45335bb8514c06860b6fd753fbf1ea~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=630&h=345&s=32411&e=png&b=ffffff" alt=""  /></p>


把 Kafka 生产者进行消息发送的原理简单讲完，接下来我们就可以聊聊如何提升消息发送的吞吐量了，主要是通过调整几个核心参数来实现的。


## 生产者提升吞吐量

我们文章开头举例说的业务场景，无论是在线教育的定点抢着约课，还是电商秒杀，都具备用户请求远远多于其想要获取资源的业务特性。

现在我们正式开始，基于上述的业务特性进行**生产者的参数调优**，以此来提升吞吐量。


### 1. 以`可靠性`换吞吐量

以上述的约课场景为例，我们是可以接受在极端情况下，存在请求（消息）丢失情况的，就当该请求（消息）没有抢占到资源处理，以此来提升吞吐量。


参数调整为：
-   **acks = 0**
-   **retries = 0（默认）**

为了更好地理解，这里我们也解释下相关参数含义：

-   **acks**（默认值为 1）

    -   acks=0，生产者不会等待来自 Broker 的消息发送成功与否的确认，如果 Broker 没有收到消息，那生产者是不知道的。该配置吞吐量高，但可能会丢失数据。
    -   acks=1，生产者将消息写入 leader 副本后，就会收到 Broker 的确认消息。如果 leader 副本同步成功了，但还没有来得及同步给 follower 副本，此时就发生宕机了，那就会丢失数据。
    -   acks=-1，生产者将消息写入 leader 副本和所有 follower 副本后，才会收到 Broker 的确认消息。该配置可以保证不丢数据，但是吞吐量低。

  


-   **retries**（默认值为 0，不进行重试），该参数用来设置当生产者发送消息失败时，可以进行重试的次数。每次重试的间隔期可以通过 `retry.backoff.ms` 参数进行设置，默认是 100ms。由此可见，我们是不需要在生产者代码中写消息失败重试逻辑的，只需要设置这个参数即可。

  


### 2. 以`内存空间`换吞吐量

如果应用服务器的内存不是瓶颈的话，我们还可以多消耗一些生产者内存，以此来换取吞吐量。

可将如下参数值适当调大，举例如下：

-   **batch.size = 32768（32KB）**
-   **max.in.flight.requests.per.connection = 10**
-   **buffer.memory = 67108864（64M）**



相关参数含义解释如下：

-   **batch.size**（默认值为 16384，即 16KB），该参数为消息发送的批次大小，在某种条件下，生产者并不是一条条发送消息给同一个分区的，而是在内存缓冲区中`攒成一个批次`再进行发送。如果我们把该参数值设置得大些，可以攒一个大的 batch 后再发送，这样吞吐量就可以进一步提升。



-   **max.in.flight.requests.per.connection**（默认值为 5），该参数用来设置生产者在收到 Broker 响应之前，可以发送几个批次的消息。`注意，该参数代表批次，而不是消息条数`。如果将该参数值调高，那么能够容忍没有响应的消息批次就更多了，虽然会消耗一些内存，但可以提升消息发送吞吐量。

  


-   **buffer.memory**（默认值为 33554432，即 32M），该参数用来设置生产者的内存缓冲区大小，生产者用它缓冲要发送到 Broker 的消息，如果应用程序发送消息的速度超过消息真正发送到 Broker 的速度，会导致内存缓冲区的空间不足。这时，调用 send() 方法会出现被阻塞和抛出异常两种情况，这取决于 max.block.ms 参数的设定，该参数表示阻塞多少毫秒后抛出异常。

  


### 3.以`时延`换吞吐量

上述的业务场景和业务特性，是可以接受一些时延性的，因此我们可以**把 linger.ms 参数值适当调大，设置为 50ms —— 500ms 之间**，以此来提升吞吐量。

该参数含义解释为如下：

**linger.ms**（默认值为 0），该参数会跟 batch.size 配合使用，表示等待生产者消息攒成批次的时间。生产者会在消息攒成 batch.size 大小或达到 linger.ms 时间的情况下，将消息发送出去。

如果将 linger.ms 的值设置为 0 的话，这就意味着生产者没有给消息留攒成批次的时间，还是按照一条条发送的，如果我们想要优化生产者的吞吐量，这个值一定不能设置为默认值。


### 4.以 `CPU 开销`换吞吐量

如果应用服务器的 CPU 不是瓶颈的话，我们也可以**将参数 `compression.type` 从 none 改为 lz4**，将发送的消息进行压缩，以达到减少数据量，提升吞吐量的目的。

参数含义解释：

**compression.type**（默认值为 none，不压缩），该参数为生产者发送数据的压缩方式，包括 gzip、snappy、lz4、zstd 等。



## 总结

在高并发场景下，如果在我们的业务系统中，把这几个参数优化为最优解，吞吐量提升 3 至 5 倍是没有问题的。

当然，整体的生产者调优，是需要理论结合实践的。在理论上，我们必须洞悉其内部实现原理，有了理论的支撑后，经过反复实践得出来的调优结果，才是最佳路径。

另外，调优的本质还是一种选择和取舍，以牺牲一些指标的方式，来换取另一部分指标的增长。