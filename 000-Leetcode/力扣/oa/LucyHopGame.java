import java.util.Scanner;

public class LucyHopGame {
          public static void main(String[] args) {
                    Scanner scanner = new Scanner(System.in);

                    // Input matrix dimensions
                    int n = scanner.nextInt();
                    int m = scanner.nextInt();

                    // Initialize the matrix
                    int[][] matrix = new int[n][m];
                    for (int i = 0; i < n; i++) {
                              for (int j = 0; j < m; j++) {
                                        matrix[i][j] = scanner.nextInt();
                              }
                    }

                    // Define direction vectors for anti-clockwise movement: right, down, left, up
                    int[] rowDir = { 0, 1, 0, -1 };
                    int[] colDir = { 1, 0, -1, 0 };

                    // Starting position and direction
                    int row = 0, col = 0, dir = 0;
                    boolean[][] visited = new boolean[n][m];
                    visited[row][col] = true;

                    int steps = 0; // Count the number of steps taken
                    int lastHoppedValue = matrix[row][col];

                    // Total cells to be visited
                    int totalCells = n * m;
                    int visitedCount = 1;

                    // Start the movement
                    while (visitedCount < totalCells) {
                              // Calculate the next position
                              int nextRow = row + rowDir[dir];
                              int nextCol = col + colDir[dir];

                              // Check if the next position is valid
                              if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                                                  && !visited[nextRow][nextCol]) {
                                        // Move to the next position
                                        row = nextRow;
                                        col = nextCol;
                                        visited[row][col] = true;
                                        visitedCount++;
                                        steps++;

                                        // Update the last hopped value if the step count is odd
                                        if (steps % 2 != 0) {
                                                  lastHoppedValue = matrix[row][col];
                                        }
                              } else {
                                        // Change direction (anti-clockwise)
                                        dir = (dir + 1) % 4;
                              }
                    }

                    // Output the last successful hop value
                    System.out.println(lastHoppedValue);

                    scanner.close();
          }
}
