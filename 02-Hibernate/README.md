

## Hibernate

framework: 
Configuration, SessionFactory, Session, Criteria, Query, Transaction

SessionFactory 

Session is not a thread-safe object which means that any number of threads can access data from it simultaneously.

lazy loading: improving the application performance by helping to load the child objects on demand. Child objects are not loaded until the parent gets loaded.

first level cache & second level cache: 
- first level cache: single session and helps to reduce database queries by caching retrieved data. belongs to the session object
- second level cache: shared across sessions and enables data to be cached across multiple requests. data is accessible by the entire application. Eg., EH (Easy Hibernate) Cache, Swarm Cache, OS Cache, JBoss Cache.

create an immutable class in hibernate: markingmutable=false/ using @Immutable annotation

Hibernate Inheritance Mapping: inheritance is most commonly used to simplify and simplify the relationship. Relational databases do not support inheritance. They have a flat structure. Solving how to hibernate being an ORM tries to map this problem between the inheritance of Java and flat structure of Databases. 

src/main/resource/**hibernate.cfg.xml**

Hibernate’s Inheritance Mapping strategies solve the problem of "Relational databases do not support inheritance": 
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

![this is the pic](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/048/original/Hibernate_Architecture.png?1614765415)

![](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/049/original/Hibernate_Framework_Objects.png?1614765478)

getCurrentSession and openSession
- getCurrentSession(): This session object gets closed once the session factory is closed.	this method is faster


save() and saveOrUpdate()
- save(): The insertion fails if the primary key already exists in the table. The return type is Serializable which is the newly generated identifier id value as a Serializable object. 
- saveOrUpdate(): The return type of the saveOrUpdate() method is void.


get() and load() 

criteria API: creating dynamic queries

Hibernate Query Language (HQL): an extension of SQL.  

@OnetoMany

@ManyToMany

session.lock(): reattach a detached object to the session. might lead to loss of data synchronization

hibernate caching: the strategy for improving the application performance by pooling objects in the cache so that the queries are executed faster.

merge(): updating existing values

setMaxResults() and setFetchSize():

Native SQL Queries

no-args constructor: The method Class.newInstance() is used which requires a no-args constructor to be present


Q: Can we declare the Entity class final? 
No, we should not define the entity class final because hibernate uses proxy classes and objects for lazy loading of data and hits the database only when it is absolutely needed. This is achieved by extending the entity bean. If the entity class (or bean) is made final, then it cant be extended and hence lazy loading can not be supported.

states of a persistent entity: 
![states of a persistent entity](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/000/054/original/Hibernate_Persistent_Entity.png?1614771245)

Query Cache

lazy loading

on-demand fetching strategy

concurrency strategies: the mediators responsible for storing and retrieving items from the cache

Single Table Strategy: a hibernate’s strategy for performing inheritance mapping.

@Inheritance
- @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  
@Disc
- @DiscriminatorColumn(name = "employee_type")

Table Per Class Strategy: each class in the hierarchy has a corresponding mapping database table. another type of inheritance mapping strategy. 

Named SQL Query: an expression represented in the form of a table

@NameQueries, @NameQuery

https://www.interviewbit.com/hibernate-interview-questions/ 

