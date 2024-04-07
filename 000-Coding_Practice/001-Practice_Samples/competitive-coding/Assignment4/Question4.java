package Assignment4;

import Lecture4.LargestSearchReverse;

public class Question4 {

	public static void main(String[] args) {
		int[] x = LargestSearchReverse.takeInput();
		sortArray(x);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + ",");
		}
		System.out.println("END");
	}

	public static void sortArray(int[] A) {
		int left = 0, right = A.length - 1, mid = (left + right / 2);
		while (left < right && mid > left && mid < right) {

		}
	}

}
