package Assignment2a;

import java.util.Scanner;

public class Question2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a number:");
		int N = scn.nextInt();
		int temp = N, sumEven = 0, sumOdd = 0;
		while (temp != 0) {
			int x = temp % 10;
			if (x % 2 == 0) {
				sumEven = sumEven + x;
			} else {
				sumOdd = sumOdd + x;
			}
			temp = temp / 10;
		}
		System.out.println("Sum of even digits is:" + sumEven);
		System.out.println("Sum of odd digits is:" + sumOdd);
	}

}
