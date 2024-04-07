package Miscellaneous;

import java.util.Scanner;

public class DecimalToBinary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the decimal number to be converted to binary:");
		int N = scn.nextInt();
		int temp = N, binary = 0;
		while (temp != 0) {
			binary = binary * 10 + temp % 2;
			temp = temp / 2;
		}
		System.out.println("The binary equivalent is:" + binary);

	}

}
