package Miscellaneous;

import java.util.Scanner;

public class NQueensBest {

	public static int n;
	public static int ans = 0, DONE;

	public static int getPosition(int n) {
		int ans = 0;
		while (n > 0) {
			ans++;
			n = n >> 1;
		}
		return ans - 1;
	}

	public static int[][] board = new int[100][100];

	public static void solve(int rowMask, int ld, int rd, int row) {

		if (rowMask == DONE) {

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			ans++;
			return;
		}

		int safe = DONE & (~(rowMask | ld | rd));

		while (safe != 0) {
			int p = safe & (-safe);
			int col = getPosition(p);
			board[row][col] = 1;
			safe = safe - p;
			solve(rowMask | p, (ld | p) << 1, (rd | p) >> 1, row + 1);
			board[row][col] = 0;
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		n = scn.nextInt();
		DONE = ((1 << n)) - 1;
		solve(0, 0, 0, 0);
		scn.close();
	}

}
