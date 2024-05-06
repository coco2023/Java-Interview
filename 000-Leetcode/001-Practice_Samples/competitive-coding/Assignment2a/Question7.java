package Assignment2a;

import java.util.Scanner;

public class Question7 {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the decimal number:");
		int N = scn.nextInt();
		decimaltobinary(N);
	}

	public static void decimaltobinary(int x) {
		int[] binary = new int[40];
		int temp = x, i = 0;
		while (temp != 0) {
			binary[i++] = temp % 2;
			temp = temp / 2;
		}
		System.out.print("The binary representation of " + x + " is:");
		for (int k = i - 1; k >= 0; k--) {
			System.out.print(binary[k]);
		}
	}
}
