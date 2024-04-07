package Assignment2a;

import java.util.Scanner;

import Lecture4.LargestSearchReverse;

public class Question8 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a number:");
		int N = scn.nextInt();
		int x1 = integralroot(N);
		System.out.println(x1);
		// integralroot(N);
	}

	public static int integralroot(int N) {
		int sqroot = 0, length = 0, temp = N;
		while (temp != 0) {
			length++;
			temp = temp / 10;
		}
		int[] q;
		if (length % 2 == 0) {
			int x = length / 2, i, j = x - 1;
			temp = N;
			q = new int[x];
			while (j >= 0) {
				q[j--] = (int) (temp % Math.pow(10, 2));
				temp = (int) (temp / Math.pow(10, 2));
			}

			j = 1;
			while (j <= length / 3) {
				i = 0;
				x = 1;
				int temp1 = 0;
				while (i < 9) {
					temp1 = x;
					if (((x + 1) * (x + 1)) <= q[j - 1]) {
						temp1 = ++x;
					}
					i++;
				}
				sqroot = (int) (sqroot + temp1 * Math.pow(10, (length / 2) - (j)));
				j++;
			}
		} else {
			int x = length / 2;
			x++;
			int i, j = x - 1;

		}
		return sqroot;
	}

	// public static void nonintegralroot(int N) {
	//
	// }

}
