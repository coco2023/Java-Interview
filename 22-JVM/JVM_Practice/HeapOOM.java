/**
 java -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -Xlog:gc* HeapOOM
 
 */
import java.util.*;

public class HeapOOM {
          static class OOMObject {}
          public static void main(String[] args) {
          //     ArrayList<OOMObject> list = new ArrayList<>();
              List<byte[]> list2 = new ArrayList<>();

              while (true) {
          //         list.add(new OOMObject());
                    list2.add(new byte[1024 * 1024]); // 持续分配1MB数组

              }
          }
      }
