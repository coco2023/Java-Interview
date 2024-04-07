package Miscellaneous;

import java.util.Scanner;

public class UpperBoundLowerBound {

	public static int upper_bound(int[] arr, int N) {
		int s = 0, e = arr.length - 1, mid = (s + e) / 2, ans = -1;
		while (s <= e) {
			if (arr[mid] < N) {
				s = mid + 1;
			} else if (arr[mid] > N) {
				e = mid - 1;
			} else {
				ans = mid;
				s = mid + 1;
			}
			mid = (s + e) / 2;
		}
		return ans;
	}

	public static int lower_bound(int[] arr, int N) {
		int s = 0, e = arr.length - 1, mid = (s + e) / 2, ans = -1;
		while (s <= e) {
			if (arr[mid] < N) {
				s = mid + 1;
			} else if (arr[mid] > N) {
				e = mid - 1;
			} else {
				ans = mid;
				e = mid - 1;
			}
			mid = (s + e) / 2;
		}
		return ans;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while(t--!=0) {
			int N = scn.nextInt();
			int[] arr = new int[N];
			for(int i = 0 ; i < N ; i++) {
				arr[i] = scn.nextInt();
			}
			int k = scn.nextInt();
			System.out.println(lower_bound(arr, k));
			System.out.println(upper_bound(arr, k));
		}
		scn.close();
	}

}
