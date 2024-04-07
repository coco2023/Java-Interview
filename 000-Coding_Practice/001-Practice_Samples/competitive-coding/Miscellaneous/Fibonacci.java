package Miscellaneous;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N-");
		int N = scn.nextInt();
		int i1 = 0, i2 = 1, k = 0;
		System.out.print(i1 + " " + i2);
		while (k <= N) {
			k = i1 + i2;
			System.out.print(" "+ k + " ");
			i1 = i2;
			i2 = k;
		}
	}

}
