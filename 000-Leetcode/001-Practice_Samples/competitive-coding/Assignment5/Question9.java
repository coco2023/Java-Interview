package Assignment5;

import java.util.Scanner;

public class Question9 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter String 1:");
		String S1 = scn.next();
		System.out.print("Enter String 2:");
		String S2 = scn.next();
		String S3 = Question4.palindrome(S1, "");
		if (S2.equals(S3)) {
			System.out.println(S2 + " is the reverse of " + S1);
		} else {
			System.out.println(S2 + " is not the reverse of " + S1);
		}
	}

}
