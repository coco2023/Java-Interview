package Assignment5;

import java.util.Scanner;

public class Question10 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String str = scn.next();
		String result = pairStar(str, "");
		System.out.print("The resulting string is:" + result);
	}

	public static String pairStar(String str, String osf) {
		if (str.length() == 0 || str.length() == 1) {
			return osf + str;
		}
		String ros = new String();
		String temp = str.substring(0, 2);
		if (temp.charAt(0) == temp.charAt(1)) {
			temp = temp.charAt(0) + "*";
			osf = osf + temp;

		} else {
			osf = osf + str.charAt(0);
		}
		ros = str.substring(1);
		ros = pairStar(ros, osf);
		return ros;
	}
}
