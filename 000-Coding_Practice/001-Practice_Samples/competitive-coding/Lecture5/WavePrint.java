package Lecture5;

import java.util.Scanner;

public class WavePrint {

	public static void main(String[] args) {
		int[][] x = takeInput();
		display(x);
		// Wave(x);
		Spiral(x);
	}

	public static int[][] takeInput() {
		int[][] retval;
		Scanner scn = new Scanner(System.in);
		System.out.print("Enter the number of rows:");
		int rows = scn.nextInt();
		retval = new int[rows][];
		int row = 0;
		while (row < rows) {
			System.out.print("Enter number of columns in " + (row + 1 + "th row:"));
			int cols = scn.nextInt();
			int col = 0;
			retval[row] = new int[cols];
			while (col < cols) {
				System.out.print("Enter value in cell(" + (row + 1) + "," + (col + 1) + "):");
				retval[row][col] = scn.nextInt();
				col++;
			}
			row++;
		}
		return retval;
	}

	public static void display(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void Wave(int[][] arr) {
		int k = 0;
		while (k < arr[0].length) {
			if (k % 2 == 0) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i][k] + "\t");
				}
			} else {
				for (int i = arr.length - 1; i >= 0; i--) {
					System.out.print(arr[i][k] + "\t");
				}
			}
			k++;
		}
	}

	public static void Spiral(int[][] arr) {
		int counter = 1, rmin = 0, cmin = 0, rmax = arr.length - 1, cmax = arr[0].length - 1;
		while (counter <= arr.length * arr[0].length) {
			for (int row = rmin; row <= rmax; row++) {
				System.out.print(arr[row][cmin] + ",");
				counter++;
			}
			cmin++;
			for (int col = cmin; counter <= (arr.length * arr[0].length) && col <= cmax; col++) {
				System.out.print(arr[rmax][col] + ",");
				counter++;
			}
			rmax--;
			for (int row = rmax; counter <= (arr.length * arr[0].length) && row >= rmin; row--) {
				System.out.print(arr[row][cmax] + ",");
				counter++;
			}
			cmax--;
			for (int col = cmax; counter <= (arr.length * arr[0].length) && col >= cmin; col--) {
				System.out.print(arr[rmin][col] + ",");
				counter++;
			}
			rmin++;
		}
		System.out.println("END");
	}
}
