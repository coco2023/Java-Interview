package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class MultiplesAgain {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		BigInteger b1, b2, b3, b4, small;
		int t = scn.nextInt();
		while (t-- != 0) {
			b1 = scn.nextBigInteger();
			b2 = scn.nextBigInteger();
			b3 = scn.nextBigInteger();
			b4 = b1.multiply(b2).divide(b1.gcd(b2));
			int count = 0;
			if (b4.equals(b3)) {
				count++;
				System.out.println(count);
			} else {
				if (b1.compareTo(b2) < 0) {
					small = b1;
				} else {
					small = b2;
				}
				while (b4.compareTo(b3) <= 0) {
					count++;
					b4 = b4.add(small);
				}
				System.out.println(count);
			}
		}
		scn.close();
	}

}
