package Assignment5;

import java.util.Scanner;

public class Question7 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a string:");
		String str = scn.next();
		int N = stringTonumber(str, 0);
		System.out.print("The integer form of " + str + " is:" + N);
	}

	public static int stringTonumber(String str, int N) {
		if (str.length() == 0) {
			return N;
		}
		char cc = str.charAt(0);
		if ((int) cc >= 48 && (int) cc <= 57) {
			N = N * 10 + ((int) cc - 48);
		}
		String ros = str.substring(1);
		N = stringTonumber(ros, N);
		return N;
	}
}
