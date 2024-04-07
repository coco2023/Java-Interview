package Lecture20;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	BST() {
		this.rootnode = null;
		this.size = 0;
	}

	public void add(int data) throws Exception {
		if (this.rootnode == null) {
			Node node = new Node(data, null, null);
			this.rootnode = node;
		} else {
			this.add(this.rootnode, data);
		}
	}

	public void add(int[] sa) {
		this.rootnode = constructNode(sa, 0, sa.length - 1);
	}

	private Node constructNode(int[] sa, int left, int right) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		Node node = new Node(sa[mid], null, null);
		node.left = constructNode(sa, left, mid - 1);
		node.right = constructNode(sa, mid + 1, right);
		return node;
	}

	public void printinRange(int k1, int k2)// k1<k2
	{
		printinRange(this.rootnode, k1, k2);
		System.out.println("END");
	}

	private void printinRange(Node node, int k1, int k2) {
		if (node == null) {
			return;
		}
		if (node.data >= k2) {
			if (node.data == k2) {
				System.out.print(node.data + ",");
			}
			printinRange(node.left, k1, k2);
		} else if (node.data <= k1) {
			if (node.data == k1) {
				System.out.print(node.data + ",");
			}
			printinRange(node.right, k1, k2);
		} else {
			System.out.print(node.data + ",");
			printinRange(node.left, k1, k2);
			printinRange(node.right, k1, k2);
		}
	}

	public void remove(int data) {
		this.rootnode = this.remove(this.rootnode, data);
	}

	private Node remove(Node node, int data) {
		if (node == null) {
			return null;
		}
		if (node.data > data) {
			node.left = this.remove(node.left, data);
			return node;
		} else if (node.data < data) {
			node.right = this.remove(node.right, data);
			return node;
		} else {
			if (node.left == null && node.right == null) {
				this.size--;
				return null;
			} else if (node.left == null) {
				this.size--;
				return node.right;
			} else if (node.right == null) {
				this.size--;
				return node.left;
			} else {
				int lmax = this.max(node.left);
				node.data = lmax;
				node.left = this.remove(node.left, lmax);
				return node;
			}
		}
	}

	private void add(Node node, int data) throws Exception {
		if (data < node.data) {
			if (node.left != null) {
				this.add(node.left, data);
			} else {
				node.left = new Node(data, null, null);
			}
		} else if (data > node.data) {
			if (node.right != null) {
				this.add(node.right, data);
			} else {
				node.right = new Node(data, null, null);
			}
		} else {
			throw new Exception("Node already exists");
		}
	}

	public void makeOneSided() {
		this.rootnode = this.makeOneSided(this.rootnode).head;
	}

	private MOSPair makeOneSided(Node node) {
		if (node == null) {
			return null;
		}
		MOSPair leftside = this.makeOneSided(node.left);
		MOSPair rightside = this.makeOneSided(node.right);
		MOSPair mypair = new MOSPair(node, node);
		if (leftside != null) {
			leftside.tail.right = node;
			mypair.head = leftside.head;
		}
		node.left = null;
		if (rightside != null) {
			node.right = rightside.head;
			mypair.tail = rightside.tail;
		}
		return mypair;
	}

	private class MOSPair {
		Node head;
		Node tail;

		public MOSPair(Node head, Node tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	private Node rootnode;
	private int size;

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public BST(Node node, int size) {
		this.rootnode = node;
		this.size = size;

	}

	public BST(int[] pre, int[] in) {
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
		int retval = node.data;
		if (node.left != null) {
			retval = this.min(node.left);
		}
		return retval;
	}

	public int max() {
		return this.max(this.rootnode);
	}

	private int max(Node node) {
		int retval = node.data;
		if (node.right != null) {
			retval = this.max(node.right);
		}
		return retval;
	}

	public boolean find(int data) {
		return this.find(this.rootnode, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}
		if (node.data == data) {
			return true;
		} else if (data > node.data) {
			return this.find(node.right, data);
		} else {
			return this.find(node.left, data);
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

	public int lca(int n1, int n2) {
		return lca(this.rootnode, n1, n2).data;
	}

	private Node lca(Node node, int n1, int n2) {
		if (node == null) {
			return null;
		}
		if (node.data > n1 && node.data > n2) {
			return this.lca(node.left, n1, n2);
		}
		if (node.data < n1 && node.data < n2) {
			return this.lca(node.right, n1, n2);
		}
		return node;
	}
}
