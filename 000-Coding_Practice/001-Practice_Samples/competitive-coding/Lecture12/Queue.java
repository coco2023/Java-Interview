package Lecture12;

public class Queue {
	protected int front;
	protected int[] data;
	protected int size;
	public static final int DEFAULT_CAPACITY = 10;

	public Queue() throws Exception {
		this(DEFAULT_CAPACITY);
	}

	public Queue(int capacity) throws Exception {
		if (capacity < 1) {
			throw new Exception("Invalid size");
		}
		this.data = new int[capacity];
		this.front = 0;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void enqueue(int data) throws Exception {
		if (this.size() == this.data.length) {
			throw new Exception("Queue is full");
		}
		int ai = (this.front + this.size) % this.data.length;
		this.data[ai] = data;
		this.size++;
	}

	public int dequeue() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Queue is empty");
		}
		int retVal = this.data[this.front];
		this.data[this.front] = 0;
		this.front = (this.front + 1) % this.data.length;
		this.size--;
		return retVal;
	}

	public int front() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Queue is empty");
		}
		int retVal = this.data[this.front];
		return retVal;
	}

	public void display() {
		for (int i = 0; i < this.size(); i++) {
			int ai = (this.front + i) % this.data.length;
			System.out.print(data[ai] + "<=");
		}
		System.out.println("END");
	}

}
