import java.util.*;

class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;

          TreeNode(int x) {
                    val = x;
                    left = null;
                    right = null;
          }
}

public class BinaryTreeDiameter {

          int maxDiameter;
          List<Integer> longestPathNodes; // List to store the nodes in the longest diameter
          List<List<Integer>> duplicateDiameterPaths; // Store all paths with the maximum diameter

          public BinaryTreeDiameter() {
                    maxDiameter = 1;
                    longestPathNodes = new ArrayList<>();
                    duplicateDiameterPaths = new ArrayList<>();
          }

          public int diameterOfBinaryTree(TreeNode root) {
                    List<Integer> currentPath = new ArrayList<>();
                    depth(root, currentPath);
                    return maxDiameter - 1;
          }

          // Helper function to calculate the depth and track the longest path
          public int depth(TreeNode node, List<Integer> currentPath) {
                    if (node == null) {
                              return 0;
                    }

                    // Add the current node to the path
                    currentPath.add(node.val);
                    System.out.println("value: " + node.val);
                    // Recursively calculate the depth of the left and right subtrees
                    List<Integer> leftPath = new ArrayList<>(currentPath);
                    List<Integer> rightPath = new ArrayList<>(currentPath);

                    int L = depth(node.left, leftPath);
                    int R = depth(node.right, rightPath);

                    int currentDiameter = L + R + 1;

                    // Update the maximum diameter and the path if we find a larger diameter
                    // maxDiameter = Math.max(maxDiameter, currentDiameter);
                    if (currentDiameter > maxDiameter) {
                              maxDiameter = currentDiameter;
                              System.out.println("currentPath: " + currentPath);
                              longestPathNodes = new ArrayList<>(currentPath);
                              duplicateDiameterPaths.clear(); // Clear previously found paths
                              duplicateDiameterPaths.add(new ArrayList<>(longestPathNodes));
                    } else if (currentDiameter == maxDiameter) {
                              // Add the duplicate path if it has the same diameter
                              List<Integer> newPath = new ArrayList<>(leftPath);
                              newPath.addAll(rightPath);
                              duplicateDiameterPaths.add(new ArrayList<>(newPath));
                    }

                    // Remove the current node from the path (backtrack)
                    currentPath.remove(currentPath.size() - 1);

                    return Math.max(L, R) + 1;
          }

          // Helper function to print the node values in the path
          public void printPath(List<Integer> path) {
                    for (Integer value : path) {
                              System.out.print(value + " ");
                    }
                    System.out.println();
          }

          // Main function to test the implementation
          public static void main(String[] args) {
                    BinaryTreeDiameter solution = new BinaryTreeDiameter();

                    // Test Case
                    TreeNode root = new TreeNode(1);
                    root.left = new TreeNode(2);
                    root.right = new TreeNode(3);
                    root.left.left = new TreeNode(4);
                    root.left.right = new TreeNode(5);
                    root.right.left = new TreeNode(6);
                    root.right.right = new TreeNode(7);

                    System.out.println("Diameter: " + solution.diameterOfBinaryTree(root)); // Expected output: 4

                    // Print the longest diameter path
                    System.out.print("Longest Diameter Path: ");
                    solution.printPath(solution.longestPathNodes);

                    // Print duplicate paths, if any
                    System.out.println("Duplicate Paths with the Same Diameter:");
                    for (List<Integer> path : solution.duplicateDiameterPaths) {
                              solution.printPath(path);
                    }
          }
}
