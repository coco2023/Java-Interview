package Assignment3;

import java.util.Scanner;

import Lecture4.LargestSearchReverse;

public class Question9 {

	public static void main(String[] args) {
		int arr1[] = LargestSearchReverse.takeInput();
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter sum to be searched:");
		int N = scn.nextInt();
		sumTriplet(arr1, N);
	}

	public static boolean sumPairs(int[] arr1, int x, int x2) {
		int i = 0, k1 = 0, k2 = 0;
		int[] m = new int[40];
		boolean flag = false;
		while (i < arr1.length) {
			if (LargestSearchReverse.Search(arr1, (x - arr1[i])) != -1) {
				if (LargestSearchReverse.Search(arr1, (x - arr1[i])) != i
						&& LargestSearchReverse.Search(arr1, (x - arr1[i])) != x2
						&& LargestSearchReverse.Search(arr1, (arr1[i])) != x2
						&& LargestSearchReverse.Search(m, (x - arr1[i])) == -1) {
					System.out.println(arr1[i] + "," + arr1[LargestSearchReverse.Search(arr1, (x - arr1[i]))]);
					k1 = arr1[i];
					k2 = arr1[LargestSearchReverse.Search(arr1, (x - arr1[i]))];
					flag = true;
				}
			}
			m[i] = k1;
			m[++i] = k2;

		}
		return flag;
	}

	public static void sumTriplet(int[] arr1, int N) {
		for (int i = 0; i < arr1.length; i++) {
			int temp = arr1[i];
			System.out.println("For" + arr1[i] + ":");
			boolean flag = sumPairs(arr1, (N - temp), i);
			if (flag == false) {
				System.out.println("No triplets");
			}
		}
	}
}
