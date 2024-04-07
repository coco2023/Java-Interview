package Miscellaneous;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number to be checked-");
		int N=scn.nextInt();
		int i=2,flag=0;
		while(i<N/2)
		{
				int k=N%i;
				if(k==0)
					{
						flag=1;
					}
				i++;
		}
		if(flag==1)
		{
			System.out.println("Number is not prime");
		}
		else
		{
			System.out.println("Number is prime");
		}
		}
		

}
