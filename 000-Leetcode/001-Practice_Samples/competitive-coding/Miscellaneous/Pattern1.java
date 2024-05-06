package Miscellaneous;

import java.util.Scanner;

public class Pattern1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N-");
		int N = scn.nextInt();
		int i = 1, value = 1;
		while (i <= N) {
			int flag = 1;
			while (flag <= i) {
				System.out.print(value + " ");
				value = value + 1;
				flag = flag + 1;
			}
			i++;
			System.out.println("");
			scn.close();
		}
	}

}
