package HackerBlocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LexographicSubsequences {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		ArrayList<String> retval = new ArrayList<>();
		printSubsequences(scn.next(), retval);
		retval.add("");
		Collections.sort(retval);
		for (int i = 0; i < retval.size(); i++) {
			System.out.println(retval.get(i));
		}
		scn.close();
	}

	static void printSubsequences(String str, ArrayList<String> retval) {
		int opsize = (int) Math.pow(2, str.length());
		for (int counter = 1; counter < opsize; counter++) {
			String subString = "";
			for (int j = 0; j < str.length(); j++) {
				if (BigInteger.valueOf(counter).testBit(j))
					subString += str.charAt(j);
			}
			retval.add(subString);
		}
	}

//	public static void getSubSequences(String str, String osf, ArrayList<String> retval) {
//		if (str.length() == 0) {
//			retval.add(osf);
//			return;
//		}
//		char cc = str.charAt(0);
//		String ros = str.substring(1);
//		getSubSequences(ros, osf + cc, retval);
//		getSubSequences(ros, osf, retval);
//	}
}
