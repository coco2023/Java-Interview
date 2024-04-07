package HackerBlocks;

import java.util.Arrays;
import java.util.Scanner;

public class DIVSUBS {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int N = scn.nextInt();
			long[] arrNums = new long[N];
			long[] prefixSums = new long[N + 1];
			prefixSums[0] = 1;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				arrNums[i] = scn.nextLong();
				sum += arrNums[i];
				sum %= N;
				sum = (sum + N) % N;
				prefixSums[(int) sum]++;
			}
			long answer = 0;
			for (int i = 0; i < N; i++) {
				answer += (prefixSums[i] * (prefixSums[i] - 1)) / 2;
			}
			System.out.println(answer);
		}
		scn.close();
	}

}
