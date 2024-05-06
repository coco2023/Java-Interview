package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class NotSoEasyMath {

	public static void main(String[] args) {
		int[] arr = { 2, 3, 5, 7, 11, 13, 17, 19 };
		ArrayList<ArrayList<Integer>> allSubsets = subsets(arr, 0, 8);
		allSubsets.remove(0);
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			long N = scn.nextInt();
			long count = 0;
			for (int i = 0; i < allSubsets.size(); i++) {
				int divider = 1;
				if (allSubsets.get(i).size() % 2 == 0) {
					divider *= -1;
				}
				for (int j = 0; j < allSubsets.get(i).size(); j++) {
					divider *= allSubsets.get(i).get(j);
				}
				count += (N / divider);
			}
			System.out.println(count);
		}
		scn.close();
	}

	public static ArrayList<ArrayList<Integer>> subsets(int[] arr, int si, int li) {
		if (li == si) {
			ArrayList<ArrayList<Integer>> baseResult = new ArrayList<>();
			ArrayList<Integer> arr1 = new ArrayList<>();
			baseResult.add(arr1);
			return baseResult;
		}
		ArrayList<ArrayList<Integer>> temp = subsets(arr, si, li - 1);
		int N = temp.size();
		int x = arr[li - 1];
		ArrayList<ArrayList<Integer>> temp2 = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			temp2.add(temp.get(i));
		}

		for (int i = 0; i < N; i++) {
			int k = temp2.get(i).size();
			ArrayList<Integer> toBeAdded = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				toBeAdded.add(temp2.get(i).get(j));
			}
			toBeAdded.add(x);
			temp2.add(toBeAdded);
		}
		return temp2;
	}

}
