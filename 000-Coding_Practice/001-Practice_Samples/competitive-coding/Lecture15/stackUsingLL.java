package Lecture15;

import Lecture15.LinkedList.Node;

public class stackUsingLL {
	private LinkedList L;

	public stackUsingLL() {
		L = new LinkedList();
	}

	public boolean isEmpty() {
		return this.L.isEmpty();
	}

	public int size() {
		return this.L.size();
	}

	public void display() {
		this.L.display();
	}

	public void push(int item) {
		this.L.AddFirst(item);
	}

	public int pop() throws Exception {
		return this.L.removeFirst();
	}

	public int top() throws Exception {
		return this.L.getFirst();
	}

	public void reverse_Iteratively_Data() throws Exception {
		int x = this.size();
		for (int i = 0; i < x / 2; i++) {
			int temp1 = this.L.getAt(i);
			int temp2 = this.L.getAt(x - i - 1);
			this.L.removeAt(i);
			this.L.AddAt(i, temp2);
			this.L.removeAt(x - i - 1);
			this.L.AddAt(x - i - 1, temp1);
		}
	}

	public void reverse_recursively_Data(int i, int x) throws Exception {
		if (i >= x) {
			return;
		}
		int temp1 = this.L.removeAt(0);
		reverse_recursively_Data(i + 1, x);
		this.L.AddLast(temp1);
	}
	
	
}
