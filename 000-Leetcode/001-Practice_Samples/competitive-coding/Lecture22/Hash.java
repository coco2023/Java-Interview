package Lecture22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Hash {

	public static void main(String[] args) {

		int[] one = { 1, 1, 1, 1, 2, 2, 2, 3, 5 };
		int[] two = { 1, 1, 1, 2, 2, 4, 5 };
		ArrayList<Integer> inter = new ArrayList<>();
		inter = intersectionwithDuplicates(one, two);
		System.out.println(inter);
		System.out.println(highfreqchar("abbcccddddefeeee"));
		System.out.println(getUniqueValues(one));
		int[] arr = { 5, -5, 0, 0, -5, -5, 0, 3, -5, -2, 0, -3, 5, 0, -3 };
		System.out.println(zeroSumPair(arr));

	}

	public static ArrayList<Integer> intersectionnoDuplicates(int[] one, int[] two) {
		HashMap<Integer, Boolean> map = new HashMap<>();
		ArrayList<Integer> retval = new ArrayList<>();
		for (int i = 0; i < one.length; i++) {
			map.put(one[i], false);
		}
		for (int i = 0; i < two.length; i++) {
			if (map.containsKey(two[i])) {
				map.put(two[i], true);
			}
		}
		Set<Map.Entry<Integer, Boolean>> allEntries = map.entrySet();
		for (Map.Entry<Integer, Boolean> entry : allEntries) {
			if (entry.getValue()) {
				retval.add(entry.getKey());
			}
		}
		return retval;
	}

	public static ArrayList<Integer> intersectionwithDuplicates(int[] one, int[] two) {
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> retval = new ArrayList<>();
		for (int i = 0; i < one.length; i++) {
			if (map.containsKey(one[i])) {
				map.put(one[i], map.get(one[i]) + 1);
			} else {
				map.put(one[i], 1);
			}
		}
		for (int i = 0; i < two.length; i++) {
			if (map.containsKey(two[i])) {
				if (map.get(two[i]) > 0) {
					retval.add(two[i]);
					map.put(two[i], map.get(two[i]) - 1);
				}
			}
		}
		return retval;
	}

	public static Character highfreqchar(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			} else {
				map.put(str.charAt(i), 1);
			}
		}
		Set<Map.Entry<Character, Integer>> allEntries = map.entrySet();
		int max = Integer.MIN_VALUE;
		char h = '*';
		for (Map.Entry<Character, Integer> entry : allEntries) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				h = entry.getKey();
			}
		}
		return h;
	}

	public static ArrayList<Integer> getUniqueValues(int[] arr) {
		ArrayList<Integer> retval = new ArrayList<>();
		HashMap<Integer, Boolean> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], true);
		}
		return new ArrayList<Integer>(map.keySet());
	}

	public static ArrayList<ArrayList<Integer>> zeroSumPair(int[] arr) {
		ArrayList<ArrayList<Integer>> retval = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		Set<Map.Entry<Integer, Integer>> allEntries = map.entrySet();
		for (Map.Entry<Integer, Integer> entry : allEntries) {
			if (map.containsKey((entry.getKey()) * (-1)) && entry.getKey() > 0) {
				while (entry.getValue() > 0 && map.get(entry.getKey() * -1) > 0 && map.get(entry.getKey()) > 0) {
					ArrayList<Integer> temp = new ArrayList<>();
					map.put(entry.getKey() * -1, map.get(entry.getKey()) - 1);
					map.put(entry.getKey(), map.get(entry.getKey()) - 1);
					temp.add(entry.getKey());
					temp.add(-1 * entry.getKey());
					retval.add(temp);
					allEntries = map.entrySet();
				}
			} else if (entry.getKey() == 0 && entry.getValue() >= 2) {
				for (int j = 0; j < entry.getValue() / 2; j++) {
					ArrayList<Integer> temp = new ArrayList<>();
					temp.add(0);
					temp.add(0);
					retval.add(temp);
				}
			}
		}
		return retval;
	}

}
