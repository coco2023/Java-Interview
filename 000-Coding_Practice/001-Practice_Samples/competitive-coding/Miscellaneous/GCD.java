package Miscellaneous;

import java.util.Scanner;

public class GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N1 and N2:");
		int N1 = scn.nextInt();
		int N2 = scn.nextInt();
		int small;
		if (N1 >= N2) {
			small = N2;
		} else {
			small = N1;
		}
		int i = 1, gcd = 1;
		while (i <= small) {
			if (N1 % i == 0 && N2 % i == 0) {
				gcd = i;
			}
			i = i + 1;
		}
		System.out.println("The HCF is:" + gcd);

	}

}
