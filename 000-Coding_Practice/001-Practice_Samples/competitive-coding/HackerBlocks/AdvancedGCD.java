package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class AdvancedGCD {
	public static void main(String[] args) {
		BigInteger N, M;
		Scanner scn = new Scanner(System.in);
		N = scn.nextBigInteger();
		M = scn.nextBigInteger();
		System.out.println(N.gcd(M));
		scn.close();
	}
}
