package Miscellaneous;

import java.util.Scanner;

public class SimpleInterest {
//Assignment 2 Question 1
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the principal,rate and time:");
		int P = scn.nextInt();
		int R = scn.nextInt();
		int T = scn.nextInt();
		int SI = (P * R * T) / 100;
		int amount = P + SI;
		System.out.println("The Simple Interest is:" + SI);
		System.out.println("The amount is:" + amount);
	}

}
