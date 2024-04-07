package Miscellaneous;

import java.util.Random;

/**
 * This algorithm generates the maximum sub array sum The basic logic goes as
 * follows Compute two variables maxSum and currSum update currSum as long as it
 * is positive because if currSum is negative it will only make the totalSum
 * less and not more, in order to maximize it we shall just take the positive
 * currSum and if it becomes -ve we make it 0. also maxSum is similar to currSum
 * but it is only updated when currSum increases more details can be found by
 * analyzing the code below
 */
public class KadaneAlgo {

	public static int maxSum(int[] arr) {
		int currSum = 0, maxSum = 0;
		for (int i = 0; i < arr.length; i++) {
			currSum += arr[i];
			if (currSum < 0) {
				currSum = 0;
			}
			maxSum = max(maxSum, currSum);
		}
		return maxSum;
	}

	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public static void main(String[] args) {
		Random random = new Random();
		int N = random.nextInt() % 101;
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt() % 101;
		}
		for(int i = 0; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println(maxSum(arr));
	}
}
