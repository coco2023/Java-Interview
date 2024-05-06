package Miscellaneous;

import java.util.Scanner;

public class BestofThree {
	// Assignment 2 Question 2
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter name of subject 1:");
		char[] S1 = scn.next().toCharArray();
		System.out.println("Enter marks in subject 1:");
		int S1M = scn.nextInt();
		System.out.println("Enter name of subject 2:");
		char[] S2 = scn.next().toCharArray();
		System.out.println("Enter marks in subject 2:");
		int S2M = scn.nextInt();
		System.out.println("Enter name of subject 3:");
		char[] S3 = scn.next().toCharArray();
		System.out.println("Enter marks in subject 3:");
		int S3M = scn.nextInt();
		if (S3M <= S2M && S3M <= S1M) {
			for (int i = 0; i < S1.length; i++) {
				System.out.print(S1[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S1M);
			for (int i = 0; i < S2.length; i++) {
				System.out.print(S2[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S2M);
			System.out.println("Average:" + (S1M + S2M) / 2);
		} else if (S2M <= S1M && S2M <= S3M) {
			for (int i = 0; i < S1.length; i++) {
				System.out.print(S1[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S1M);
			for (int i = 0; i < S3.length; i++) {
				System.out.print(S3[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S3M);
			System.out.println("Average:" + (S1M + S3M) / 2);
		} else

		{
			for (int i = 0; i < S3.length; i++) {
				System.out.print(S3[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S3M);
			for (int i = 0; i < S2.length; i++) {
				System.out.print(S2[i]);
			}
			System.out.println("");
			System.out.println("Marks:" + S2M);
			System.out.println("Average:" + (S3M + S2M) / 2);
		}
		scn.close();

	}
}
