package Assignment2a;
import java.util.Scanner;

public class Question9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the value of N:");
		int N = scn.nextInt();
		int i = 2, mid;
		if (N % 2 == 0) {
			mid=(N / 2);
		} else {
			mid=( (N / 2) + 1);
		}
		int k1, k2;
		boolean flag = true;
		System.out.println("Enter an element:");
		k1 = scn.nextInt();
		System.out.println("Enter an element:");
		k2 = scn.nextInt();
		while (flag && i < N) {

			if (i <=mid) {
				while (i <= mid) {
					if (k2 < k1) {
						flag = false;
						break;
					} else {
						k1 = k2;
					}
					System.out.println("Enter an element:");
					k2 = scn.nextInt();
					i++;
				}
			}
			if (i >mid) {
				while (i<N) {
					if (k2 > k1) {
						flag = false;
						break;
					} else {
						k1 = k2;
					}
					System.out.println("Enter an element:");
					k2 = scn.nextInt();
					i++;
				}
			}

		}
		if (flag) {
			System.out.println("The series can be divided in two parts as specified.");
		} else {
			System.out.println("The series can not be divided in two parts as specified.");
		}

	}

}
