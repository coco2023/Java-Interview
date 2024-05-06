package Miscellaneous;

import java.util.Arrays;
import java.util.HashMap;

public class TotientDivisors {

	public static void makeSieve(boolean[] arr) {
		arr[0] = arr[1] = false;
		for (int i = 2; i < arr.length; i++) {
			if (arr[i])
				for (int j = i * i; j < arr.length; j += i) {
					arr[j] = false;
				}
		}
	}

	public static void main(String[] args) {
		boolean[] arr = new boolean[101];
		Arrays.fill(arr, true);
		makeSieve(arr);
		int[] divArr = new int[101];
		Arrays.fill(divArr, -1);
		divArr[1] = 1;
		divArr[0] = 0;
		for (int i = 0; i < divArr.length; i++) {
			if (arr[i]) {
				for (int j = i; j < divArr.length; j += i) {
					if (divArr[j] == -1) {
						divArr[j] = i;
					}
				}
			}
		}
		HashMap<Integer, Integer> divCount = new HashMap<>();
		for (int i = 0; i < divArr.length; i++) {
			divCount.put(i, 0);
			int temp = i;
			while (temp != 0 && temp != 1) {
				divCount.put(i, divCount.get(i)+1);
				if(divArr[temp] != 0)
					temp /= divArr[temp];
			}
		}
		for(int i=0;i<divArr.length;i++) {
			System.out.print(i+":"+divCount.get(i)+",");
		}
		System.out.println();
	}

}
