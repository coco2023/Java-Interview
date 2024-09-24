import java.util.PriorityQueue;
import java.util.Collections;

public class TikTokShoppingCost {

          public static long calculateTikTokShoppingCost(int vouchersCount, int[] prices) {
                    // Priority queue with max heap behavior (reverse order)
                    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

                    // Add all prices to the priority queue
                    for (int price : prices) {
                              pq.add(price);
                    }

                    // Apply the vouchers
                    for (int i = 0; i < vouchersCount; ++i) {
                              if (pq.isEmpty())
                                        break;
                              int highestPrice = pq.poll();
                              pq.add(highestPrice / 2);
                    }

                    // Calculate the total cost
                    long totalCost = 0;
                    while (!pq.isEmpty()) {
                              totalCost += pq.poll();
                    }

                    return totalCost;
          }

          public static void main(String[] args) {
                    int vouchersCount = 3;
                    int[] prices = { 8, 2, 13 };

                    long result = calculateTikTokShoppingCost(vouchersCount, prices);
                    System.out.println(result);
          }
}
