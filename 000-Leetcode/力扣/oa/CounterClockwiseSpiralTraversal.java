import java.util.ArrayList;
import java.util.List;

public class CounterClockwiseSpiralTraversal {
          public List<Integer> spiralOrderCounterClockwise(int[][] matrix) {
                    List<Integer> result = new ArrayList<>();
                    if (matrix == null || matrix.length == 0)
                              return result;

                    int n = matrix.length; // Number of rows
                    int m = matrix[0].length; // Number of columns

                    int top = 0; // Top boundary
                    int bottom = n - 1; // Bottom boundary
                    int left = 0; // Left boundary
                    int right = m - 1; // Right boundary

                    while (top <= bottom && left <= right) {
                              // 1. Traverse from Top to Bottom along the Leftmost Column
                              for (int i = top; i <= bottom; i++) {
                                        result.add(matrix[i][left]);
                              }

                              // 2. Traverse from Left to Right along the Bottom Row
                              if (left + 1 <= right) { // Ensure there are at least two columns
                                        for (int j = left + 1; j <= right; j++) {
                                                  result.add(matrix[bottom][j]);
                                        }
                              }

                              // 3. Traverse from Bottom to Top along the Rightmost Column
                              if (left != right && top < bottom) { // Ensure there are at least two columns and two rows
                                        for (int i = bottom - 1; i >= top; i--) {
                                                  result.add(matrix[i][right]);
                                        }
                              }

                              // 4. Traverse from Right to Left along the Top Row
                              if (top != bottom && left < right) { // Ensure there are at least two rows and two columns
                                        for (int j = right - 1; j > left; j--) {
                                                  result.add(matrix[top][j]);
                                        }
                              }

                              // Move the boundaries inward for the next layer
                              top++;
                              bottom--;
                              left++;
                              right--;
                    }

                    if (n * m % 2 != 0) {
                              System.out.println("***" + result.get(n * m - 1));
                    } else {
                              System.out.println("&&" + result.get(n * m - 2));
                    }
                    return result;
          }

          // Helper method to print the list
          public void printList(List<Integer> list) {
                    System.out.println(list);
          }

          // Main method for testing
          public static void main(String[] args) {
                    CounterClockwiseSpiralTraversal solution = new CounterClockwiseSpiralTraversal();

                    // Example 1
                    int[][] matrix1 = {
                                        { 1, 2, 3, 4 },
                                        { 5, 6, 7, 8 },
                                        { 9, 10, 11, 12 },
                                        { 13, 14, 15, 16 }
                    };
                    List<Integer> result1 = solution.spiralOrderCounterClockwise(matrix1);
                    System.out.println("Example 1 Output:");
                    solution.printList(result1); // Expected: [1, 5, 9, 13, 14, 15, 16, 12, 8, 4, 3, 2, 6, 10, 11, 7]

                    // Example 2
                    int[][] matrix2 = {
                                        { 1, 2, 3 },
                                        { 4, 5, 6 },
                                        { 7, 8, 9 }
                                        // { 10, 11, 12 }
                    };
                    List<Integer> result2 = solution.spiralOrderCounterClockwise(matrix2);
                    System.out.println("Example 2 Output:");
                    solution.printList(result2); // Expected: [1, 4, 7, 10, 11, 12, 9, 6, 3, 2, 5, 8]

                    // Additional Test Cases

                    // Single Row
                    int[][] matrix3 = {
                                        { 1, 2, 3, 4, 5 }
                    };
                    List<Integer> result3 = solution.spiralOrderCounterClockwise(matrix3);
                    System.out.println("Test Case 3 Output (Single Row):");
                    solution.printList(result3); // Expected: [1, 2, 3, 4, 5]

                    // Single Column
                    int[][] matrix4 = {
                                        { 1 },
                                        { 2 },
                                        { 3 },
                                        { 4 },
                                        { 5 }
                    };
                    List<Integer> result4 = solution.spiralOrderCounterClockwise(matrix4);
                    System.out.println("Test Case 4 Output (Single Column):");
                    solution.printList(result4); // Expected: [1, 2, 3, 4, 5]

                    // Empty Matrix
                    int[][] matrix5 = {};
                    List<Integer> result5 = solution.spiralOrderCounterClockwise(matrix5);
                    System.out.println("Test Case 5 Output (Empty Matrix):");
                    solution.printList(result5); // Expected: []

                    // Non-Rectangular Matrix
                    int[][] matrix6 = {
                                        { 1, 2, 3, 4 },
                                        { 5, 6, 7, 8 },
                                        { 9, 10, 11, 12 }
                    };
                    List<Integer> result6 = solution.spiralOrderCounterClockwise(matrix6);
                    System.out.println("Test Case 6 Output (Non-Rectangular Matrix):");
                    solution.printList(result6); // Expected: [1, 5, 9, 10, 11, 12, 8, 4, 3, 2, 6, 7]
          }
}
