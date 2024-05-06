package HackerBlocks;

import java.util.Arrays;
import java.util.Scanner;

public class CountingSort {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scn.nextInt();
		}
		sort(arr, N);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		scn.close();
	}

	public static int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}

	public static void sort(int[] arr, int N) {
		int max = max(arr);
		int[] count = new int[max + 3];
		Arrays.fill(count, 0);
		for (int i = 0; i < N; i++) {
			if (count[arr[i]] == -1)
				count[arr[i]] = 1;
			else
				++count[arr[i]];
		}
		int prev = -1;
		for (int i = 0; i < max + 3; i++) {
			if (prev == -1 && count[i] != -1) {
				prev = i;
			} else if (count[i] != -1) {
				count[i] += count[prev];
				prev = i;
			}
		}
		int[] retval =new int[arr.length];
		for(int i=0;i<N;i++){
			retval[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
		}
		for(int i=0;i<arr.length;i++){
			arr[i]=retval[i];
		}

	}
}
