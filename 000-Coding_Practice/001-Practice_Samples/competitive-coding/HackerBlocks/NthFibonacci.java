package HackerBlocks;

import java.util.Scanner;

public class NthFibonacci {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		System.out.println(nthfib(N));
		scn.close();
	}

	public static int nthfib(int N) {
		if (N == 0) {
			return 0;
		}
		if (N == 1) {
			return 1;
		}
		return nthfib(N - 1) + nthfib(N - 2);
	}

}
