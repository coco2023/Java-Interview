package Assignment3;

import java.util.Scanner;

public class Question4 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a string:");
		String S1 = scn.next();
		char[] distinct = BonusProblem2.distinct(S1);
		int[] count = BonusProblem2.returnCount(S1, distinct);
		int big = -1;
		big = 0;
		for (int j = 1; count[j] != 0; j++) {
			if (count[j] > count[big]) {
				big = j;
			}
		}
		System.out.print(distinct[big]);
		System.out.println(count[big]);
	}

}
