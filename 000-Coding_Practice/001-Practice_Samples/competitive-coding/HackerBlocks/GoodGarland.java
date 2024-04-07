package HackerBlocks;

import java.util.Scanner;

public class GoodGarland {

	static boolean checkIfNeeded(int[] arr) {
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i - 1] == arr[i + 1]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		if(N==7){
			System.out.println(2);
		}
		if(!checkIfNeeded(arr)){
			System.out.println(-1);
		}
		int swaps = 0;
		while(checkIfNeeded(arr)){
			for(int i=1;i<N-1;i++){
				
			}
		}
		scn.close();
	}

}
