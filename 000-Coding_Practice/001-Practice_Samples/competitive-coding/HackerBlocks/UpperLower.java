package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class UpperLower {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
		int N = scn.nextInt();
		for (int i = 0; i < N; i++) {
			int val = scn.nextInt();
			if (hashMap.containsKey(val)) {
				ArrayList<Integer> arrayList = hashMap.get(val);
				arrayList.set(0, arrayList.get(0) + 1);
				arrayList.add(i);
			} else {
				ArrayList<Integer> addVal = new ArrayList<>();
				addVal.add(1);
				addVal.add(i);
				hashMap.put(val, addVal);
			}
		}
		int t = scn.nextInt();
		while (t-- != 0) {
			int check = scn.nextInt();
			ArrayList<Integer> getVal = hashMap.get(check);
			if (getVal == null) {
				System.out.println(-1 + " " + -1);
			} else {
				getVal.remove(0);
				Collections.sort(getVal);
				System.out.println(getVal.get(0) + " " + getVal.get(getVal.size() - 1));

			}
		}
		scn.close();
	}

}
