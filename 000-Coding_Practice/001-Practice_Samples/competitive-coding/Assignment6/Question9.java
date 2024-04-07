package Assignment6;

//Assignment 6 Question 9
public class Question9 {

	public static void main(String[] args) {
		System.out.println(countSteps(10, 0, 0));

	}

	public static int countSteps(int N, int count, int counter) {
		if (count == N) {
			return counter + 1;
		}
		int temp = 0;
		for (int i = 0; i <= 3; i++) {
			if (count + i <= N) {
				temp = i;
			}
		}
		for (int i = 1; i <= temp; i++) {
			counter = countSteps(N, count + i, counter);
		}
		return counter;
	}
}
