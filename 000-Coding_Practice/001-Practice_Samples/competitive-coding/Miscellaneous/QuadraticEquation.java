package Miscellaneous;

import java.util.Scanner;

public class QuadraticEquation {
	// Assignment 2 Question 6
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter values of a, b and c:");
		double a = scn.nextDouble();
		double b = scn.nextDouble();
		double c = scn.nextDouble();
		if ((b * b - 4 * (a * c)) >= 0) {
			System.out.println("Real roots exist.");
			double x1 = ((-b + Math.sqrt(b * b - 4 * (a * c))) / (2 * a));
			double x2 = ((-b - Math.sqrt(b * b - 4 * (a * c))) / (2 * a));
			System.out.println("The first real root is:" + x1);
			System.out.println("The second real root is:" + x2);
		} else {
			System.out.println("Imaginary roots exist for the given equation!");
		}
	}

}
