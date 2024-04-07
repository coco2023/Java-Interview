package Miscellaneous;

import java.util.Scanner;

public class InversionCount {

	public static int inversionCount(int[] arr, int s, int e) {
		if (s >= e) {
			return 0;
		}
		int mid = (s + e) / 2;
		int count = inversionCount(arr, s, mid);
		count += inversionCount(arr, mid + 1, e);
		count += merge(arr, s, e, mid + 1);
		return count;
	}

	public static int merge(int[] arr, int s, int e, int mid) {
		int[] sorted_arr = new int[arr.length];
		int ans = 0;
		int k = s, i = s, j = mid;
		while (i <= mid - 1 && j <= e) {
			if (arr[i] < arr[j]) {
				sorted_arr[k++] = arr[i++];
			} else if (arr[i] > arr[j]) {
				ans += mid - i;
				sorted_arr[k++] = arr[j++];
			} else {
				sorted_arr[k++] = arr[i++];
				j++;
			}
		}
		while (i <= mid - 1) {
			sorted_arr[k++] = arr[i++];
		}
		while (j <= e) {
			sorted_arr[k++] = arr[j++];
		}
		for (int m = s; m <= e; m++) {
			arr[m] = sorted_arr[m];
		}
//		print_arr(sorted_arr, k);
		return ans;
	}

	public static void print_arr(int[] arr, int k) {
		for (int i = 0; i < k; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static int inversionCount(int[] arr) {
		return inversionCount(arr, 0, arr.length - 1);
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int N = scn.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = scn.nextInt();
			}
			System.out.println(inversionCount(arr));
		}
		scn.close();
	}

}
