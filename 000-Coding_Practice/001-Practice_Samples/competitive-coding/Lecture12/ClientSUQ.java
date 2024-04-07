package Lecture12;

import Lecture13.StackUsingQueue;

public class ClientSUQ {

	public static void main(String[] args) throws Exception {
		StackUsingQueue S = new StackUsingQueue();
		for (int i = 1; i <= 10; i++) {
			S.push(i);
			S.display();
		}
		
	}

}
