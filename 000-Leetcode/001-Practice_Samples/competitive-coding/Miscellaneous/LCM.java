package Miscellaneous;

import java.util.Scanner;

public class LCM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N1 and N2:");
		int N1 = scn.nextInt();
		int N2 = scn.nextInt();
		int small;
		if (N1 > N2) {
			small = N2;
		} else {
			small = N1;
		}
		int lcm = small;
		int i = 2;
		boolean flag = true;
		while (flag) {
			if (lcm % N1 == 0 && lcm % N2 == 0) {
				flag = false;

			} else {
				lcm = small * i;
			}
			i = i + 1;
		}
		System.out.println("LCM is:" + lcm);
	}

}
