package Assignment4;

import java.util.Scanner;

public class Question1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a number:");
		int N = scn.nextInt();
		int[] Narray = numbertoArray(N);
		pushzeroestoEnd(Narray);
	}

	public static int[] numbertoArray(int N) {
		int length = 0, temp = N;
		while (temp != 0) {
			length++;
			temp = temp / 10;
		}
		temp = N;
		int[] Narray = new int[length];
		for (int i = Narray.length - 1; i >= 0; i--) {
			Narray[i] = temp % 10;
			temp = temp / 10;
		}
		return Narray;
	}

	public static void pushzeroestoEnd(int[] Narray) {
		int j = 0;
		int[] ZeroesAtEnd = new int[Narray.length];
		for (int i = 0; i < Narray.length; i++) {
			if (Narray[i] != 0) {
				ZeroesAtEnd[j++] = Narray[i];
			}
		}
		for (int i = 0; i < ZeroesAtEnd.length; i++) {
			Narray[i] = ZeroesAtEnd[i];
		}
		for (int i = 0; i < Narray.length; i++) {
			System.out.print(Narray[i] + ",");
		}
		System.out.println("END");

	}
}
