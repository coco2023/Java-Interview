package Miscellaneous;

import java.util.Scanner;
//Assignment 2 Question 3
public class Calculator {
	// Assignment 2 Question 3
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter two numbers:");
		int N1 = scn.nextInt();
		int N2 = scn.nextInt();
		System.out.println("Enter +,-,/ or *:");
		char x = scn.next(".").charAt(0);
		if (x == '+') {
			System.out.println(N1 + N2);
			return;
		} else if (x == '-') {
			System.out.println(N1 - N2);
			return;
		}

		else if (x == '/') {
			System.out.println(N1 / N2);
			return;
		} else {
			System.out.println(N1 * N2);
			return;
		}

	}

}
