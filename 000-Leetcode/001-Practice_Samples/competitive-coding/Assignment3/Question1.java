package Assignment3;

import java.util.Scanner;

public class Question1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a decimal number:");
		int N = scn.nextInt();
		int temp = N;
		while (temp != 0) {
			System.out.print(temp % 2);
			temp = temp / 2;
		}
		System.out.println();

	}

}
