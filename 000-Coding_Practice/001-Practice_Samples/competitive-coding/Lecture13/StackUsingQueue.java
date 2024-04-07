package Lecture13;

import Lecture12.DynamicQueue;

public class StackUsingQueue {
	private DynamicQueue PrimaryQ;
	private DynamicQueue SecondaryQ;

	public StackUsingQueue() throws Exception {
		this.SecondaryQ = new DynamicQueue();
		this.PrimaryQ = new DynamicQueue();
	}

	public void push(int N) throws Exception {
		this.PrimaryQ.enqueue(N);
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
		while (this.PrimaryQ.size() != 1) {
			this.SecondaryQ.enqueue(this.PrimaryQ.dequeue());
		}
		int x = this.PrimaryQ.dequeue();

		DynamicQueue temp = this.PrimaryQ;
		this.PrimaryQ = this.SecondaryQ;
		this.SecondaryQ = temp;
		
		return x;
	}

	public void display() throws Exception {
		reverseQueue(this.PrimaryQ);
		this.PrimaryQ.display();
		reverseQueue(this.PrimaryQ);
	}

	private static void reverseQueue(DynamicQueue Q) throws Exception {
		if (Q.isEmpty()) {
			return;
		}
		int swap = Q.dequeue();
		reverseQueue(Q);
		Q.enqueue(swap);
	}
}
