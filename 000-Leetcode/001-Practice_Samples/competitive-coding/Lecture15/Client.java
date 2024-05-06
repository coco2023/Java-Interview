package Lecture15;

import java.util.Random;

public class Client {

	public static void main(String[] args) throws Exception {
		Random random = new Random();
		LinkedList L = new LinkedList();
		for (int i = 15; i >= 1; i--) {
			L.AddLast(random.nextInt(100));
		}
		L.display();
		L.kreverse(3);
		L.display();
	}

}
