# refer
https://refactoring.guru/design-patterns/java

Design Pattern - Factory Pattern
https://www.tutorialspoint.com/design_pattern/factory_pattern.htm 


Design Pattern - Singleton Pattern


Design Patterns - Prototype Pattern
https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm


Chain of Responsibility Pattern
Design Patterns - Observer Pattern


Design Patterns - State Pattern

Design Patterns - MVC Pattern
https://github.com/iluwatar/java-design-patterns/tree/master/model-view-controller



Business Delegate Pattern


Data Access Object Pattern
https://github.com/iluwatar/java-design-patterns/tree/master/dao


# 单例模式 (Singleton Pattern)
### **单例模式与 Immutable Class 的区别**
[**单例模式与 Immutable Class 的区别**](https://github.com/coco2023/Java-Interview/tree/main/08-Core%20Java#%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F-singleton-pattern)


# 例题
Here’s a curated list of **Java design pattern coding problems** that resemble the type of questions asked in Amazon or similar technical interviews. Each problem focuses on implementing or understanding common design patterns in Java.

---

### **1. Singleton Pattern**
**Problem:**
Implement a thread-safe `Logger` class that uses the Singleton design pattern. Ensure that:
1. Only one instance of the `Logger` is created throughout the application.
2. Multiple threads can safely write logs.

**Expected Features:**
- A `log(String message)` method to append logs.
- A method `getLogs()` to retrieve all logged messages.

---

### **2. Factory Pattern**
**Problem:**
Create a `PaymentProcessorFactory` to instantiate different payment processors based on the type of payment:
- `CreditCardPaymentProcessor`
- `PayPalPaymentProcessor`
- `UPIPaymentProcessor`

**Requirements:**
- Input: Payment type as a string (e.g., "CreditCard", "PayPal", "UPI").
- Output: The corresponding payment processor object.

---

### **3. Builder Pattern**
**Problem:**
Design a `Computer` class using the Builder pattern. A `Computer` has:
- Mandatory attributes: `CPU`, `RAM`.
- Optional attributes: `GPU`, `Storage`, `CoolingSystem`.

**Requirements:**
- Use the Builder pattern to allow flexible object creation.
- Support the creation of minimal and fully configured `Computer` objects.

---

### **4. Observer Pattern**
**Problem:**
Implement a `WeatherStation` system using the Observer pattern. 
- Observers (`MobileApp`, `WebApp`) should get notified whenever the weather changes.

**Requirements:**
- The `WeatherStation` should notify all registered observers of temperature changes.
- Observers should independently display the updated temperature.

---

### **5. Strategy Pattern**
**Problem:**
Implement a discount system for an e-commerce platform using the Strategy pattern. 
- Discounts:
  - `FestivalDiscount` (e.g., 20% off)
  - `LoyaltyDiscount` (e.g., 15% off)
  - `NoDiscount`

**Requirements:**
- A `ShoppingCart` class should dynamically apply the discount strategy.
- Input: Total price and discount type.
- Output: Total price after applying the discount.

---

### **6. Decorator Pattern**
**Problem:**
Design a coffee ordering system where:
- Base coffee (`Espresso`, `Latte`) can have decorators like `Milk`, `Sugar`, and `WhippedCream`.

**Requirements:**
- Each decorator should add to the coffee's cost and description.
- Example: `Espresso with Milk and Sugar` costs $5.50.

---

### **7. Command Pattern**
**Problem:**
Implement a `RemoteControl` system to control multiple devices (`Light`, `Fan`) using the Command pattern. 
- Commands:
  - `TurnOnCommand`
  - `TurnOffCommand`

**Requirements:**
- The `RemoteControl` should execute commands dynamically for each device.
- Include an undo functionality.

---

### **8. Prototype Pattern**
**Problem:**
Design a system where objects like `Circle`, `Rectangle`, and `Square` can be cloned to create new objects. 
- Each shape should store its own properties like color and dimensions.

**Requirements:**
- Use the Prototype pattern to clone objects.
- Ensure that the cloned objects are independent of the original.

---

### **9. Chain of Responsibility Pattern**
**Problem:**
Create a request handling system for a support ticket system:
- Levels: `BasicSupport`, `TechnicalSupport`, `ManagerSupport`.
- Each level can handle specific types of requests; if it cannot, it forwards the request to the next level.

**Requirements:**
- Input: A support request with a type (e.g., "Technical").
- Output: The request gets processed by the correct handler.

---

### **10. Adapter Pattern**
**Problem:**
Implement an adapter to connect a legacy system (providing `getName()` and `getAge()`) with a new interface that requires `getFullName()` and `getAgeInYears()`.

**Requirements:**
- Use the Adapter pattern to bridge the two systems.
- Ensure seamless integration.

---

### **11. Mediator Pattern**
**Problem:**
Design a chatroom system where multiple users can communicate. 
- Users can send messages to the chatroom, which distributes them to all other users.

**Requirements:**
- Use the Mediator pattern for message communication.
- Add features to allow private messages between users.

---

### **12. Flyweight Pattern**
**Problem:**
Optimize a system to manage a large number of `Tree` objects in a forest:
- Each tree has a `type` (e.g., `Oak`, `Pine`), `height`, and `position`.

**Requirements:**
- Use the Flyweight pattern to share common attributes (`type`) while keeping unique attributes (`position`, `height`) separate.

---

### **13. Proxy Pattern**
**Problem:**
Create a `DatabaseAccessProxy` to control access to a database:
- Only authenticated users should be allowed to query the database.
- The proxy should log every query.

**Requirements:**
- Input: Username, password, and query.
- Output: Query result if authenticated; error otherwise.

---

### **14. State Pattern**
**Problem:**
Implement a `Document` class that can transition between states:
- `Draft`, `Moderation`, and `Published`.

**Requirements:**
- Each state should define behavior for `edit()`, `publish()`, and `revert()` methods.
- The document's behavior should change based on its state.

---

### **15. Template Method Pattern**
**Problem:**
Create a system for generating reports:
- Report types: `PDFReport`, `HTMLReport`.

**Requirements:**
- Use a `generateReport()` template method in the base class.
- Allow subclasses to customize specific steps (e.g., `formatContent()`).

---

### **16. Composite Pattern**
**Problem:**
Design a file system where:
- A `Folder` can contain files and other folders.
- A `File` stores its name and size.

**Requirements:**
- Support operations like adding/removing files/folders and calculating the total size of a folder.

---

### **17. Dependency Injection**
**Problem:**
Implement a `NotificationService` using Dependency Injection:
- Notification types: `EmailNotification`, `SMSNotification`.
- Use a constructor to inject the dependency.

**Requirements:**
- The service should send notifications dynamically based on the injected type.

---

### **18. Observer Pattern (Real-time Stock Updates)**
**Problem:**
Implement a stock market system where:
- `Stock` prices update frequently.
- `StockViewer` observes the updates and displays the latest prices.

---

These problems test your ability to design scalable, extensible, and maintainable systems using design patterns. If you'd like a detailed solution for any specific problem, let me know!









