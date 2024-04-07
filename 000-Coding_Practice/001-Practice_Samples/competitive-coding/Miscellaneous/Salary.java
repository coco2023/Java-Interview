package Miscellaneous;

import java.util.Scanner;

public class Salary {
	// Assignment 2 Question 5
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter your basic salary and grade:");
		double basic = scn.nextDouble();
		char grade = scn.next().charAt(0);
		double hra = 0.2 * basic;
		double da = 0.5 * basic;
		double allow;
		if (grade == 'A' || grade == 'a') {
			allow = 1700;
		} else if (grade == 'B' || grade == 'b') {
			allow = 1500;
		} else if (grade == 'C' || grade == 'c') {
			allow = 1300;
		} else {
			allow = 0;
		}
		double pf = 0.11 * basic;
		double t_sal = basic + hra + da + allow - pf;
		System.out.println("The total salary is:" + t_sal);
	}

}
