package Assignment5;

import java.util.Scanner;

public class Question1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the first number:");
		int M = scn.nextInt();
		System.out.print("Enter the second number:");
		int N = scn.nextInt();
		int X = multiply(M, N, 0);
		System.out.println(M + "*" + N + "=" + X);
	}

	public static int multiply(int M, int N, int x) {
		if (N == 0) {
			return x;
		} else {
			x = x + M;
		}
		x = multiply(M, N - 1, x);
		return x;
	}
}
