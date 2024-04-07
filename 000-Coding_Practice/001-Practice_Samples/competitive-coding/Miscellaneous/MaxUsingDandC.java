package Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class MaxUsingDandC {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		Random random = new Random();
		int N = random.nextInt(101);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = random.nextInt(101);
		}
		System.out.println(N);
		for(int i = 0;i<N;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		int ans = maxElement(arr, 0, arr.length - 1, -1);
		ans = ans == -1 ? -1 : arr[ans];
		System.out.println(ans);
		scn.close();
	}

	public static int maxElement(int[] arr, int start, int end, int max) {
		if (start == end) {
			int temp;
			if (max != -1) {
				temp = arr[max] < arr[start] ? start : max;
			} else {
				temp = start;
			}
			return temp;
		}
		if (end == start + 1) {
			int temp = arr[end] > arr[start] ? end : start;
			if (max != -1) {
				temp = arr[temp] < arr[max] ? max : temp;
			}
			return temp;
		}
		if (start > end) {
			return max;
		}
		int mid = (start + end) / 2;
		int t1 = maxElement(arr, start, mid, max);
		int t2 = maxElement(arr, mid + 1, end, max);
		int ans = arr[t1] > arr[t2] ? t1 : t2;
		return ans;
	}
}