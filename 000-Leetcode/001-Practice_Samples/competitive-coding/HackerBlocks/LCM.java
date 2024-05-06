package HackerBlocks;

import java.util.Scanner;

public class LCM {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		long n1 = scn.nextLong();
		long n2 = scn.nextLong();
		scn.close();
		if (n1 > n2) {
			System.out.println((n1*n2)/gcd(n1, n2));
		} else {
			System.out.println((n1*n2)/gcd(n2, n1));
		}
	}

	public static long gcd(long n1, long n2) {
		if (n2 == 0) {
			return n1;
		} else {
			return gcd(n2, n1 % n2);
		}
	}
}
