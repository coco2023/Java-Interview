package Assignment7;

import java.util.Scanner;

import Lecture12.Stack;

public class stockSpan {
	public static void main(String[] args) throws Exception {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the number of elements:");
		final int N = scn.nextInt();
		int[] stock = new int[N];
		for (int i = 0; i < stock.length; i++) {
			System.out.print("Enter stock value on Day " + (i + 1) + ":");
			int temp = scn.nextInt();
			stock[i] = temp;
		}
		Stack PS = new Stack();
		for (int i = stock.length - 1; i >= 0; i--) {
			PS.push(stock[i]);
		}
		Stack PS1 = new Stack();
		for (int i = stock.length - 1; i >= 0; i--) {
			PS1.push(stock[i]);
		}
		int[] si = new int[stock.length];
		for (int i = 0; !PS1.isEmpty(); i++) {
			Stack SS = PS;
			int min = SS.top();
			SS.pop();
			if (SS.isEmpty()) {
				si[i] = 1;
				break;
			}
			int x = SS.pop(), j = 1;
			while (x <= min && !SS.isEmpty()) {
				j++;
				x = SS.pop();
			}
			si[i] = j;
			PS1.pop();
			PS = PS1;

		}
		for (int i = si.length - 1; i >= 0; i--) {
			System.out.println(si[i]);
		}
	}
}
