package HackerBlocks;

import java.util.Scanner;

public class XORING {

	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {

			int x = scn.nextInt();
			int y = scn.nextInt();
			int result = x ^ y;
			int msb = 0;
			while (result != 0) {
				msb++;
				result = result >> 1;
			}
			int former = 1, max = 0;
			while (msb != 0) {
				max += former;
				former <<= 1;
				msb--;
			}
			System.out.println(max);

		}
		scn.close();
	}

}
