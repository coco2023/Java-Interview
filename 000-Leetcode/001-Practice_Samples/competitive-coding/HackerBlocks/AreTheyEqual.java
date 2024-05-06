package HackerBlocks;

import java.util.Scanner;

public class AreTheyEqual {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			String s1 = scn.next();
			String s2 = scn.next();
			if (equal(s1, s2)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		scn.close();
	}

	public static boolean equal(String s1, String s2) {
		if (s1.equals(s2)) {
			return true;
		}
		for (int i = 1; i < s1.length(); i++) {
			if (s1.substring(0, i).equals(s2.substring(0, i)) && s1.substring(i).equals(s2.substring(i))) {
				return true;
			}
			if (s1.substring(0, i).equals(s2.substring(i)) && s1.substring(i).equals(s2.substring(0, i))) {
				return true;
			}
		}
		return false;
	}

}
