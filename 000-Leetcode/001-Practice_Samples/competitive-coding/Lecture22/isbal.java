package Lecture22;

import java.util.*;

public class isbal {

	public static void main(String[] args) {
		String str = "[a+{b+(c+d)+e}+f]";
		System.out.println(isBalanced(str));
	}

	public static boolean isBalanced(String str) {
		Stack<Character> stack = new Stack<>();
		while (!str.equals("")) {
			if (str.charAt(0) == '{' || str.charAt(0) == '[' || str.charAt(0) == '(') {
				stack.push(str.charAt(0));
			}
			if (str.charAt(0) == '}' || str.charAt(0) == ']' || str.charAt(0) == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				switch (stack.peek()) {
				case '{':
					if (str.charAt(0) != '}') {
						return false;
					}
					stack.pop();
					break;
				case '(':
					if (str.charAt(0) != ')') {
						return false;
					}
					stack.pop();
					break;
				case '[':
					if (str.charAt(0) != ']') {
						return false;
					}
					stack.pop();
					break;
				}
			}
			str = str.substring(1);
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}
}
