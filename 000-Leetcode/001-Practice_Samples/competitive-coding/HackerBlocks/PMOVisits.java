package HackerBlocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PMOVisits {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int a = scn.nextInt();
			int b = scn.nextInt();
			int temp = a;
			int[] arr = new int[b - a + 1];
			for (; temp <= b; temp++) {
				arr[temp - a] = temp;
			}
			int count = 0;
			for (int i = 0; i < arr.length; i++) {
				if (BigInteger.valueOf(arr[i]).isProbablePrime(Integer.MAX_VALUE)) {
					count++;
				}
			}
			System.out.println(count);
		}
		scn.close();
		
	}
}
