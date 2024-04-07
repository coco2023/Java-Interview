package Lecture21;

import java.util.ArrayList;

public class Heap<K extends Comparable<K>, V> {
	private class Node implements Comparable<Node> {
		K score;
		V value;

		public Node(K score, V value) {
			this.score = score;
			this.value = value;
		}

		@Override
		public int compareTo(Heap<K, V>.Node o) {
			if (isMin) {
				return -1 * this.score.compareTo(o.score);
			} else {
				return this.score.compareTo(o.score);
			}
		}

		@Override
		public String toString() {
			return "{" + this.value + " : " + this.score + " }";
		}

	}

	private boolean isMin;
	private ArrayList<Node> nodes;

	public Heap() {
		this(false);
	}

	public Heap(boolean isMin) {
		this.isMin = isMin;
		this.nodes = new ArrayList<>();
	}

	public int size() {
		return this.nodes.size();
	}

	public boolean isEmpty() {
		return this.nodes.size() == 0;
	}

	public void add(K score, V Value) {
		Node tobeAdded = new Node(score, Value);
		this.nodes.add(tobeAdded);
		this.upheapify(this.nodes.size() - 1);
	}

	private void upheapify(int ci) {
		if (ci == 0) {
			return;
		}
		int pi = (ci - 1) / 2;
		Node parent = this.nodes.get(pi);
		Node child = this.nodes.get(ci);
		if (parent.compareTo(child) < 0) {
			this.swap(pi, ci);
			this.upheapify(pi);
		}
	}

	private void swap(int k1, int k2) {
		Node temp = this.nodes.get(k1);
		this.nodes.set(k1, this.nodes.get(k2));
		this.nodes.set(k2, temp);
	}

	public V removeHP() {
		V value = this.nodes.get(0).value;
		this.swap(0, this.nodes.size() - 1);
		this.nodes.remove(this.nodes.size() - 1);
		this.downheapify(0);
		return value;
	}

	public K removeHPkey() {
		K value = this.nodes.get(0).score;
		this.swap(0, this.nodes.size() - 1);
		this.nodes.remove(this.nodes.size() - 1);
		this.downheapify(0);
		return value;
	}

	public Heap(K[] scores, V[] values, boolean isMin) {
		this(isMin);
		for (int i = 0; i < scores.length; i++) {
			Node node = new Node(scores[i], values[i]);
			this.nodes.add(node);
		}
		for (int i = (nodes.size() / 2) - 1; i >= 0; i--) {
			this.downheapify(i);
		}

	}

	private void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		int maxi = pi;
		if (lci < this.nodes.size() && this.nodes.get(lci).compareTo(this.nodes.get(maxi)) > 0) {
			maxi = lci;
		}
		if (rci < this.nodes.size() && this.nodes.get(rci).compareTo(this.nodes.get(maxi)) > 0) {
			maxi = rci;
		}
		if (maxi != pi) {
			this.swap(maxi, pi);
			this.downheapify(maxi);
		}
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.nodes.toString();
	}

	public void displayasTree() {
		System.out.println(this.getTreeString(0));
	}

	public String getTreeString(int pi) {
		String retval = "";
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		if (lci < this.nodes.size()) {
			retval += this.nodes.get(lci) + " = >";
		} else {
			retval += "END = > ";
		}
		retval += this.nodes.get(pi) + " < = ";
		if (rci < this.nodes.size()) {
			retval += this.nodes.get(rci) + "";
		} else {
			retval += "END";
		}
		retval += "\n";
		if (lci < this.nodes.size()) {
			retval += this.getTreeString(lci);
		}
		if (rci < this.nodes.size()) {
			retval += this.getTreeString(rci);
		}
		return retval;
	}
}
