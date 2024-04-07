package Lecture13;

import Lecture12.DynamicQueue;

public class StackUsingQueue2 {
	private DynamicQueue PrimaryQ;
	private DynamicQueue SecondaryQ;

	public StackUsingQueue2() throws Exception {
		this.SecondaryQ = new DynamicQueue();
		this.PrimaryQ = new DynamicQueue();
	}

	public void push(int N) throws Exception {

	}

	public int top() throws Exception {
		while (this.PrimaryQ.size() != 1) {
			this.SecondaryQ.enqueue(this.PrimaryQ.dequeue());
		}
		int x = this.PrimaryQ.dequeue();
		this.SecondaryQ.enqueue(x);
		DynamicQueue temp = this.PrimaryQ;
		this.PrimaryQ = this.SecondaryQ;
		this.SecondaryQ = temp;
		return x;
	}

	public int pop() throws Exception {
		DynamicQueue temp = reverseQueue(this.PrimaryQ);
		int x = temp.dequeue();
		this.PrimaryQ = reverseQueue(temp);
		return x;
	}

	public void display() throws Exception {
		this.PrimaryQ = reverseQueue(this.PrimaryQ);
		this.PrimaryQ.display();
		this.PrimaryQ = reverseQueue(this.PrimaryQ);
	}

	private static DynamicQueue reverseQueue(DynamicQueue Q) throws Exception {
		if (Q.isEmpty()) {
			return Q;
		}
		int swap = Q.dequeue();
		reverseQueue(Q);
		Q.enqueue(swap);
		return Q;
	}
}
