package Miscellaneous;

import java.util.ArrayList;
import java.util.Scanner;

class GFG {
	public static void main (String[] args) throws Exception {
		Scanner scn=new Scanner(System.in);
		int N=scn.nextInt();
		countDivisors(N);
	}
	public static void countDivisors(int N)
	{
	    int fact=1,temp=N;
	    while(temp!=0)
	    {
	        fact=fact*(temp);
	        temp=temp-1;
	    }
	    int i=1;
	    ArrayList<Integer> retVal=new ArrayList<>();
	    while(i<=fact)
	    {
	        if(fact%i==0)
	        {
	            retVal.add(i);
	        }
	        i++;
	    }
	    System.out.println(fact);
	    System.out.print(N+"! is "+fact+". Divisors of "+fact+" are ");
	    for(i=0;i<retVal.size()-2;i++)
	    {
	        System.out.print(retVal.get(i)+", ");
	    }
	    System.out.println(retVal.get(retVal.size()-2)+" and "+retVal.get(retVal.size()-1));
	}
}