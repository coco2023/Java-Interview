package HackerBlocks;

import java.util.Scanner;

public class TavvasAndSaddas {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String number = scn.next();
		int length = number.length();
		int count = 0;
		count += (1 << length) - 2;
		for (int i = 0; i < length; i++) {
			if (number.charAt(i) - '0' == 7) {
				count += 1 << (length - i - 1);
			}
		}
		count += 1;
		System.out.println(count);
		scn.close();
	}

}
