package Assignment2a;

import java.util.Scanner;

public class Question1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N:");
		int N = scn.nextInt();
		System.out.println("Enter 'S' for sum from 1 to N and 'P' for product from 1 to N:");
		char choice = scn.next().charAt(0);
		if (choice == 'S') {
			int i = 1, sum = 0;
			while (i <= N) {
				sum += i;
				i++;
			}
			System.out.println("The sum is:" + sum);
		} else if (choice == 'P') {
			int i = 1, product = 1;
			while (i <= N) {
				product = product * i;
				i++;
			}
			System.out.println("The product is:" + product);
		} else {
			System.out.println("Error!");
		}
	}

}
