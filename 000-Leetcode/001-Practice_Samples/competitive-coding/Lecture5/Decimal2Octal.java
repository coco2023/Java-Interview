package Lecture5;

import java.util.Scanner;

public class Decimal2Octal {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a number:");
		int N = scn.nextInt();
		int x = DecimalToOctal(N);
		System.out.println(x + " is the decimal representation of the octal number " + N);
	}

	public static int DecimalToOctal(int N) {
		int x = 0, i = 0;
		while (N != 0) {
			x = (int) (x + N % 10 * Math.pow(8, i));
			N = N / 10;
			i++;
		}
		return x;
	}

}
