package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StringSort {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < t; i++) {
			arrayList.add(scn.next());
		}
		Collections.sort(arrayList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.contains(o2)) {
					return o2.compareTo(o1);
				} else {
					return o1.compareTo(o2);
				}
			}
		});
		for(int i=0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i));
		}
		scn.close();
	}

}
