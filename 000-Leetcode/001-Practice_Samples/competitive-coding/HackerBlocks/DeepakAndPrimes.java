package HackerBlocks;

import java.util.Scanner;

public class DeepakAndPrimes {

	static boolean prime[] = new boolean[10000000];

	public static void sieveOfEratosthenes() {

		for (int i = 0; i < prime.length; i++)
			prime[i] = true;

		for (int p = 2; p * p < prime.length; p++) {
			if (prime[p] == true) {
				for (int i = p * p; i < prime.length; i += p)
					prime[i] = false;
			}
		}
		prime[0] = prime[1] = false;

	}

	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int counter = 0, pos = 0;
		sieveOfEratosthenes();
		while (counter != N) {
			if (prime[pos]) {
				counter++;
			}
			pos++;
		}
		System.out.println(pos - 1);
		scn.close();
	}
}
