package Miscellaneous;

import java.util.Random;

public class MaxSumUsingDandC {

	public static int maxCrossingSum(int arr[], int l, int m, int h) {
		int sum = 0;
		int left_sum = Integer.MIN_VALUE;
		for (int i = m; i >= l; i--) {
			sum = sum + arr[i];
			if (sum > left_sum)
				left_sum = sum;
		}
		sum = 0;
		int right_sum = Integer.MIN_VALUE;
		for (int i = m + 1; i <= h; i++) {
			sum = sum + arr[i];
			if (sum > right_sum)
				right_sum = sum;
		}
		return left_sum + right_sum;
	}

	public static int maxSum(int[] arr, int s, int e) {
		if (s == e) {
			return arr[s];
		}
		int mid = (s + e) / 2;
		return max(maxCrossingSum(arr, s, mid, e), max(maxSum(arr, s, mid), maxSum(arr, mid + 1, e)));

	}

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static void main(String[] args) {
		Random random = new Random();
		int N = random.nextInt(101);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = random.nextInt() % 100;
		}
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println(maxSum(arr, 0, arr.length - 1));
	}

}
