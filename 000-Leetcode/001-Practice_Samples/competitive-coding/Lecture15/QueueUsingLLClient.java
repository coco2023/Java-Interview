package Lecture15;

public class QueueUsingLLClient {

	public static void main(String[] args) throws Exception {
		QueueUsingLL Queue = new QueueUsingLL();
		for (int i = 1; i <= 10; i++) {
			Queue.enqueue(i);
			Queue.display();
		}
		System.out.println(Queue.size());
		System.out.println(Queue.front());
		while (!Queue.isEmpty()) {
			System.out.println(Queue.dequeue());
			Queue.display();
		}
	}

}
