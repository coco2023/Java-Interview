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