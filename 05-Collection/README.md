# refer
https://www.interviewbit.com/java-collections-interview-questions/

# Collection(*New) 

Collection: 
- the two basic "root" interfaces of Java collection classes are the Collection interface (java.util.Collection) and the Map interface(java.util.Map)
- the standard techniques for aggregating Java objects (or collections) were Arrays, Vectors, or Hash Tables. There was no common interface for all of these collections

Array V.S Collection:
- Size
  - Array: can not change the default size
  - Collection: can change
- stored data type：
  - Array: Only homogeneous data 
  - Collection: homogeneous and heterogeneous data
- data structure:
  - Array: no data structure
  - Collection: built on a standard data structure
- primitive & object data type: Java has **8 primitive data types** namely byte, short, int, long, float, double, char and boolean, These data types are held in a **stack**.;  **Object Data Types** are also referred to as Non-primitive or Reference Data Type, the non-primitive ones are created by the users in Java. the original object is kept in the **heap**, and the reference variable is kept in the **stack**. Examples include arrays, strings, classes, interfaces etc. Object data type although two copies will be created they both will point to the same variable in the heap, hence changes made to any variable will reflect the change in both the variables.
  - Array: primitive & object data type
  - Collection: Only object types
- memory: Collection better
- performance: Array better

https://www.geeksforgeeks.org/primitive-data-type-vs-object-data-type-in-java-with-examples/ 

Collection V.S Collections
- package: java.util.package
  - Collection: **interface** Collection with a static function
    - It has a number of classes and interfaces for representing a collection of individual objects as a single unit
    - The key sub-interfaces of the collection interface are List, Set, and Queue
    - functions are add(), remove(), clear(), size(), and contains()
  - Collections: utility **class** Collections
    - static methods for sorting and searching 
    - functions are sort(), min() and max()
  
![Collection V.S Collections](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/001/108/original/difference_between_collection_and_collections.jpg?1631716673)


hierarchy of the Collection framework

![hierarchy of the Collection framework](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/001/109/original/Collection_Hierarchy_in_Java.jpg?1631716908s)

advantages of the Collection framework
- Consistent API: The： Collection, Set, List, or Map, and all the classes (ArrayList, LinkedList, Vector, and so on)
- Improves program speed and quality
  
Interfaces in the Collection framework

ArrayList V.S. LinkedList
- sequence: 
  - ArrayList: stored in a dynamic array
  - LinkedList: stored in a doubly-linked list
- List and Deque: 
  - ArrayList: List
  - LinkedList: both List and Deque
- manipulating time: 
  - ArrayList: manipulating takes longer
  - LinkedList: manipulating it takes less time 
- specialist: 
  - ArrayList: data storage and access
  - LinkedList: data manipulation 

ArrayList V.s Vector
- synchronized:
  - Vector: synchronized, which means that only one thread can access the code at a time
  - ArralyList: not synchronized, which means that multiple threads can operate on ArrayList at the same time
- Data Growth: number of elements in an array exceeds its limit
  - Vector: vector increments 100% of the current array size
  - ArralyList: ArrayList increments 50% of the current array size

List V.s Set
- Order:
  - Set: unorder
  - List: ordered
- Duplicate:
  - Set: not allowed 
  - List: allowed 
- access via index:
  - Set: can not
  - List: can
- Null value:
  - Set: only once
  - List: several null elements

Iterator V.s ListIterator


HashSet V.s TreeSet

priority queue

Set V.s Map

HashSet V.s HashMap

/16. What is the default size of the load factor in hashing based collection?  <br>
0.75;
The Load factor is a measure that decides when to increase the HashTable capacity to maintain the search and insert operation complexity of O(1)


Array V.s ArrayList

make an ArrayList read-only? Collections.unmodifiableList() 
```           
List<String> read_only_list = Collections.unmodifiableList(sample_list);
```
Comparable V.s Comparator

BlockingQueue?
- BlockingQueue interface enables flow control by adding blocking if either BlockingQueue is full or empty
- concurrent Utility classes such as ConcurrentHashMap, Counting Semaphore, CopyOnWriteArrayList
- if a thread wanna to enter a full queue, the other thread should dequeuing one/ more elements to clean the current blockingqueue.
- two types of BlockingQueue:
  - Unbounded Queue: An unbounded blocking queue will never block since it has the potential to grow to a very big size
  - Bounded Queue: the capacity of the queue can be passed to the constructor when the blocking queue is created
```
BlockingQueue unbounded_queue = new LinkedBlockingDeque();

// Creates a Blocking Queue with capacity 10
BlockingQueue bounded_queue = new LinkedBlockingDeque(10);
```

![BlockingQueue](https://s3.ap-south-1.amazonaws.com/myinterviewtrainer-domestic/public_assets/assets/000/001/126/original/BlockingQueue.jpg?1631721215)


fail-fast V.s fail-safe iterators:
- structural alteration:
  - Fail-Fast: ConcurrentModificationException
  - Fail-safe: ArrayList and HashMap Iterator
- structurally update:
  - Fail-Fast: modCount -- is updated each time a collection is modified, to determine if the collection has been structurally modified or not。
    - When a fail-fast iterator gets the next item, it **checks the modCount** flag, and if it discovers that the modCount has been changed after the iterator was generated, it throws a **ConcurrentModificationException**
  - Fail-safe: fail-safe operates on a clone of the collection rather than the original collection. So if a collection is structurally updated while iterating over it, fail-safe iterators don't throw any exceptions. Fail-safe Iterators include the **CopyOnWriteArrayList** and **ConcurrentHashMap** classes.

RandomAccess Interface:
- Marker interface used by List implementations to indicate that they support fast (generally constant time) random access
- The primary purpose of this interface is to allow generic algorithms to alter their behavior to provide good performance when applied to either random or sequential access lists.
- a marker interface: like Serializable and Cloneable interfaces. There are no methods defined in any of these marker interfaces. Rather, they designate a class as having a specific capability.
  
Iterator V.s Enumeration
- universal cursor
  - Iterator: Yes. can traverse HashMaps, LinkedLists, ArrayLists, HashSets, TreeMaps, and TreeSets
  - Enumeration: No. legacy interface. can traverse Hashtables and Vectors
- make changes
  - Iterator: Yes. Exp: delete(), remove()
  - Enumeration: No

Properties: java.util.Properties; Hashtable subclass; used to calculate the value of a property based on its key
```
    FileReader reader = new FileReader("info.properties");  
    Properties obj_p = new Properties();  
    obj_p.getProperty("user")
```

HashMap V.s HashTable
- HashMap: (is preferred if thread synchronization is not required)
  - non-synchronized data structure
  - not thread-safe
  - cannot be shared across many threads without the use of synchronization code
  - HashMap allows one null key and multiple null values
- Hashtable:
  - synchronized
  - thread-safe
  - can be used by several threads
  - Hashtable doesn't allow any null key or value:
> Since null isn't an object, you can't call .equals() or .hashCode() on it, so the Hashtable can't compute a hash to use it as a key/ value.

synchronize an ArrayList?
- Collections.synchronizedList()
```
List<String> synchronized_list = Collections.synchronizedList(new ArrayList<String>());
synchronized_list.add("learn");
synchronized_list.add("practice");

synchronized(synchronized_list) {
    Iterator it = synchronized_list.iterator();
    while (it.hasNext()) {
        System.out.println(it.next());
    }
}
```
- CopyOnWriteArrayList:
```
// a thread-safe variant of ArrayList is created. 
// T represents generic.
CopyOnWriteArrayList<T> list_name = new CopyOnWriteArrayList<T>();
synchronized_list.add("learn");
synchronized_list.add("practice");

Iterator<String> it = synchronized_list.iterator();

while (it.hasNext()) {
    System.out.println(it.next());
}
```

/28. Why do we need synchronized ArrayList when we have Vectors (which are synchronized) in Java?
- Vector is **slightly slower** than ArrayList because it is synchronized and thread-safe
- Individual operations are less safe and take longer to synchronize. We prefer synchronize an entire sequence of actions
- Vectors are considered outdated in Java and have been unofficially deprecated

HashMap V.s TreeMap
- structure (implement Map interface)
  - HashMap: based on hash tables
  - TreeMap: based on a Tree structure
- implement
  - HashMap: Map, Cloneable, and Serializable
  - TreeMap: NavigableMap, Cloneable, and Serializable
- speed
  - HashMap:  O(1), Faster, get() and put()
  - TreeMap: O(log(n)), add(), remove(), and contains()
- NULL
  - HashMap:  A single null key & numerous null values are allowed in HashMap
  - TreeMap: does not allow null keys; while multiple null values are allowed.
- compare:
  - HashMap:  quals()
  - TreeMap: compareTo()
- key-value pair order
  - HashMap:  unsorted
  - TreeMap: sorted order

convert Array into Collection
```
List converted_list = Arrays.asList(sample_array)
```

display the contents of a HashTable using enumeration:
```
Hashtable hash_table = new Hashtable();
hash_table.put("1", "Monday");
hash_table.put("2", "Tuesday");

Enumeration enumeration_hash_table = hash_table.elements(); //  Hashtable.elements()
while(enumeration_hash_table.hasMoreElements()) {
    enumeration_hash_table.nextElement();                   // while + nextElement
}

```

shuffle all the elements of a collection in Java:
```
Collections.shuffle(array_list);
```

clone a Treeset to another Treeset: ` TreeSet<String>. clone() `
```
TreeSet<String> tree_set = new TreeSet<String>();
tree_set.add("Monday");
tree_set.add("Tuesday");

TreeSet<String> cloned_tree_set = (TreeSet<String>)tree_set.clone();

```

get the collection view of the values present in a HashMap:
`HashMap<String,String>.values()`
```
HashMap<String,String> hash_map = new HashMap<String,String>();
hash_map.put("1","Monday");
hash_map.put("2","Tuesday");

// get collection view of the hash map
hash_map.values();
```

































