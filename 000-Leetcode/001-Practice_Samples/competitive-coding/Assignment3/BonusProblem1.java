package Assignment3;

import Lecture4.LargestSearchReverse;

public class BonusProblem1 {
	public static void main(String args[]) {
		System.out.println("Enter elements for the first array!");
		int[] arr1 = LargestSearchReverse.takeInput();
		System.out.println("Enter elements for the second array!");
		int[] arr2 = LargestSearchReverse.takeInput();
		int[] result = sum(arr1, arr2);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + ",");
		}
		System.out.println("END");
	}

	public static int[] sum(int[] arr1, int[] arr2) {
		int num1 = 0;
		for (int i = 0; i < arr1.length; i++) {
			num1 = num1 * 10 + arr1[i];
		}
		int num2 = 0;
		for (int i = 0; i < arr2.length; i++) {
			num2 = num2 * 10 + arr2[i];
		}
		int num3 = num1 + num2;
		int length = 0, temp = num3;
		while (temp != 0) {
			length++;
			temp = temp / 10;
		}
		int[] sum = new int[length];
		for (int i = length - 1; i >= 0; i--) {
			sum[i] = num3 % 10;
			num3 = num3 / 10;
		}
		return sum;
	}
}
