package Assignment5;

import java.util.Scanner;

public class Question4 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String str = scn.next();
		String temp = palindrome(str, "");
		if (str.equals(temp)) {
			System.out.print(str + " is a palindrome.");
		} else {
			System.out.print(str + " is not a palindrome.");
		}
	}

	public static String palindrome(String str, String osf) {
		if (str.length() == 1) {
			osf = osf + str;
			return osf;
		} else {
			char cc = str.charAt(str.length() - 1);
			osf = osf + cc;
		}
		String ros = str.substring(0, str.length() - 1);
		ros = palindrome(ros, osf);
		return ros;
	}
}
