package Assignment4;

import Lecture4.LargestSearchReverse;

public class Question2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = LargestSearchReverse.takeInput();
		int k = findRotationfactor(arr);
		System.out.println("The array has been rotated in the clockwise direction by " + k);
	}

	public static int findRotationfactor(int[] arr) {
		int small = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[small]) {
				small = i;
			}
		}
		return small;
	}
}
