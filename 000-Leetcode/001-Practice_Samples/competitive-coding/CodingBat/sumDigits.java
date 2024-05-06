package CodingBat;

public class sumDigits {

	public static void main(String[] args) {
		System.out.println(sumDigits("7 11"));
	}

	public static int sumDigits(String str) {
		int sum = 0, temp = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				temp = temp * 10 + Integer.parseInt(str.charAt(i) + "");
			} else {
				sum += temp;
				temp = 0;
			}
		}
		return sum + temp;
	}
}
