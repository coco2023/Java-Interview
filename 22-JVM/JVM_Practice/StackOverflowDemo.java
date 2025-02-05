/**
 异常信息：java.lang.StackOverflowError

 参数调整：-Xss256k（减小栈容量加速溢出）

 处理方法：检查递归终止条件或优化算法。
 
 */

public class StackOverflowDemo {
          public static void recursion() {
                    System.out.println(1);
                    recursion();
          }

          public static void main(String[] args) {
                    recursion(); // 无限递归导致栈溢出
          }
}
