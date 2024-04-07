package Lecture17;

import Lecture15.LinkedList;

public class Check {

	public static void main(String[] args) throws Exception {
		LinkedList temp = new LinkedList();
		temp.AddLast(3);
		temp.AddLast(4);
		temp.AddLast(5);
		temp.AddLast(2);
		temp.AddLast(6);
		temp.AddLast(1);
		temp.AddLast(9);
		temp.display();
		temp.kreverse(3);
		temp.display();
	}

}
