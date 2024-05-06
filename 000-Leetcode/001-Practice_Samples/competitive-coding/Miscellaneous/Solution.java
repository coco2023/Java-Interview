package Miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scn.nextInt();
		}
		Pair p = new Pair();
		numTriangles(arr, p, 0, -1);
		System.out.println(p.result.size());
	}

	private static class Pair {
		ArrayList<Integer> temp;
		ArrayList<ArrayList<Integer>> result;

		public Pair() {
			this.temp = new ArrayList<>();
			this.result = new ArrayList<>();
		}
	}

	public static void numTriangles(int[] arr, Pair p, int i, int x) {
		if (i == arr.length) {
			p.temp = new ArrayList<>();
			return;
		}
		if (x != -1) {
			p.temp.add(arr[x]);
		}
		if (p.result.size() == 3) {
			p.result.add(p.temp);
			p.temp = new ArrayList<>();
			return;
		}
		if (p.temp.size() != 2) {
			numTriangles(arr, p, i + 1, -1);// didn't add the ith element;
			x = i;
			numTriangles(arr, p, i + 1, x);// added the ith element

		} else {
			if (arr[i] < (p.temp.get(0) + p.temp.get(1)) && p.temp.get(0) < arr[i] + p.temp.get(1)
					&& p.temp.get(1) < arr[i] + p.temp.get(0)) {
				x = i;
				numTriangles(arr, p, i + 1, x);
			} else {
				numTriangles(arr, p, i + 1, -1);
			}
		}
	}

	public static ArrayList<Integer> maxpertri(int[] Arr) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		Pair p = new Pair();
		numTriangles(Arr, p, 0, -1);
		result = p.result;
		if (result.size() == 0) {
			ArrayList<Integer> retval = new ArrayList<>();
			retval.add(-1);
			return retval;
		} else if (result.size() == 1) {
			return result.get(0);
		}
		int[] arr = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				arr[i] += result.get(i).get(j);
			}
		}
		int max = 0, maxcount = 0, maxi = 0;
		int[] temp = new int[arr.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = -1;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= max) {
				maxi = i;
				max = arr[i];
				if (arr[i] == max) {
					temp[i] = i;
					maxcount++;
				}
			}
		}
		if (maxcount == 1) {
			return result.get(maxi);
		} else {
			int j;
			for (j = 0; j < temp.length; j++) {
				if (temp[j] != -1) {
					break;
				}
			}
			ArrayList<Integer> compare = result.get(j);
			Integer maxtemp = Collections.max(compare);
			for (int i = j + 1; i < temp.length; i++) {
				if (temp[i] != -1) {
					if (Collections.max(result.get(i)) > maxtemp) {
						compare = result.get(i);
					}
				}
			}
			return compare;
		}
	}
}
