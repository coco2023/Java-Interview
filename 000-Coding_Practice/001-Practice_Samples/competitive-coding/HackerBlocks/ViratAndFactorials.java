package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class ViratAndFactorials {
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		int N=scn.nextInt();
		BigInteger result=BigInteger.ONE;
		while(N!=0){
			result=result.multiply(BigInteger.valueOf(N));
			N--;
		}
		System.out.println(result);
	}
}
