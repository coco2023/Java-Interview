package Miscellaneous;

import java.util.Scanner;

public class FastExponentiation {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int base = input.nextInt();
		int exponent = input.nextInt();
		System.out.println(base+"^"+exponent+"="+power(base,exponent));	
		input.close();
	}
	public static int power(int base, int exponent){
		if(exponent == 0){
			return 1;
		}
		int retval = power(base,exponent/2);
		retval *= retval;
		if(exponent%2 == 1){
			retval *= base;
		}
		return retval;
	}
}