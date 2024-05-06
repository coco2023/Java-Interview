package Assignment2a;

import java.util.Scanner;

public class Question4 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a number:");
		int N = scn.nextInt();
		int temp = N, rev = 0;
		while (temp != 0) {
			rev = rev * 10 + temp % 10;
			temp = temp / 10;
		}
		System.out.println("The reverse of " + N + " is:" + rev);
	}

}
