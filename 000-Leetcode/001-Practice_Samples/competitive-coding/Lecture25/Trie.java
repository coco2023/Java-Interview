package Lecture25;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trie {
	private class Node {
		Character data;
		HashMap<Character, Node> children;
		boolean isEOW;

		public Node(Character data, boolean isEOW) {
			this.data = data;
			this.isEOW = isEOW;
			this.children = new HashMap<>();
		}

		public String toString() {
			return "{" + this.data + ":" + this.isEOW + "}";
		}
	}

	private Node rootnode;
	private int size;

	public Trie() {
		this.rootnode = new Node('\0', false);
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return this.toString(this.rootnode);
	}

	private String toString(Node node) {
		String retVal = "";
		retVal += node + "=>";

		Set<Map.Entry<Character, Node>> entrySet = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : entrySet) {
			retVal += entry.getValue() + ", ";
		}
		retVal += "END\n";
		for (Map.Entry<Character, Node> entry : entrySet) {
			retVal += toString(entry.getValue());
		}
		return retVal;
	}

	public void addWord(String str) {
		this.addWord(this.rootnode, str);
	}

	private void addWord(Node parent, String str) {
		if (str.length() == 0) {
			if (!parent.isEOW) {
				parent.isEOW = true;
				this.size++;
			}
			return;
		}
		char ch = str.charAt(0);
		String ros = str.substring(1);
		Node child = parent.children.get(ch);
		if (child == null) {
			child = new Node(ch, false);
			parent.children.put(ch, child);
		}
		this.addWord(child, ros);
	}

	public boolean search(String str) {
		return this.search(this.rootnode, str);
	}

	private boolean search(Node parent, String str) {
		if (str.length() == 0) {
			if (!parent.isEOW) {
				return false;
			}
			return true;
		}
		char ch = str.charAt(0);
		String ros = str.substring(1);
		Node child = parent.children.get(ch);
		if (child == null) {
			return false;
		}
		return this.search(child, ros);
	}

	public boolean remove(String str) {

		return this.remove(this.rootnode, str);
	}

	private boolean remove(Node parent, String str) {
		if (str.length() == 0) {
			if (parent.isEOW) {
				parent.isEOW = false;
				this.size--;
				return true;
			}
			return false;
		}
		char ch = str.charAt(0);
		String ros = str.substring(1);
		Node child = parent.children.get(ch);
		if (child == null) {
			return false;
		}
		boolean removed = this.remove(child, ros);
		if (!child.isEOW && child.children.size() == 0) {
			parent.children.remove(ch);
		}
		return removed;
	}

	public void displayAllWords() {
		this.displayAllWords(this.rootnode, "");
	}

	private void displayAllWords(Node node, String str) {
		if (node.isEOW) {
			System.out.println(str);
		}
		Set<Map.Entry<Character, Node>> allEntries = node.children.entrySet();
		for (Map.Entry<Character, Node> entry : allEntries) {
			this.displayAllWords(entry.getValue(), str + entry.getKey());
		}
	}

}
