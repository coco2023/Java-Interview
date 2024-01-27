import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * HashMap
 */
public class SimpleHashMap<K, V> {
    private Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 16;

    public SimpleHashMap() {
        this.buckets = new Entry[INITIAL_CAPACITY];
    }

    private int getBucketIndex(K key) {
        return key.hashCode() % INITIAL_CAPACITY;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> existing = buckets[bucketIndex];
        for (Entry<K, V> e = existing; e != null; e = e.next) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
        }
        Entry<K, V> entryInOldBucket = new Entry<>(key, value);
        entryInOldBucket.next = existing;
        buckets[bucketIndex] = entryInOldBucket;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        while (entry != null) {
            if (key.equals(entry.key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * HashSet
 */
public class SimpleHashSet<E> {
    private HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public SimpleHashSet() {
        map = new HashMap<>();
    }

    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean contains(E e) {
        return map.containsKey(e);
    }

    public boolean remove(E e) {
        return map.remove(e) == PRESENT;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}

/**
 * HashTable: HashTable与 HashMap 类似，但所有的方法都是同步的，从而提供线程安全。
 * 然而，Hashtable 的使用已经被新的集合类，如 HashMap 所取代。
 */
public class SimpleHashtable2<K, V> {
    private class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;

    public SimpleHashtable2() {
        table = new LinkedList[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public synchronized void put(K key, V value) {
        int bucketIndex = key.hashCode() % CAPACITY;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];

        for (Entry<K, V> entry: bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public synchronized V get(K key) {
        int bucketIndex = key.hashCode() % CAPACITY;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];

        for (Entry<K, V> entry : bucket ) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public synchronized void remove(K key) {
        int bucketIndex = key.hashCode() % CAPACITY;
        LinkedList<Entry<K, V>> bucket = table[CAPACITY];
        bucket.removeIf(
            entry -> entry.key.equals(key)
        );
    }
}

/**
 * 红黑树
 */
// 节点
class RedBlackTreeNode {
    int data;
    RedBlackTreeNode left, right, parent;
    boolean isRed;

    RedBlackTreeNode(int data) {
        this.data = data;
        this.isRed = true;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

// framework
public class RedBlackTree {
    private RedBlackTreeNode redBlackTreeNode;

    public RedBlackTree () {
        root = null;
    }

    public void insert(int data) {
        RedBlackTreeNode node = new RedBlackTreeNode(data);
        fixViolation(node);
    }

    private void fixViolation(RedBlackTreeNode node) {
        RedBlackTreeNode parent = null;
        RedBlackTreeNode grandParent = null;

        while (node != root && node.isRed && node.parent.isRed) {
            parent = node.parent;
            grandParent = parent.parent;
            // 检查父节点是祖父节点的左子节点还是右子节点
            // 并执行相应的旋转和重新着色操作
            // ...实现旋转和重新着色逻辑

        }

        root.isRed = false;
    }

    private void rotateLeft(RedBlackTreeNode node) {

    }

    private void rotateRight(RedBlackTreeNode node) {

    }
}

/**
 * B树
 */
class BTreeNode {
    int[] keys;
    int t;                              // 节点的最小度数
    BTreeNodep[] children;
    int n;                              // 当前节点中键的数量
    boolean leaf;

    // init
    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.key = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    // insert
    public void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) {
            // 在合适的位置插入新键
            while (i >= 0 && keys[i] > key) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = key;
            n++;
        } else {
            // 找到孩子节点的位置
            while (i >= 0 && keys[i] > key)
                i--;

            if (children[i + 1].n == 2 * t - 1) {
                // 分裂满子节点
                splitChild(i + 1, children[i + 1]);

                if (keys[i + 1] < key)
                    i++;
            }
            children[i + 1].insertNonFull(key);
        }
    }

    // split node
    public void splitChild(int i, BTreeNode y) {
        // 创建新节点存储 t-1 个键
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        // 复制最后 t-1 个键到新节点
        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        // 复制最后 t 个孩子到新节点
        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children[j] = y.children[j + t];
        }

        y.n = t - 1;

        // 将孩子节点插入到当前节点
        for (int j = n; j >= i + 1; j--)
            children[j + 1] = children[j];

        children[i + 1] = z;

        // 将键移动到当前节点
        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];

        n++;
    }
}

/**
 * 堆排序
 */
public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        // 构建堆（重新排列数组）
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

       // 一个个从堆顶取出元素
        for (int i = n - 1; i > 0; i--) {
            // 移动当前根到末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // 调用 max heapify 在减小的堆上
            heapify(arr, i, 0);
        }
    }

    // get the max heap
    void heapify(int arr[], int n, int i) {
        int largest = i;    // 初始化最大为根
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        // if l is the largest
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        // if right is the largest
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {                 // 如果最大不是根
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);       // 递归地调整受影响的子树
        }
    }
}


/**
 * Sort
 */
class SortMethods {

    /**
     * Bubble Sort
     */
    void bubbleSort(int arr[]) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) 
            for (int j = 0; j < n - i - 1; j++) 
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

    /**
     * Selection Sort
     */
    void selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[minIdx])
                    minIdx = j;

            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * insert Sort
     */
    void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Quick Sort
     */
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
    void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}

/**
 * 创建和启动线程
 */
public class ThreadExample {
    public static void main(String[] args) {
        // 创建线程的方式一：继承Thread类
        Thread thread1 = new MyThread();
        thread1.start(); // 启动线程

        // 创建线程的方式二：实现Runnable接口
        Runnable runnable = new MyRunnable();
        Thread thread2 = new Thread(runnable);
        thread2.start(); // 启动线程
    }
}

// 继承Thread类创建线程
class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 1: " + i);
        }
    }
}

// 实现Runnable接口创建线程
class MyRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 2: " + i);
        }
    }
}

/**
 * import java.util.concurrent.locks.ReentrantLock;
 */

class MyLock {
    private final ReentrantLock lock = new ReentrantLock();

    public void someMethod() {
        lock.lock();
        try {
            // 同步代码块
        } finally {
            lock.unlock();
        }
    }

}
 
/**
 * Sigleton Pattern
 */
class Sigleton {
    private static Sigleton instance;

    private Sigleton() {
    }

    public static Sigleton getInstance() {
        if (instance == null) {
            instance = new Sigleton();
        }
        return instance;
    }
}

/**
 * factory: 创建对象的接口，但不暴露对象的创建逻辑。
 */
public interface Shape {
    void draw();    
}

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

public class ShapeFactory {
    public Shape createShape(String type) {
        if (type.equals("circle"))
            return new Circle();
        return null;
    }
}

/**
 * 观察者模式（Observer Pattern）
 * 定义了一种对象之间的一对多依赖关系，当一个对象状态改变时，所有依赖它的对象都会得到通知并自动更新。
 */


/**
 * 策略模式（Strategy Pattern）
 * 将每个算法封装成一个类，并使它们可以相互替换
 */
public interface PaymentStrategy {
    void pay(int amount);
}

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid with credit card: $" + amount);
    }
}

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid with PayPal: $" + amount);
    }
}

/**
 * 装饰器模式（Decorator Pattern）
 * 动态地给一个对象添加额外的职责，装饰器模式与继承无关。
 */
interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {
    public double cost() {
        return 2.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public double cost() {
        return super.cost() + 0.5;
    }
}

/**
 * 生产者-消费者模式
 * 多线程协作的设计模式，用于解决生产者和消费者之间的数据共享和同步问题。
 * 在这个模式中，生产者负责生产数据并将其放入共享的缓冲区，而消费者则负责从缓冲区中取出数据进行处理。
 * 这个模式可以有效地实现不同线程之间的解耦和协作。
 */
class SharedBuffer {
    private final Queue<Integer> buffer;
    private final int maxSize;

    public SharedBuffer(int maxSize) {
        this.buffer = new LinkedList<>();
        this.maxSize = maxSize;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == maxSize) {
            // 缓冲区已满，等待消费者消费
            wait();
        }
        buffer.add(item);
        System.out.println("Produced: " + item);
        notify(); // 唤醒一个等待的消费者线程
    }

    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            // 缓冲区为空，等待生产者生产
            wait();
        }
        int item = buffer.poll();
        System.out.println("Consumed: " + item);
        notify(); // 唤醒一个等待的生产者线程
        return item;
    }
}

class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                buffer.produce(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int item = buffer.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(3); // 缓冲区大小为3

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}

/**
 * 依赖注入（Dependency Injection）
 * 将依赖关系从一个类传递到另一个类，以减少类之间的紧耦合。
 * 依赖可以通过构造函数、属性或方法进行注入。
 * case: 设我们有一个订单处理系统，其中有一个订单类（Order）和一个邮件服务类（EmailService）。订单类需要使用邮件服务来发送订单确认邮件。我们可以使用依赖注入来将邮件服务注入到订单类中，而不是在订单类内部创建邮件服务的实例。
 */
// 邮件服务接口
public interface EmailService {
    void sendEmail(String to, String subject, String body);
}

// 具体的邮件服务实现类
public class SmtpEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        // 实现邮件发送逻辑
        System.out.println("Sending email to " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

// 订单类，通过构造函数注入邮件服务依赖
public class Order {
    private final EmailService emailService;

    public Order(EmailService emailService) {
        this.emailService = emailService;
    }

    public void placeOrder() {
        // 订单处理逻辑...
        
        // 发送订单确认邮件
        emailService.sendEmail("customer@example.com", "Order Confirmation", "Thank you for your order!");
    }
}

public class Main {
    public static void main(String[] args) {
        // 创建邮件服务实例
        EmailService emailService = new SmtpEmailService();
        
        // 创建订单并注入邮件服务依赖
        Order order = new Order(emailService);
        
        // 下单并发送订单确认邮件
        order.placeOrder();
    }
}









