package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class EvaluatingFunctions {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		BigInteger x = scn.nextBigInteger();
		BigInteger result = BigInteger.ZERO;
		result=result.add(x.multiply(x.multiply(x)).multiply(BigInteger.valueOf(4)).
				add(x.multiply(x).multiply(BigInteger.valueOf(5))).
				subtract(x.multiply(BigInteger.valueOf(6))).
				add(BigInteger.valueOf(14)));
		System.out.println(result);
		scn.close();
	}

}
