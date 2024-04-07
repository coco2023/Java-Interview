package Lecture8;

import java.util.ArrayList;

public class MazePaths {

	public static void main(String[] args) {
		// int[] arr = { 5, 2, 8, 11, 1 };
		// QuickSort(arr, 0, 4);
		// for (int i = 0; i < arr.length; i++) {
		// System.out.print(arr[i] + "\t");
		// }
		// System.out.println(getCode("abc"));
		System.out.print(getMazePaths(3, 3, 0, 0));
	}

	public static int countMaze(int tr, int tc, int cr, int cc) {
		int counter = 0;
		if (cr == tr && cc == tc) {
			return 1;
		}
		if (tr > cr && tc == cc) {
			counter = countMaze(tr, tc, cr + 1, cc);
		} else if (tc > cc && tr == cr) {
			counter = countMaze(tr, tc, cr, cc + 1);
		} else if (tc > cc && tr > cr) {
			counter = countMaze(tr, tc, cr + 1, cc);
			counter += countMaze(tr, tc, cr, cc + 1);
		}
		return counter;
	}

	public static ArrayList<String> getMazePaths(int tr, int tc, int cc, int cr) {
		ArrayList<String> myResult = new ArrayList<>();
		ArrayList<String> colResult = new ArrayList<>();
		ArrayList<String> rowResult = new ArrayList<>();
		if (cr < tr && cc < tc) {
			rowResult = getMazePaths(tr, tc, cc, cr + 1);
			colResult = getMazePaths(tr, tc, cc + 1, cr);

		} else if (cc < tc) {
			colResult = getMazePaths(tr, tc, cc + 1, cr);
		} else if (cr < tr) {
			rowResult = getMazePaths(tr, tc, cc, cr + 1);

		} else {
			ArrayList<String> baseResult = new ArrayList<>();
			baseResult.add("");
			return baseResult;
		}
		for (int i = 0; i < colResult.size(); i++) {
			myResult.add("h" + colResult.get(i));
		}
		for (int i = 0; i < rowResult.size(); i++) {
			myResult.add("v" + rowResult.get(i));
		}
		return myResult;
	}

	public static int countMazePathsallDiag(int tr, int tc, int cc, int cr) {
		int counter = 0;
		if (cr == tr && cc == tc) {
			return 1;
		}
		if (tr > cr && tc > cc) {
			counter += countMazePathsallDiag(tr, tc, cc, cr + 1);
			counter += countMazePathsallDiag(tr, tc, cc + 1, cr);
			counter += countMazePathsallDiag(tr, tc, cc + 1, cr + 1);
		} else if (tr > cr) {
			counter += countMazePathsallDiag(tr, tc, cc, cr + 1);
		} else if (tc > cc) {
			counter += countMazePathsallDiag(tr, tc, cc + 1, cr);
		}
		return counter;
	}

	public static int countMazePathsallDiagCond(int tr, int tc, int cc, int cr) {
		int counter = 0;
		if (cr == tr && cc == tc) {
			return 1;
		}
		if (tr > cr && tc > cc && cc == cr) {
			counter += countMazePathsallDiagCond(tr, tc, cc, cr + 1);
			counter += countMazePathsallDiagCond(tr, tc, cc + 1, cr);
			counter += countMazePathsallDiagCond(tr, tc, cc + 1, cr + 1);
		} else if (tr > cr && tc > cc) {
			counter += countMazePathsallDiagCond(tr, tc, cc, cr + 1);
			counter += countMazePathsallDiagCond(tr, tc, cc + 1, cr);
		} else if (tr > cr) {
			counter += countMazePathsallDiagCond(tr, tc, cc, cr + 1);
		} else if (tc > cc) {
			counter += countMazePathsallDiagCond(tr, tc, cc + 1, cr);
		}
		return counter;
	}

	public static void printKeypad(String str, String osf) {
		if (str.length() == 0) {
			System.out.print(osf + " ");
			return;
		}
		int cc = (int) str.charAt(0) - 48;
		String ros = str.substring(1);
		String str1 = Lecture6.Recursion.getCode(cc);
		for (int i = 0; i < str1.length(); i++) {
			printKeypad(ros, osf + str1.charAt(i));
		}
	}

	public static ArrayList<String> getCode(String str) {

		if (str.length() == 1) {
			ArrayList<String> baseResult = new ArrayList<>();
			baseResult.add(str);
			char cc = str.charAt(0);
			baseResult.add((int) cc + "");
			return baseResult;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);
		ArrayList<String> temp = getCode(ros);
		ArrayList<String> myResult = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++) {
			myResult.add(temp.get(i));
		}
		myResult.add(cc + "");
		myResult.add((int) cc + "");
		for (int i = 0; i < temp.size(); i++) {
			myResult.add(cc + temp.get(i));
			myResult.add((int) cc + "" + temp.get(i));
		}

		return myResult;
	}

	public static int[] merge(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] <= arr2[j]) {
				result[k++] = arr1[i++];
			} else {
				result[k++] = arr2[j++];
			}
		}
		while (i < arr1.length) {
			result[k++] = arr1[i++];
		}
		while (j < arr2.length) {
			result[k++] = arr2[j++];
		}
		return result;
	}

	public static int[] mergeSort(int[] arr, int left, int right) {
		if (left == right) {
			int[] baseResult = new int[1];
			baseResult[0] = (arr[left]);
			return baseResult;
		}
		int mid = (left + right) / 2;
		int[] fhalf = mergeSort(arr, left, mid);
		int[] shalf = mergeSort(arr, mid + 1, right);
		int[] sorted = merge(fhalf, shalf);
		return sorted;
	}

	public static void QuickSort(int[] arr, int lo, int hi) {
		if (lo > hi) {
			return;
		}
		int pi = (hi + lo) / 2;
		int pivot = arr[pi];
		int left = lo, right = hi;
		while (left <= right) {
			while (arr[left] < pivot) {
				left++;
			}
			while (arr[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}
		QuickSort(arr, lo, right);
		QuickSort(arr, left, hi);
	}
}
	