package Assignment4;

import Lecture4.LargestSearchReverse;

public class Question5 {

	public static void main(String[] args) {
		int[] arr1 = LargestSearchReverse.takeInput();
		int x = duplicate(arr1);
		if (x != -1) {
			System.out.println(x + " is the duplicate number in the array.");
		} else {
			System.out.println("There is no duplicate element in the array.");
		}
	}

	public static int duplicate(int[] A) {
		for (int i = 0; i < A.length / 2; i++) {
			int count = 0;
			for (int j = 0; j < A.length; j++) {
				if (A[j] == A[i]) {
					count++;
				}
			}
			if (count > 1) {
				return A[i];
			}
		}
		return -1;
	}

}
