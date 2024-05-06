package Miscellaneous;

import java.util.Scanner;

public class Pattern2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N-");
		int N = scn.nextInt();
		int i = 1, value1;
		while (i <= N) {
			int k = 1;
			while (k <= N - i) {
				System.out.print(" ");
				k = k + 1;
			}
			k = 1;
			value1 = i;
			while (k <= i) {
				System.out.print(value1);
				value1 = value1 + 1;
				k = k + 1;
			}
			k = 1;
			value1 = 2 * (i - 1);
			while (k <= i - 1) {
				System.out.print(value1);
				value1 = value1 - 1;
				k = k + 1;
			}
			System.out.println("");
			i = i + 1;

		}
	}

}
