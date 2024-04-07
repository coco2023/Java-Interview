package Lecture15;

public class QueueUsingLL {
	private LinkedList LL;

	public QueueUsingLL() {
		LL = new LinkedList();
	}

	public void display() {
		LL.display();
	}

	public int front() throws Exception {
		return this.LL.getFirst();
	}

	public boolean isEmpty() {
		return this.LL.isEmpty();
	}

	public int size() {
		return this.LL.size();
	}

	public void enqueue(int item) {
		this.LL.AddLast(item);
	}

	public int dequeue() throws Exception {
		return this.LL.removeFirst();
	}
}
