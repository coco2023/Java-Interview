package Lecture17;

import java.util.Random;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the number of cars:");
		int N = scn.nextInt();
		Car[] cars = new Car[N];
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			cars[i] = new Car(random.nextInt(200), random.nextInt(1000));
		}
		for (int i = 0; i < N; i++) {
			System.out.print(cars[i]);
		}
		bubblesort(cars);
		System.out.println("\n");
		for (int i = 0; i < N; i++) {
			System.out.print(cars[i]);
		}
		scn.close();
	}

	public static <T extends Comparable<T>> void bubblesort(T[] arr) {
		int counter = 1;
		while (counter < arr.length) {
			for (int i = 0; i < arr.length - counter; i++) {
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					T temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			counter++;
		}
	}
}
