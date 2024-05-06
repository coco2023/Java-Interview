package Assignment3;

import java.util.Scanner;

public class Question5 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter number of strings:");
		int N = scn.nextInt();
		String[] S1 = new String[N];
		for (int i = 0; i < N; i++) {
			System.out.print("Enter a string:");
			S1[i] = scn.next();
		}
		sortArray(S1, N);
		for (int i = 0; i < N; i++) {
			System.out.println(S1[i]);
		}
	}

	public static void sortArray(String[] S1, int N) {
		int[] lengths = new int[N];
		for (int i = 0; i < N; i++) {
			lengths[i] = 0;
			for (int j = 0; j < S1[i].length(); j++) {
				lengths[i]++;
			}
		}
		for (int i = 0; i < N - 1; i++) {
			int small = i;
			for (int j = i + 1; j < N; j++) {
				if (lengths[j] < lengths[small]) {
					small = j;
				}
			}
			if (small != i) {
				String S2 = S1[i];
				S1[i] = S1[small];
				S1[small] = S2;
				int temp = lengths[i];
				lengths[i] = lengths[small];
				lengths[small] = temp;
			}
		}
	}
}
