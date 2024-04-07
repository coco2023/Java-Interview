package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class ArraysBinarySearch {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			arrayList.add(scn.nextInt());
		}
		int M = scn.nextInt();
		int left = 0, right = arrayList.size() - 1;
		int mid = left + right / 2;
		boolean flag = false;
		while (left <= right) {
			if (arrayList.get(mid) > M) {
				right = mid - 1;
			} else if (arrayList.get(mid) < M) {
				left = mid + 1;
			} else {
				System.out.println(mid);
				flag = true;
				break;
			}
			mid = (left + right) / 2;
		}
		if (!flag)
			System.out.println(-1);
		scn.close();
	}
}
