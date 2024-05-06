package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class SimpleGCD {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t=scn.nextInt();
		int x=scn.nextInt();
		BigInteger b1=BigInteger.valueOf(x);
		for(int i=1;i<t;i++){
			b1=b1.gcd(BigInteger.valueOf(scn.nextInt()));
		}
		System.out.println(b1);
		scn.close();
	}
}
