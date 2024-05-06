package Assignment6;

import java.util.Scanner;

import Lecture6.Recursion;

public class Question2 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String str = scn.next();
		System.out.println("The following are the subsequences:");
		Recursion.printSubSequences(str, "");
	}

}
