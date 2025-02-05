/**
 异常信息：java.lang.OutOfMemoryError: Java heap space（JDK8后字符串存储在堆）

 参数调整：-Xmx5m（限制堆大小）

 java -Xmx5m StringInternOOM
 
*/
import java.util.*;

public class StringInternOOM {
          public static void main(String[] args) {
                    List<String> list = new ArrayList<>();
                    int i = 0;
                    while (true) {
                              list.add(String.valueOf(i++).intern()); // 强制入池

                    }
          }
}
