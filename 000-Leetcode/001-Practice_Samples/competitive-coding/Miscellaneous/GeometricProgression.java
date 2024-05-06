package Miscellaneous;

import java.util.Scanner;

public class GeometricProgression {

	public static void main(String[] args) {
		int[] gpCount = new int[1];
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int r = scn.nextInt();
		int l = scn.nextInt();
		if (n == 1) {
			System.out.println(r - l + 1 + "");
		} else {
			numGPI(n, l, l, r, 0, -1.0, gpCount);
			numGPD(n, r, l, r, 0, -1.0, gpCount);
			System.out.println(gpCount[0]);
		}
		scn.close();
	}

	private static void numGPD(int n, int x, int l, int r, int count, double cr, int[] gpCount) {
		if (n == 2) {
			gpCount[0] = ((r - l + 1) * (r - l)) / 2;
			return;
		} else if (n > 2) {
			if (count > n) {
				return;
			}
			if (count == n) {
				gpCount[0]++;
				return;
			}
			// loop for decreasing GP's
			for (int i = 1; i <= r - l; i--) {
				if (cr == -1.0) {
					boolean flag = isGPPossible(x, x - i, l, r);
					if (flag) {
						cr = (double) x - i / (double) x;
						count++;
						numGPD(n, x - i, l, r, count, cr, gpCount);
					} else {
						numGPD(n, x - i, l, r, count, cr, gpCount);
					}
				} else {
					if (isGPPossible(x, x - i, l, r)) {
						if (cr == (double) x - i / (double) x) {
							count++;
							numGPD(n, x - i, l, r, count, cr, gpCount);
						} else {
							numGPD(n, x - i, l, r, count, cr, gpCount);
						}
					} else {
						numGPD(n, x - i, l, r, count, cr, gpCount);
					}
				}
			}
		} else {
			return;
		}
	}

	public static void numGPI(int n, int x, int l, int r, int count, double cr, int[] gpCount) {
		if (n == 2) {
			gpCount[0] = ((r - l + 1) * (r - l)) / 2;
			return;
		} else if (n > 2) {
			if (count > n) {
				return;
			}
			if (count == n) {
				gpCount[0]++;
				return;
			}
			// loop for increasing GP's
			for (int i = 1; i <= r - l; i++) {
				if (cr == -1.0) {
					boolean flag = isGPPossible(x, x + i, l, r);
					if (flag) {
						cr = (double) x + i / (double) x;
						count++;
						numGPI(n, x + i, l, r, count, cr, gpCount);
					} else {
						numGPI(n, x + i, l, r, count, cr, gpCount);
					}
				} else {
					if (isGPPossible(x, x + i, l, r)) {
						if (cr == (double) x + i / (double) x) {
							count++;
							numGPI(n, x + i, l, r, count, cr, gpCount);
						} else {
							numGPI(n, x + i, l, r, count, cr, gpCount);
						}
					} else {
						numGPI(n, x + i, l, r, count, cr, gpCount);
					}
				}
			}
		} else {
			return;
		}
	}

	public static boolean isGPPossible(int x1, int x2, int l, int r) {
		if (((int) (x2 * ((double) x2 / (double) x1)) == x2 * ((double) x2 / (double) x1))
				&& (int) (x2 * ((double) x2 / (double) x1)) <= r && (int) (x2 * ((double) x2 / (double) x1)) >= l) {
			return true;
		}
		return false;
	}
}
