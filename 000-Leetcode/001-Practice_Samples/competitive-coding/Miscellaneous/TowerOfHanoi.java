package Miscellaneous;

public class TowerOfHanoi {
	public static void towerOfHanoi(int N, char src, char dest, char helper) {
		if (N == 0) {
			return;
		}
		towerOfHanoi(N - 1, src, helper, dest);
		System.out.println("Move " + N + " from " + src + " to " + dest);
		towerOfHanoi(N - 1, helper, dest, src);
	}

	public static void main(String[] args) {
		int N = 3;
		towerOfHanoi(N, 'A', 'C', 'B');

	}

}
