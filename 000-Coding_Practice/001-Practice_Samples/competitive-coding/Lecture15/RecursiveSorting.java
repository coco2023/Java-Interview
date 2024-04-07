package Lecture15;

import java.util.Random;

public class RecursiveSorting {

	public static void main(String[] args) {
		Random random = new Random();
		int[] arr = new int[random.nextInt(10)];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(50);
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println("END");
		insertionSort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println("END");
		sort0index(arr);
		// recursive_Bubble_Sort(arr, 0,1);
		// recurcive_selection_Sort(arr,0,0,0);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
		System.out.println("END");

	}

	public static void insertionSort(int[] ele, int sortedIndex, int index) {
		if (sortedIndex < ele.length) {
			if (index < ele.length) {
				if (ele[sortedIndex] > ele[index]) {
					int temp = ele[sortedIndex];
					ele[sortedIndex] = ele[index];
					ele[index] = temp;
				}
				insertionSort(ele, sortedIndex, index + 1);
				return;
			}
			if (index == ele.length) {
				sortedIndex++;
				insertionSort(ele, sortedIndex, sortedIndex + 1);
			}

		}
//		if(sortedIndex>=ele.length)
//		{
//			sort0index(ele);
//		}
	}
	public static void sort0index(int[] arr)
	{
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[0]) {
				break;
			}
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
		}
	}
	// public static void recursive_insertion_sort(int[] arr, int i, int j) {
	// if (i == arr.length) {
	// return;
	// }
	// if (j < 0) {
	// // arr[j + 1] = arr[i];
	// recursive_insertion_sort(arr, i + 1, i);
	// return;
	// }
	// if (arr[i] < arr[j]) {
	// int temp = arr[i];
	// arr[i] = arr[j];
	// arr[j] = temp;
	// }
	// recursive_insertion_sort(arr, i, j - 1);
	//
	// }
	// // for (int j = 1; j < n; j++) {
	// // int key = array[j];
	// // int i = j-1;
	// // while ( (i > -1) && ( array [i] > key ) ) {
	// // array [i+1] = array [i];
	// // i--;
	// // }
	// // array[i+1] = key;
	// // printNumbers(array);
	// // }

	public static void recursive_selection_Sort(int[] arr, int small, int j, int counter) {
		if (counter >= arr.length - 1) {
			return;
		}
		if (j >= arr.length) {
			int temp = arr[small];
			arr[small] = arr[counter];
			arr[counter] = temp;
			recursive_selection_Sort(arr, counter + 1, counter + 2, counter + 1);
			return;
		}
		if (arr[j] < arr[small]) {
			small = j;
		}
		recursive_selection_Sort(arr, small, j + 1, counter);
	}

	public static void recursive_Bubble_Sort(int[] arr, int i, int j) {
		if (j == arr.length - 1) {
			return;
		}
		if (i == arr.length - j) {
			return;
		}
		if (arr[i] > arr[i + 1]) {
			int temp = arr[i];
			arr[i] = arr[i + 1];
			arr[i + 1] = temp;
		}
		recursive_Bubble_Sort(arr, i + 1, j);
		recursive_Bubble_Sort(arr, i, j + 1);
	}

	// if (low < high) {
	// swap(data, low, findMinIndex(data, low));
	// recursive_selection_Sort(data, low + 1, high);
	// }
	// }
	//
	// public static void swap(int[] array, int index1, int index2) {
	// int tmp = array[index1];
	// array[index1] = array[index2];
	// array[index2] = tmp;
	// }
	//
	// public static int findMinIndex(int[] data, int index) {
	// int minIndex;
	// if (index == data.length - 1)
	// return index;
	// minIndex = findMinIndex(data, index + 1);
	// if (data[minIndex] < data[index])
	// return minIndex;
	// else
	// return index;
	// }

}
