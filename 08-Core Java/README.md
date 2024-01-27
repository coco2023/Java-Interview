# Refer
https://www.simplilearn.com/tutorials/java-tutorial/java-interview-questions  (-52)

https://www.javatpoint.com/corejava-interview-questions

# Core Java 1
JDK:
  - JRE + loader(Java) + compiler(javac) + archiver(jar) + documentation generator (javadoc) + other tools
![JDK](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)

Just-In-Time(JIT) compiler
- used to improve the performance
- a component of the runtime environment that improves the performance of Java™ applications by compiling bytecodes to native machine code at run time

ClassLoader
  - Class loaders are responsible for loading Java classes dynamically to the JVM (Java Virtual Machine) during runtime. They're also part of the JRE (Java Runtime Environment). Therefore, the JVM doesn't need to know about the underlying files or file systems in order to run Java programs thanks to class loaders.
  - **Bootstrap Class Loader** – It loads JDK internal classes. It loads rt.jar and other core classes for example java.lang.* package classes.
  - **Extensions Class Loader** – It loads classes from the JDK extensions directory, usually $JAVA_HOME/lib/ext directory.
  - **System Class Loader** – This classloader loads classes from the current classpath. We can set classpath while invoking a program using -cp or -classpath command line option.

Memory allocation
  - it is the process where the computer programs and services are allocated dedicated to virtual memory spaces. The Java Virtual Machine divides the memory into Stack and Heap Memory
  - Class Memory
  - Heap Memory
  - Stack Memory
  - Program Counter-Memory
  - Native Method Stack Memory

- Types in Java are divided into 2 categories: primitive types and reference types
  - primitive types: boolean , byte , char , short , int , long , float and double
  - All other types are reference types, so classes, which specify the types of objects, are reference types

Association & Aggregation & Composition 
- **Association**: relation between two separate classes which establishes through their Objects； one-to-one, one-to-many, many-to-one, many-to-many
- **Aggregation**: is a collection, or the gathering of things together; “has a” relationship; aggregation is a group, body, or mass composed of many parts or individuals. 
  - **Aggregation** implies a relationship where the child can **exist independently** of the parent
- **Composition** implies a relationship where the child **relays and depends on** (cannot exist independent of) the parent
- **Aggregation** and **Composition** are **subsets** of **Association** meaning they are specific cases of association
- Aggregation represents the weak relationship whereas composition represents the strong relationship

Inheritance V.s. Composition
- testability: A class's testability is improved through composition over inheritance
  - If a class is comprised of another class, it is simple to create a mock object to simulate the combined class for testing purposes
- breaking encapsulation  
  - Inheritance has the drawback of breaking encapsulation

Copy Constructor
- to create a copy of an object such that the new object (copied) has the exact same values as the original object has
- just like we create an extra temporary variable when swapping values of two variables 

Marker Interface: an empty interface
- **Cloneable interface**: is present in java.lang package; clone()
- **Serializable interface**: in java.io package; make an object eligible for saving its state into a file
- **Remote interface**: java.rmi; an object which is stored at one machine and accessed from another machine; make an object a remote object, we need to flag it with Remote interface;

Object Cloning: clone()
- recreate an object entirely similar to an existing object

object-oriented paradigm
- is based on the concepts of “Objects” 

Wrapper Classes
- convert boolean , byte , char , short , int , long , float and double data types(primitive data types) into object(Reference types).

singleton class
- A class that can possess only one object

instance V.s. local variables
- Instance variable: These variables are declared within a class but outside any method, constructor, or block of code. They are associated with individual instances (objects) of the class
- Local variables: must be initialized before they can be used

Java String Pool
- A collection of strings in Java's Heap memory is referred to as Java String Pool.
- When try to create a new string object, JVM first checks for the presence of the object in the pool

Exception
- an unexpected event that can disrupt the program's normal flow
- These events can be fixed through the process of Exception Handling

final

why main() is static:
- There is **no object of the class existing when the Java runtime starts**. This is why the main() method must be static: static main() method **helps the JVM to load the class into memory and call the main function**. 
- If the main method is **not static**, **JVM will be unable to call it** since no object of the class is present.
- main() is static by default, **allowing the compiler to call it either before or after creating a class object**

garbage collection & Heap memory
- Garbage Collection runs on the heap memory to free the memory used by objects that don't have any reference
- Any object created in the heap space has global access and can be referenced from anywhere of the application

program V.s. process
- **programme**: a non-active entity that includes the collection of codes necessary to carry out a specific operation
- **process**: begins by a programme once it has been run. The process carries out the program's specified instructions

String V.s. StringBuffer V.s. StringBuilder
- **String** is **immutable** whereas StringBuffer and StringBuilder are mutable classes. 
- **StringBuffer** is thread-safe and synchronized whereas StringBuilder is not. 
- That's why **StringBuilder** is faster than StringBuffer

daemon thread
- daemon threads are low-priority threads that run in the background to perform tasks such as **garbage collection** or provide services to user threads

main() thread a daemon thread
- By default, the main thread is always a non-daemon thread

exception propagate
- An exception is first thrown from the top of the stack and if it is not caught, it drops down the call stack to the previous method

Contiguous memory locations are usually used for storing actual values in an array but not in ArrayList

**Contiguous memory allocation**: is a classical memory allocation model. Here, a system assigns consecutive memory blocks (that is, memory blocks having consecutive addresses) to a process


LinkedList V.s. Array
- there is no resizing of an array done in the background
![](https://journaldev.nyc3.digitaloceanspaces.com/2019/10/java-list-add-addAll.png)

 ArrayList V.s. List
 - ArrayList: a resizable array implementation in Java is called ArrayList

new String() V.s. String str = ""
- new String() create a new instance
- The String pool may return an existing object if we build an object using the String literal syntax

new obj. V.s. newInstance() 
- both are used for object creation


garbage collector & memory
- **Garbage collection**: aids in identifying and removing programme objects that are no longer needed in order to release the resources they use
- When an object in a programme cannot be reached, Garbage collection is executed with respect to that object
- If there is not enough memory available to create new objects, a garbage collector is used to free up memory for the computer


synchronization 
- Multiple threads trying to access the same resources in a multi-threaded software may frequently result in unexpected and incorrect outcomes
- so, it must be ensured through some form of synchronization that only one thread can access the resource at any given time

Java thread lifecycle
- create(new) - active(start) - blocked/watiing - time waiting - terination



# Link 2
access specifiers
- public 
- private
- protected
- default

static methods & variables
- The methods or variables defined as static are shared among all the objects of the class
- The **static variable** is used to refer to the common property of all objects (that is not unique for each object)
- A **static method** belongs to the class rather than the object;
- There is no need to create the object to call the static methods.
- A static method can access and change the value of the static variable.'
- The static method can not use non-static data member or call the non-static method directly
- this and super cannot be used in static context as they are non-static.
- The static method can access only static type data
- There is no need to create an object of the class to invoke the static method
- A static method cannot be overridden in a subclass




advantages of Packages
- avoid the name clashes
- provides easier access control
- easier to locate the related classes

object reference
- An object reference is information on how to find a particular object
- The object is a chunk of main memory; 
- a reference to the object is a way to get to that chunk of memory. 
- The variable str does not actually contain the object, but contains information about where the object

constructor
- Default Constructor: initialize the instance variable with the default values; assign the default value
- Parameterized Constructor
- The constructor implicitly returns the current instance of the class

we cannot make a constructor final

overload the constructors

copy constructor
- By constructor
  ```
  Student6(Student6 s) {}
  ```
- By assigning the values of one object into another
  ```
  Student6 s2 = new Student6(s1);
  ```
- By clone() method of Object class

static block
- Static block is used to initialize the static data member. 
- It is executed before the main method, at the time of classloading

static method V.s. instance method
- Static methods are just utility functions
- To invoke a **instance method**, we have to create an Object of the class in which the method is defined
- there is no sense to make the constructors static

this, this()
- "this" refers to current class properties such as instance methods, variable, constructors...
- this() can be used to invoke the current class constructor.

We cannot assign the reference to `this` variable?
- `this` cannot be assigned to any value because it always **points to the current class object** and `this` is **the final reference in Java**. 

this to refer static method:
- it is unnecessary to access static variables through objects, therefore, it is not the best practice to use this to refer static members

constructor chaining
- Constructor chaining enables us to call one constructor from another constructor of the class with respect to the current class
- We can use this keyword to perform constructor chaining within the same class
  ```
      public Employee (int age)  
      {  
          this.age = age;  
      }  
      public Employee(int id, int age)  
      {  
          this(age);  
          this.id = id;  
      }  

  ```

advantages of passing `this` into a method instead of the current class object itself
- `this` is a `final` variable
- `this` can be used in the `synchronized` block

Inheritance: what; why

superclass


multiple inheritance not supported
- To reduce the complexity and simplify the language

why Java not support pointers?
- The pointer is a variable that refers to the **memory address**. 
- They are not used in Java because they are **unsafe(unsecured) and complex** to understand.

super
- refer to the immediate parent class instance variable
- used to invoke the immediate parent class method
  
  this V.s. super
  - `super` keyword always points to the parent class contexts whereas `this` keyword always points to the current class context.
  - `super` keyword is primarily used for initializing the base class variables within the derived class constructor whereas `this` keyword primarily used to differentiate between local and instance variables when passed in the class constructor.
  - we cannot use `this()` and `super()` both in a constructor. because `this()` and `super()` must be the **first statement in the class** constructor.

object cloning
- create the exact copy of an object
- clone() method of the Object class is used to clone an object
- `java.lang.Cloneable` interface must be implemented by the class whose object clone we want to create. Otherwise `CloneNotSupportedException` will be thrown by the clone() method.

method overloading V.s. method overriding
- method overloading: same class
  - By Changing the number of arguments
  - By Changing the data type of arguments
- method overriding
  - a subclass provides a specific implementation of a method that is already provided by its parent class
  - have the same name as in the parent class
  - have the same signature as in the parent class
  - have an IS-A relationship between them
  - **we cannot override the static method because they are the part of the class, not the object**
  - parameters must be the same

covariant return type

final variable & final method
- final variable is used to restrict the user from updating it. If we initialize the final variable, we can't change its value
- If we change any method to a final method, we can't override it

initialize the final blank variable
- if it is not static, we can initialize it in the constructor. 
- If it is static blank final variable, it can be initialized only in the static block
- The constructor can never be declared as final because it is never inherited
- we cannot declare an interface as final because the interface must be implemented by some class to provide its definition

final method V.s. abstract method
- abstract method cannot be final as we need to override them in the subclass to give its definition

https://www.javatpoint.com/corejava-interview-questions-2

compile-time polymorphism V.s runtime polymorphism
- Compile time polymorphism: 
  - this process is executed during compile time; it is obtained through method overloading. 
  - The term method overloading allows us to have more than one method with the same name
  - static polymorphism or early binding
  - **Overloading** is a way to achieve compile-time polymorphism
- runtime polymorphism: 
  - receives the information to call a method in runtime
  - **Overriding** is a way to achieve runtime polymorphism
  - we cannot achieve Runtime Polymorphism by data members: because method overriding is used to achieve runtime polymorphism and data members cannot be overridden. We can override the member functions but not the data members


static binding V.s dynamic binding V.s. Dynamic method dispatch [refer](https://www.geeksforgeeks.org/static-vs-dynamic-binding-in-java/)
- static binding: the type of the object is determined at compile-time
- dynamic binding: the type of the object is determined at runtime

instanceOf
- returns either true or false

Abstraction
- a process of hiding the implementation details and showing only functionality to the user.
- 























