package Miscellaneous;

import java.util.Scanner;

public class UpperOrLower {
//Assignment 2 Question 4
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a letter:");
		char c = scn.next().charAt(0);
		int k = c;
		if (k >= 65 && k <= 90) {
			System.out.println("Uppercase");
		} else if (k >= 97 && k <= 122) {
			System.out.println("Lowercase");
		} else {
			System.out.println("Error");
		}

	}

}
