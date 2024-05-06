package Miscellaneous;

import java.util.Scanner;

public class BookAllocation {

	public static int minPages(int[] arr, int numOfChild) {
		int s = arr[arr.length-1], e = 0;
		for (int i = 0; i < arr.length; i++) {
			e += arr[i];
		}
		int mid = (s + e) / 2, ans = mid;
		while (s <= e) {
			if (!validConfig(arr, mid, numOfChild)) {
				s = mid + 1;
			} else {
				ans = mid;
				e = mid - 1;
			}
			mid = (s + e) / 2;
		}
		return ans;
	}

	public static boolean validConfig(int[] arr, int limit, int numOfChild) {
		int ans = 0, temp = 0;
		for (int i = 0; i < arr.length; i++) {
			while (i < arr.length && temp + arr[i] <= limit) {
				temp += arr[i++];
			}
			i--;
			ans++;
			temp = 0;
		}
		return ans == numOfChild;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		int numOfChild = scn.nextInt();
		System.out.println(minPages(arr, numOfChild));
		scn.close();
	}

}
