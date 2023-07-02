## Container

The container will create the objects, wire them together, configure them, and manage their complete life cycle from creation till destruction


## IoC

IOC containers support both Eager Instantiation and Lazy loading of beans:  While lazy loading delays the initialization of a resource, eager loading initializes or loads a resource as soon as the code is executed. Eager loading also involves pre-loading related entities referenced by a resource

## Dependency Injection

Dependency Injection (DI) is a design pattern used to implement IoC. It allows the creation of dependent objects outside of a class and provides those objects to a class through different ways. Using DI, we move the creation and binding of the dependent objects outside of the class that depends on them.

A container is responsible for creating and wiring the objects. The container can call injecting code and wire the objects as per the configuration at runtime

https://www.tutorialsteacher.com/ioc/dependency-injection
https://stackify.com/dependency-injection/ 
https://www.geeksforgeeks.org/spring-dependency-injection-with-example/


Core Container layer: Core, Beans, Context, and Expression Language(EL) modules

The Core and Beans modules provide the fundamental parts of the framework, including the IoC and Dependency Injection features

https://docs.spring.io/spring-framework/docs/4.0.x/spring-framework-reference/html/overview.html#:~:text=The%20Core%20Container%20consists%20of,IoC%20and%20Dependency%20Injection%20features. 

The BeanFactory interface provides an advanced configuration mechanism capable of managing any type of object. 
The BeanFactory is a sophisticated implementation of the factory pattern.
It separates the configuration and dependencies of an application from the rest of application code.
XMLBeanFactory is the most popular implementation of BeanFactory in Spring.This factory loads its beans based on the definitions mentioned in an XMLfile. 
Spring container reads bean configuration metadata from an XML file and creates a fully configured application with the help of XMLBeanFactory class.

https://docs.spring.io/spring-framework/docs/4.0.x/spring-framework-reference/html/beans.html#beans-introduction

Data Access/Integration layer: JDBC, ORM, OXM, JMS, Transaction modules

-- ORM: Hibernate, JPA, TopLink, JDO, ORB

Web layer: Web, Web-Servlet, WebSocket and Web-Portlet modules

Test layer: JUnit, TestNG

## Aspect Oriented Programming

AOP addresses the problem of cross-cutting concerns, which would be any kind of code that is repeated in different methods and can't normally be completely refactored into its own module, like with logging or verification. So, with AOP you can leave that stuff out of the main code and define it vertically like so:

separating similar application business logic from system services which are common.

https://stackoverflow.com/a/242194

## Transaction Management:

Transactions are a set of operations used to perform a logical set of work

the supervision of critical business applications and services by auditing the individual transactions that flow across the application infrastructure. <br>
transaction management is a way for an eCommerce business to ensure that the transaction-related operations of their online business are functioning in the way they intend it to, and in a way that will facilitate sales

target specific components that may need to be improved


https://www.geeksforgeeks.org/transaction-management/

## ApplicationContext



## Spring Bean
A Spring Bean is a plain old Java object (POJO) that is created and managed by a Spring container. 

all these Beans are instantiated and assembled by Spring container.

configuration metadata to Spring container for creating and managing the lifecycle of Spring Bean

In general a Spring Bean is singleton. Evert bean has an attribute named "singleton". If its value is true then bean is a singleton. If its value is false then bean is a prototype bean. By default all the beans in spring framework are singleton in nature

Spring Bean definition contains configuration metadata for bean. The configuration is used to: Create the bean, Manage its lifecycle, Resolve its dependencies

Is it safe to assume that a Singleton bean is thread safe in Spring Framework?
- No. Spring framework does not guarantee anything related to multi-threaded behavior of a singleton bean. Developer is responsible for dealing with concurrency issues and maintaining thread safety of a singleton bean.

lifecycle of a Bean? 146.

Inner beans: inner beans are beans that are defined within the scope of another bean

DI: can inject not only objects but also collection of objects.

Bean wiring:  A Spring container is responsible for injecting dependencies between beans. This process of connecting beans is called wiring.  Dependencies between beans in Config file --> spring container reads --> container wires the beans on createion.

inject null or empty String values in Spring(√)

@Required, @Autowired, @Qualifier

@Required annotation applies to bean property setter methods and it indicates that the affected bean property must be populated in XML configuration file at configuration time. Otherwise, the container throws a BeanInitializationException exception

@Qualifier annotation is used in conjunction with @Autowired to avoid confusion when we have two or more beans configured for the same type.

https://www.javaguides.net/2018/06/spring-qualifier-annotation-example.html


## Design pattern

Design-patterns used in Spring framework? 145.


## AOP

Concern and a Cross cutting concern: 

Joinpoint

Advice

Pointcut: the group of one or more Joinpoints where an advice can be applied

Introduction(inter-type declaration)

Target(advised object/ proxy obj)

Proxy

Weaving

@Aspect, @Pointcut, @Joinpoin


## Spring MVC

DispatcherServlet

WebApplicationContext

Controller

How will you decide which scope-Prototype or Singleton to use for a bean in Spring?

differences between Dependency Injection (DI) and Factory Pattern? 200.