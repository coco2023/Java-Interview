package HackerBlocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TPrime {

	public static HashMap<Integer, Boolean> hashMap = new HashMap<>();
	public static boolean[] primeSieve = new boolean[100000];

	public static void precompute() {
		Arrays.fill(primeSieve, true);
		for (int i = 4; i < primeSieve.length; i += 2) {
			primeSieve[i] = false;
		}
		for (int i = 3; i < primeSieve.length; i += 2) {
			if (primeSieve[i]) {
				for (int j = i * i; j < primeSieve.length; j += i) {
					if (j > 0)
						primeSieve[j] = false;
				}
			}
		}
		// Arrays.fill(Tprimes, false);
		primeSieve[0] = primeSieve[1] = false;
		for (int i = 0; i < primeSieve.length; i++) {
			if (primeSieve[i] && i * i < primeSieve.length) {
				hashMap.put(i * i, true);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		precompute();
		int t = scn.nextInt();
		while (t-- != 0) {
			int x = scn.nextInt();
			if (hashMap.containsKey(x)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		scn.close();
	}

}
