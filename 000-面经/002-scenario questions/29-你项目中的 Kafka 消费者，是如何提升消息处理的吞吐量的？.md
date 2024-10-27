在这本小册之前的文章中，我曾经写过一篇“[Kafka 生产者吞吐量调优，那些参数和那些原理](https://juejin.cn/book/7331654939661795339/section/7332670778309083155)”。

如文中所述，生产者的调优只需要调整几个常用的参数，以牺牲可靠性、内存空间、时延性、CPU 开销的方式，来换取吞吐量即可，完全遵从于系统架构的选择和取舍规律。

其实，在真实的业务场景中，提升 Kafka 消费者的消息处理吞吐量更加重要一些，毕竟可以有效地解决最为常见的消息积压问题。

但消费者吞吐量调优的思路，则跟生产者的截然不同，并不能靠调整几个核心参数来达到满意的吞吐量，而是需要`提升消息传递链路 + 消息业务处理的并行度`，以及要`定位解决影响其并行度的性能瓶颈点`。

另外，在技术面试中，“你项目中的 Kafka 消费者，是如何提升消息处理的吞吐量的”是一个消息队列方向妥妥的高频面试题，甚至不亚于“消息重复消费、漏消费”。

话不多说，接下来我们就先从 Kafka 的一些基础知识开始，深入浅出地进行讲解。



## 消息传递链路的并行度

Kafka 是通过 Topic（主题）进行消息分类的，我们可以将 Kafka 中的 Topic 理解为 MySQL 数据库中的表，或是文件系统中的文件夹。每个 Topic 下可以包含若干个 Partition（分区），这些 Partition 可以分布在不同的 Broker 上，这样会提升消息传递的并行度，具备比单个 Broker 更大的吞吐量。

如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/74d3988f38dd4c9f96cc7c3f365be43f~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=682&h=416&s=43445&e=png&b=ffffff" alt=""  /></p>



在同一个消费者组中，一个消费者可以消费一个或多个 Partition 的消息，但一个 Partition 的消息只能被一个消费者所消费。

此外，如果一个消费者组中的消费者数量多于 Topic 中的 Partition 数量，那就会出现多出来的消费者没有消息可消费的情况。

因此，从提升消息链路并行度最优解的角度说：

1. 一个 Broker 下最好只对应一个 Topic 下的一个 Partition，这样既可以将 Kafka 集群中的硬件能力充分利用上，又不会由于 Broker 中存在多个 Partition，使原本的磁盘顺序 IO 变成随机 IO。

2. 一个消费者组中的消费者也只对应一个 Topic 下的一个 Partition，这样可以最大限度地从消息链路上解决消费者消费能力不足的问题。

如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4533e75c99184807b1c0d20ef81aa6f1~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=678&h=405&s=60721&e=png&b=ffffff" alt=""  /></p>




## 消息业务处理的并行度

如果从一个 Topic 下的一个 Partition 获取消息，消费者只能以单线程 + 轮询的方式进行。代码如下：

```java
package com.example.demo;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SingleThreadKafkaConsumer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("tony_topic"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                // 处理消息的逻辑
                System.out.println("Received message: " + record.value());
            }
        }
    }
}
```

  


当然，消息获取后的业务处理逻辑，我们是可以通过多线程的方式来提升吞吐量的。

改造后的代码如下：

```java
package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadKafkaConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final ExecutorService executorService;

    public MultiThreadKafkaConsumer() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("tony_topic"));
        // 创建一个包含20个线程的线程池
        executorService = Executors.newFixedThreadPool(20);
    }

    public void pollMessages() {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            records.forEach(record -> {
                executorService.submit(() -> {
                    // 在线程池中处理消息
                    handleMessage(record);
                });
            });
        }
    }

    private void handleMessage(ConsumerRecord<String, String> record) {
        // 处理消息的逻辑
        System.out.println("Received message: " + record.value());
    }

    public static void main(String[] args) {
        MultiThreadKafkaConsumer consumer = new MultiThreadKafkaConsumer();
        consumer.pollMessages();
    }
}
```

  


<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/394fd06fc2ad4c47be82baa32fa4d831~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=703&h=397&s=65736&e=png&b=ffffff" alt=""  /></p>


通过这种方式，我们便实现了单线程从 Topic 下的一个 Partition 获取消息，再将消息提交给线程池，以多线程并行的方式进行业务处理。

如果我们对于消息的业务处理，就像代码中那样只打印一行日志的话，那确实已经把消息处理吞吐量调整到最优了，但真实的业务场景中肯定不会那么简单的。




## 并行处理的性能瓶颈点

如果我们已经提升了消息传递链路的并行度，并且在消息的业务处理中使用了线程池的话，那很有可能将瓶颈点落在了消息的业务处理逻辑上。

举个例子：在线教育 1v1 业务，周五中午 12 点开放预约下周的课程，那么几十万用户都去抢着预约心仪老师的课程，我们引入 Kafka 进行消峰处理，保证约课服务不会由于系统过载而宕机。

如下图所示：

<p align=center><img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eb1860c5d2584c1bb2c81cdce4184a86~tplv-k3u1fbpfcp-jj-mark:0:0:0:0:q75.image#?w=720&h=401&s=63944&e=png&b=fefefe" alt=""  /></p>



但此时，如果我们想要提升用户体验，不要让用户等待太久才返回约课结果的话，那就需要提升约课服务消息处理的吞吐量，其关键点在于找到约课业务处理中的瓶颈点。

毫无疑问，约课业务处理的性能瓶颈点在数据库上。

因为在作为 Kafka 消费者的“约课服务”中，我们实现了全部业务逻辑，比如：生成约课记录、占用老师的 Time Slot、扣减课时、后调课标等写操作，以及其对应的若干个读操作，其中包括：读取老师基本信息及 Time Slot 信息、用户的课时信息及课标信息等。

而我们应对数据库瓶颈的策略如下：

1. **单次改批量**，由于用户在进行约课的时候，系统是支持用户进行批量约课操作的，比如：一次性地约某个老师周二晚上 19 点、周四晚上 19 点、周六上午 10 点和周日上午 10 点的课程。

   此时，我们就将对应约课代码从循环 + 单次约课操作，改为了支持一次性地批量约课操作。

   本次改动能够改善数据库瓶颈点的原因是，无论是网络传输、SQL 解析、生成 SQL 执行计划，以及数据库的磁盘 IO，都从多次变成了一次，唯独本次操作约课数据量是一样的。

2. **缓存预热**，将系统中 10% 热门的老师基本信息和对应的 Time Slot 信息，提前预热加载进 Redis Cluster中，这样挡住 80% 的该类查询请求。

3. **逻辑简化**， 之前我们每次在预约课程之前，都会先查询一下用户的课时信息，但后来我们通过日志发现，99.9% 的约课请求，它所对应的用户课时都是不为 0 的。

    因此，我们在代码中将 “查询用户课时信息” 这个步骤去掉，直接进行约课操作，约课成功后直接进行课时扣减。

    对应的 SQL 语句为：

```SQL
update student set course_number = course_number - 1 where id = 12376 and course_number > 0
```

> 若课时扣减不成功，则直接将之前预约成功的课程进行取消操作。

通过如上三种代码优化策略，我们缓解了约课业务场景下的瓶颈点 —— 数据库，并将约课消息处理的吞吐量提升了 150%。



## 总结

在本文中，我们先是阐述了提升 Kafka 消费者吞吐量与生产者吞吐量的不同之处，然后带着大家优化了“消息传递链路”和“消息业务处理”的并行度，最后以一个较为常见的约课场景，case by case 地进行了并行处理的性能瓶颈点的优化。

大家可以用本文中输出的知识点，去回答面试官有关于 “消费者提升吞吐量”，以及 “消息积压问题解决方案” 的问题。