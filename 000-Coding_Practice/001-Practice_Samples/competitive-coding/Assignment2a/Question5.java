package Assignment2a;

public class Question5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 1, count = 1;
		while (count <= 20) {
			if (((3 * i) + 2) % 4 != 0) {
				System.out.print((3 * i) + 2 + ",");
				count = count + 1;
			}
			i++;
		}
		System.out.println("END");
	}

}
