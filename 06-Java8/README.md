# refer
  
https://intellipaat.com/blog/interview-question/java-8-interview-questions/

https://www.interviewbit.com/java-8-interview-questions/

(*New)  stream API
https://quescol.com/interview-preparation/java-8-interview-questions
(*New)
https://www.codingninjas.com/codestudio/library/stream-api-interview-questions 


# Java 8
Features:
- Lambda expressions
- Functional interface: A functional interface is an interface that contains **only one abstract** method. They can have only one functionality to exhibit. From Java 8 onwards, lambda expressions can be used to represent the instance of a functional interface. Like Runnable, ActionListener, and Comparable...
- Stream API
- Time API, Date API
- forEach() method in Iterable interface
- Optional API
- default and static methods in Interfaces
- Collection API improvements
- Concurrency API improvements
- Java IO improvements
![Java8 Features](https://journaldev.nyc3.digitaloceanspaces.com/2013/12/java8-features.png)

Lambda:
```
(int a, int b) -> { System.out.println(a+b); return a+b;}
```

@FunctionalInterface: to ensure that the functional interface can’t have more than one abstract method

String::ValueOf <br>
referencing the valueOf method in String class

Method References
- Class::methodname( String::ValueOf )

predicate V.s function
- return:
  - predicate: True or False; Predicate is a functional interface used in Java to take in an object and return a Boolean value
  - function: object
- 
  - predicate: one of the types of Functional Interfaces; Used as an assignment target for lambda expressions	
  - function: Can be used for both lambda expressions and method references
```
// Predicate Interface
public boolean test(T object){
    return boolean;
}
```

default methods
```
interface TestInterface
{
    // abstract method
    public void square(int a);
  
    // default method
    default void show()
    {
      System.out.println("Default Method Executed");
    }
}

```

intermediate Operation V.s terminal operations
- usage:
  - intermediate Operation: Used for the transition to a new state	
  - terminal operations: Used to end the process under execution
- Lazy loading?
  - Lazy execution of code, i.e., code is not executed as soon as it is encountered	
  - Not lazy; code is immediately executed upon encounter

Nashorn?

optional keyword: to avoid the occurrence of the NullPointerException
```
@Test
public void whenCreatesEmptyOptional_thenCorrect() {

    Optional<String> empty = Optional.empty();
    assertFalse(empty.isPresent());
}
```

stream pipelining? users can chain more than one operation at a time
- Intermediate operations: Return the instance of the stream when running
- Terminal operations: Used to terminate the operation and return the final value

JJS

Collections.sort + Lambda:
```
Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

```

call a static method of any interface in a class using Java 8? 
- Yes

Random class

collectors
```
    .stream()
    .map(Class::method)
    .collect(Collectors.toList())
```

When is an ideal situation to use the Stream API in Java 8?
- Perform database operations
- Execute operations lazily
- Write functional-style programming
- Perform parallel processing
- Use pipeline operations
- Use internal iteration

Base64.Encoder: implements the encoder used for encoding byte data
- getDecoder(): to return a Base64.Decoder
- getUrlEncoder(): to encode the URLs

supplier in Java 8: It represents a function which does not take in any argument but produces a value of type T.

consumer in Java 8: It represents a function which takes in one argument and produces a result. However these kind of functions don’t return any value.

findFirst() V.s findAny() 

Collection API V.s Stream API
- store data type:
  - Collection API: store object data	
  - Stream API: computation of data
- storage
  - Collection API: limited number of elements
  - Stream API: unlimited number of elements
- execution
  - Collection API: Eager execution
  - Stream API: Lazy execution

Spliterators: for traversing the elements of a source
```
Spliterator splitr = c.spliterator();
```

Spliterator iterator V.s Regular iterator
- Spliterator: use Stream API; tryAdvance()... 
- Regular: use Collection API; next() and hasNext()...

Common types of functional interfaces: BiFuction, BinaryOperator, Consumer, Predicate, Supplier, UnaryOperator

Map V.s FlatMap

intermediate operations: used to pipeline other methods and to transform into the other streams
- Distinct()
- Limit(long n)
- Filter(Predicate)
- Map(Function)
- skip(long n)

remove duplicate elements: `Collections.toSet()`

peek(): used to see actions performed through a stream pipeline. Peeking can be done at every step to print messages about the code being executed onto the console

convert an array into a stream: 
```
Stream.of(array).forEach(System.out::println);
```

# Comparable & Comparator
In Java, the `Comparable` interface and the `Comparator` interface are both used for defining the order of objects, but they serve slightly different purposes and are used in different scenarios. Understanding when to use each can help you write more flexible and reusable code.

### 1. `Comparable` Interface

**Usage**: The `Comparable` interface is used when you want to define a **natural ordering** for objects of a particular class. It requires the class to implement a single method, `compareTo`, which compares the object with another object of the same type.

**When to Use**:
- When there's a single, natural ordering of the elements that you want to enforce throughout your application.
- When you're working with your own classes and can modify the class source.

**Example**:
```java
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return name + ": " + age;
    }
}
```
Here, `Person` objects are naturally ordered by age.

**Usage in Code**:
```java
List<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Charlie", 35));

Collections.sort(people); // Sorts by natural order, which is by age
```

### 2. `Comparator` Interface

**Usage**: The `Comparator` interface is used when you want to define an **external, possibly multiple different orders** for objects, which may not modify the objects' class or when you want to provide multiple ways of ordering instances of the same class.

**When to Use**:
- When you cannot modify the class whose objects you need to sort (e.g., classes from a library).
- When you need multiple different ways to sort the same class of objects.
- When sorting based on criteria that are not intrinsic to the objects themselves.

**Example**:
```java
public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
```

**Usage in Code**:
```java
List<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Charlie", 35));

Collections.sort(people, new AgeComparator()); // Sorts by age
Collections.sort(people, new NameComparator()); // Sorts by name
```

### Practical Considerations

- **Flexibility**: `Comparator` provides more flexibility as it allows multiple sorting criteria for the same objects and can be used without altering the object's class definition.
- **Design**: Use `Comparable` when sorting with a single natural order and `Comparator` when you need specific control over the sort order or multiple different sorting orders.

Using these interfaces effectively depends on understanding your specific needs for ordering elements in your application, whether that's enforcing a natural order with `Comparable` or defining multiple specific orders with `Comparator`. This flexibility makes Java collections highly adaptable to a wide range of use cases.






















