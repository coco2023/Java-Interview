package HackerBlocks;

import java.util.Arrays;
import java.util.Scanner;

public class FactorizationGame {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int N = scn.nextInt();
			int[] arr = new int[N];
			for(int i=0;i<N;i++){
				arr[i]=scn.nextInt();
			}
			Arrays.sort(arr);
			
		}
		scn.close();
	}

}
