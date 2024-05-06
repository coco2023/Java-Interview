package Assignment5;

import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		int n = 0;
		System.out.print("Enter a number:");
		int N = scn.nextInt();
		int x = numberOfZeroes(N, n);
		System.out.println(x);
	}

	public static int numberOfZeroes(int N, int x) {
		if (N == 0) {
			return x;
		} else if (N % 10 == 0) {
			x++;
		}
		x = numberOfZeroes(N / 10, x);
		return x;
	}

}
