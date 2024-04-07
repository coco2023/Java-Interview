package Miscellaneous;

import java.util.Scanner;

public class FahrenheitToCelsius {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the start value:");
		int start = scn.nextInt();
		System.out.println("Enter the step value:");
		int increment = scn.nextInt();
		System.out.println("Enter the end value:");
		int end = scn.nextInt();
		while (start <= end) {
			int fahrenheit = (5 * (start - 32)) / 9;
			System.out.println(start + " " + fahrenheit);
			start = start + increment;
		}

	}

}
