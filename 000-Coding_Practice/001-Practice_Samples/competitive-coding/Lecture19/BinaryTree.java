package Lecture19;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public Node() {
			this(0, null, null);
		}
	}

	private Node rootnode;
	private int size;

	public BinaryTree(int root, BinaryTree LeftSt, BinaryTree RightSt) {
		Node node = new Node(root, null, null);
		this.size += LeftSt != null ? LeftSt.size() : 0;
		this.size += RightSt != null ? RightSt.size() : 0;
		this.size++;
		node.left = LeftSt != null ? LeftSt.rootnode : null;
		node.right = RightSt != null ? RightSt.rootnode : null;
		this.rootnode = node;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	BinaryTree() {
		Scanner scn = new Scanner(System.in);
		this.rootnode = this.takeInput(scn, null, false);
	}

	private Node takeInput(Scanner scn, Node parent, boolean LeftorRight) {
		if (parent == null) {
			System.out.println("Enter data for root node:");
		} else {
			if (LeftorRight) {
				System.out.println("Enter data for left node:");
			} else {
				System.out.println("Enter data for right node:");
			}
		}
		int childData = scn.nextInt();
		Node child = new Node(childData, null, null);
		this.size++;
		System.out.println("Does this node have a left child?:");
		boolean choiceLeft = scn.nextBoolean();
		if (choiceLeft) {
			child.left = this.takeInput(scn, child, true);
		}
		System.out.println("Does this node have a left child?:");
		boolean choiceRight = scn.nextBoolean();
		if (choiceRight) {
			child.right = this.takeInput(scn, child, false);
		}
		return child;
	}

	public BinaryTree(int[] pre, int[] in) {
		this.rootnode = this.construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private Node construct(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
		if (psi > pei || isi > iei) {
			return null;
		}

		Node node = new Node(pre[psi], null, null);
		this.size++;
		int si = -1;
		for (int i = isi; i <= iei; i++) {
			if (in[i] == pre[psi]) {
				si = i;
				break;
			}
		}
		int lsi = si - isi;
		node.left = this.construct(pre, psi + 1, psi + lsi, in, isi, si - 1);
		node.right = this.construct(pre, psi + lsi + 1, pei, in, si + 1, iei);
		return node;
	}

	@Override
	public String toString() {
		return this.toString(this.rootnode);
	}

	private String toString(Node node) {
		if (node == null) {
			return "";
		}
		String retVal = "";
		if (node.left != null) {
			retVal += node.left.data + "";
		} else {
			retVal += "END";
		}
		retVal += " => " + node.data + " <= ";
		if (node.right != null) {
			retVal += node.right.data + "";
		} else {
			retVal += "END";
		}
		retVal += "\n";
		retVal += this.toString(node.left);
		retVal += this.toString(node.right);
		return retVal;
	}

	public void display() {
		System.out.println(this);
	}

	public int size2() {
		return this.size2(this.rootnode);
	}

	private int size2(Node node) {
		if (node == null) {
			return 0;
		}
		int retVal = 0;
		retVal += size2(node.left);
		retVal += size2(node.right);
		retVal++;
		return retVal;
	}

	public int min() {
		return this.min(this.rootnode);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}
		int retval = node.data;
		int leftmin = min(node.left);
		if (leftmin < retval) {
			retval = leftmin;
		}
		int rightmin = min(node.right);
		if (rightmin < retval) {
			retval = rightmin;
		}
		return retval;
	}

	public int max() {
		return this.max(this.rootnode);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		int retval = node.data;
		int leftmax = max(node.left);
		if (leftmax > retval) {
			retval = leftmax;
		}
		int rightmax = max(node.right);
		if (rightmax > retval) {
			retval = rightmax;
		}
		return retval;
	}

	public boolean find(int data) {
		return this.find(this.rootnode, data, false);
	}

	private boolean find(Node node, int data, boolean flag) {

		if (node == null) {
			return flag;
		}
		if (flag == false) {
			if (node.left == null && node.right == null) {
				if (node.data == data) {
					flag = true;
				} else {
					flag = false;
				}
				return flag;
			} else {
				flag = find(node.left, data, flag);
				flag = find(node.right, data, flag);
			}
			return flag;
		} else {
			return flag;
		}

	}

	public int height() {
		return this.height(this.rootnode);
	}

	private int height(Node node) {
		int retval = 0;
		if (node == null) {
			return -1;
		}

		int leftheight = height(node.left);
		int rightheight = height(node.right);
		if (leftheight > rightheight) {
			retval += leftheight;
		} else {
			retval += rightheight;
		}
		return retval + 1;

	}

	public int diameter() {
		return this.diameterbtr(this.rootnode).diameter;
	}

	@SuppressWarnings("unused")
	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}
		int left = diameter(node.left);
		int right = diameter(node.right);
		int factor = this.height(node.left) + this.height(node.right) + 2;
		int x1 = Math.max(left, right);
		int x2 = Math.max(x1, factor);
		return x2;

	}

	private class diheightpair {
		int height;
		int diameter;

		diheightpair(int height, int diameter) {
			this.height = height;
			this.diameter = diameter;
		}
	}

	public diheightpair diameterbtr(Node node) {
		if (node == null) {
			return new diheightpair(-1, 0);
		}
		diheightpair lp = diameterbtr(node.left);
		diheightpair rp = diameterbtr(node.right);
		int myheight = Math.max(lp.height, rp.height) + 1;
		int myDiameter = Math.max(lp.diameter, Math.max(rp.diameter, lp.height + rp.height + 2));
		return new diheightpair(myheight, myDiameter);
	}

	public void mirror() {
		mirror(this.rootnode);
	}

	private void mirror(Node node) {
		if (node == null) {
			return;
		}
		this.mirror(node.left);
		this.mirror(node.right);
		Node data = node.left;
		node.left = node.right;
		node.right = data;
	}

	public void preOrder() {
		this.preOrder(this.rootnode);
		System.out.println("END");
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + ", ");
		this.preOrder(node.left);
		this.preOrder(node.right);
	}

	public void postOrder() {
		this.postOrder(this.rootnode);
		System.out.println("END");
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + ", ");
	}

	public void inOrder() {
		this.inOrder(this.rootnode);
		System.out.println("END");
	}

	private LinkedList<LinkedList<Integer>> list = new LinkedList<>();

	public LinkedList<LinkedList<Integer>> LevelList() {
		this.LevelList(this.rootnode, list);
		return this.list;
	}

	private void LevelList(Node node, LinkedList<LinkedList<Integer>> list) {
		if (node == null) {
			return;
		}
		if (node == this.rootnode) {
			LinkedList<Integer> temp = new LinkedList<>();
			temp.add(node.data);
			list.add(temp);
		} else {
			LinkedList<Integer> temp = new LinkedList<>();
			this.LevelChecker(node, temp);
			list.add(temp);
			temp = new LinkedList<>();
			this.LevelChecker(node.left, temp);
			this.LevelChecker(node.right, temp);
			list.add(temp);
			this.LevelList(node.left, list);
			this.LevelList(node.right, list);
		}
	}

	private void LevelChecker(Node node, LinkedList<Integer> temp) {
		if (node.left != null) {
			temp.add(node.left.data);
		}
		if (node.right != null) {
			temp.add(node.right.data);
		}
		list.add(temp);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		System.out.print(node.data + ", ");
		inOrder(node.right);
	}

	public void preOrderIterative() {
		Stack<Node> stack = new Stack<>();
		stack.push(this.rootnode);
		while (!stack.isEmpty()) {
			Node temp = stack.pop();
			System.out.print(temp.data + ", ");
			if (temp.right != null) {
				stack.push(temp.right);
			}
			if (temp.left != null) {
				stack.push(temp.left);
			}
		}
		System.out.println("END");
	}

	public void levelOrder() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(this.rootnode);
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			System.out.print(temp.data + ", ");
			if (temp.left != null) {
				queue.offer(temp.left);
			}
			if (temp.right != null) {
				queue.offer(temp.right);
			}
		}
		System.out.println("END");
	}

	private class SPPair {
		Node check;
		Node successor;
		Node predecessor;

	}

	public void inOrderSuccessor(int data) {
		SPPair pair = new SPPair();
		inOrderSuccessor(this.rootnode, data, pair);
		System.out.println(pair.successor.data);
	}

	private void inOrderSuccessor(Node node, int data, SPPair pair) {
		if (node == null) {
			return;
		}
		inOrderSuccessor(node.left, data, pair);
		if (pair.check == null) {
			if (node.data == data) {
				pair.check = node;
			}

		} else {
			if (pair.successor == null) {
				pair.successor = node;
			}
		}
		inOrderSuccessor(node.right, data, pair);
	}

	public void inOrderpredecessor(int data) {
		SPPair pair = new SPPair();
		inOrderpredecessor(this.rootnode, data, pair);
		if (pair.check != null) {
			if (pair.predecessor != null) {
				System.out.println(pair.predecessor.data);
			} else {
				System.out.println("null");
			}
		} else {
			System.out.println("NOT FOUND");
		}
	}

	private void inOrderpredecessor(Node node, int data, SPPair pair) {
		if (node == null) {
			return;
		}
		if (pair.check != null) {
			return;
		}

		inOrderpredecessor(node.left, data, pair);
		if (pair.check == null) {
			if (node.data == data) {
				pair.check = node;
			} else {
				pair.predecessor = node;
			}
		}
		inOrderpredecessor(node.right, data, pair);
	}

	public boolean isBST() {
		return isBST(this.rootnode);
	}

	private boolean isBST(Node node) {
		if (node == null) {
			return true;
		}
		int lmax = this.max(node.left);
		int rmin = this.min(node.right);
		if (lmax > node.data || rmin < node.data) {
			return false;
		}
		boolean isLeftBST = this.isBST(node.left);
		boolean isRightBST = this.isBST(node.right);
		return isLeftBST && isRightBST;
	}

	private class triplet {
		public int max;
		public int min;
		public boolean bst;

		triplet(int max, int min, boolean bst) {
			this.max = max;
			this.min = min;
			this.bst = bst;
		}
	}

	public boolean isBSTbtr() {
		return isBSTbtr(this.rootnode).bst;
	}

	private triplet isBSTbtr(Node node) {
		if (node == null) {
			return null;
		}

		// if (node == null) {
		// return new triplet(Integer.MIN_VALUE, Integer.MAX_VALUE, true);
		// remove all null checks
		// }

		triplet lt = this.isBSTbtr(node.left);
		triplet rt = this.isBSTbtr(node.right);
		triplet mt = new triplet(node.data, node.data, true);
		if (lt != null && (!lt.bst || lt.max > node.data)) {
			mt.bst = false;
		}
		if (rt != null && (!rt.bst || rt.min < node.data)) {
			mt.bst = false;
		}
		if (mt.bst == true) {
			if (lt != null) {
				mt.min = lt.min;
			}
			if (rt != null) {
				mt.max = rt.max;
			}
		}
		return mt;
	}

	public boolean isBST3() {
		return this.isBST3(this.rootnode, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public void duplicatenodes() {
		duplicatenodes(this.rootnode);
	}

	private void duplicatenodes(Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null) {
			Node temp = new Node(node.data, null, null);
			node.left = temp;
			return;
		} else {
			Node temp = new Node();
			temp.data = node.data;
			temp.left = node.left;
			node.left = temp;
			this.duplicatenodes(node.left.left);
			this.duplicatenodes(node.right);
		}
	}

	public boolean checkPair(int value) {
		return this.checkPair(this.rootnode, false, value);
	}

	private boolean checkPair(Node node, boolean flag, int value) {
		if (flag == true) {
			return true;
		}
		if (node == null) {
			return flag;
		}
		if (node.data < value) {
			flag = this.find(value - node.data);
			if (flag == true) {
				return flag;
			} else {
				flag = checkPair(node.left, flag, value);
				flag = checkPair(node.right, flag, value);
				return flag;
			}
		} else {
			flag = checkPair(node.left, flag, value);
			flag = checkPair(node.right, flag, value);
			return flag;
		}
	}

	public void printnosiblingnodes() {
		this.printnosiblingnodes(this.rootnode);
	}

	private void printnosiblingnodes(Node node) {
		if (node == null) {
			return;
		}
		if (node.left != null && node.right == null) {
			System.out.println(node.left.data);
			this.printnosiblingnodes(node.left);
		}
		if (node.right != null && node.left == null) {
			System.out.println(node.right.data);
			this.printnosiblingnodes(node.right);
		}
		this.printnosiblingnodes(node.left);
		this.printnosiblingnodes(node.right);

	}

	public void printBinaryTree() {
		this.printBinaryTree(this.rootnode, 0);
	}

	private void printBinaryTree(Node node, int level) {
		if (node == null) {
			return;
		}
		printBinaryTree(node.right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++) {
				System.out.print("|\t");
			}
			System.out.println("|----->" + node.data + "");
		} else {
			System.out.println(node.data);
		}
		printBinaryTree(node.left, level + 1);
	}

	private boolean isBST3(Node node, int min, int max) {
		if (node == null) {
			return true;
		}
		if (node.data > max || node.data < min) {
			return false;
		}
		boolean rightBST = this.isBST3(node.right, node.data, max);
		boolean leftBST = this.isBST3(node.left, min, node.data);
		return rightBST && leftBST;
	}

	public boolean isBalanced() {
		return this.isBalanced(this.rootnode);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}
		boolean lb = this.isBalanced(node.left);
		boolean rb = this.isBalanced(node.right);
		int lh = this.height(node.left);
		int rh = this.height(node.right);
		if (lb && rb && Math.abs(lh - rh) <= 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBalancedbtr() {
		return this.isBalancedbtr(this.rootnode).isbalanced;
	}

	private Pair isBalancedbtr(Node node) {
		if (node == null) {
			return new Pair(-1, true);
		}
		Pair lp = this.isBalancedbtr(node.left);
		Pair rp = this.isBalancedbtr(node.right);
		Pair mp = new Pair(0, false);
		if (lp.isbalanced && rp.isbalanced && Math.abs(lp.height - rp.height) <= 1) {
			mp.isbalanced = true;
		}
		mp.height = Math.max(lp.height, rp.height) + 1;

		return mp;
	}

	private class Pair {
		private int height;
		private boolean isbalanced;

		public Pair(int height, boolean isbalanced) {
			this.height = height;
			this.isbalanced = isbalanced;
		}
	}

	public void traversalforHC(HashMap<Character, String> encoder, HashMap<String, Character> decoder) {
		this.traversalforHC(this.rootnode, encoder, decoder, "");
	}

	private void traversalforHC(Node node, HashMap<Character, String> encoder, HashMap<String, Character> decoder,
			String osf) {
		if (node == null) {
			return;
		}
		this.traversalforHC(node.left, encoder, decoder, osf + "0");
		if (node.left == null && node.right == null) {
			encoder.put((char) node.data, osf);
			decoder.put(osf, (char) node.data);
		}
		this.traversalforHC(node.right, encoder, decoder, osf + "1");

	}
}
