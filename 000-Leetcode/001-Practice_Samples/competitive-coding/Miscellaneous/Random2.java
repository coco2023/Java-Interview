package Miscellaneous;

import java.util.ArrayList;

public class Random2 {

	public static void main(String[] args) {
		System.out.println(mazePaths(2,4,new ArrayList<>(),1));
	}

	public static ArrayList<ArrayList<Integer>> mazePaths(int K, int N,ArrayList<Integer> temp, int cp) {
		if (cp >= N) {
			if (cp == N) {
				ArrayList<ArrayList<Integer>> baseresult = new ArrayList<>();
				baseresult.add(temp);
				return baseresult;
			}

		}
		int temp1 = 0;
		for (int i = 1; i - cp <= K && i + cp <= N; i++) {
			temp1++;
		}
		ArrayList<ArrayList<Integer>> retmaze = new ArrayList<>();
		for (int i = 1; i <= temp1; i++) {
			temp.add(i);
			retmaze = mazePaths(K, N, temp, cp + i);
		}
		return retmaze;
	}
}
