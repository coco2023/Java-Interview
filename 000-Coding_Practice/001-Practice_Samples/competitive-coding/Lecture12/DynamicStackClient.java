package Lecture12;

public class DynamicStackClient {

	public static void main(String[] args) throws Exception{
		Stack S=new DynamicStack(12);
		for (int i = 1; i <= 12; i++) {
			S.push(i);
		}
		for(int i=1;i<=12;i++)
		{
			System.out.println(S.pop()+" deleted!");
		}
	}

}
