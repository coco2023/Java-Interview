package HackerBlocks;

import java.util.Scanner;

public class ThreeDDataStructure {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			long x1, y1, z1, x2, y2, z2, ax3, ay3, az3, cx3, cy3, cz3;
			x1 = scn.nextLong();
			y1 = scn.nextLong();
			z1 = scn.nextLong();
			x2 = scn.nextLong();
			y2 = scn.nextLong();
			z2 = scn.nextLong();
			long dotProduct = x1 * x2 + y1 * y2 + z1 * z2;
			ax3 = x1 + x2;
			ay3 = y1 + y2;
			az3 = z1 + z2;
			cx3 = y1 * z2 - z1 * y2;
			cy3 = z1 * x2 - x1 * z2;
			cz3 = x1 * y2 - y1 * x2;
			System.out.println(ax3 + " " + ay3 + " " + az3);
			System.out.println(dotProduct);
			System.out.println(cx3 + " " + cy3 + " " + cz3);
		}
		scn.close();
	}

}
