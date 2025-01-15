import java.util.*;

public class InventoryTracking {
          public static int[] solution(String[][] logs) {
                    Map<String, PriorityQueue<int[]>> inventory = new HashMap<>();
                    List<Integer> revenue = new ArrayList<>();

                    for (String[] log : logs) {
                              String type = log[0];
                              String itemName = log[1];

                              switch (type) {
                                        case "supply":
                                                  int supplyCount = Integer.parseInt(log[2]);
                                                  int supplyPrice = Integer.parseInt(log[3]);
                                                  inventory.putIfAbsent(itemName, new PriorityQueue<>(
                                                                      Comparator.comparingInt(a -> a[0])));
                                                  inventory.get(itemName).add(new int[] { supplyPrice, supplyCount });
                                                  break;

                                        case "sell":
                                                  int sellCount = Integer.parseInt(log[2]);
                                                  int sellRevenue = 0;

                                                  PriorityQueue<int[]> itemQueue = inventory.get(itemName);

                                                  while (sellCount > 0 && itemQueue != null && !itemQueue.isEmpty()) {
                                                            int[] batch = itemQueue.poll();
                                                            int price = batch[0];
                                                            int available = batch[1];

                                                            if (available > sellCount) {
                                                                      sellRevenue += sellCount * price;
                                                                      batch[1] -= sellCount;
                                                                      itemQueue.add(batch);
                                                                      sellCount = 0;
                                                            } else {
                                                                      sellRevenue += available * price;
                                                                      sellCount -= available;
                                                            }
                                                  }

                                                  revenue.add(sellRevenue);
                                                  break;

                                        case "return":
                                                  int returnCount = Integer.parseInt(log[2]);
                                                  int sellPrice = Integer.parseInt(log[3]);
                                                  int newPrice = Integer.parseInt(log[4]);

                                                  PriorityQueue<int[]> returnQueue = inventory.get(itemName);
                                                  if (returnQueue != null) {
                                                            for (int[] batch : returnQueue) {
                                                                      if (batch[0] == sellPrice) {
                                                                                batch[1] += returnCount;
                                                                                break;
                                                                      }
                                                            }
                                                  }
                                                  inventory.get(itemName).add(new int[] { newPrice, returnCount });
                                                  break;
                              }
                    }

                    // Convert List<Integer> to int[]
                    int[] result = new int[revenue.size()];
                    for (int i = 0; i < revenue.size(); i++) {
                              result[i] = revenue.get(i);
                    }

                    return result;
          }

          public static void main(String[] args) {
                    String[][] logs = {
                                        { "supply", "item1", "2", "100" },
                                        { "supply", "item2", "3", "60" },
                                        { "sell", "item1", "1" },
                                        { "sell", "item1", "1" },
                                        { "sell", "item2", "2" },
                                        { "return", "item2", "1", "60", "40" },
                                        { "sell", "item2", "1" },
                                        { "sell", "item2", "1" }
                    };

                    int[] result = solution(logs);
                    System.out.println(Arrays.toString(result)); // Expected output: [100, 100, 120, 40, 60]
          }
}
