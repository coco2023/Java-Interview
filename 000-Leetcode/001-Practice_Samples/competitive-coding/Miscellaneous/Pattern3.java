package Miscellaneous;

import java.util.Scanner;

public class Pattern3 {
	// Assignment 2 Question 7
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter number of rows and pattern no:");
		int N = scn.nextInt();
		int p = scn.nextInt();
		if (p == 1) {
			int i = 1;
			while (i <= N) {
				int k = 1;
				while (k <= i) {
					System.out.print("1");
					k = k + 1;
				}
				System.out.println("");
				i++;
			}
		} else if (p == 2) {
			int i = 1;
			while (i <= N) {
				int k = 1;
				while (k <= i) {
					if (k == 1 || k == i) {
						System.out.print("1");
					} else {
						System.out.print("0");
					}
					k = k + 1;
				}
				i++;
				System.out.println("");
			}
		} else if (p == 5) {
			int i = 1;
			while (i <= N) {
				int k = 1;
				while (k <= i) {
					if (k == 1 || k == i) {
						System.out.print("1");
					} else {
						System.out.print("2");
					}
					k = k + 1;
				}
				i++;
				System.out.println("");
			}
		} else if (p == 4) {
			int i = 1;
			while (i <= N) {
				int k = 1;
				while (k <= i) {
					if (i >= 2) {
						if (k == 1 || k == i) {
							System.out.print(i - 1);
						} else {
							System.out.print("0");
						}
						k = k + 1;
					} else {
						while (k <= i) {
							System.out.print("1");
							k = k + 1;
						}
					}

				}
				i = i + 1;
				System.out.println("");
			}

		} else if (p == 6) {
			int i = 1;
			while (i <= N) {
				int k = 1;
				while (k <= i) {
					System.out.print(N + 1 - i);
					k++;
				}
				System.out.println("");
				i++;
			}
		} else if (p == 3) {
			int i = 0;
			while (i < N) {
				int k = 0;
				int value = 1;
				while (k <= i) {
					System.out.print(value + " ");
					k = k + 1;
					value = value * (i + 1 - k);
					value = value / k;
				}
				i = i + 1;
				System.out.println("");
			}
		} else {
			int i = 1;
			while (i <= N) {
				for (int j = 1; j <= N - i; j++) {
					System.out.print(" ");
				}
				int value = 1;
				int k = 0;
				for (int j = 0; j <= i; j++) {
					System.out.print(value + " ");
					k = k + 1;
					value *= (i + 1 - k);
					value /= k;
					if (i == 1) {
						break;
					}
				}
				System.out.println();
				i++;
			}
			// System.out.println("Error!");
		}

	}

}
