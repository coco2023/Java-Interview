package Assignment3;

import java.util.Scanner;

public class Question8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number of elements in the array:");
		int N = scn.nextInt();
		int[] arr = new int[N];
		System.out.println("Enter elements for the array:");
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		Reverse(arr);
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println("END");
	}

	public static void Reverse(int[] arr) {
		for (int i = 0; i < (arr.length) / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = temp;
		}
		System.out.println("Reversed Array:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
	}
}
