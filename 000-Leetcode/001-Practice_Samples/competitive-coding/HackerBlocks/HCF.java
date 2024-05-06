package HackerBlocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class HCF {
	public static void main(String[] args) {
		int N;
		Scanner scn = new Scanner(System.in);
		N = scn.nextInt();
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arrayList.add(scn.nextInt());
		}
		BigInteger result = BigInteger.valueOf(arrayList.get(0));
		result = result.gcd(BigInteger.valueOf(arrayList.get(1)));
		if (N == 2) {
			System.out.println(result);
		} else {
			for (int i = 2; i <= N - 3; i++) {
				result = result.gcd(BigInteger.valueOf(arrayList.get(i)));
			}
			System.out.println(result);
		}
	}
}
