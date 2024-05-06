package Miscellaneous;

import java.util.Scanner;

public class GreenFly {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int days = scn.nextInt();
		System.out.println(greenFly(days));
	}

	public static int greenFly(int days) {
		int x = 1;
		int counter = 1;
		while (counter <= days) {
			if (counter % 7 == 0||counter==1) {
				x = x * 8 + 1;
			}
			counter++;
		}
		return x;
	}
}
