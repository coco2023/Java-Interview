

## Hibernate

framework: 
Configuration, SessionFactory, Session, Criteria, Query, Transaction

SessionFactory: heavyweight and thread-safe object used to create and manage multiple sessions across an application. configuring and managing the underlying connection pooling and caching mechanisms. It is also configuring and managing the underlying connection pooling and caching mechanisms. an immutable thread-safe cache

Session is not a thread-safe object which means that any number of threads can access data from it simultaneously. lightweight, non-thread-safe object representing a single unit of work with the database. It is used to perform CRUD operations on persistent objects. and should not be shared across multiple threads. It is the interface that exists between the Hibernate framework and the Java application code, providing methods for CRUD operations. A session should be opened only when required, then closed as soon as the user is finished》

Transaction (org.hibernate.transaction). The transaction is a single-threaded, short-lived object that the application uses to specify atomic units of work.

lazy loading: objects are loaded as needed. It improving the application performance by helping to load the child objects on demand. Child objects are not loaded until the parent gets loaded.

first level cache & second level cache: 
- first level cache: single session and helps to reduce database queries by caching retrieved data. belongs to the session object. The first level cache is associated with a Session and is used to store the currently loaded objects in memory
- second level cache: shared across sessions and enables data to be cached across multiple requests. data is accessible by the entire application. Eg., EH (Easy Hibernate) Cache, Swarm Cache, OS Cache, JBoss Cache. The second level cache is associated with a SessionFactory and is used to hold objects across multiple sessions, thereby reducing the number of database queries needed.
  - Hashtable
  - EHCache
  - ...

create an immutable class in hibernate: markingmutable=false/ mutable' attribute of the 'property' or 'component' element in the Hibernate mapping file to make a property or component immutable / using @Immutable annotation/ mark all class properties as 'final' and provide only getter methods for them without any setters/ 

Hibernate Inheritance Mapping: represent the inheritance relationships between classes in a relational database. It allows the developer to map a single table to multiple classes using techniques like table per class hierarchy, table per subclass, and table per concrete class.

inheritance is most commonly used to simplify and simplify the relationship. Relational databases do not support inheritance. They have a flat structure. Solving how to hibernate being an ORM tries to map this problem between the inheritance of Java and flat structure of Databases. 

src/main/resource/**hibernate.cfg.xml**

Hibernate's Inheritance Mapping strategies solve the problem of "Relational databases do not support inheritance": 
- Single Table Strategy
- Table Per Class Strategy
- Mapped Super Class Strategy
- Joined Table Strategy


SQL injection attack: attacker can interfere with the queries made by an application/website to its database thereby allowing the attacker to view sensitive data which are generally irretrievable. It can also give the attacker to modify/ remove the data resulting in damages to the application behavior.

Hibernate does not provide immunity to SQL Injection:
- Incorporate Prepared Statements that use Parameterized Queries.
- Use Stored Procedures.
- Ensure data sanity by doing input validation.

Hibernate mapping file: defining the entity bean fields and corresponding database column mappings

@Cascade: 
- @OneToMany(mappedBy = "person", cascade = CascadeType.ALL) 
-  @OneToOne(mappedBy = "employee") <br/>
   @Cascade(value = org.hibernate.annotations.CascadeType.ALL)

Hibernate architecture: Session Factory, Session, Transaction, and Query

design patterns does the Hibernate framework use: 
- Data Mapper, which moves data between objects and a database, keeping them independent of each other and the mapper
- Domain Model Pattern, which is a domain object model that incorporates both behavior and data
- Proxy Pattern, for lazy loading
- Factory pattern in SessionFactory

![this is the pic](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/048/original/Hibernate_Architecture.png?1614765415)

![](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/049/original/Hibernate_Framework_Objects.png?1614765478)

getCurrentSession and openSession
- getCurrentSession(): This session object gets closed once the session factory is closed.	this method is faster.  creates or retrieves the current Session from the current session context
- openSession(): begins a new session that is not bound to the current context

save() and saveOrUpdate()
- save(): The insertion fails if the primary key already exists in the table. The return type is Serializable which is the newly generated identifier id value as a Serializable object. 
- saveOrUpdate(): The return type of the saveOrUpdate() method is void.


get() and load() 
- get(): retrieves an object from the database by its primary key and throws an exception if the object is not found
- load(): used to retrieve an object from the database by its primary key, but it returns a **proxy object** if it is not found

criteria API: 
- to get entities from database by creating Criterion objects
- The criteria API in hibernate is a programmatic way of creating and executing queries. It allows developers to build complex queries using a fluent interface rather than writing raw SQL.  
- It is a very intuitive and convenient approach for search features.
- Users can specify different criteria for searching entities and Criteria API can handle these.

Hibernate Query Language (HQL): an extension of SQL.  HQL works well with persistent objects and their properties

@OnetoMany

@ManyToMany

session.lock(): This method is used to acquire a lock on an object in the current Session. This is useful for preventing concurrent updates to the same thing. 
reattach a detached object to the session. might lead to loss of data synchronization

hibernate caching: the strategy for improving the application performance by pooling objects in the cache so that the queries are executed faster. refers to **storing data in memory** to **retrieve it quickly without hitting the database** again. This improves performance and reduces the load on the database. There are two types of hibernate caching: first-level caching and second-level caching. First-level caching is enabled by default and is associated with the Session object. Second-level caching is optional and is associated with the SessionFactory object. 


merge(): updating existing values

setMaxResults() and setFetchSize():
- setMaxResults(): limits the number of results returned by a query. limit the total number of results returned
- setFetchSize(): controls the number of rows retrieved from the database at a time. controls the number of rows retrieved to avoid memory issues.
  

Native SQL Queries: Hibernate supports Native SQL Queries, which allow you to use SQL statements directly to interact with the database. This can be useful when you want to perform complex queries that are impossible with HQL or Criteria API.

no-args constructor: The method Class.newInstance() is used which requires a no-args constructor to be present


Q: Can we declare the Entity class final? 
No, we should not define the entity class final because hibernate uses proxy classes and objects for lazy loading of data and hits the database only when it is absolutely needed. This is achieved by extending the entity bean. If the entity class (or bean) is made final, then it cant be extended and hence lazy loading can not be supported.

states of a persistent entity: 
![states of a persistent entity](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/054/original/Hibernate_Persistent_Entity.png?1614771245)

Query Cache: 
- second-level cache that stores the results of a query so that they can be reused later. 
- It improves performance by avoiding multiple times hitting the database for the same Query.  
- improve the performance of queries that run multiple times with same parameters.

lazy loading

on-demand fetching strategy

concurrency strategies: the mediators responsible for storing and retrieving items from the cache. When enabling a second-level cache, the developer must decide which cache concurrency to implement for each persistent class and collection.


Single Table Strategy: a hibernate's strategy for performing inheritance mapping. The Single Table Strategy maps multiple classes that inherit from a single root class to a single table in the database. Each row in the table represents an instance of one of the classes in the hierarchy.

@Inheritance
- @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  
@Disc
- @DiscriminatorColumn(name = "employee_type")

Table Per Class Strategy: each class in the hierarchy has a corresponding mapping database table. another type of inheritance mapping strategy. maps classes in an inheritance hierarchy to separate tables in the database. Each class in the hierarchy has its table, and the data for that class is stored in the corresponding table.

Named SQL Query: an expression represented in the form of a table. It is a feature in Hibernate that allows you to define a named query and reuse it throughout your application. It is defined in the mapping file or by using the @NamedQuery annotation. benefits:
- Improved code maintainability and readability.
- Reduced code duplication.
- Ability to easily change the Query without affecting its code.


@NameQueries, @NameQuery

https://www.interviewbit.com/hibernate-interview-questions/ 

Why is Hibernate better than Java Database Connectivity (JDBC)?
- HQL (Hibernate Query Language) is closer to Java and is more object-oriented
- Developers don't need to write code to store and load data into the database
- Connection Pool: Hibernate handles connection pooling very well. JDBC requires connection pooling to be defined by developer.
- Database Portability: Hibernate can be used with multiple types of database with easy portability. In JDBC, developer has to write database specific native queries.

ORM levels in Hibernate: Full Object Mapping, Light Object Mapping, Medium Object Mapping, Pure Relational

Hibernate's five collection types used in one-to-many relationship mappings: Array, Bag, List, Map, Set

dirty checking: Dirty checking changes or updates only the fields that require action, while keeping the rest of the fields untouched and unchanged. This feature helps developers and users avoid time-consuming write actions, thereby reducing database write times

Hibernate’s default cache service: EHCache

validator framework: Hibernate Validator allows to express and validate application constraints. The default metadata source are annotations, with the ability to override and extend through the use of XML

Hibernate tuning: is designed to optimize Hibernate applications’ performance
- SQL Optimization
- Session Management
- Data Caching

Persistent entities exist in only three states: 
- Transient: This state describes new objects that are created in Java but not associated with a Hibernate session
- Persistent: This state describes objects associated with a Hibernate session
- Detached: This state describes an object that was formerly Persistent and associated with a Hibernate session. Developers can reattach the object to a Hibernate session by using either update() or saveOrUpdate(). Once the session attached to an Object is closed, the object goes into Detached state. This state is quite useful in concurrent applications that have long unit of work.


view the Hibernate-generated SQL on a console:  <property name="show_sql">true</property>  (add in Hibernate configuration file)

ways for object being fetched from Hibernate’s database: 
- Criteria API
- HQL
- The identifier
- Standard SQL

disable Hibernate’s second-level cache: 
- hibernate. cache. use_second_level_cache=false
- CACHEMODE.IGNORE
- Using a cache provider such as org.hibernate.cache.NoCacheProvider

Can we declare the Entity class final? No, the Entity class cannot be declared final, as Hibernate uses runtime proxies to enhance the class for persistence. Therefore, a final class cannot be subclassed and thus cannot be proxied. 

N+1 SELECT problem

https://www.simplilearn.com/hibernate-interview-questions-article 

## 1000

Object Relational Mapping: programming technique to map data from a relational database to Object oriented domain model. It reduces the effort of developers in writing queries to access and insert data.

Configuration Interface: It can be implemented in an application to specify the properties and mapping documents for creating a SessionFactory in Hibernate. Configuration is an initialization time object that loads the properties in helps in creating SessionFactory with these properties.

Object Relational Impedance Mismatch: It means that Object model and Relational model do not work well with each other. 
- Relational model or a RDBMS represents data in tabular format like a spreadsheet. 
- Object model or OOPS represents the data as an inter-connected graph of objects.
- Mixing these two models leads to various problems.

Problem of Object Relational Impedance Mismatch: 
- Inheritance: Object model supports inheritance. But Relational model does not have any concept of inheritance.
- Associations: In Object model associations are uni-directional. multiplicity of a relationship is hard to judge by looking at object model.

Hibernate key characters:
- Object/Relational Mapping (ORM)
- JPA Provider
- Idiomatic persistence: based on natural Object-oriented, Hibernate provides full support for inheritance, polymorphism, association, composition, and the Java collections framework
- High Performance: high level of performance supporting features like- lazy initialization, multiple fetching strategies, optimistic locking etc
- Scalability
- Reliable
- Extensible

core interfaces:
- Configuration
- SessionFactory
- Session
- Transaction
- Query
- Cirteria

map the columns of a DB table to the properties of a Java class in Hibernate： XML/Annotation(@Entity and @Table)

steps for creating a SessionFactory in Hibernate

/219. Why do we use POJO in Hibernate: A POJO is java bean with getter and setter methods for each property of the bean

/221. call a stored procedure in Hibernate: 

Collections supported by Hibernate:
- Indexed Collections: List and Maps
- Sorted Collections: java.util.SortedMap and java.util.SortedSet

/228. get the statistics of a SessionFactory by using Statistics interface: get information like Close Statement count, Collection Fetch count, Collection Load count, Entity insert count etc

Transient state of an object in Hibernate: 
- In Transient state, object does not have a persistent representation in database. Also there is no identifier assigned to an object in Transient state.
- An object in Transient state can be garbage collected if there is no reference pointing to it.

Detached state: An object is in detached state if it was persistent earlier but its Session is closed now.

/231. Dirty Checking

/232. Callback interface: used for receiving notifications of different events from an object.

/233. different ORM levels in Hibernate

/236. different types of Association mappings supported by Hibernate?
- Unidirectional association
- Bidirectional association

/238. What is Unit of Work design pattern?
- Unit of Work is a design pattern to define business transactions
- A Unit of Work is a list of ordered operations that we want to run on a database together
- Most of the time, we use term business transaction in place of Unit of Work

/241. How does Example criterion work in Hibernate?


/242. How does Transaction management work in Hibernate?
- use **Session interface** to get a new transaction
- get the transaction we can **run business operations** in that transaction
- At the end of successful business operations, we **commit** the transaction
- In case of failure, we **rollback** the transaction
```
Session s = null;
Transaction trans = null;
try {
   s = sessionFactory.openSession();
   trans = s.beginTransaction();
   doTheAction(s);
   trans.commit();
} catch (RuntimeException exc) {
   trans.rollback();
} finally {
   s.close();
}
```

/244. What are the different options to retrieve an object from database in Hibernate?
- get()/ load()
- HQL
- Criteria API
- Native SQL

/246. re-attach an object in Detached state in Hibernate

/251. the different fetching strategies?

/252. difference between Immediate fetching and Lazy collection fetching?

/253. Extra lazy fetching?

/255. What are the different strategies for cache mapping in Hibernate?

/256. difference between a Set and a Bag:
- A Bag in Hibernate is an unordered collection.
- A Set in Hibernate can only store unique objects. By default a Set is unordered collection in Hibernate.

/257. monitor the performance of Hibernate in an application?

/260. ORM metadata: ORM maps classes to tables and stores this information in Metadata.

/262. get() & load()
- We should use get() method when we want to load an object
- We should use load() method when we need a reference to an object without running extra SQLqueries

/263. derived property: a derived property is not mapped to any column of a database table. It is computed at runtime by evaluation of an expression

/265. two locking strategies:
- Optimistic locking: multiple transactions can complete without affecting each other. So we let the transactions do their work without locking the resources initially. Before the commit, we check if any of the resource has changed by another transaction, then we throw exception and rollback the transaction.
- Pessimistic locking: concurrent transactions will conflict while working with same resources. A transaction has to first obtain lock on the resources it wants to update. The other transaction can proceed with same resource only after the lock has been released by previous transaction.

/266. What is the use of version number in Hibernate?
- version number is used in optimistic locking. When a transaction modifies an object, it increments its version.
- Based on version number, second transaction can determine if the object it has read earlier has changed or not.
- If the version number at the time of write is different than the version number at the time of read
- then we should not commit the transaction.

/268. 268. What inheritance mapping strategies are supported by Hibernate?































