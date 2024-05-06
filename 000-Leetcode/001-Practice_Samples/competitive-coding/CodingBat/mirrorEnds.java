package CodingBat;

public class mirrorEnds {

	public static void main(String[] args) {

	}

	public static String mirrorEnds(String str) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			String temp1 = str.substring(str.length() - i - 1, str.length());
			String temp2 = new StringBuilder(temp1).reverse().toString();
			if (str.substring(0, i + 1).equals(temp2)) {
				temp.delete(0, temp.length());
				temp.append(str.substring(0, i + 1));
			}
		}
		return temp.toString();
	}
}
