package Lecture12;

public class StackClient {

	public static void main(String[] args) throws Exception {
		Stack S1 = new Stack(12);

		for (int i = 1; i <= 12; i++) {
			S1.push(i);
		}
		S1.display();
		S1.pop();
		S1.display();
		S1.top();
		S1.push(13);
		S1.display();
		System.out.println(S1.size());
	}

}
