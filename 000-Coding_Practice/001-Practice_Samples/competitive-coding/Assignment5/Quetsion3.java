package Assignment5;

import java.util.Scanner;

public class Quetsion3 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the value of k:");
		int k = scn.nextInt();
		double X = geometricSum(k, 0);
		System.out.println("The geometric sum is:" + X);
	}

	public static double geometricSum(int k, double n) {
		if (k == 0) {
			n = n + 1;
			return n;
		}
		n = n + Math.pow(0.5, k);
		n = geometricSum(k - 1, n);
		return n;
	}
}
