/**
异常信息：java.lang.OutOfMemoryError: Direct buffer memory

参数调整：-XX:MaxDirectMemorySize=10m

处理方法：检查NIO代码或调整直接内存限制。

 */
// 代码示例：通过Unsafe分配直接内存
import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class DirectMemoryOOM {
          private static final int _1MB = 1024 * 1024;

          public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, ReflectiveOperationException, SecurityException {
                    Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
                    unsafeField.setAccessible(true);
                    Unsafe unsafe = (Unsafe) unsafeField.get(null);
                    while (true) {
                              unsafe.allocateMemory(_1MB); // 直接分配内存
                          }                  
          }
}
