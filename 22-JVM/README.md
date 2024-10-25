# 【Refer】
1. ChatGPT: https://chatgpt.com/c/671c02db-c5e0-800d-a0d2-581aea4e304c
2. ChatGPT: https://chatgpt.com/c/671c031f-ca3c-800d-9f10-08ac15ce1639
   
### JVM 主要组成部分

JVM 的组成可以分为以下几部分：

1. **类加载子系统（Class Loader Subsystem）**
2. **运行时数据区（Runtime Data Area）**
3. **执行引擎（Execution Engine）**
4. **本地方法接口（Native Interface）**
5. **垃圾收集器（Garbage Collector）**

#### 1. 类加载子系统

类加载子系统的职责是将 `.class` 文件加载到内存中，并将其转化为 JVM 能理解的数据结构。类加载子系统主要包括以下几种：

- **Bootstrap ClassLoader（引导类加载器）：** 负责加载 Java 核心类（如 `java.lang.*` 等），由 JVM 自身实现。
- **Extension ClassLoader（扩展类加载器）：** 负责加载 `ext` 目录中的类库，通常加载系统的扩展类。
- **Application ClassLoader（应用类加载器）：** 负责加载应用程序类路径（`classpath`）下的类，通常是用户自定义的类。

以编译 `.class` 文件为例，类加载过程可以分为以下几个步骤：

- **加载（Loading）：** 这一步将 `.class` 文件加载到 JVM 中，创建相应的类的字节码表示。通过类加载器（如 Bootstrap ClassLoader、Extension ClassLoader、Application ClassLoader）来完成。
  - J*VM 中的类加载器主要包括以下几种：*
    - ***Bootstrap ClassLoader（引导类加载器）：***\* 负责加载 Java 核心类（如 **`java.lang.*`** 等），由 JVM 自身实现。\*
    - ***Extension ClassLoader（扩展类加载器）：***\* 负责加载 **`ext`** 目录中的类库，通常加载系统的扩展类。\*
    - ***Application ClassLoader（应用类加载器）：***\* 负责加载应用程序类路径（**`classpath`**）下的类，通常是用户自定义的类。\*
- **验证（Verification）：** 验证步骤确保 `.class` 文件中的字节码符合 JVM 规范，不包含破坏性或不安全的代码。它主要检查字节码的格式和语义。
- **准备（Preparation）：** 为类的静态变量分配内存，并将其初始化为默认值（如 0、null）。

**实现方式：** 在准备阶段，JVM 会为所有类的静态变量分配内存，并设置默认值。例如，整型变量初始化为 0，引用类型初始化为 `null`。这种操作通常由 JVM 自动完成，无需程序员显式操作。

##### 示例代码

```java
public class PreparationExample {
    static int staticVar;  // 默认值为 0
    static String staticString;  // 默认值为 null

    public static void main(String[] args) {
        System.out.println("Static Int: " + staticVar);  // 输出 0
        System.out.println("Static String: " + staticString);  // 输出 null
    }
}
```

在上述代码中，`staticVar` 和 `staticString` 在准备阶段被分配内存并初始化为默认值，程序员无需手动初始化。

- **解析（Resolution）：** 将符号引用（如类、接口、字段、方法等）转换为直接引用。解析可以是静态的，也可以是延迟进行的。

**实现方式：** 解析阶段主要是将符号引用替换为直接引用，这一步可以由 JVM 在类加载的过程中完成，也可以在第一次使用符号引用时完成（即延迟解析）。

##### 示例代码

```java
public class ResolutionExample {
    static class Parent {
        static void display() {
            System.out.println("Parent display method");
        }
    }

    static class Child extends Parent {
        static void display() {
            System.out.println("Child display method");
        }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        p.display();  // 输出 "Parent display method"，符号引用在解析阶段被替换为直接引用
    }
}
```

在上述代码中，`p.display()` 的调用是符号引用到直接引用的过程。JVM 会在解析阶段确认 `p` 的实际类型，并调用相应的 `display()` 方法。输出结果显示 `p` 是 `Parent` 类型，因为在声明 `p` 时使用了 `Parent p = new Child();`，这里的 `p` 是静态类型 `Parent`，而不是运行时的实际类型 `Child`。**静态类型是在编译期间确定的，它决定了编译器在编译时如何检查代码的正确性，而实际类型是在运行时确定的，它决定了程序的行为**。因此，调用的是 `Parent` 类中的静态方法 `display()`。

##### 示例代码

```java
public class StaticBindingExample {
    static class Parent {
        static void display() {
            System.out.println("Parent display method");
        }
    }

    static class Child extends Parent {
        static void display() {
            System.out.println("Child display method");
        }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        p.display();  // 输出 "Parent display method"
    }
}
```

在这个例子中，由于 `display()` 方法是静态方法，静态方法在编译期间绑定，因此它们不会表现出多态性。多态性是指在运行时根据对象的实际类型调用相应的方法，而静态方法在编译时已经确定了调用关系，无法根据运行时的实际类型进行动态绑定。`p` 的类型在编译时是 `Parent`，所以调用的是 `Parent` 类的 `display()` 方法，而不是 `Child` 类的版本。如果 `display()` 方法不是静态的，而是实例方法，并且 `Parent p = new Child();`，那么 `p` 的实际类型是 `Child`，会表现出多态性，此时输出结果会是 "Child display method"。

##### 示例代码

```java
public class DynamicBindingExample {
    static class Parent {
        void display() {
            System.out.println("Parent display method");
        }
    }

    static class Child extends Parent {
        @Override
        void display() {
            System.out.println("Child display method");
        }
    }

    public static void main(String[] args) {
        Parent p = new Child();
        p.display();  // 输出 "Child display method"
    }
}
```

在上述代码中，`display()` 方法是实例方法，`p` 的静态类型是 `Parent`，但实际类型是 `Child`。在运行时，JVM 根据实际类型来调用 `Child` 类的 `display()` 方法，这体现了多态性。

- **初始化（Initialization）：** 执行类中的静态初始化代码（如静态块和静态变量的初始化）。

**实现方式：** 初始化阶段是执行类的静态代码块和静态变量赋值的过程。JVM 在这个阶段确保所有静态成员被初始化。

##### 示例代码

```java
public class InitializationExample {
    static int staticVar;
    static {
        staticVar = 10;  // 静态块初始化
        System.out.println("静态块执行: staticVar = " + staticVar);
    }

    public static void main(String[] args) {
        System.out.println("静态变量的值: " + staticVar);  // 输出 10
    }
}
```

在上述代码中，静态变量 `staticVar` 通过静态代码块进行初始化，这个过程在类第一次被使用时执行，属于 JVM 的类加载子系统的一部分。

这些步骤与 JVM 的类加载子系统有关，负责将类从 `.class` 文件加载到 JVM 中并进行相应的准备工作。

##### 示例代码

```java
public class TestClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println("ClassLoader: " + classLoader);
    }
}
```

在上述代码中，通过 `ClassLoader` 对象我们可以了解类的加载器信息，例如 Bootstrap ClassLoader、Extension ClassLoader 和 Application ClassLoader。

#### 2. 运行时数据区

JVM 在运行时会为每个 Java 应用程序分配运行时数据区。这些区域可以进一步割分为以下部分：

- **方法区（Method Area）：** 存储已加载类的元数据、静态变量、常量池等。
- **堆（Heap）：** 用于分配对象实例和数组，是垃圾收集的主要目标。
- **Java 栈（Java Stack）：** 每个线程有自己的 Java 栈，存储方法调用的信息（如局部变量、操作数栈等）。
- **本地方法栈（Native Method Stack）：** 用于处理调用本地代码的线程。
- **程序记数器（Program Counter Register）：** 保存当前线程执行的字节码指令地址。

##### 示例代码

```java
public class TestMemoryArea {
    static int staticVar = 0; // 方法区中
    int instanceVar = 1;      // 堆中

    public static void main(String[] args) {
        TestMemoryArea tma = new TestMemoryArea();
        tma.method();         // 调用方法，栈中有相关信息
    }

    public void method() {
        int localVar = 5;    // 局部变量在栈中
        System.out.println(localVar);
    }
}
```

在上述代码中：

- **`staticVar`** 是静态变量，存储在方法区中。
- **`instanceVar`** 是实例变量，存储在堆中。
- **`localVar`** 是局部变量，存储在 Java 栈中。
- **方法调用信息**（如 `tma.method()`）也会被存储在 Java 栈中，用于管理方法执行过程中的局部数据。

#### 3. 执行引擎

执行引擎负责将字节码转化为机器码并执行。主要包括：

- **解释器（Interpreter）：** 按行解释执行字节码，执行速度慢。
- **即时编译器（JIT Compiler）：** 将字节码编译成本地机器码，以提高性能。

JIT 编译器会在热点代码（频繁执行的代码）上进行优化，提高代码执行效率。

##### 示例代码

```java
public class HotspotTest {
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            System.out.println("Count: " + i);
        }
    }
}
```

在上述代码中，由于循环次数较多，JIT 编译器可能会优化循环体的执行，从而提高执行速度。

#### 4. 本地方法接口

本地方法接口（Java Native Interface, JNI）允许 Java 调用本地（非 Java）代码，例如 C/C++ 编写的库，用于与底层系统交互。

##### 示例代码

```java
public class TestJNI {
    static {
        System.loadLibrary("nativeLib");
    }

    public native void nativeMethod();

    public static void main(String[] args) {
        new TestJNI().nativeMethod();
    }
}
```

通过 `System.loadLibrary("nativeLib")` 可以加载本地动态链接库，使用 `native` 关键字声明方法为本地方法。

#### 5. 垃圾收集器

垃圾收集器（Garbage Collector, GC）用于回收堆中的无用对象。Java 中常见的垃圾收集算法有：

- **标记-清除算法（Mark-Sweep）：** 适用于**老年代**，用于回收不再被引用的对象。该算法的主要特点是遍历所有的对象并标记出无用对象，然后清除它们，会产生内存碎片。
- **复制算法（Copying）：** 适用于新生代，将存活的对象复制到另一块区域，效率高，适合新生代对象生命周期短的情况。
- **标记-压缩算法（Mark-Compact）：** 适用于老年代，结合了标记和压缩的过程，先标记存活对象，然后将它们移动到一起，避免了内存碎片的问题。
- **分代收集算法（Generational Collection）：** 结合了新生代和老年代的特点，将堆划分为新生代、老年代，根据对象的生命周期选择不同的算法进行回收。

**新生代老年代区别？**

**- 老年代是由新生代经过多次Minor GC，从Eden Space进化到Survivor Space后熬过来到Tenured Gen(养老区)。老年代被Full GC回收，但是更不频繁且回收时间更长。这些在JVM的heap区；**

**而元空间(Perm Gen), JVM Stack, Local Method Statck又属于JVM的非heap区**

**refer:** [**https://www.cnblogs.com/snowwhite/p/9532311.html**](https://www.cnblogs.com/snowwhite/p/9532311.html)

![JAVA]\([https://img-blog.csdn.net/20170518171044703?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc3RlZF96eHo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast](https://img-blog.csdn.net/20170518171044703?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvc3RlZF96eHo=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast))

- **新生代**

新生代是 GC 收集垃圾的频繁区域。 当对象在 Eden 出生后，在经过一次 **Minor GC** 后，如果对象还存活，并且能够被另外一块 Survivor 区域所容纳，则使用复制算法将这些仍然还存活的对象复制到另外一块 Survivor 区域中，然后清理所使用过的 Eden 以及 Survivor 区域，并且将这些对象的年龄设置为1，以后对象在 Survivor 区每熬过一次 Minor GC，就将对象的年龄 + 1，当对象的年龄达到某个值时 ( 默认是 15 岁，可以通过参数 -XX\:MaxTenuringThreshold 来设定 )，这些对象就会成为老年代。 但这也不是一定的，对于一些较大的对象 ( 即需要分配一块较大的连续内存空间 ) 则是直接进入到老年代。

- **老年代**

年代里面的对象几乎个个都是在 Survivor 区域中熬过来的，它们是不会那么容易就 “死掉” 了的。因此，Full GC 发生的次数不会有 Minor GC 那么频繁，并且做一次 Full GC 要比进行一次 Minor GC 的时间更长。

常用的垃圾收集器有：

- **Serial Collector**：适用于单线程环境，采用单线程进行垃圾收集，暂停时间较长，适合单处理器机器。
- **Parallel Collector**：也称为吞吐量收集器，使用多线程进行垃圾收集，减少垃圾收集的总暂停时间，适合多处理器环境。
- **G1 Garbage Collector**：适合低延迟应用程序，将堆划分为多个区域（Region），通过并发和增量的方式减少垃圾收集的停顿时间。
- **Z Garbage Collector**：低延迟垃圾收集器，旨在保持非常低的暂停时间（通常不超过 10ms），支持非常大的堆，适用于需要高响应的应用程序。

##### 示例代码

```java
public class TestGC {
    public static void main(String[] args) {
        TestGC obj1 = new TestGC();
        TestGC obj2 = new TestGC();

        obj1 = null;
        obj2 = null;

        System.gc(); // 请求垃圾收集器进行回收
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object is garbage collected");
    }
}
```

在上述代码中，通过 `System.gc()` 方法显式地请求 JVM 进行垃圾回收，`finalize()` 方法在对象被回收之前调用。

### JVM 知识导图

可以按如下思路形成 JVM 的知识导图：

1. **类加载器（Class Loader）**

   - 加载、验证、准备、解析、初始化
   - Bootstrap ClassLoader、Extension ClassLoader、Application ClassLoader

2. **运行时数据区（Runtime Data Area）**

   - 方法区：类元数据、静态变量、常量池
   - 堆：对象实例和数组
   - Java 栈：局部变量、操作数栈
   - 本地方法栈：调用本地代码
   - 程序记数器：字节码指令地址

3. **执行引擎（Execution Engine）**

   - 解释器：按行解释执行
   - JIT 编译器：将字节码编译成机器码

4. **本地方法接口（Native Interface）**

   - JNI 调用本地代码

5. **垃圾收集器（Garbage Collector）**

   - 收集算法：标记-清除、复制、标记-压缩、分代收集
   - 收集器类型：Serial、Parallel、G1、Z

这张知识导图可以帮助你对 JVM 的各个组件及其相互关系进行全面理解。

##### StaticBindingExample.java 文件的编译和运行过程

```java
public class StaticBindingExample {
    static class Parent {
        static void display() {
            System.out.println("Parent display method");
        }
    }

    static class Child1 extends Parent {
        static void display() {
            System.out.println("Child display method");
        }
    }

    static class Child2 extends Parent {
        @Override
        void display() {
            System.out.println("Child display method");
        }
    }

    public static void main(String[] args) {
        Parent p1 = new Child1();
        p1.display();  // 输出 "Parent display method"
        Parent p2 = new Child2();
        p2.display();  // 输出 "Child display method"
    }
}
```

**编译过程**

在编译阶段，JVM 会经历类加载子系统的几个步骤来编译和加载 `.java` 文件：

1. **加载（Loading）**：`StaticBindingExample.java` 被编译成字节码文件 `StaticBindingExample.class`。编译器将类定义转换为 `.class` 文件，并且 `.class` 文件会被加载到 JVM 中。

2. **验证（Verification）**：编译器生成的 `.class` 文件在加载后，JVM 会对字节码进行验证，以确保 `.class` 文件的安全性和合法性。

3. **准备（Preparation）**：JVM 为所有类的静态字段分配内存并初始化为默认值。`Parent` 类和 `Child1` 类的静态方法 `display()` 会在这一步准备好。

4. **解析（Resolution）**：JVM 会将类、方法和字段的符号引用解析为直接引用。在这里，静态方法 `Parent.display()` 和 `Child1.display()` 都在解析阶段完成绑定。

5. **初始化（Initialization）**：静态方法和静态代码块会在这个阶段进行初始化。在 `StaticBindingExample` 中，静态方法属于类而非对象，因此不需要实例化类就可以调用它们。

**运行过程**

- **静态绑定（Static Binding）**

  - 在 `main()` 方法中，`Parent p1 = new Child1();` 语句创建了一个 `Child1` 类型的对象，但是引用变量的静态类型是 `Parent`。
  - `p1.display()` 调用的是 `Parent` 类中的静态方法，而不是 `Child1` 的方法。这是因为静态方法的调用在编译时已经确定，称为静态绑定。JVM 根据引用变量的类型 `Parent` 在编译时就绑定了 `Parent.display()`，所以输出的是 "Parent display method"。

- **动态绑定（Dynamic Binding）**

  - 对于 `Child2` 类，`display()` 方法是实例方法。实例方法可以被子类重写，这种方法的调用在运行时根据对象的实际类型进行动态绑定。
  - `Parent p2 = new Child2();` 语句创建了一个 `Child2` 对象，并用 `Parent` 类型的引用变量 `p2` 来引用它。
  - 在调用 `p2.display()` 时，JVM 根据 `p2` 的实际类型 `Child2` 来调用对应的 `display()` 方法。这就是动态绑定，因此输出的是 "Child display method"。

**结合 JVM 知识点**

- **类加载子系统**：负责将 `StaticBindingExample` 类及其内部类加载到 JVM 中，包括 `Parent`、`Child1` 和 `Child2`。
- **运行时数据区**：在堆中分配 `Child1` 和 `Child2` 对象，在方法区中存储静态方法 `display()`。
- **执行引擎**：执行引擎负责解释和执行字节码。对于静态方法，执行引擎在编译时绑定调用；对于实例方法，执行引擎在运行时根据对象的实际类型进行绑定，这就是多态的体现。
- **静态绑定与动态绑定**：静态绑定用于静态方法的调用，在编译时绑定；动态绑定用于实例方法的调用，在运行时根据对象的实际类型绑定。

