## RabbitMQ Refer
https://www.techgeeknext.com/java/rabbitmq-interview-questions


<hr>
RabbitMQ: open-source message-broker software is also known as message queueing technology. It defines queues to which applications communicate for data transfer, or message transmission. Any kind of information could be included in a message. This message is processed by the recipient application.

How RabbitMQ works?  An exchange must accept messages from the supplier request and route them to message queues using header attributes, bindings and routing keys. A binding is set up to connect the queue to an exchange.

message flow <br>
![message flow](https://www.techgeeknext.com/SpringBoot/rabbitmq-working.JPG)

1) The producer publishes a message to an exchange. Specify the type of the exchange when creating the exchange.
2) The exchange receives the message and is now responsible for the routing of the message. The exchange takes different message attributes into account, such as routing key, depending on the exchange type.
3) Bindings have to be created from the exchange to queues. In this case, we see two bindings to two different queues from the exchange. The Exchange routes the message into the queues depending on message attributes.
4) The messages stay in the queue until they are handled by a consumer
5) The consumer handles the message.

Load balancing

Data binding


Q: When and why to use RabbitMQ?
- Message queueing is useful when exchanging a message for consumption or for balance of loads between workers with multiple recipients
- The user is able to take a message from the queue while the producer is in the queue, and start processing. 
- The consumer can be on a different server or on the same server than the publisher
- The requested application can be of one language and the consumer application is of other language (the message broker would not care about the application languages, it just send messages between consumer and reciever.)


binding And routing Key:
- A binding is a "bridge" for setting up to connect a queue to an exchange.
- The routing key is an attribute message that the exchange examines when determining how to route the message to queues.

RabbitMQ channel:
- it enables a single connection to the RabbiMQ server, but for different parts of application they have sandboxed communication
- Channels are how the RabbitMQ server communicates with the application. It hold one connection (instance) per client process and many channels in that process (instance)

death letter queue (DLQ): 
- an undelivered-message queue.  
- a holding queue of messages that can not be sent to their destinations for some reason
- is service implementation to store messages that satisfy one or more of the following failure criteria:
  - Message that is sent to a queue that does not exist
  - Queue length limit exceeded
  - Message length limit exceeded
  - Message is rejected by another queue exchange
  - Message reaches a threshold read counter number, because it is not consumed. Sometimes this is called a "back out queue"

RabbitMQ retry mechanism: the message queue is marked as undeliverable or deadLetter queue in these instances: 
- any data in the message is transmitted that the receiver does not accept
- or when a message is sent to a queue that does not exist
- The message is retried and sent up to a set number of times
- Even if the communication is not received by the recipient but is sent from the sender's end

Exchange:
- An exchange is responsible for routing the messages to the various queues
- An exchange is responsible for routing the messages to the various queues
- A binding is a linkage between an exchange and a queue
- the user sends messages to the exchange, Messages are not posted directly in the queue

Types of Exchange:
- Direct: Direct exchange transfers messages to **queues on the basis of a message routing key**. 
  - The message is routed in a direct exchange to the queues whose binding key exactly matches the message's routing key. 
  - If the queue is bound to the binding key exchange, a message will be sent to the exchange with a routing key to the queue.
- Fanout: A fanout sends messages to **all the queues** linked to it.
- Topic: The topic exchange **matches** the **wildcard** between the **routing key and the binding routing pattern**.
- Headers: Headers exchanges use the **routing attributes** of the message header.

Protocol RabbitMQ uses: Advanced Message Queuing Protocol (AMQP)

integrate RabbitMQ with Spring Cloud Stream

RabbitMQ Vhost
- A Virtual Host (a.k.a. ' vhost ') in AMQP is a namespace for objects such as Exchanges, Queues and Bindings
- RabbitMQ utilizes more concrete implementation of virtual hosts, through effectively making them "virtual clusters" on top of the broker.

RabbitMQ VS. ActiveMQ
- all are open source message broker with support for several protocols
- RabbitMQ: Erlang; ActiveMQ: Java

RabbitMQ support MQTT (Message Queue Telemetry Transport):

Q: Is RabbitMQ persistent? / queue durable/ persistent messages?
- Having a queue durable is not same as making persistent messages
- Messages can be distributed either with making mode to persistent or transient
- When you post your message, you need to set the delivery mode to persistent if you want it to remain in your long-lasting queue during restart.

Is RabbitMQ uses database?
RabbitMQ intentionally does not store messages in a database. RabbitMQ writes messages to disk in below two ways:
- Messages published in delivery_mode=2
- Memory pressure causes RabbitMQ to run out of RAM and transfers messages to the disk to free up RAM.

RabbitMQ is PUSH or PULL:
- **RabbitMQ** uses a PUSH template and prevents exhausting consumers through the prefetch configured limit
- PULL is used by **Kafka**, where consumers request for messages from a particular offset batches.

Dead Letter Exchange:  If there is no appropriate queue for the message, the message will be dropped quietly. The dead letter exchange provides features for collecting non-deliverable messages

pub/sub messaging: a asynchronous service-to-service communication, it's a two way communication mainly used in statelss, microservices.
- once message published to a topic, it gets received immediately by all subscriber to the topic.

Q: Is RabbitMQ an ESB?
- ESB (Enterprise Service Bus)
- An ESB includes additional layers on top of a message broker like routing, transformations and business process management
- **RabbitMQ is a messaging broker**, whereas Mule is an ESB (Enterprise Service Bus).

design patterns RabbitMQ uses?
- It uses asynchronous architectural patterns to decouple applications.
  
Q: How to monitor rabbitMQ queue: AppDynamics, DataDog, AWS CloudWatch etc

**Synchronous and Asychronous messaging**:
- Synchronous messaging is a two way communication, where sender sends a message to receiver and receiver receives message and gives reply to the sender
- Asynchronous Messaging is a communication where a message is placed in a message queue and does not require an immediate response from receiver

Which Messaging patterns are getting used in RabbitMq?
- Messaging patterns are implemented in RabbitMQ **on the basis of exchanges queues and bindings**
- point-to-point communication: communications connection between two communication endpoints/nodes
- publish-subscribe communication
- request-response communication: strong messaging patterns where requester send message to a replier, and in return replier send response back to replier.

**RabbitMQ V.S Kafka**
- RabbitMQ is a lightweight message broker used to communicates messages between consumer and producer and it supports many protocols like AMQP, MQTT,STOMP
-  Kafka manages large amounts of data with very little overhead-Kafka was designed for large volumes of messages to be stored and distributed.

Q: Is RabbitMQ support message priority queues?
To make the queue function as a priority queue, using `x-max-priority` which declare maximum priority number in the queue

```
Map<String, Object> queuePriorityProp = new HashMap<>();
queuePriorityProp.put("x-max-priority", 10); // max priority is 10
channel.queueDeclare(QUEUE_NAME, durable, false, false, queuePriorityProp);

```
Publish messages of a particular priority:
```
String queueNote = "Set note priority 8";
AMQP.BasicProperties.Builder queueProps = new AMQP.BasicProperties.Builder();
queueProps.contentType("text/plain")
           ** .priority(8);**
channel.basicPublish("", QUEUE_NAME, queueProps.build(), queueNote.getBytes());
```




