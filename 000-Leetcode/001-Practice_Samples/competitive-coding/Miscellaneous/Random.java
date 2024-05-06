package Miscellaneous;

import java.util.ArrayList;
import java.util.Scanner;

public class Random {

	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in);
		// double payment = scanner.nextDouble();
		// scanner.close();
		// String S1 = new String();
		// S1 = "" + payment;
		// // System.out.println(S1);
		// int pospoint = -1;
		// for (int i = 0; i < S1.length(); i++) {
		// if (S1.charAt(i) == '.') {
		// pospoint = i;
		// }
		// }
		// // 1234567.890
		// ArrayList<Character> Sreturn = new ArrayList<>();
		// if (pospoint > 4) {
		// for (int i = 0; i < S1.length(); i++) {
		// Sreturn.add(S1.charAt(i));
		// }
		// if (pospoint != -1) {
		// for (int i = pospoint - 1; i >= 0; i--) {
		// if (pospoint - i == 3) {
		// Sreturn.add(i, ',');
		// } else if ((pospoint - i) % 2 == 1 && pospoint - i > 4) {
		// if (i != 0 && Sreturn.get(i) != ',') {
		// Sreturn.add(i, ',');
		// }
		// }
		// }
		// }
		// } else {
		// for (int i = 0; i < S1.length(); i++) {
		// Sreturn.add(S1.charAt(i));
		// }
		// }
		// for (int i = 0; i < Sreturn.size(); i++) {
		// if (Sreturn.get(i) == '.') {
		// pospoint = i;
		// }
		// }
		// int x = -1;
		// if (Sreturn.size() - pospoint > 2) {
		// x = pospoint + 2;
		// }
		// if (x != -1) {
		// for (int i = Sreturn.size() - 1; i > x; i--) {
		// if ((int) Sreturn.get(i) > 53) {
		// int k = (int) Sreturn.get(i - 1);
		// k++;
		// String temp = new String();
		// temp = (k - 48) + "";
		// char c = temp.charAt(0);
		// Sreturn.set(i - 1, c);
		// }
		// Sreturn.remove(i);
		// }
		// }
		// System.out.print("US format = $");
		// for (int i = 0; i <= x; i++) {
		// System.out.print(Sreturn.get(i));
		// }
		// System.out.println();
		// System.out.print("India format = Rs.");
		// for (int i = 0; i <= x; i++) {
		// System.out.print(Sreturn.get(i));
		// }
		// boolean[] arr = new boolean[34];
		// for (int i = 0; i < 34; i++) {
		// arr[i] = true;
		// }
		// getPos2(arr, 2, 0);
		// for (int i = 0; i < 34; i++) {
		// if (arr[i]) {
		// System.out.println(i + 1);
		// return;
		// }
		// }
		System.out.println(getPos3(2));
	}

	public static void getPos(boolean[] arr, int i, int counter, int j) {
		if (counter >= arr.length - 1) {
			return;
		}
		if (i >= arr.length) {
			j *= 2;
			for (int k = 0; k < arr.length; k++) {
				if (arr[k]) {
					i = k;
					break;
				}
			}
		}
		arr[i] = false;
		counter++;
		getPos(arr, i + j, counter, j);
	}

	public static int getPos2(boolean[] arr, int j, int counter) {
		int i = 0, k = 1;
		while (counter < arr.length - 1) {
			arr[i] = false;
			i += j;
			if (i >= arr.length) {
				i = k;
				k += j;
				j *= 2;

			}
			counter++;
		}
		return k;
	}

	public static int getPos3(int N) {
		int i = 1, j = 2, counter = 0;
		while (counter < N - 1) {
			if (i > N) {
				i = j;
				j *= 2;
			}
			i = i + j;
			counter++;
		}
		return j;
	}
}
