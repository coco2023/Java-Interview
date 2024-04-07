package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GermanLotto {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int k = scn.nextInt();
		int[] arr = new int[k];
		for (int i = 0; i < k; i++) {
			arr[i] = scn.nextInt();
		}
		ArrayList<ArrayList<Integer>> subsets = new ArrayList<>();
		makeSubsets(arr, subsets);
		for (ArrayList<Integer> l : subsets) {
			Collections.sort(l);
		}
		Collections.sort(subsets, new Comparator<ArrayList<Integer>>() {
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				int size = o1.size() > o2.size() ? o2.size() : o1.size();
				for (int i = 0; i < size; i++) {
					if (o1.get(i) != o2.get(i)) {
						return o1.get(i).compareTo(o2.get(i));
					}
				}
				Integer x = o1.size();
				return x.compareTo(o2.size());
			}
		});
		for (ArrayList<Integer> subset : subsets) {
			for (int i = 0; i < subset.size(); i++) {
				System.out.print(subset.get(i) + " ");
			}
			System.out.println();
		}
		scn.close();
	}

	public static void makeSubsets(int[] arr, ArrayList<ArrayList<Integer>> subsets) {
		for (long i = 0; i < (1 << arr.length); i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int j = 0; j < arr.length; j++)
				if ((i & (1 << j)) > 0)
					temp.add(arr[j]);
			if (temp.size() >= 6 && temp.size() <= 13) {
				subsets.add(temp);
			}

		}
	}

}
