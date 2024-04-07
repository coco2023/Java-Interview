package Assignment2a;

import java.util.Scanner;

public class Question3 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter values of x and n:");
		int x = scn.nextInt();
		int n = scn.nextInt();
		int i = 1, product = 1;
		while (i <= n) {
			if (n == 0) {
				System.out.println(x + "^" + n + "=1");
				return;
			} else {
				product = product * x;
				i++;
			}
		}
		System.out.println(x + "^" + n + "=" + product);
	}
}
