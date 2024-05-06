package Lecture4;

import java.util.Scanner;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = LargestSearchReverse.takeInput();
		BubbleAndSelection.bubbleSort(arr);
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+",");
		}
		System.out.println("END");
		System.out.println(binarySearch(arr));
	}

	public static int binarySearch(int[] arr) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number to be searched:");
		int N = scn.nextInt();
		int left = 0, right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] == N) {
				return mid;
			} else if (arr[mid] > N) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

}
