package Lecture12;

public class QueueClient {

	public static void main(String[] args) throws Exception {
		Stack S = new Stack(5);
		for (int i = 1; i <= 5; i++) {
			S.push(i);
		}
		S.display();
		// Q.dequeue();
		// Q.display();
		// System.out.println(Q.front());
		// Q.enqueue(13);
		// Q.display();
		// System.out.println(Q.size());
		Stack S2=new Stack(5);
		S2=reverseStack(S, S2);
		S2.display();
	}
	public static Queue reverseQueue(Queue Q) throws Exception {
		if (Q.isEmpty()) {
			return Q;
		}
		int swap = Q.dequeue();
		reverseQueue(Q);
		Q.enqueue(swap);
		return Q;

	}

	public static Stack reverseStack(Stack S1, Stack S2) throws Exception {
		if (S1.isEmpty()) {
			return S2;
		}
		int item = S1.top();
		S2.push(item);
		S1.pop();
		S2 = reverseStack(S1, S2);
		return S2;
	}

}
