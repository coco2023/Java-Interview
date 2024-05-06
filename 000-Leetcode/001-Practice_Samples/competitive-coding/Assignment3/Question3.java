package Assignment3;

import java.util.Scanner;

public class Question3 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a string:");
		String S1 = scn.next();
		noDuplicates(S1);
	}

	public static void noDuplicates(String S1) {
		char ch1, ch2;
		int i = 0;
		for (i = 0; i < S1.length() - 1; i++) {
			ch1 = S1.charAt(i);
			ch2 = S1.charAt(i + 1);
			if (ch1 != ch2) {
				System.out.print(ch1 + "");
			}
		}
//		i--;
		ch1 = S1.charAt(i);
		ch2 = S1.charAt(i + 1);
		if (ch1 != ch2) {
			System.out.println(ch2);
		}
	}
}
