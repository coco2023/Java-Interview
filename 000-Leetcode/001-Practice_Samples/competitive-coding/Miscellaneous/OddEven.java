package Miscellaneous;

import java.util.Scanner;

public class OddEven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N-");
		int N = scn.nextInt();
		int i = 1, sum = 0;
		while (i <= N) {
			sum = sum + i;
			i = i + 2;
		}
		System.out.println("Sum of odd numbers is-" + sum);
		i = 2;
		sum = 0;
		while (i <= N) {
			sum = sum + i;
			i = i + 2;
		}
		System.out.println("Sum of even numbers is-" + sum);
		scn.close();
	}

}
