package SPOJ;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class AGGRCOW {

	public static BigInteger maxDist(BigInteger[] arr, BigInteger c) {
		BigInteger s = BigInteger.ZERO, e = arr[arr.length - 1], mid = (s.add(e)).divide(BigInteger.valueOf(2));
		BigInteger ans = BigInteger.ZERO;
		while (s.compareTo(e) <= 0) {
			if (validator(arr, mid, c)) {
				ans = mid;
				s = mid.add(BigInteger.ONE);
			} else {
				e = mid.subtract(BigInteger.ONE);
			}
			mid = (s.add(e)).divide(BigInteger.valueOf(2));
		}
		return ans;
	}

	public static boolean validator(BigInteger[] arr, BigInteger num, BigInteger c) {
		BigInteger cows = BigInteger.ONE, pos = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].subtract(pos).compareTo(num) >= 0) {
				pos = arr[i];
				cows = cows.add(BigInteger.ONE);
				if (cows.compareTo(c) == 0)
					return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int N = scn.nextInt();
			BigInteger c = scn.nextBigInteger();
			BigInteger[] pos = new BigInteger[N];
			for (int i = 0; i < N; i++) {
				pos[i] = scn.nextBigInteger();
			}
			Arrays.sort(pos);
			System.out.println(maxDist(pos, c));
		}
		scn.close();
	}

}
