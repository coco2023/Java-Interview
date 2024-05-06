package Lecture12;

public class DynamicQueueClient {

	public static void main(String[] args) throws Exception {
		Queue Q = new DynamicQueue(5);
		for (int i = 1; i <= 5; i++) {
			Q.enqueue(i);
		}
		Q.enqueue(6);
		Q.enqueue(7);
		Q.display();
		Q.dequeue();
		Q.display();
		System.out.println(Q.front());
		Q.enqueue(13);
		Q.display();
		System.out.println(Q.size());

	}

}
