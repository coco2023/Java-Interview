package Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class QuickSort {

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void randomized_quicksort(int[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			int j = (new Random()).nextInt(i + 1);
			swap(arr, i, j);
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static int partition(int[] arr, int pivot, int start) {
		int i = start - 1;
		for (int j = start; j < pivot; j++) {
			if (arr[j] <= arr[pivot]) {
				i++;
				swap(arr, i, j);
			}
		}
		if (i + 1 < arr.length)
			swap(arr, pivot, i + 1);
		return i + 1;
	}

	public static void quickSort(int[] arr, int s, int e) {
		if (s >= e) {
			return;
		}
		int pos = partition(arr, e, s);
		quickSort(arr, s, pos - 1);
		quickSort(arr, pos + 1, e);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		randomized_quicksort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		scn.close();
	}

}
