package Assignment6;

import java.util.ArrayList;

public class Question4 {

	public static void main(String[] args) {
		// char c='b';
		// int k=(int)(c)-96;
		// String str=""+k;
		// System.out.println(str);
		System.out.println(printCode("1123"));
		// String str="1";
		// System.out.println(str.substring(1));
	}

	public static char getCode(String str) {
		int temp = 0;
		while (str.length() != 0) {
			temp = temp * 10 + ((int) (str.charAt(0)) - 48);
			str = str.substring(1);
		}
		switch (temp) {
		case 1:
			return 'a';
		case 2:
			return 'b';
		case 3:
			return 'c';
		case 4:
			return 'd';
		case 5:
			return 'e';
		case 6:
			return 'f';
		case 7:
			return 'g';
		case 8:
			return 'h';
		case 9:
			return 'i';
		case 10:
			return 'j';
		case 11:
			return 'k';
		case 12:
			return 'l';
		case 13:
			return 'm';
		case 14:
			return 'n';
		case 15:
			return 'o';
		case 16:
			return 'p';
		case 17:
			return 'q';
		case 18:
			return 'r';
		case 19:
			return 's';
		case 20:
			return 't';
		case 21:
			return 'u';
		case 22:
			return 'v';
		case 23:
			return 'w';
		case 24:
			return 'x';
		case 25:
			return 'y';
		case 26:
			return 'z';
		default:
			return ' ';
		}
	}

	public static ArrayList<String> printCode(String str) {
		if (str.length() == 0) {
			ArrayList<String> baseResult = new ArrayList<>();
			baseResult.add("");
			return baseResult;
		}
		String ros = str.substring(1);
		ArrayList<String> temp = printCode(ros);
		int n = temp.size();
		String cc = str.charAt(0) + "";
		ArrayList<String> retVal = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			retVal.add(getCode(cc) + temp.get(i));
			if (!temp.get(i).equals("") && temp.get(i).charAt(0) < 'j' && temp.get(i).charAt(0) >= 'a') {
				char cc1 = temp.get(i).charAt(0);
				int temp2 = (int) (cc1) - 96;
				String tobeAdded = "" + temp2;
				if (getCode(cc + tobeAdded) != ' ') {
					retVal.add(getCode(cc + tobeAdded) + temp.get(i).substring(1));
				}

			}
		}
		return retVal;
	}

}
