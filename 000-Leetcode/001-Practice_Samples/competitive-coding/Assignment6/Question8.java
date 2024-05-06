package Assignment6;

public class Question8 {

	public static void main(String[] args) {
		printKeypad("123", "");
	}

	public static void printKeypad(String str, String result) {
		if (str.length() == 0) {
			System.out.println(result);
			return;
		}
		char cc = str.charAt(0);
		String ros = str.substring(1);

		String code = Lecture6.Recursion.getCode(((int) cc) - 48);
		for (int i = 0; i < code.length(); i++) {
			printKeypad(ros, result + code.charAt(i));
		}
	}
}
