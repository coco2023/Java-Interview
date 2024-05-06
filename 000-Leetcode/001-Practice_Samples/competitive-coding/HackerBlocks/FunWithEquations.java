package HackerBlocks;

import java.util.Scanner;

public class FunWithEquations {
	static int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static void main(String[] args) {
		int t;
		int a, b, c, d, e, f;
		Scanner scn = new Scanner(System.in);
		t = scn.nextInt();
		while (t-- != 0) {
			a = scn.nextInt();
			b = scn.nextInt();
			c = scn.nextInt();
			d = scn.nextInt();
			e = scn.nextInt();
			f = scn.nextInt();
			System.out.println(ans(a, b, c, d, e, f));
		}
		scn.close();
	}

	static double ans(int a, int b, int c, int d, int e, int f) {
		double ans = 1.0;
		// ae^(-p) + bsin(p) + ccos(p) + dtan(p) + e*p^2 + f
		if ((a * Math.pow(Math.E, -ans) + b * Math.sin(ans) + c * Math.cos(ans) + d * Math.tan(ans)
				+ Math.pow(Math.E, Math.pow(ans, 2)) + f) == 0.0) {
			double temp = a * Math.pow(Math.E, -ans) + b * Math.sin(ans) + c * Math.cos(ans) + d * Math.tan(ans)
					+ Math.pow(Math.E, Math.pow(ans, 2)) + f;
			return ans;
		}
		ans = 0.0;
		int count = 0;
		double divider = 10.0;
		while (count < 4) {
			int i = 9;
			double temp = ans;
			int left = 0, right = arr.length;
			i = (left + right) / 2;
			while (left < right) {
				temp = ans + arr[i] / divider;
				if ((a * Math.pow(Math.E, temp) + b * Math.sin(temp) + c * Math.cos(temp) + d * Math.tan(temp)
						+ Math.pow(Math.E, Math.pow(temp, 2)) + f) > 0) {
					right = i - 1;
				} else if ((a * Math.pow(Math.E, temp) + b * Math.sin(temp) + c * Math.cos(temp) + d * Math.tan(temp)
						+ Math.pow(Math.E, Math.pow(temp, 2)) + f) == 0) {
					break;
				} else {
					left = i + 1;
				}
				i = (left + right) / 2;
			}
			temp = ans + arr[i] / divider;
			if ((a * Math.pow(Math.E, temp) + b * Math.sin(temp) + c * Math.cos(temp) + d * Math.tan(temp)
					+ Math.pow(Math.E, Math.pow(temp, 2)) + f) == 0) {
				ans = temp;
				break;
			} else {
				divider *= 10.0;
			}
			count++;
		}
		return ans;
	}

}
