### 1. Binary Tree Inorder Traversal

**DFS (Recursive):**
```java
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
```

**DFS (Iterative):**
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
```

### 2. Binary Tree Preorder Traversal

**DFS (Recursive):**
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}
```

**DFS (Iterative):**
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }
}
```

### 3. Binary Tree Postorder Traversal

**DFS (Recursive):**
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }
}
```

**DFS (Iterative):**
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }

        return res;
    }
}
```

### 4. Binary Tree Level Order Traversal

**BFS:**
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }
}
```

### 5. Maximum Depth of Binary Tree

**DFS (Recursive):**
```java
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```

**DFS (Iterative):**
```java
import java.util.Stack;

public class Solution {
    private class NodeDepth {
        TreeNode node;
        int depth;
        NodeDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // using 2 stack
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stackDepth = new Stack<>();
        stack.push(root);
        stackDepth.push(1);
        int maxDepth = 0;       

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int depth = stackDepth.pop();

            if (node != null) {
                maxDepth = Math.max(maxDepth, depth);
                stack.push(stack.left);
                stackDepth.push(depth + 1);
                stack.push(stack.right);
                stackDepth.push(depth + 1);
            }
        }
        return maxDepth;
    }

    // using queue
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxDepth = 0;

        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            maxDepth++;
        }

        return maxDepth;
    }
}
```

### 6. Symmetric Tree

**DFS (Recursive):**
```java
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
```

**BFS (Iterative):**
```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
```

### 7. Path Sum

**DFS (Recursive):**
```java
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```

**DFS (Iterative):**
```java
import java.util.Stack;

public class Solution {
    private class NodeSum {
        TreeNode node;
        int sum;
        NodeSum(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<NodeSum> stack = new Stack<>();
        stack.push(new NodeSum(root, sum));

        while (!stack.isEmpty()) {
            NodeSum current = stack.pop();
            TreeNode node = current.node;
            int currentSum = current.sum;

            if (node.left == null && node.right == null && node.val == currentSum) return true;
            if (node.left != null) stack.push(new NodeSum(node.left, currentSum - node.val));
            if (node.right != null) stack.push(new NodeSum(node.right, currentSum - node.val));
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null || root.val > sum) return false;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sums = new Stack<>();
        stack.push(root);
        sums.push(sum - root.val);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currSum = sums.pop();

            if (currSum == 0 && node.left == null && node.right == null) return true;

            if (node.left != null) {
                stack.push(node.left);
                sum.push(currSum - node.left.val);
            }
            if (node.right != null) {
                stack.push(node.right);
                sum.push(currSum - node.right.val);
            }
        }

        return false;
    }
}
```

### 7. LeetCode 113: Path Sum II
#### dfs solution for 113
```java
public class Solution {
    public List<List<Integer>> pathSum||(TreeNode node, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, result, currentPath);
        return result;
    }

    private void dfs(TreeNode node, int sum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;

        currentPath.add(node.val);
        if (node.left == null && node.right == null && sum == 0) {
            result.add(currentPath);
        } else {
            dfs(node.left, sum - node.left.val, currentPath, result);
            dfs(node.right, sum - node.right.val, currentPath, result);
        }
        // Backtrack: remove the current node's value from the path
        currentPath.remove(currentPath.size() - 1);
    }
}
```
#### Iterative DFS Solution for Path Sum II 

```java

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Stack to hold nodes along with the current sum up to that node
        Stack<TreeNode> stack = new Stack<>();
        // Map to track the parent of each node to reconstruct paths
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // Map to store the current sum up to each node
        Map<TreeNode, Integer> sumMap = new HashMap<>();

        stack.push(root);
        parentMap.put(root, null);
        sumMap.put(root, root.val);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int currentSum = sumMap.get(node);

            // If it's a leaf node and the path sum matches the target sum
            if (node.left == null && node.right == null && currentSum == sum) {
                List<Integer> path = new ArrayList<>();
                TreeNode currentNode = node;
                while (currentNode != null) {
                    path.add(currentNode.val);
                    currentNode = parentMap.get(currentNode);
                }
                Collections.reverse(path);
                result.add(path);
            }

            // Process right child
            if (node.right != null) {
                stack.push(node.right);
                parentMap.put(node.right, node);
                sumMap.put(node.right, currentSum + node.right.val);
            }

            // Process left child
            if (node.left != null) {
                stack.push(node.left);
                parentMap.put(node.left, node);
                sumMap.put(node.left, currentSum + node.left.val);
            }
        }

        return result;
    }
}

```

### 8. Construct Binary Tree from Preorder and Inorder Traversal

**DFS (Recursive):**
```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    private int preorderIndex = 0;
    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = buildTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}
```

### 9. Lowest Common Ancestor of a Binary Search Tree

**DFS (Recursive):**
```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
```

**DFS (Iterative):**
```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
```

### 10. Serialize and Deserialize Binary Tree

**BFS (Serialization & Deserialization):**
```java
import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (!nodes[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.left);
            }
            i++;

            if (!nodes[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(nodes[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }
}
```
/**
** 20240524
**/
### 11. Diameter of Binary Tree  LC543
```java
public class Solution {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // Calculate the diameter passing through this node
        int diameter = leftDepth + rightDepth;

        // Update the maximum diameter found so far
        maxDiameter = Math.max(maxDiameter, diameter);

        // Return the depth of this node
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        Solution solution = new Solution();
        int diameter = solution.diameterOfBinaryTree(root);
        System.out.println("Diameter of the binary tree: " + diameter);  // Output should be 3
    }
}
```
### Problem 114: Flatten Binary Tree to Linked List
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int x) {val = x;}
}

public class Solution {

    private void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = null;
        TreeNode right = root.left;

        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }

        curr.right = right;
    }
}
```

### Problem 116: Populating Next Right Pointers in Each Node
```java
public class Solution {

    public Node connect(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (prev != null) prev.next = curr;
                prev = curr;

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return root;
    }

    private static void printNextPointers(Node root) {
        Node levelStart = root;

        while (levelStart != null) {
            Node curr = levelStart;
            while (curr != null) {
                System.out.print(curr.val + "->");
                curr = curr.next;
            }
        }
        System.out.print("null");
        levelStart = levelStart.left;
    }
}
```

### Problem 226: Invert Binary Tree
```java
public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
```

### Problem: 剑指 Offer 07. 重建二叉树
```java
public class Solution {
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int leftSize = inorderMap.get(rootVal) - inStart;

        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSize, inStart, inorderMap.get(rootVal) - 1);
        root.right = buildTreeHelper(preorder, preStart + leftSize + 1, preEnd, inorderMap.get(rootVal) + 1, inEnd);

        return root;
    }
}
```

### Problem: 654. Maximum Binary Tree
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int maxIndex = findMaxIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIndex]);

        root.left = buildTree(nums, left, maxIndex - 1);
        root.right = buildTree(nums, maxIndex + 1, right);

        return root;
    }

    private int findMaxIndex(int[] nums, int start, int end) {
        int maxIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        // Example usage:
        int[] nums = {3, 2, 1, 6, 0, 5};

        Solution solution = new Solution();
        TreeNode root = solution.constructMaximumBinaryTree(nums);

        // Output the constructed tree (inorder traversal)
        printInOrder(root);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 652. Find Duplicate Subtrees
```java
import java.util.*;

public class Solution {
    private Map<String, Integer> subtreeMap;
    private List<TreeNode> duplicates;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        subtreeMap = new HashMap<>();
        duplicates = new ArrayList<>();
        serializeTree(root);
        return duplicates;
    }

    private String serializeTree(TreeNode node) {
        if (node == null) {
            return "#"; // A marker for null nodes
        }

        String serialized = node.val + "," + serializeTree(node.left) + "," + serializeTree(node.right);

        subtreeMap.put(serialized, subtreeMap.getOrDefault(serialized, 0) + 1);

        if (subtreeMap.get(serialized) == 2) {
            duplicates.add(node);
        }

        return serialized;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);

        Solution solution = new Solution();
        List<TreeNode> result = solution.findDuplicateSubtrees(root);

        // Output the duplicate subtrees' root values
        for (TreeNode node : result) {
            System.out.println(node.val);
        }
    }
}
```

### 1038. Binary Search Tree to Greater Sum Tree
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            sum += root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        Solution solution = new Solution();
        TreeNode newRoot = solution.bstToGst(root);

        // Output the new tree values (in-order)
        printInOrder(newRoot);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 230. Kth Smallest Element in a BST
```java
public class Solution {
    private int count = 0;
    private int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inOrder(node.left, k);
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        inOrder(node.right, k);
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        Solution solution = new Solution();
        int k = 1;  // Example: finding the 1st smallest element
        int kthSmallest = solution.kthSmallest(root, k);
        System.out.println("Kth smallest element is: " + kthSmallest);  // Output should be 1
    }
}
```

### 538. Convert BST to Greater Tree
```java
public class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        Solution solution = new Solution();
        TreeNode newRoot = solution.convertBST(root);

        // Output the new tree values (in-order)
        printInOrder(newRoot);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### LCR 054. 把二叉搜索树转换为累加树
```java
public class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(8);

        Solution solution = new Solution();
        TreeNode newRoot = solution.convertBST(root);

        // Output the new tree values (in-order)
        printInOrder(newRoot);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 450. Delete Node in a BST
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node to be deleted found
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // Node with two children
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        root = solution.deleteNode(root, 3);

        // Output the new tree values (in-order)
        printInOrder(root);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 700. Search in a Binary Search Tree
```java
public class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Solution solution = new Solution();
        TreeNode result = solution.searchBST(root, 2);

        // Output the result
        printInOrder(result);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 701. Insert into a Binary Search Tree
```java
public class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        Solution solution = new Solution();
        root = solution.insertIntoBST(root, 5);

        // Output the new tree values (in-order)
        printInOrder(root);
    }

    private static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
}
```

### 98. Validate Binary Search Tree
```java
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        if (!isValidBST(node.right, val, upper)) {
            return false;
        }
        if (!isValidBST(node.left, lower, val)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        Solution solution = new Solution();
        boolean isValid = solution.isValidBST(root);
        System.out.println("Is valid BST: " + isValid);  // Output should be true
    }
}
```

### 95. Unique Binary Search Trees II
```java
public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;  // Example input
        int result = solution.numTrees(n);
        System.out.println("Number of unique BSTs with " + n + " nodes: " + result);  // Output should be 5
    }
}
```

### 96. Unique Binary Search Trees
```java
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = left;
                    currentNode.right = right;
                    allTrees.add(currentNode);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;  // Example input
        List<TreeNode> result = solution.generateTrees(n);
        System.out.println("Unique BSTs with " + n + " nodes: " + result.size());
        
        // Print all unique BSTs
        for (TreeNode root : result) {
            printTree(root);
            System.out.println();
        }
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}
```

### 1644. Lowest Common Ancestor of a Binary Tree II
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    private boolean foundP = false;
    private boolean foundQ = false;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = findLCA(root, p, q);
        if (foundP && foundQ) {
            return lca;
        }
        return null;
    }

    private TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (root == p) {
            foundP = true;
            return root;
        }

        if (root == q) {
            foundQ = true;
            return root;
        }

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        Solution solution = new Solution();
        TreeNode p = root.left; // Node 5
        TreeNode q = root.right; // Node 1

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));
    }
}
```

### 1650. Lowest Common Ancestor of a Binary Tree III
```java
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

public class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = (a == null) ? q : a.parent;
            b = (b == null) ? p : b.parent;
        }
        return a;
    }

    public static void main(String[] args) {
        // Example usage:
        Node root = new Node();
        root.val = 3;
        Node node5 = new Node();
        node5.val = 5;
        Node node1 = new Node();
        node1.val = 1;
        root.left = node5;
        root.right = node1;
        node5.parent = root;
        node1.parent = root;

        Solution solution = new Solution();
        Node lca = solution.lowestCommonAncestor(node5, node1);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));
    }
}
```

### 1676. Lowest Common Ancestor of a Binary Tree IV
```java
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> nodeSet = new HashSet<>();
        for (TreeNode node : nodes) {
            nodeSet.add(node);
        }
        return findLCA(root, nodeSet);
    }

    private TreeNode findLCA(TreeNode root, Set<TreeNode> nodeSet) {
        if (root == null || nodeSet.contains(root)) {
            return root;
        }

        TreeNode left = findLCA(root.left, nodeSet);
        TreeNode right = findLCA(root.right, nodeSet);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode[] nodes = {root.left, root.right}; // Nodes 5 and 1

        Solution solution = new Solution();
        TreeNode lca = solution.lowestCommonAncestor(root, nodes);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));
    }
}
```

### 235. Lowest Common Ancestor of a Binary Search Tree
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        Solution solution = new Solution();
        TreeNode p = root.left;  // Node 2
        TreeNode q = root.left.right;  // Node 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));  // Output should be 2
    }
}
```

### 236. Lowest Common Ancestor of a Binary Tree
```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution solution = new Solution();
        TreeNode p = root.left;  // Node 5
        TreeNode q = root.left.right.right;  // Node 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));  // Output should be 5
    }
}
```

### LCR 193. 二叉搜索树的最近公共祖先
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        Solution solution = new Solution();
        TreeNode p = root.left;  // Node 2
        TreeNode q = root.left.right;  // Node 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));  // Output should be 2
    }
}
```
### LCR 194. 二叉树的最近公共祖先
```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution solution = new Solution();
        TreeNode p = root.left;  // Node 5
        TreeNode q = root.left.right.right;  // Node 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null"));  // Output should be 5
    }
}
```
###  
```java

```

### 
```java

```




































