package Lecture4;

import java.util.Scanner;

public class PalindromeSSSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S1;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter a string:");
		S1 = scn.next();
		System.out.println(isPalindrome(S1));
		StringSequence(S1);
	}

	public static boolean isPalindrome(String S1) {
		boolean flag = true;
		for (int i = 0; i < (S1.length()) / 2; i++) {
			if (S1.charAt(i) != S1.charAt(S1.length() - i - 1)) {
				flag = false;
			}
		}
		return flag;
	}

	public static void printSS(String S1) {
		for (int i = 0; i < S1.length(); i++) {
			for (int j = i; j <= S1.length(); j++) {
				System.out.println(S1.substring(i, j));
			}
		}
	}

	public static void StringSequence(String S1) {
		int i = 0;
		while (i < Math.pow(2,S1.length())) {
			String result="";
			int number=i,bitPosition=0;
			while(number!=0)
			{
				int bit=number%2;
				if(bit==1)
				{
					result=result+S1.charAt(bitPosition);
				}
				number=number/2;
				bitPosition++;
			}
			System.out.println(result);
			i++;
		}
	}
}
