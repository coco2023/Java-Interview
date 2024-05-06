package Assignment5;

import java.util.ArrayList;
import java.util.Scanner;

public class HackerRank1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the number of squares on the board:");
		int N1 = scn.nextInt();
		System.out.print(countMazePaths(N1, 0, 0, 1));
		// int N2=scn.nextInt();
		// System.out.println(countMazePaths(N1,N2));
	}
	public static ArrayList<Integer> createArray(int N, int k) {
		ArrayList<Integer> baseResult = new ArrayList<>();
		for (int i = k; i <= N/2; i += k) {
			if ((N - i )!= i) {
				baseResult.add(i);
			}
		}
		return baseResult;
	}

	public static int countMazePaths(int N, int count, int counter, int k) {
		if (count == N) {
			return (counter + 1);
		}
		int temp = 0;
		for (int i = 1; i <= 6; i++) {
			if (count + i <= N) {
				temp = i;
			}
		}
		for (int i = 1; i <= temp; i++) {
			ArrayList<Integer> check = new ArrayList<>();
			check = createArray(N, k);
			if (func(N, i, check)) {
				counter = countMazePaths(N, count + i, counter, k);
			} else {
				int temp1 = 0;
				for (int j = 0; j < check.size(); j++) {
					if (count + j == check.get(j)) {
						temp1 = N - i;
					}
				}
				counter = countMazePaths(N, temp1, counter, k);
			}
		}
		return counter;
	}

	public static boolean func(int N, int N1, ArrayList<Integer> X) {
		for (int i = 0; i < X.size(); i++) {
			if (N1 == X.get(i)) {
				return false;
			}
		}
		return true;
	}
}
