package Lecture13;

public class Lexico {

	public static void main(String[] args) {
		lexico(1000, 0);
	}

	public static void lexico(int max, int curr) {

		if (curr > max) {
			return;
		}
		System.out.println(curr);
		if (curr == 0) {

			for (int i = 1; i <= 9; i++) {
				lexico(max, curr + i);
			}
		} else {
			for (int i = 0; i < 10; i++) {
				lexico(max, (curr * 10) + i);
			}
		}
	}
}
