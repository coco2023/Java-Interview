package Assignment2a;

import java.util.Scanner;

public class Question6 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a binary number:");
		int N = scn.nextInt();
		binarytodecimal(N);
	}

	public static void binarytodecimal(int N) {
		int temp = N, i = 0;
		double sum = 0;
		int length = 0, temp1 = temp;
		while (temp1 != 0) {
			length++;
			temp1 = temp1 / 10;
		}
		if (temp % Math.pow(10, length - 1) != 1) {
			while (temp != 0) {
				sum = sum + (temp % 10) * Math.pow(2, i);
				temp = temp / 10;
				i++;
			}
			System.out.println("The decimal equivalent of " + N + " is:" + sum);
		} else {
			if (length % 3 == 0) {
				temp1 = temp;
				int rev = 0;
				while (temp1 != 0) {
					rev = rev * 10 + temp1 % 10;
					temp1 = temp1 / 10;
				}
				int nb2pb = 0, rem = 0;
				while (rev != 0) {
					if (rev % 10 == 1) {
						nb2pb = nb2pb * 10;
					} else {
						nb2pb = nb2pb * 10 + 1;
					}
					rev = rev / 10;
				}
				double decimal = 0;
				i = 0;
				while (nb2pb != 0) {
					decimal = decimal + (nb2pb % 10) * Math.pow(2, i);
					nb2pb = nb2pb / 10;
				}
				System.out.println("The decimal equivalent of " + N + " is:" + decimal);
			} else {
				temp1 = temp;
				int rev = 0;
				while (temp1 != 0) {
					rev = rev * 10 + temp1 % 10;
					temp1 = temp1 / 10;
				}
				double temp2 = rev * Math.pow(10, 3 - (length % 3));
				int nb2pb = 0, rem = 0;
				while (temp2 != 0) {
					if (temp2 % 10 == 1) {
						nb2pb = nb2pb * 10;
					} else {
						nb2pb = nb2pb * 10 + 1;
					}
					temp2 = temp2 / 10;
				}
				double decimal = 0;
				i = 0;
				while (nb2pb != 0) {
					decimal = decimal + (nb2pb % 10) * Math.pow(2, i);
					nb2pb = nb2pb / 10;
				}
				System.out.println("The decimal equivalent of " + N + " is:-" + decimal);
			}
		}
	}
}
