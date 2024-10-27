import java.util.*;
// refer: https://chatgpt.com/c/52eb07ec-9e1b-422a-9609-387189945254
// refer: https://chatgpt.com/g/g-WKIaLGGem-tech-support-advisor/c/30e68c51-79e6-4620-9a32-e9520d673499
 
// 635. 设计日志存储系统
class LogSystem {
          TreeMap<String, List<Integer>> logs;

          public LogSystem() {
                    this.logs = new TreeMap<>();
          }

          public void put(int id, String timestamp) {
                    if (!logs.containsKey(timestamp)) {
                              logs.put(timestamp, new ArrayList<>()); 
                   }
                   logs.get(timestamp).add(id);
          }

          public List<Integer> retrieve(String start, String end, String granularity) {
                    int index = getIndex(granularity);
                    String startTime = start.substring(0, index);
                    String endTime = end.substring(0, index) + getMaxSuffix(granularity);

                    List<Integer> result = new ArrayList<>();
                    for (Map.Entry<String, List<Integer>> entry : logs.subMap(startTime, true, endTime, true).entrySet()) {
                                        result.addAll(entry.getValue());
                              }
                    return result;
          }

          private int getIndex(String granularity) {
                    if (granularity == "Year") return 4;
                    else if (granularity == "Month") return 7;
                    else if (granularity == "Day") return 10;
                    else if (granularity == "Hour") return 13;
                    else if (granularity == "Minute") return 16;
                    else if (granularity == "Second") return 19;
                    else return 19;
          }

          private String getMaxSuffix(String granularity) {
                    switch (granularity) {
                              case "Year": return "12-31:23:59:59";
                              case "Month": return "-31:23:59:59";
                              case "Day": return ":23:59:59";
                              case "Hour": return ":59:59";
                              case "Minute": return ":59";
                              case "Second": return "";
                              default: return "";
                    }
          }

          public static void main(String[] args) {
                    LogSystem logSystem = new LogSystem();
                    logSystem.put(1, "2016:01:01:00:00:00");
                    logSystem.put(2, "2016:05:01:00:00:00");
                    logSystem.put(3, "2017:01:01:23:00:00");
          
                    List<Integer> resultYear = logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year");
                    System.out.println("Logs retrieved for Year granularity: " + resultYear);
                
                    List<Integer> resultMonth = logSystem.retrieve("2016:05:01:00:00:00", "2016:06:01:00:00:00", "Month");
                    System.out.println("Logs retrieved for Month granularity: " + resultMonth);
                
                    List<Integer> resultDay = logSystem.retrieve("2016:05:15:00:00:00", "2016:05:16:00:00:00", "Day");
                    System.out.println("Logs retrieved for Day granularity: " + resultDay);          
          }         

}

// 981. 基于时间的键值存储
class TimeMap {
    private Map<String, TreeMap<Integer, String>> map; // key : <timestamp : value>

    public TimeMap() {
        this.map = new HashMap<>();
    }

    /** Stores the key-value pair along with the given timestamp. */
    public void set(String key, String value, int timestamp) {
          map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
      }

    /** Retrieves the value corresponding to the given key and timestamp. */
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) {
            return ""; // Return empty string if the key does not exist
        }

        Map.Entry<Integer, String> entry = treeMap.floorEntry(timestamp);
        if (entry == null) {
            return ""; // Return empty string if no such timestamp exists
        }

        return entry.getValue(); // Return the value associated with the found timestamp
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1)); // Outputs "bar"
        System.out.println(timeMap.get("foo", 3)); // Outputs "bar"

        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4)); // Outputs "bar2"
        System.out.println(timeMap.get("foo", 5)); // Outputs "bar2"
    }
}
      
// LCP 77. 符文储备

// LCR 031. LRU 缓存

// LCR 158. 库存管理 II
// LCR 159. 库存管理 III
// LCR 128. 库存管理 I

// LCR 057. 存在重复元素 III

// TreeMap
class TreeMapNode<K extends Comparable<K>, V> {
          K key;
          V value;
          TreeMapNode<K, V> left, right;

          public TreeMapNode(K key, V value) {
                    this.key = key;
                    this.value = value;
                    this.left = null;
                    this.right = null;
          }
}

class BasicTreeMap<K extends Comparable<K>, V> {
          private TreeMapNode<K, V> root;

          public BasicTreeMap() {
                    this.root = null;
          }

          public void put(K key, V value) {
                    root = insert(root, key, value);
          }

          private TreeMapNode<K, V> insert(TreeMapNode<K, V> node, K key, V value) {
                    if (node == null) return new TreeMapNode<>(key, value);

                    int cmp = key.compareTo(node.key);
                    if (cmp < 0) { // all node.left.value are < node.value
                              node.left = insert(node.left, key, value);
                    } else if (cmp > 0) { // all node.right.value are > node.right
                              node.right = insert(node.right, key, value);
                    } else {
                              node.value = value;
                    }

                    return node;
          }

          public V get(K key) {
                    return get(root, key);
          }

          private V get(TreeMapNode<K, V> node, K key) {
                    while (node != null) {
                              int cmp = key.compareTo(node.key);
                              if (cmp < 0) {
                                        node = node.left;
                              } else if (cmp > 0) {
                                        node = node.right;
                              } else {
                                        return node.value;
                              }
                    }
                    return null;
          }

          public void inoder (TreeMapNode<K, V> root) {
                    inorderRec(root);
                    // inorderIter(root);
          }

          private void inorderRec(TreeMapNode<K, V> root) {
                    if (root != null) {
                              inorderRec(root.left);
                              System.out.println(root.key + ":" + root.value);
                              inorderRec(root.right);
                    }
          }

          private void inorderIter(TreeMapNode<K, V> root) {

                    Stack<TreeMapNode<K, V>> stack = new Stack<TreeMapNode<K, V>>();
                    TreeMapNode<K, V> current = root;

                    while (!stack.isEmpty() || current != null) {
                              while (current != null) {
                                        stack.push(current);
                                        current = current.left;
                              }

                              current = stack.pop();
                              System.out.println("Key: " + current.key + ", Value: " + current.value);

                              current = current.right;
                    }
          }
}
