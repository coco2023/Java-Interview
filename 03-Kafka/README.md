## Refer
https://www.interviewbit.com/kafka-interview-questions/

https://www.simplilearn.com/kafka-interview-questions-and-answers-article

https://intellipaat.com/blog/interview-question/kafka-interview-questions/

<hr>
elements of Kafka
- **Topic**: It is a bunch of similar kinds of messages.
- **Producer**: Using this, one can issue communications to the topic.
- **Consumer**: It endures to a variety of topics and takes data from brokers.
- **Broker**: This is the place where the **issued messages are stored**.

ZooKeeper in Kafka: 
- distributed, open-source configuration and synchronization service, along with being a naming registry for distributed applications. It keeps track of the status of the Kafka cluster nodes, as well as of Kafka topics, partitions, etc.
- ZooKeeper is used in Kafka for managing service discovery for Kafka brokers, which form the cluster. ZooKeeper communicates with Kafka when a new broker joins, when a broker dies, when a topic gets removed, or when a topic is added so that each node in the cluster knows about these changes. Thus, it provides an in-sync view of the Kafka cluster configuration.

What is Kafka?
- Kafka is a message divider project coded in Scala. 
- The purpose of the project was to achieve the best stand for conducting the real-time statistics nourishment.

Data Replication in Kafka: Apache Kafka uses the concept of data replication to ensure high availability of data at all times via the Replication Factor Kafka. It supports data replication at the partition level, as it stores all data events in the form of topic-based partitions, and hence makes use of the topic partition’s write-ahead log to place partition copies across different brokers.

https://hevodata.com/learn/kafka-replication-factor-a-comprehensive-guide/#introrep 

Duplication in Kafka

Kafka Producer API: kafka.producer.SyncProducer, kafka.producer.async.AsyncProducer

partitioning key: to specify the target divider of the memo within the producer. a hash-oriented divider concludes the divider ID according to the given factors. Consumers also use tailored partitions.

It is impossible to use Kafka without ZooKeeper because it is not feasible to go around ZooKeeper and attach it in a straight line with the server. If ZooKeeper is down for a number of causes, then we will not be able to serve customers’ demands.

Architecture of Kafka: 
- In Kafka, a cluster contains multiple brokers since it is a distributed system
- Topic in the system will get divided into multiple partitions
- each broker stores one or more of those partitions so that multiple producers and consumers can publish and retrieve messages at the same time

start a Kafka server:
- start the ZooKeeper’s server
> bin/zookeeper-server-start.shconfig/zookeeper.properties
- start kafka server
> bin/Kafka-server-start.shconfig/server.properties

consumers/ users:
- Kafka tags itself with a user group, and every communication available on a topic is distributed to one user case within every promising user group
- User instances are in the disconnected process
- We can determine the messaging model of the consumer based on the consumer groups
- If all consumer instances have the **same consumer set**, then this works like a **conventional queue** adjusting load over the consumers
- If all customer instances have **dissimilar consumer groups**, then this works like a **publish–subscribe system**, and all messages are transmitted to all the consumers

Offset:
- the offset will be used to identify each message in the partition uniquely
- The messages in partitions will be given a sequential ID known as an offset
- With the aid of ZooKeeper, Kafka stores the offsets of messages used for a specific topic and partition by a consumer group

partition key: used to point to the aimed division of communication in Kafka producer. A hash-oriented divider concludes the division ID with the input, and also people use modified divisions.














































