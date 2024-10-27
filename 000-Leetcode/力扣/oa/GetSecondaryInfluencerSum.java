package oa;

import java.util.*;

public class GetSecondaryInfluencerSum {
          private List<List<Integer>> graph;
          private boolean[] isPrimary;
          private int maxDistance;

          public long getSecondaryInfluencerSum(int g_nodes, List<Integer> g_from, List<Integer> g_to) {
                    // Initialize graph and isPrimary array
                    graph = new ArrayList<>(g_nodes + 1);
                    for (int i = 0; i <= g_nodes; i++) {
                              graph.add(new ArrayList<>());
                    }
                    isPrimary = new boolean[g_nodes + 1];

                    // Build the graph
                    for (int i = 0; i < g_from.size(); i++) {
                              int from = g_from.get(i);
                              int to = g_to.get(i);
                              graph.get(from).add(to);
                              graph.get(to).add(from);
                    }

                    // Find max distance and mark primary influencers
                    maxDistance = 0;
                    for (int i = 1; i <= g_nodes; i++) {
                              dfs(i, new boolean[g_nodes + 1], 0, i);
                    }

                    // Calculate sum of secondary influencers
                    long sum = 0;
                    for (int i = 1; i <= g_nodes; i++) {
                              if (!isPrimary[i]) {
                                        sum += i;
                              }
                    }

                    return sum;
          }

          private void dfs(int node, boolean[] visited, int distance, int start) {
                    visited[node] = true;

                    if (distance > maxDistance) {
                              maxDistance = distance;
                              Arrays.fill(isPrimary, false);
                    }

                    if (distance == maxDistance) {
                              markPrimaryPath(start, node, new boolean[visited.length]);
                    }

                    for (int neighbor : graph.get(node)) {
                              if (!visited[neighbor]) {
                                        dfs(neighbor, visited, distance + 1, start);
                              }
                    }

                    visited[node] = false;
          }

          private boolean markPrimaryPath(int start, int end, boolean[] visited) {
                    if (start == end) {
                              isPrimary[start] = true;
                              return true;
                    }

                    visited[start] = true;

                    for (int neighbor : graph.get(start)) {
                              if (!visited[neighbor]) {
                                        if (markPrimaryPath(neighbor, end, visited)) {
                                                  isPrimary[start] = true;
                                                  return true;
                                        }
                              }
                    }

                    return false;
          }

          public static void main(String[] args) {
                    // Test case from the problem description
                    int g_nodes = 4;
                    List<Integer> g_from = Arrays.asList(1, 1, 2);
                    List<Integer> g_to = Arrays.asList(2, 3, 4);

                    GetSecondaryInfluencerSum solution = new GetSecondaryInfluencerSum();
                    long result = solution.getSecondaryInfluencerSum(g_nodes, g_from, g_to);

                    System.out.println("Sum of secondary influencers: " + result);
                    System.out.println("Expected result: 0");

                    // Additional test case
                    g_nodes = 5;
                    g_from = Arrays.asList(1, 1, 2, 2);
                    g_to = Arrays.asList(2, 3, 4, 5);

                    result = solution.getSecondaryInfluencerSum(g_nodes, g_from, g_to);

                    System.out.println("\nSum of secondary influencers: " + result);
                    System.out.println("Expected result: 3"); // Node 3 is the only secondary influencer
          }

}
