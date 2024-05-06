package Lecture15;

public class LinkedListClient {

	public static void main(String[] args) throws Exception {
		LinkedList L = new LinkedList();
		for (int i = 1; i <= 2; i++) {
			L.AddFirst(i);
		}
		L.AddAt(0, 3);
		L.AddLast(4);
		L.removeAt(1);
		L.display();
	}

}
