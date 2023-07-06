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


## refer
  
https://intellipaat.com/blog/interview-question/java-8-interview-questions/

https://www.interviewbit.com/java-8-interview-questions/

(*New)  stream API
https://quescol.com/interview-preparation/java-8-interview-questions
(*New)
https://www.codingninjas.com/codestudio/library/stream-api-interview-questions 

























