package HackerBlocks;

import java.util.Scanner;

public class SimpleFactorial {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		long result = 0;
		while (N != 0) {
			result += result(scn.nextInt());
			result %= 107;
			N--;
		}
		result%=107;
		System.out.println(result);
		scn.close();
	}

	public static long result(int N) {
		long result = 1;
		for (int i = 1; i <= N; i++) {
			result *= i;
			result %= 107;
		}
		return result % 107;
	}
}
