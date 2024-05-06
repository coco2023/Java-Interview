package Miscellaneous;

import java.util.Scanner;
import java.util.Stack;

public class HIFHSIHF {
	// public static void main(String[] args) {
	// Scanner in = new Scanner(System.in);
	// int t = in.nextInt();
	// for (int a0 = 0; a0 < t; a0++) {
	// String s = in.next();
	// Stack<Character> stack = new Stack<>();
	// int i;
	// for (i = 0; i < s.length(); i++) {
	// if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
	// stack.push(s.charAt(i));
	// }
	// if (s.charAt(i) == ')') {
	// if (stack.isEmpty() || stack.pop() != '(') {
	// break;
	// }
	// }
	// if (s.charAt(i) == ']') {
	// if (stack.isEmpty() || stack.pop() != '[') {
	// break;
	// }
	// }
	// if (s.charAt(i) == '}') {
	// if (stack.isEmpty() || stack.pop() != '{') {
	// break;
	// }
	// }
	// }
	// if (i != s.length() || !stack.isEmpty()) {
	// System.out.println("NO");
	// } else {
	// System.out.println("YES");
	// }
	// }
	// in.close();
	// }
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		int[] arr = new int[N+2];
		int M = scn.nextInt();
		for (int i = 1; i <= M; i++) {
			int a = scn.nextInt();
			int b = scn.nextInt();
			int k = scn.nextInt();
			arr[a] += k;
			arr[b+1] -= k;
		}
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0) {
				break;
			}
			sum += arr[i];
		}
		System.out.println(sum);
		scn.close();
	}
}
