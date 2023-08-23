# Refer
https://www.simplilearn.com/tutorials/java-tutorial/java-interview-questions



# Core Java
JDK:
  - JRE + loader(Java) + compiler(javac) + archiver(jar) + documentation generator (javadoc) + other tools
![JDK](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)

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
- **Aggregation**: is a collection, or the gathering of things together; “has a” relationship; aggregation is a group, body, or mass composed of many parts or individuals

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



















