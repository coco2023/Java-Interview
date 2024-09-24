import java.util.ArrayList;
import java.util.List;

public class GoodIntervals {

          public static long countGoodIntervals(int numServers, int numDisconnectedPairs,
                              List<int[]> disconnectedPairs) {
                    // Initialize all connections as true (connected)
                    boolean[] isConnected = new boolean[numServers - 1];
                    for (int i = 0; i < numServers - 1; i++) {
                              isConnected[i] = true;
                    }

                    // Mark disconnected pairs
                    for (int[] pair : disconnectedPairs) {
                              int u = pair[0];
                              int v = pair[1];
                              if (v == u + 1) {
                                        isConnected[u - 1] = false;
                              }
                    }

                    long totalGoodIntervals = 0;
                    int i = 0;

                    // Count good intervals
                    while (i < numServers) {
                              long segmentLength = 1;
                              while (i < numServers - 1 && isConnected[i]) {
                                        segmentLength++;
                                        i++;
                              }
                              totalGoodIntervals += segmentLength * (segmentLength + 1) / 2;
                              i++;
                    }

                    return totalGoodIntervals;
          }

          public static void main(String[] args) {
                    int numServers = 4;
                    int numDisconnectedPairs = 2;
                    List<int[]> disconnectedPairs = new ArrayList<>();
                    disconnectedPairs.add(new int[] { 1, 2 });
                    disconnectedPairs.add(new int[] { 2, 3 });

                    long result = countGoodIntervals(numServers, numDisconnectedPairs, disconnectedPairs);
                    System.out.println(result);
          }
}
