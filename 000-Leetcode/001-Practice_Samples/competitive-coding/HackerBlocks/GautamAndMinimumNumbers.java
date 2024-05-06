package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GautamAndMinimumNumbers {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arrayList.add(scn.nextInt());
		}
		Collections.sort(arrayList);
		while (arrayList.size() != 1) {
			int A = arrayList.remove(0);
			int B = arrayList.remove(0);
			int q_type = scn.nextInt();
			int p = scn.nextInt();
			if (q_type == 1) {
				arrayList.add(A + B + p);
			} else {
				arrayList.add(A - B + p);
			}
			Collections.sort(arrayList);
		}
		System.out.println(arrayList.get(0));
		scn.close();
	}

}
