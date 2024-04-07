package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class PrimalityChecker {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while(t--!=0){
			BigInteger b1 = scn.nextBigInteger();
			if(b1.isProbablePrime(Integer.MAX_VALUE)){
				System.out.println("YES");
			}
			else{
				System.out.println("NO");
			}
		}
		scn.close();
	}
}
