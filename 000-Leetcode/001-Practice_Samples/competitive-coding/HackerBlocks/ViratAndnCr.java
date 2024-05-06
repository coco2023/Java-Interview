package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class ViratAndnCr {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		BigInteger N = scn.nextBigInteger();
		BigInteger r = scn.nextBigInteger();
		BigInteger conditional = BigInteger.ZERO;
		BigInteger numerator = BigInteger.ONE;
		BigInteger denominator = BigInteger.ONE;
		BigInteger result = BigInteger.ONE;
		while (conditional.compareTo(N.subtract(r)) == -1) {
			numerator = numerator.multiply(N.subtract(conditional));
			denominator = denominator.multiply((N.subtract(r)).subtract(conditional));
			conditional = conditional.add(BigInteger.ONE);
		}
		result = result.multiply(numerator);
		result = result.divide(denominator);
		System.out.println(result.remainder(BigInteger.valueOf(1000000007)));
	}

}
