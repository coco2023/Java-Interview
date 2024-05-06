package HackerBlocks;

import java.util.Scanner;

public class IsArmstrongNumber {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		long b1= scn.nextLong();
		System.out.println(isArmstrongNumber(b1));
		scn.close();
	}
	
	public static Boolean isArmstrongNumber(long n){
		long result=0,N=n;
		while(N!=0){
			result+=(N%10)*(N%10)*(N%10);
			N /= 10;
		}
		return result==n;
	}
}
