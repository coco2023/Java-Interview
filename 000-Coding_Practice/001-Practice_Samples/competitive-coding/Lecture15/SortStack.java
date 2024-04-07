package Lecture15;

import Lecture12.DynamicStack;

public class SortStack {

	public static void main(String[] args) throws Exception {
		DynamicStack S = new DynamicStack();
		for (int i = 10; i >= 1; i--) {
			S.push(i);
		}
		S.display();
		System.out.println();
		SortSUSI(S);
		S.display();
	}

	public static void SortSUSI(DynamicStack Stack) throws Exception {
		DynamicStack helper = new DynamicStack();
		int counter = 0, origSize = Stack.size();
		while (counter <= origSize) {
			int min = Integer.MAX_VALUE;
			while (!Stack.isEmpty()) {
				int x = Stack.pop();
				if (x < min) {
					min = x;
				}
				helper.push(x);
			}
			Stack.push(min);
			counter++;
			while (!helper.isEmpty()) {
				int item = helper.pop();
				if (item != min) {
					Stack.push(item);
				}
			}
		}
	}
}
