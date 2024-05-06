package HackerBlocks;

import java.util.Scanner;

public class LADDER {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int k = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		int[] ans = new int[1];
		numWays(N, k, 1, false, ans, 0, arr);
		System.out.println(ans[0]);
		scn.close();
	}

	public static void numWays(int N, int k, int start, boolean flag, int[] ans, int val, int[] arr) {
		if (flag || start > N) {
			return;
		}
		if (start == N) {
			ans[0] = val;
			flag = true;
			return;
		}
		for (int i = 1; i <= k; i++) {
			if (start + i <= N && arr[start + i - 1] != 1) {
				numWays(N, k, start + i, flag, ans, val + 1, arr);
			}
		}
	}

}
