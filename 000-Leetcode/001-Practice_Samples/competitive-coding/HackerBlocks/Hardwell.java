package HackerBlocks;

import java.util.Scanner;

public class Hardwell {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)=='W'){
				i+=2;
			}
			else{
				result+=str.charAt(i);
			}
		}
		System.out.println(result);
		scn.close();
	}

}
