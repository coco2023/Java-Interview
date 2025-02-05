/**
 参数设置：-Xmx20m -Xms20m -Xmn10m -XX:+PrintGCDetails
 java -Xmx20m -Xms20m -Xmn10m -Xlog:gc* EdenAllocation
 */
public class EdenAllocation  {
          public static void main(String[] args) {
                    byte[] allocation1 = new byte[2 * 1024 * 1024]; // 分配2MB
                    byte[] allocation2 = new byte[2 * 1024 * 1024]; // 再次分配2MB（触发Minor GC）
                    byte[] allocation3 = new byte[2 * 1024 * 1024];
                }            
}
