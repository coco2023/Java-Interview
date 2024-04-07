package Lecture26;

import java.util.ArrayList;

public class DP {

	public static void main(String[] args) {
		// System.out.println(editDistance("aaaddadada", "adsasdasasd"));
		// System.out.println(editDistanceI("aaaddadada", "adsasdasasd"));
		System.out.println(editDistanceI("bbaaaddada", "adsasasd"));
		EDPair p = new EDPair(Integer.MAX_VALUE, "");
		editDistance("bbaaaddada", "adsasasd", "Begin", 0, p);
		System.out.println(p.cost);
		System.out.println(p.ops);
		System.out.println("END");
		editDistance("bbaaaddada", "adsasasd");
		Pair p1 = new Pair();
	}

	public static int editDistance(String S1, String S2) {
		if (S1.length() == 0) {
			return S2.length();// removal
		}
		if (S2.length() == 0) {
			return S1.length();// insertion
		}
		char ch1 = S1.charAt(0);
		char ch2 = S1.charAt(0);
		String ros1 = S1.substring(1);
		String ros2 = S2.substring(1);
		int rv = 0;
		if (ch1 == ch2) {
			rv = editDistance(ros1, ros2);
		} else {
			int f1 = 1 + editDistance(ros1, ros2);
			int f2 = 1 + editDistance(S1, ros2);
			int f3 = 1 + editDistance(ros1, S2);
			rv = Math.min(f1, Math.min(f2, f3));
		}
		return rv;
	}

	private static class EDPair {
		int cost;
		String ops;

		public EDPair(int cost, String ops) {
			this.cost = cost;
			this.ops = ops;
		}
	}

	public static void editDistance(String S1, String S2, String psf, int csf, EDPair p) {
		if (S1.length() == 0) {
			for (int i = 0; i < S2.length(); i++) {
				psf += ",{" + S2.charAt(i) + "-rm}";
				csf += 1;
			}
			if (csf < p.cost) {
				p.cost = csf;
				p.ops = psf;
			}
			return;
		}
		if (S2.length() == 0) {
			for (int i = 0; i < S1.length(); i++) {
				psf += ",{" + S1.charAt(i) + "-i}";
				csf += 1;
			}
			if (csf < p.cost) {
				p.cost = csf;
				p.ops = psf;
			}
			return;
		}
		char ch1 = S1.charAt(0);
		char ch2 = S2.charAt(0);
		String ros1 = S1.substring(1);
		String ros2 = S2.substring(1);
		if (ch1 == ch2) {
			editDistance(ros1, ros2, psf + ",{N}", csf, p);
		} else {
			editDistance(ros1, ros2, psf + ",{" + ch2 + "-rep-" + ch1 + "}", csf + 1, p);
			editDistance(S1, ros2, psf + ",{" + ch2 + "-rm}", csf + 1, p);
			editDistance(ros1, S2, psf + ",{" + ch1 + "-i}", csf + 1, p);
		}
	}

	private static class Pair {
		ArrayList<Integer> include;
		ArrayList<Integer> exclude;

		public Pair() {
			this.include = new ArrayList<>();
			this.exclude = new ArrayList<>();
		}
	}

	public static void minDiffSA(int[] arr, int si, int sumI, int sumE, ArrayList<Integer> include,
			ArrayList<Integer> exclude, Pair p) {
		if (si >= arr.length) {
			p.include = include;
			p.exclude = exclude;
			return;
		}
		include.add(arr[si]);
		minDiffSA(arr, si + 1, sumI + arr[si], sumE, include, exclude, p);
		exclude.add(arr[si]);
		minDiffSA(arr, si + 1, sumI, sumE + arr[si], include, exclude, p);
	}

	public static int editDistanceI(String S1, String S2) {
		int[][] storage = new int[S1.length() + 1][S2.length() + 1];
		storage[0][0] = 0;
		for (int i = 0; i <= S1.length(); i++) {
			for (int j = 0; j <= S2.length(); j++) {
				if (i == 0) {
					storage[i][j] = j;
					continue;
				}
				if (j == 0) {
					storage[i][j] = i;
					continue;
				}
				if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
					storage[i][j] = storage[i - 1][j - 1];
				} else {
					int f1 = 1 + storage[i - 1][j - 1];
					int f2 = 1 + storage[i][j - 1];
					int f3 = 1 + storage[i - 1][j];
					storage[i][j] = Math.min(f1, Math.min(f2, f3));
				}
			}
		}
		return storage[S1.length()][S2.length()];
	}
	
}
