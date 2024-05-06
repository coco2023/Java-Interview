package Assignment3;

import java.util.Scanner;

public class BonusProblem2 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String S1 = scn.next();
		char[] S2 = distinct(S1);
		int[] count = returnCount(S1, S2);
		for (int i = 0; i < S2.length; i++) {
			if (S2[i] != '\0') {
				if (count[i] != 1) {
					System.out.print((char) S2[i]);
					System.out.print(count[i]);
				} else {
					System.out.print(S2[i]);
				}
			}
		}
		System.out.println();

	}

	public static boolean searchString(char[] S1, char ch) {
		boolean flag = true;
		for (int i = 0; i < S1.length; i++) {
			if (S1[i] == ch) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static char[] distinct(String S1) {
		char[] temp = new char[50];
		char[] distinct = new char[50];
		int i = 0;
		for (i = 0; i < S1.length(); i++) {
			char ch = S1.charAt(i);
			if (searchString(temp, ch)) {
				temp[i] = (char) ch;
			}
		}
		temp[++i] = '\0';
		int j = 0;
		for (i = 0; i <= S1.length() + 1; i++) {
			if (temp[i] != '\u0000') {
				distinct[j++] = temp[i];
			}
		}
		return distinct;
	}

	public static int[] returnCount(String S1, char[] distinct) {
		int[] count = new int[distinct.length];
		for (int i = 0; i < distinct.length; i++) {
			for (int j = 0; j < S1.length(); j++) {
				if (S1.charAt(j) == distinct[i]) {
					count[i]++;
				}
			}
		}
		return count;
	}
}
