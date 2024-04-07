package Assignment5;

import java.util.Scanner;

public class Question5 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter a string:");
		String str = scn.next();
		System.out.print("The converted string is:" + replacePi(str, ""));
	}

	public static String replacePi(String str, String osf) {
		if (str.length() == 0||str.length()==1) {
			return osf+str;
		}
		String ros = new String();
		String temp = str.substring(0, 2);
		if (temp.equals("pi") || temp.equals("Pi")) {
			temp = "3.14";
			osf = osf + temp;
			ros = str.substring(2);

		} else {
			osf = osf + str.charAt(0);
			ros = str.substring(1);
		}
		ros = replacePi(ros, osf);
		return ros;
	}

}
