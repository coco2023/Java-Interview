package Lecture13;

import Lecture12.DynamicStack;
import Lecture12.Stack;

public class Balanced {

	public static void main(String[] args) throws Exception {
		System.out.println(isBalanced("(a+b)"));
	}

	public static boolean isBalanced(String str) throws Exception {
		Stack S = new DynamicStack();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == '[') {
				S.push(((int) str.charAt(i)));
			}
			if (S.isEmpty()) {
				return false;
			} else {
				if (str.charAt(i) == '}') {
					if (S.top() == (int) '{') {
						S.pop();
					} else {
						return false;
					}
				}
				if (str.charAt(i) == ']') {
					if (S.top() == (int) '[') {
						S.pop();
					} else {
						return false;
					}
				}
				if (str.charAt(i) == ')') {
					if (S.top() == (int) '(') {
						S.pop();
					} else {
						return false;
					}
				}
			}
		}
		if (S.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
