package HackerBlocks;

import java.util.Arrays;
import java.util.Scanner;

public class DeepakAndPrimesII {

	public static boolean[] primes = new boolean[1000000];

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int a = scn.nextInt();
			int b = scn.nextInt();
			segmented_sieve(a, b);
		}
		scn.close();
	}

	private static void sieve() {
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		for (int i = 4; i < primes.length; i += 2) {
			primes[i] = false;
		}
		for (int i = 2; i < primes.length; i++) {
			if (primes[i]) {
				for (int j = 2 * i; j < primes.length; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	private static void segmented_sieve(int a, int b) {
		sieve();
		boolean[] pp = new boolean[b - a + 1];
		Arrays.fill(pp, true);
		for (int i = 2; i <= b; i++) {
			for (int j = a; j <= b; j++) {
				if (primes[i]) {
					if (j == i) {
						continue;
					}
					if (j % i == 0) {
						pp[(j - a)] = false;
					}
				}
			}
		}
		for (int i = 0; i < pp.length; i++) {
			if (pp[i]) {
				System.out.println(i + a);
			}
		}
		System.out.println();
	}

}
