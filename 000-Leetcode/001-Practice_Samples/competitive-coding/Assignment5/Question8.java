package Assignment5;

import java.util.Scanner;

public class Question8 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a number:");
		int N = scn.nextInt();
		int sum = sumOfdigits(N, 0);
		System.out.println("The sum of the digits is:" + sum);
	}

	public static int sumOfdigits(int N, int sum) {
		if (N == 0) {
			return sum;
		}
		sum = sum + N % 10;
		N = sumOfdigits(N / 10, sum);
		return N;
	}
}
