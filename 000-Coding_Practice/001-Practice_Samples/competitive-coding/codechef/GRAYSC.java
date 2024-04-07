package codechef;

import java.util.Scanner;

public class GRAYSC {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for(int i = 0;i<N;i++) {
			arr[i] = scn.nextInt();
		}
		if(N >= 130) {
			System.out.println("Yes");
			scn.close();
			return;
		}
		for(int i = 0;i<N;i++) {
			for(int j = i+1;j<N;j++) {
				for(int k = j+1;k<N;k++) {
					for(int l = k+1;k<N;k++) {
						if((arr[i]^arr[j]^arr[k]^arr[l]) == 0) {
							System.out.println("Yes");
							scn.close();
							return;
						}
					}
				}
			}
		}
		System.out.println("No");
		scn.close();
	}
}
