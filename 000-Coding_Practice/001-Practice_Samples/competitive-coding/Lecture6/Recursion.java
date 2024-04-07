package Lecture6;

import java.util.ArrayList;
import java.util.Scanner;

public class Recursion {

	public static void main(String[] args) {

		// Scanner scn = new Scanner(System.in);
		// System.out.println("Enter a number:");
		// int N = scn.nextInt();
		// ArrayList<String> S = KeypadProblem(N);
		// System.out.println(S);
		// System.out.println(permutations("abcd"));
		String str = "abcd";
		// System.out.println(str.substring(0, 0) + str.substring(1, 4));
		printSubSequences(str,"");
//		printPermutations("abcd", "");
	}

	public static void bubbleSortR(int[] arr, int si, int li) {
		if (li == 0) {
			return;
		}
		if (si >= li) {
			bubbleSortR(arr, 0, li - 1);
			return;
		}
		if (arr[si] > arr[si + 1]) {
			int temp = arr[si];
			arr[si] = arr[si + 1];
			arr[si + 1] = temp;
		}
		bubbleSortR(arr, si + 1, li);
	}

	public static ArrayList<String> getSS(String str) {
		if (str.isEmpty()) {
			ArrayList<String> size = new ArrayList<>();
			size.add("");
			return size;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);
		ArrayList<String> rosResult = getSS(ros);
		int rosResultsize = rosResult.size();
		for (int i = 0; i < rosResultsize; i++) {
			rosResult.add(cc + rosResult.get(i));
		}
		return rosResult;
	}

	public static ArrayList<String> permutations(String str) {
		if (str.length() == 1) {
			ArrayList<String> size = new ArrayList<>();
			size.add(str.charAt(0) + "");
			return size;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);
		ArrayList<String> S3 = permutations(ros);
		int num = S3.size();
		ArrayList<String> myResult = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			String str1 = S3.get(i);
			int x = str1.length();
			for (int j = 0; j <= x; j++) {
				String tobeAdded = str1.substring(0, j) + cc + str1.substring(j);
				myResult.add(tobeAdded);
			}
		}
		return myResult;
	}

	public static void printPermutations(String str, String osf) {
		int n = str.length();
		if (n == 0) {
			System.out.print(osf + "\t");
		} else {
			for (int i = 0; i < n; i++) {
				printPermutations(str.substring(0, i) + str.substring(i + 1, n), osf + str.charAt  (i));
			}
		}
	}

	public static void printSubSequences(String str, String osf) {
		if (str.length() == 0) {
			System.out.print(osf + "\t");
			return;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);
		printSubSequences(ros, osf + cc);
		printSubSequences(ros, osf);
	}

	public static String getCode(int c) {
		String str = "";
		switch (c) {
		case 1:
			str = "abc";
			return str;

		case 2:
			str = "def";
			return str;

		case 3:
			str = "ghi";
			return str;

		case 4:
			str = "jkl";
			return str;

		case 5:
			str = "mno";
			return str;

		case 6:
			str = "pqr";
			return str;

		case 7:
			str = "stu";
			return str;

		case 8:
			str = "vwx";
			return str;
		case 9:
			str = "yz";
			return str;
		}
		return str;
	}

	public static ArrayList<String> KeypadProblem(int num) {

		if (num / 10 == 0) {
			ArrayList<String> baseResult = new ArrayList<>();
			String baseCode = getCode(num % 10);
			for (int i = 0; i < baseCode.length(); i++) {
				baseResult.add(baseCode.substring(i, i + 1));
			}
			return baseResult;
		}
		ArrayList<String> temp = KeypadProblem(num / 10);
		ArrayList<String> myResult = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++) {
			String temp2 = getCode(num % 10);
			String temp3 = temp.get(i);
			for (int j = 0; j < temp2.length(); j++) {
				myResult.add(temp3 + temp2.charAt(j));
			}
		}
		return myResult;
	}

	public static void printIncreasingDecreasingSkip(int num) {

		if (num == 0) {
			return;
		}
		if (num % 2 == 1) {
			System.out.print(num + "\t");
		}
		printIncreasingDecreasingSkip(num - 1);
		if (num % 2 == 0) {
			System.out.print(num + "\t");
		}
	}

	public static void printPattern(int value, int upperlimit) {
		if (value == upperlimit + 1) {
			return;
		}
		System.out.print(value + "\t");
		printPattern(value + 1, upperlimit);
		System.out.print(value + "\t");
	}

	public static int power(int x, int n) {
		if (n == 1) {
			return x;
		}
		int num = power(x, n - 1);
		return num * x;
	}

	public static boolean isSorted(int[] arr, int num) {
		boolean flag = true;
		if (num == 0) {
			return flag;
		}
		flag = isSorted(arr, num - 1);
		if (arr[num] < arr[num - 1]) {
			flag = false;
		}
		return flag;
	}

	public static boolean contains(int[] arr, int el, int si) {
		boolean flag = false;
		if (si == arr.length) {
			return flag;
		}
		if (arr[si] == el) {
			return true;
		} else {
			flag = contains(arr, el, si + 1);
			return flag;
		}
	}

	public static int firstIndex(int[] arr, int el, int si) {
		int flag = -1;
		if (si == arr.length) {
			return flag;
		}
		if (arr[si] == el) {
			return si;
		} else {
			flag = firstIndex(arr, el, si + 1);
			return flag;
		}
	}

	public static int lastIndex(int[] arr, int el, int si) {
		if (si == arr.length) {
			return -1;
		}
		int found = lastIndex(arr, el, si + 1);
		if (found != -1) {
			return found;
		} else {
			if (arr[si] == el) {
				return si;
			} else {
				return -1;
			}
		}
	}

	public static int[] allIndices(int[] arr, int el, int si) {
		if (si == arr.length) {
			return new int[0];
		}
		int[] indicesinSA = allIndices(arr, el, si + 1);
		if (arr[si] == el) {
			int[] retval = new int[indicesinSA.length + 1];
			for (int i = 0; i < indicesinSA.length; i++) {
				retval[i] = indicesinSA[i];
			}
			retval[retval.length - 1] = si;
			return retval;
		} else {
			return indicesinSA;
		}
	}
}
