package Miscellaneous;

import java.util.Scanner;

public class PrimeNos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn= new Scanner(System.in);
		System.out.println("Enter the value of N-");
		int N=scn.nextInt();
		int i=2;
		while(i<=N)
		{
			int i1=2,flag=0;
			while(i1<=i/2)
			{
				if(i%i1==0)
				{
					flag=1;
				}
				i1=i1+1;
				
			}
			if(flag==0)
			{
				System.out.println(i);
			}
				i=i+1;
		}
	}

}
