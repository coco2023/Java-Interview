package Assignment4;

import Lecture4.LargestSearchReverse;

public class Question3 {

	public static void main(String[] args) {
		int[] x = LargestSearchReverse.takeInput();
		sortArray(x);
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + ",");
		}
		System.out.println("END");
	}

	public static void sortArray(int[] A) {
		int left = 0, right = A.length - 1;
		while (left < right) {
			if (A[left] == 0 && left < right) {
				left++;
			}
			if (A[right] == 1 && left < right) {
				right--;
			}
			if (left < right) {
				A[left] = 0;
				A[right] = 1;
				left++;
				right--;
			}
		}
	}

}
