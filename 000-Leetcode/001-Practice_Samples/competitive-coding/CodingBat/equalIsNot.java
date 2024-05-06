package CodingBat;

public class equalIsNot {

	public static void main(String[] args) {
		System.out.println(equalIsNot("isnotis"));
	}

	public static boolean equalIsNot(String str) {
		int not = 0, is = 0;
		for (int i = 0; i < str.length(); i++) {
			if (i + 3 <= str.length() && str.substring(i, i + 3).equals("not")) {
				not++;
			}
			if (i + 2 <= str.length() && str.substring(i, i + 2).equals("is")) {
				is++;
			}
		}
		return is == not;
	}
}
