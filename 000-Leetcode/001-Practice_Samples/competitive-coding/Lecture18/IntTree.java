package Lecture18;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class IntTree {
	private class Node {
		int data;
		ArrayList<Node> children;

		Node(int data, ArrayList<Node> children) {
			this.data = data;
			this.children = children;
		}
	}

	private int size;
	private Node rootnode;

	public IntTree() {
		Scanner scn = new Scanner(System.in);
		this.rootnode = this.takeInput(scn, null, -1);
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	private Node takeInput(Scanner scn, Node parent, int ithitem) {
		if (parent == null) {
			System.out.print("Enter data for root node:");
		} else {
			System.out.println("Enter data for Child:");
		}
		int childData = scn.nextInt();
		System.out.println("Enter the number of children for this node:");
		Node child = new Node(childData, new ArrayList<>());
		this.size++;
		int numGrandChild = scn.nextInt();
		for (int i = 0; i < numGrandChild; i++) {
			Node grandChild = takeInput(scn, child, i + 1);
			child.children.add(grandChild);
		}
		return child;
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return toString(this.rootnode);

	}

	private String toString(Node node) {
		String retVal = "";
		retVal += node.data + "=>";
		for (int i = 0; i < node.children.size(); i++) {
			retVal += node.children.get(i).data + ", ";
		}
		retVal += "END\n";
		for (int i = 0; i < node.children.size(); i++) {
			retVal += toString(node.children.get(i));
		}
		// retVal += "END\n";
		return retVal;
	}

	public int max() {
		return this.max(this.rootnode, this.rootnode.data);
	}

	private int max(Node node, int max) {
		for (int i = 0; i < node.children.size(); i++) {
			if (node.children.get(i).data > max) {
				max = node.children.get(i).data;
			}
		}
		for (int i = 0; i < node.children.size(); i++) {
			max = max(node.children.get(i), max);
		}
		return max;
	}

	public boolean find(int data) {
		return this.find(data, this.rootnode, false);
	}

	private boolean find(int data, Node node, boolean flag) {
		if (flag == false) {
			for (int i = 0; i < node.children.size(); i++) {
				if (node.children.get(i).data == data) {
					return true;
				}
			}
		} else {
			return flag;
		}
		for (int i = 0; i < node.children.size(); i++) {

			flag = find(data, node.children.get(i), flag);
		}
		return flag;
	}

	public int size2() {
		return this.size2(this.rootnode);
	}

	private int size2(Node node) {
		int retVal = 0;
		for (int i = 0; i < node.children.size(); i++) {
			retVal += size2(node.children.get(i));
		}
		retVal++;
		return retVal;
	}

	public int height() {
		return this.height(this.rootnode);
	}

	private int height(Node node) {

		int max = -1;
		for (int i = 0; i < node.children.size(); i++) {
			int childheight = this.height(node.children.get(i));
			if (childheight > max) {
				max = childheight;
			}
		}
		return ++max;
	}

	public void printAtLevel(int k) {
		this.printAtLevel(this.rootnode, k);
		System.out.println("END");
	}

	private void printAtLevel(Node node, int k) {
		if (k == 0) {
			System.out.print(node.data + ",");
		}
		for (int i = 0; i < node.children.size(); i++) {
			this.printAtLevel(node.children.get(i), k - 1);
		}
	}

	public Integer justLarger(int data) {
		Node jl = justLarger(this.rootnode, data);
		return jl == null ? null : jl.data;
	}

	private Node justLarger(Node node, int data) {
		Node retval = null;
		for (int i = 0; i < node.children.size(); i++) {
			Node temp = justLarger(node.children.get(i), data);
			if (temp != null) {
				if (retval != null) {
					if (temp.data < retval.data) {
						retval = temp;
					}
				} else {
					retval = temp;
				}
			}
		}
		if (node.data > data) {
			if (retval != null) {
				if (node.data < retval.data) {
					retval = node;
				}
			} else {
				retval = node;
			}
		}
		return retval;
	}

	public int kthsmallestvalue(int k) {
		int i = 0;
		int compare = Integer.MIN_VALUE, retval = Integer.MIN_VALUE;
		while (i <= k) {
			retval = this.justLarger(compare);
			i++;
			compare = retval;
		}
		return retval;
	}

	public int maxSonc() {
		return this.maxsonc(this.rootnode).data;
	}

	public int maxSoncbtr() {
		return this.maxsoncbtr(this.rootnode).node.data;
	}

	private class soncbtr {
		Node node;
		int score;

		soncbtr(Node node) {
			this.node = node;
			this.score = node.data;
			for (int i = 0; i < node.children.size(); i++) {
				this.score += node.children.get(i).data;
			}
		}
	}

	private soncbtr maxsoncbtr(Node node) {
		soncbtr retval = new soncbtr(node);
		for (int i = 0; i < node.children.size(); i++) {
			soncbtr tempnode = this.maxsoncbtr(node.children.get(i));
			if (tempnode.score > retval.score) {
				retval = tempnode;
			}
		}
		return retval;
	}

	private Node maxsonc(Node node) {
		Node retval = node;
		for (int i = 0; i < node.children.size(); i++) {
			Node tempnode = this.maxsonc(node.children.get(i));
			if (getSONCscore(tempnode) > getSONCscore(retval)) {
				retval = tempnode;
			}
		}
		return retval;
	}

	private int getSONCscore(Node node) {
		int retval = node.data;
		for (int i = 0; i < node.children.size(); i++) {
			retval += node.children.get(i).data;
		}
		return retval;
	}

	public void preOrder() {
		this.preOrder(this.rootnode);
		System.out.println("END");
	}

	private void preOrder(Node node) {
		System.out.print(node.data + ",");
		for (int i = 0; i < node.children.size(); i++) {
			preOrder(node.children.get(i));
		}
	}

	public void postOrder() {
		this.postOrder(this.rootnode);
		System.out.println("END");
	}

	private void postOrder(Node node) {
		for (int i = 0; i < node.children.size(); i++) {
			postOrder(node.children.get(i));
		}
		System.out.print(node.data + ",");
	}

	public void preOrderIterative() throws Exception {
		Stack<Node> stack = new Stack<>();
		stack.push(this.rootnode);
		while (!stack.isEmpty()) {
			Node temp = stack.pop();
			System.out.print(temp.data + ",");
			for (int i = temp.children.size() - 1; i >= 0; i--) {
				stack.push(temp.children.get(i));
			}
		}
		System.out.println("END");
	}

	public void LevelOrder() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(this.rootnode);
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			System.out.print(temp.data + ",");
			for (int i = 0; i < temp.children.size(); i++) {
				queue.offer(temp.children.get(i));
			}
		}
		System.out.println("END");
	}

	public boolean IdenticalTrees(IntTree tree2) {
		if (this.size() != tree2.size()) {
			return false;
		}
		boolean flag = IdenticalTrees(this.rootnode, tree2, tree2.rootnode, true);
		return flag;
	}

	private boolean IdenticalTrees(Node node1, IntTree tree2, Node node2, boolean flag) {

		if (flag == false || (node1 == null && node2 == null)) {
			return flag;
		}
		if ((node1 == null && node2 != null) || node2 == null && node1 != null) {
			return false;
		} else {
			for (int i = 0; i < node1.children.size(); i++) {
				flag = IdenticalTrees(node1.children.get(i), tree2, node2.children.get(i), flag);
			}
		}
		return flag;
	}

	public int leafnodes() {
		return this.leafnodes(this.rootnode, 0);
	}

	private int leafnodes(Node node, int retval) {

		if (node.children.size() == 0) {
			retval++;
			return retval;
		} else {
			for (int i = 0; i < node.children.size(); i++) {
				retval = leafnodes(node.children.get(i), retval);
			}
		}
		return retval;

	}

	public void changeTree2() {
		this.changeTree2(this.rootnode, 0);
	}

	private void changeTree2(Node node, int value) {
		if (node == null) {
			return;
		}
		node.data = value;
		if (node.children.size() != 0) {
			for (int i = 0; i < node.children.size(); i++) {
				this.changeTree2(node.children.get(i), value + 1);
			}
		}

	}
}
