package CJK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DiceStraight {
	public static class Pair {
		public int data;
		public int arrayNo;

		@Override
		public String toString() {
			return "[" + data + "," + arrayNo + "]";
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		ArrayList<ArrayList<Pair>> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			ArrayList<Pair> temp = new ArrayList<>();
			for (int j = 0; j < 6; j++) {
				Pair pair = new Pair();
				pair.arrayNo = i;
				pair.data = scn.nextInt();
				temp.add(pair);
			}
			arrayList.add(temp);
		}
		// System.out.println(arrayList);
		for (int i = 0; i < arrayList.size(); i++)
			Collections.sort(arrayList.get(i), new Comparator<Pair>() {
				public int compare(Pair p1, Pair p2) {
					// if (p1.arrayNo == p2.arrayNo)
					return p1.data - p2.data;
					// return p1.arrayNo-p2.arrayNo;
				}
			});
		ArrayList<Integer> lengths = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 6; j++) {
				Pair item = arrayList.get(i).get(j);
				Pair searchVal = new Pair();
				searchVal.data = item.data;
				int length = 0, k = 0;
				while (k < N) {
					if (k == item.arrayNo) {
						k++;
						continue;
					}
					searchVal.data += 1;
					int x = 0;
					for (; x < 6; x++) {
						if (x == item.arrayNo) {
							continue;
						}
						searchVal.arrayNo = x;
						if (contains(arrayList.get(x), searchVal)) {
							length++;
						} else {
							break;
						}
					}
					k++;
					if (length > 1)
						lengths.add(length);
				}
				
			}
		}
		// for(int i=0;i<lengths.size();i++){
		System.out.println(lengths);
		// }
		scn.close();
	}

	public static Boolean contains(ArrayList<Pair> arrayList, Pair p) {
		int left = 0, right = arrayList.size() - 1, mid = (left + right) / 2;
		while (left < right) {
			if (arrayList.get(mid).data < p.data) {
				left = mid + 1;
			} else if (arrayList.get(mid).data > p.data) {
				right = mid - 1;
			} else {
				return true;
			}
			mid = (left + right) / 2;
		}
		return false;
	}
}
