package Lecture4;

import java.util.Scanner;

public class ArraySumtoX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = LargestSearchReverse.takeInput();
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter sum to be searched:");
		int N = scn.nextInt();
		SumPairs(arr1, N);
	}

	public static void SumPairs(int[] arr1, int x) {
		int i = 0;
		while (i < arr1.length) {
			if (LargestSearchReverse.Search(arr1, (x - arr1[i])) != -1) {
				if (LargestSearchReverse.Search(arr1, (x - arr1[i])) != i) {
					System.out.println(arr1[i] + "," + arr1[LargestSearchReverse.Search(arr1, (x - arr1[i]))]);
				}
			}
			i++;
		}

	}

}
