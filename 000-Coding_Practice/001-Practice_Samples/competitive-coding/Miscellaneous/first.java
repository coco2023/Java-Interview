package Miscellaneous;

import java.util.ArrayList;
import java.util.Scanner;

public class first {

	public static void main(String[] args) {
		testBoardWithSnakesAndLadders();
	}

	public static ArrayList<Integer> getPrimes(int n) {
		ArrayList<Integer> retVal = new ArrayList<>();

		boolean[] map = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {
			map[i] = true;
		}

		for (int i = 2; i * i <= n; i++) {
			if (map[i]) {
				for (int j = 2; i * j <= n; j++) {
					map[i * j] = false;
				}
			}
		}

		for (int i = 2; i <= n; i++) {
			if (map[i]) {
				retVal.add(i);
			}
		}

		return retVal;
	}

	/*
	 * Without snakes and without ladders
	 */
	public static void testBoardSimple() {
		int N;
		Scanner scn = new Scanner(System.in);

		System.out.println("Please enter the final number on board ");
		N = scn.nextInt();

		System.out.println(countPathsSimple(0, N));
//		System.out.println(getPathsSimple(0, N));
	}

	/*
	 * Without snakes and without ladders
	 */
	public static int countPathsSimple(int num, int finalNum) {
		if (num >= finalNum) {
			if (num == finalNum) {
				return 1;
			} else {
				return 0;
			}
		}

		int count = 0;

		for (int i = 1; i <= 6; i++) {
			count += countPathsSimple(num + i, finalNum);
		}

		return count;
	}

	/*
	 * Without snakes and without ladders
	 */
	public static ArrayList<String> getPathsSimple(int num, int finalNum) {
		if (num >= finalNum) {
			ArrayList<String> baseResult = new ArrayList<>();

			if (num == finalNum) {
				baseResult.add("END\n");
			}

			return baseResult;
		}

		ArrayList<String> myResult = new ArrayList<>();
		ArrayList<String> recResult = null;

		for (int i = 1; i <= 6; i++) {
			recResult = getPathsSimple(num + i, finalNum);

			for (int j = 0; j < recResult.size(); j++) {
				myResult.add(num + " => " + recResult.get(j));
			}
		}

		return myResult;
	}

	/*
	 * Board without snakes, but ladders
	 */
	public static void testBoardWithLadders() {
		int N, M;
		Scanner scn = new Scanner(System.in);

		System.out.println("Please enter the final number on board ");
		N = scn.nextInt();

		System.out.println("Please enter the multiple for making ladders ");
		M = scn.nextInt();

		ArrayList<Integer> map = new ArrayList<>();
		ArrayList<Integer> reverse = new ArrayList<>();

		for (int i = 1; true; i++) {
			int begin = 0 + (M * i);
			int end = N - (M * i);

			if (begin >= end) {
				break;
			} else {
				map.add(begin);
				reverse.add(end);
			}
		}

		System.out.println(countPathsWithLadders(0, N, map, reverse));
//		System.out.println(getPathsWithLadders(0, N, map, reverse));
	}

	/*
	 * Board without snakes, but ladders
	 */
	public static int countPathsWithLadders(int num, int finalNum, ArrayList<Integer> map, ArrayList<Integer> reverse) {
		if (num >= finalNum) {
			return num == finalNum ? 1 : 0;
		}

		int count = 0;

		int index = map.indexOf(num);
		if (index != -1) {
			count += countPathsWithLadders(reverse.get(index), finalNum, map, reverse);
		} else {
			for (int i = 1; i <= 6; i++) {
				count += countPathsWithLadders(num + i, finalNum, map, reverse);
			}
		}

		return count;
	}

	/*
	 * Board without snakes, but ladders
	 */
	public static ArrayList<String> getPathsWithLadders(int num, int finalNum, ArrayList<Integer> map,
			ArrayList<Integer> reverse) {
		if (num >= finalNum) {
			ArrayList<String> baseResult = new ArrayList<>();

			if (num == finalNum) {
				baseResult.add("END\n");
			}

			return baseResult;
		}

		ArrayList<String> myResult = new ArrayList<>();
		ArrayList<String> recResult = null;

		int index = map.indexOf(num);
		if (index != -1) {
			recResult = getPathsWithLadders(reverse.get(index), finalNum, map, reverse);

			for (int i = 0; i < recResult.size(); i++) {
				myResult.add(num + " => " + recResult.get(i));
			}
		} else {
			for (int i = 1; i <= 6; i++) {
				recResult = getPathsWithLadders(num + i, finalNum, map, reverse);

				for (int j = 0; j < recResult.size(); j++) {
					myResult.add(num + " => " + recResult.get(j));
				}
			}
		}

		return myResult;
	}

	/*
	 * Test Board with snakes and with ladders
	 */
	public static void testBoardWithSnakesAndLadders() {
		int N, M;
		Scanner scn = new Scanner(System.in);

		System.out.println("Please enter the final number on board ");
		N = scn.nextInt();

		System.out.println("Please enter the multiple for making ladders ");
		M = scn.nextInt();

		ArrayList<Integer> map = new ArrayList<>();
		ArrayList<Integer> reverse = new ArrayList<>();

		for (int i = 1; true; i++) {
			int begin = 0 + (M * i);
			int end = N - (M * i);

			if (begin >= end) {
				break;
			} else {
				if (i % 2 == 1) { // ladder
					map.add(begin);
					reverse.add(end);
				} else { // snake
					map.add(end);
					reverse.add(begin);
				}
			}
		}

		ArrayList<Integer> diceTurns = new ArrayList<>();

		System.out.println("Please enter the number of dice turns ");
		int numTurns = scn.nextInt();

		for (int i = 1; i <= numTurns; i++) {
			System.out.println("Enter dice turn ");
			diceTurns.add(scn.nextInt());
		}

		System.out.println(isValidPath(0, N, map, reverse, diceTurns, 0));
	}

	/*
	 * Board with snakes and with ladders
	 */
	public static boolean isValidPath(int num, int finalNum, ArrayList<Integer> map, ArrayList<Integer> reverse,
			ArrayList<Integer> diceTurns, int diceSI) {
		if(num == finalNum){
			return true;
		}
		
		if (diceSI == diceTurns.size()) {
			return num == finalNum;
		}

		int index = map.indexOf(num);
		if (index != -1) {
			return isValidPath(reverse.get(index), finalNum, map, reverse, diceTurns, diceSI);
		} else {
			return isValidPath(num + diceTurns.get(diceSI), finalNum, map, reverse, diceTurns, diceSI + 1);
		}
	}
}
