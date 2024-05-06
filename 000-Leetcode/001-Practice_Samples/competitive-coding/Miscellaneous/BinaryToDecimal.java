package Miscellaneous;

import java.util.Scanner;

public class BinaryToDecimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn=new Scanner(System.in);
		System.out.println("Enter the binary number you wish to convert to decimal:");
		int N=scn.nextInt();
		int temp=N;
		int temp1;
		int i=0;
		int decimalc=0;
		while(temp!=0)
		{
			temp1=temp%10;
			int k=1;
			while(k<=i)
				{
					k=k*2;
				}
			decimalc=decimalc+temp1*k;
			temp=temp/10;
			i=i+1;
		}
		System.out.println("The decimal equivalent is:"+decimalc);
		
	}

}
