package HackerBlocks;

import java.util.Arrays;
import java.util.Scanner;

public class Misc {
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		int compare = arr[N - 1];
		int i = 0;
		for (; i < N; i++) {
			if (arr[i] < compare) {
				break;
			}
		}
		int[] rotated = new int[i];
		for (int j = 0; j <= i; j++) {
			if (j < rotated.length)
				rotated[j] = arr[j];
			arr[j] = arr[(j + i) % N];
		}

		for (int j = i + 1, x = 0; x < rotated.length; j++, x++) {
			arr[j] = rotated[x];
		}
		int k = scn.nextInt();
		System.out.println((Arrays.binarySearch(arr, k) + i) % N);
		scn.close();
	}
}
