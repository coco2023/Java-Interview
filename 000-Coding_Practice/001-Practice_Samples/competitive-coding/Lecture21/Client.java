package Lecture21;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// Heap<Integer, String> H=new Heap<>();
		// H.add(200, "Hello");
		// H.add(1000, "Helloo");
		// H.add(2000, "Heello");
		// H.add(20, "prror");
		// H.add(192, "jdhsjhd");
		// H.add(2344, "dih");
		// H.display();
		// H.displayasTree();
		int[] Scores = { 25, 22, 100, 24, 78, 15, 90, 65, 32, 10 };
		// Scanner scn = new Scanner(System.in);
		// // String[] Names={"A","B","C","D","E","F","G","H","I","J"};
		// // Heap<Integer, String> H=new Heap<>(Scores,Names,true);
		// // H.displayasTree();
		for (int i = 0; i < Scores.length; i++) {
		System.out.print(Scores[i] + ", ");
		}
		System.out.println("END");
		inPlaceHeapSort(Scores);
		for (int i = 0; i < Scores.length; i++) {
		System.out.print(Scores[i] + ", ");
		}
		System.out.println("END");
		// int k = scn.nextInt();
		// System.out.println(klargest(Scores, k));
		// ArrayList<Integer> one = new ArrayList<>(Arrays.asList(1, 5, 10));
		// ArrayList<Integer> two = new ArrayList<>(Arrays.asList(3, 19, 22, 24));
		// System.out.println(klargest(Scores, 2));
		// ArrayList<Integer> three = new ArrayList<>(Arrays.asList(-4, 8, 16));
		// ArrayList<Integer> four = new ArrayList<>(Arrays.asList(0, 2, 12));
		// System.out.println(ksortArrayList(one, two, three, four));

	}

	private static class Pair {
		int li;
		int iwli;

		Pair(int li, int iwli) {
			this.li = li;
			this.iwli = iwli;
		}
	}

	public static ArrayList<Integer> ksortArrayList(ArrayList<Integer>... arrayLists) {
		ArrayList<Integer> retval = new ArrayList<>();
		Heap<Integer, Pair> heap = new Heap<>(true);
		for (int i = 0; i < arrayLists.length; i++) {
			Pair p = new Pair(i, 0);
			heap.add(arrayLists[i].get(0), p);
		}
		while (!heap.isEmpty()) {
			Pair p = heap.removeHP();
			retval.add(arrayLists[p.li].get(p.iwli));
			if (p.iwli < arrayLists[p.li].size() - 1) {
				Pair ap = new Pair(p.li, p.iwli + 1);
				heap.add(arrayLists[ap.li].get(ap.iwli), ap);
			}
		}
		return retval;
	}

	public static ArrayList<Integer> klargest(int[] arr, int k) {
		Heap<Integer, Boolean> heap = new Heap<>(false);
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i], false);
		}
		ArrayList<Integer> retval = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			retval.add(heap.removeHPkey());
		}
		return retval;
	}

	// remove and print gives sorted array in decreasing order because removal
	// is of the max item in a maxHeap
	// this(in Place HeapSort) implements the same algorithm without taking
	// extra space of a heap which happens because of the nodes arraylist
	public static void inPlaceHeapSort(int[] arr) {
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			downheapify(arr, i, arr.length);
		}
		for (int i = 0; i < arr.length - 1; i++) {
			swap(arr, 0, arr.length - i - 1);
			downheapify(arr, 0, arr.length - i - 1);
		}
	}

	public static void downheapify(int[] arr, int pi, int lp) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		int maxi = pi;
		if (lci < lp && arr[lci] > arr[maxi]) {
			maxi = lci;
		}
		if (rci < lp && arr[rci] > arr[maxi]) {
			maxi = rci;
		}
		if (maxi != pi) {
			swap(arr, maxi, pi);
			downheapify(arr, maxi, lp);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
