package Lecture12;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void helperFunction(int left, int right, ArrayList<Integer> arrayList) {
		while (left >= 0 && right <= arrayList.size() - 1) {
			if (arrayList.get(left) == 9) {
				arrayList.set(left, 0);
				arrayList.set(right, 0);
			} else {
				arrayList.set(left, arrayList.get(left) + 1);
				arrayList.set(right, arrayList.get(right) + 1);
				break;
			}
			left--;
			right++;
		}
		if (left < 0 && right >= arrayList.size()) {
			arrayList.set(0, 1);
			arrayList.add(arrayList.size(), 1);
		}
	}

	public static void main(String[] args) {
		int x;
		Scanner scn = new Scanner(System.in);
		x = scn.nextInt();
		System.out.println(x);
		ArrayList<Integer> arrayList = new ArrayList<>();
		while (x != 0) {
			arrayList.add(0, x % 10);
			x = x / 10;
		}
		if (isPalindrome(arrayList)) {
			if (arrayList.size() % 2 == 1) {
				if (arrayList.get(arrayList.size() / 2) == 9) {
					int left = arrayList.size() / 2 - 1, right = arrayList.size() / 2 + 1;
					arrayList.set(arrayList.size() / 2, 0);
					helperFunction(left, right, arrayList);
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				} else {
					arrayList.set(arrayList.size() / 2, arrayList.get(arrayList.size() / 2) + 1);
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				}
			} else {
				if (arrayList.get(arrayList.size() / 2) == 9) {
					int left = arrayList.size() / 2 - 1, right = arrayList.size() / 2;
					helperFunction(left, right, arrayList);
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				} else {
					arrayList.set(arrayList.size() / 2, arrayList.get(arrayList.size() / 2) + 1);
					arrayList.set(arrayList.size() / 2 - 1, arrayList.get(arrayList.size() / 2));
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				}
			}
		} else {
			int left = 0, right = arrayList.size() / 2;
			DigitComparer digitComparer = new DigitComparer();
			digitFinder(digitComparer, left, right, arrayList);
			if (digitComparer.leftSide > digitComparer.rightSide) {
				int x1 = 1;
				for (int i = arrayList.size() / 2 + 1; i < arrayList.size(); i++) {
					arrayList.set(i, arrayList.get(i - 2 * x1));
					x1++;
				}
				for (int i = 0; i < arrayList.size(); i++) {
					System.out.print(arrayList.get(i));
				}
				System.out.println();
			} else {
				if (arrayList.size() % 2 == 1) {
					int mid = arrayList.size() / 2;
					if (arrayList.get(mid) == 9) {
						while (mid >= 0) {
							if (arrayList.get(mid) == 9) {
								arrayList.set(mid, 0);
								mid--;
							}
							break;
						}
					}
					arrayList.set(mid, arrayList.get(mid) + 1);
					int x1 = 1;
					for (int i = arrayList.size() / 2 + 1; i < arrayList.size(); i++) {
						arrayList.set(i, arrayList.get(i - 2 * x1));
						x1++;
					}
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				} else {
					int mid = arrayList.size() / 2;
					boolean flag = false;
					if (arrayList.get(mid) == 9) {
						arrayList.set(mid, 0);
						mid--;
						while (mid >= 0) {
							if (arrayList.get(mid) == 9) {
								arrayList.set(mid, 0);
								arrayList.set(mid - 1, arrayList.get(mid - 1) + 1);
								mid--;
							} else {
								break;
							}
						}
					} else {
						if (arrayList.get(mid - 1) == 9) {
							arrayList.set(mid - 1, 0);
							mid -= 2;
							while (mid >= 0) {
								if (arrayList.get(mid) == 9) {
									arrayList.set(mid, 0);
									arrayList.set(mid - 1, arrayList.get(mid - 1) + 1);
									mid--;
								} else {
									break;
								}
							}
						} else {
							arrayList.set(mid - 1, arrayList.get(mid - 1) + 1);
						}
					}
					int x1 = 1;
					for (int i = arrayList.size() / 2; i < arrayList.size(); i++) {
						arrayList.set(i, arrayList.get(i - (2 * x1 - 1)));
						x1++;
					}
					for (int i = 0; i < arrayList.size(); i++) {
						System.out.print(arrayList.get(i));
					}
					System.out.println();
				}
			}
		}
	}

	public static class DigitComparer {
		public int leftSide;
		public int rightSide;
	}

	public static boolean isPalindrome(ArrayList<Integer> arrayList) {
		for (int i = 0; i < arrayList.size() / 2; i++) {
			if (arrayList.get((arrayList.size() - i - 1)) != arrayList.get(i)) {
				return false;
			}
		}
		return true;
	}

	public static void digitFinder(DigitComparer digitComparer, int left, int right, ArrayList<Integer> arrayList) {
		if (arrayList.size() % 2 == 1) {
			right++;
			while (left < arrayList.size() / 2 && right < arrayList.size()) {
				digitComparer.leftSide = digitComparer.leftSide * 10 + arrayList.get(left);
				left++;
				digitComparer.rightSide = digitComparer.rightSide * 10 + arrayList.get(right);
				right++;
			}
		} else {
			while (left < arrayList.size() / 2 && right < arrayList.size()) {
				digitComparer.leftSide = digitComparer.leftSide * 10 + arrayList.get(left);
				left++;
				digitComparer.rightSide = digitComparer.rightSide * 10 + arrayList.get(right);
				right++;
			}
		}
	}
}
