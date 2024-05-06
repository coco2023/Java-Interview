package Miscellaneous;

import java.util.Scanner;

public class LargestSearchReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = takeInput();
		int x = Largest(arr);
		System.out.println("Largest element is:" + x);
		int k = Search(arr);
		if (k == -1) {
			System.out.println("Search was unsuccessful.");
		} else {
			System.out.println("Search was successful.");
		}
		Reverse(arr);
	}

	public static int Largest(int[] arr) {
		int largest = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > largest) {
				largest = arr[i];
			}
		}
		return largest;
	}

	public static int[] takeInput() {
		int[] retval;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number of elements:");
		int N = scn.nextInt();
		retval = new int[N];
		for (int i = 0; i < N; i++) {
			System.out.print("Enter the element:");
			retval[i] = scn.nextInt();
		}
		return retval;
	}

	public static int Search(int[] arr) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the element to be searched:");
		int search = scn.nextInt();
		boolean flag = false;
		int i = 0;
		while (!flag&&i<arr.length) {
			if (arr[i] == search) {
				flag = true;
			}
			i++;
		}
		if (!flag) {
			System.out.println("Value not found!");
			return -1;
		} else {
			System.out.println("Value found at" + (i + 1) + "th position.");
			return i;
		}
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
