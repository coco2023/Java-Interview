package CodingBat;

public class sameEnds {

	public static void main(String[] args) {
		System.out.println(sameEnds("xxxx"));
	}

	public static String sameEnds(String str) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.substring(0, i + 1).equals(str.substring(str.length() - i - 1, str.length()))) {
				temp.delete(0, temp.length());
				temp.append(str.substring(0, i + 1));
			}
		}
		return temp.toString();
	}
}
