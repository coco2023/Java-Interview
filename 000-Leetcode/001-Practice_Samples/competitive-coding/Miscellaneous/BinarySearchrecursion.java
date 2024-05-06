package Miscellaneous;

import java.util.Scanner;

public class BinarySearchrecursion {

	public static int binary_search(int arr[], int mid, int left, int right, int element) {
		mid = (left + right) / 2;
		if (left > right) {
			return -1;
		}
		if (arr[mid] == element) {
			return mid;
		}
		if (arr[mid] < element) {
			left = mid + 1;
			return binary_search(arr, mid, left, right, element);
		}
		right = mid - 1;
		return binary_search(arr, mid, left, right, element);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		int search = scn.nextInt();
		int ans = binary_search(arr, 0, 0, 0, search);
		System.out.println(ans);
		scn.close();
	}

}
