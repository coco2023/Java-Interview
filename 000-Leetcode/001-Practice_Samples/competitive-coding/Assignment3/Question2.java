package Assignment3;

import Lecture4.LargestSearchReverse;

public class Question2 {

	public static void main(String[] args) {
		int arr1[] = LargestSearchReverse.takeInput();
		int arr2[] = LargestSearchReverse.takeInput();
		intersection(arr1, arr2);
	}

	public static void intersection(int arr1[], int arr2[]) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i] == arr2[j]) {
					System.out.print(arr1[i] + ",");
					break;
				}
			}
		}
		System.out.println("END");
	}

}
