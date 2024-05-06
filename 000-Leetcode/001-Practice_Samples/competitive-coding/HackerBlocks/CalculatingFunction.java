package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class CalculatingFunction {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		int t=scn.nextInt();
		for(int i=0;i<t;i++)
		{
			BigInteger N=scn.nextBigInteger();
			if(N.remainder(BigInteger.valueOf(2))== BigInteger.ONE){
				BigInteger result=N.multiply(BigInteger.valueOf(-1));
				System.out.println(result);
				result=result.subtract(BigInteger.valueOf(-1));
				System.out.println(result);
				result=result.divide(BigInteger.valueOf(2));
				System.out.println(result);
			}
			else{
				System.out.println(N.divide(BigInteger.valueOf(2)));
			}
		}
		scn.close();
	}
}
