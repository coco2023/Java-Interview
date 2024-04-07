package HackerBlocks;

import java.util.Arrays;

public class LetsPlayGame {

	static boolean[] primes = new boolean[501];

	public static void main(String[] args) {
		
	}

	public static void makeDivisors(int N) {
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		for (int i = 4; i <= 500; i += 2) {
			primes[i] = false;
		}
		for (int i = 3; i <= 500; i += 2) {
			if (primes[i]) {
				for (int j = i * i; j <= 500; j += i) {
					primes[j] = false;
				}
			}
		}
	}

	public static int magicCount(int N) {
		int count = 0;
		
		return 0;
	}

}
