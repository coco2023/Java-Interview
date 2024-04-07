package Assignment3;

import Lecture4.LargestSearchReverse;

public class Question7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = LargestSearchReverse.takeInput();
		SelectionSort(arr1);
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + ",");
		}
		System.out.println("END");
	}

	public static void bubbleSort(int[] arr) {
		int counter = 1;
		while (counter <= arr.length - 1) {
			for (int i = 0; i < arr.length - counter; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			counter++;
		}
	}

	public static void SelectionSort(int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			int small = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[small]) {
					small = j;
				}
			}
			if (small != i) {
				int temp = arr[i];
				arr[i] = arr[small];
				arr[small] = temp;
			}
		}
	}
}
