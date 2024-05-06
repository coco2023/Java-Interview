package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class FactorialProblem {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int t = scn.nextInt();
		while (t-- != 0) {
			int N = scn.nextInt();
			int k = scn.nextInt();
			boolean flag = false;
			int temp1 = k;
			int y = 2;
			int i = 0;
			ArrayList<Integer> powerCount = new ArrayList<>();
			ArrayList<Integer> primeFactorization = new ArrayList<>();
			while (temp1 != 1) {
				if (temp1 % y == 0) {
					if (!flag) {
						flag = true;
						powerCount.add(1);
						primeFactorization.add(y);
					} else {
						powerCount.set(i, powerCount.get(i) + 1);
					}
					temp1 /= y;
				} else {
					if (flag) {
						i++;
					}
					flag = false;
					y++;

				}
			}
			ArrayList<Integer> countPower = new ArrayList<>();
			for (int j = 0; j < primeFactorization.size(); j++) {
				int temp = N;
				while (temp % primeFactorization.get(j) != 0) {
					temp--;
				}
				int x = 0, z = temp;
				while (temp >= 1) {
					if (temp % primeFactorization.get(j) != 0) {
						temp = z - primeFactorization.get(j);
						z -= primeFactorization.get(j);
					}
					if (temp >= 1)
						x++;
					temp /= primeFactorization.get(j);
				}
				countPower.add(x);
			}
			int min = 0;
			for (int z = 1; z < countPower.size(); z++) {
				if (countPower.get(min) > countPower.get(z)) {
					min = z;
				}
			}
			System.out
					.println((countPower.get(min) / powerCount.get(min)) - (countPower.get(min) % powerCount.get(min)));
		}
		scn.close();
	}

}
