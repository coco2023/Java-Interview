package Assignment5;

import java.util.Scanner;

public class Question6 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String str = scn.next();
		String result = noX(str, "");
		System.out.println("The resulting string (with no X's) is:" + result);
	}

	public static String noX(String str, String osf) {
		if (str.length() == 0) {
			return osf;
		}
		char cc = str.charAt(0);
		if (cc == 'x' || cc == 'X') {
			osf = osf + "";
		} else {
			osf = osf + cc;
		}
		String ros = str.substring(1);
		ros = noX(ros, osf);
		return ros;
	}
}
