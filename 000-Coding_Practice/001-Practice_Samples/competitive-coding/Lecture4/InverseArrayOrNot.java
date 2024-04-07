package Lecture4;

import java.util.Scanner;

public class InverseArrayOrNot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[]= LargestSearchReverse.takeInput();
		int arr2[]=LargestSearchReverse.takeInput();
		boolean flag=inverse(arr1,arr2);
		System.out.println(flag);
		int arr3[]=printInverse(arr1);
	}
	public static boolean inverse(int[] one,int[] two)
	{
		boolean flag=true;
		for(int i=0;i<one.length;i++)
		{
			if(i!=two[one[i]])
			{
				flag=false;
				break;
			}
		}
		return flag;
	}
	public static int[] printInverse(int[] one)
	{
		int[] two=new int[one.length];
		for(int i=0;i<one.length;i++)
		{
			two[one[i]]=i;
		}
		return two;
	}
	
}
