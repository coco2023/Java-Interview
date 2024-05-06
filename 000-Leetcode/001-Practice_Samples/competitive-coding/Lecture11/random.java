package Lecture11;


public class random {

	public static void main(String[] args) {
		System.out.println(formbig("2134"));
	}

	public static int getPV(int X, int N) {

		int coefficient = N;
		int power = X;
		int retVal = 0;
		for (int i = 1; i <= N; i++) {

			retVal += power * coefficient;
			power *= X;
			coefficient--;
		}
		return retVal;
	}

	public static int formbig(String X) {
		// ArrayList<String> permutations=Lecture6.Recursion.permutations(X);
		// int tobeCompared=0;
		// for(int i=X.length()-1;i>=0;i--)
		// {
		// tobeCompared=tobeCompared*10+(((int)X.charAt(i))-48);
		// }
		//
		// int temp=0;
		// int count=0;
		// for(int i=0;i<permutations.size();i++)
		// {
		// for(int j=permutations.get(i).length()-1;j>=0;j--)
		// {
		// temp=temp*10+(((int)permutations.get(i).charAt(j))-48);
		// }
		// if(temp>tobeCompared)
		// {
		// count++;
		// }
		// }
		// return count;
		int retVal = 0;
		int counter = X.length() - 2, factorial = 1, num = 2;
		while (counter >= 0) {
			int count = 0;
			int innerCounter = counter + 1;
			while (innerCounter < X.length()) {
				if (X.charAt(innerCounter) > X.charAt(counter)) {
					count++;
				}
				innerCounter++;
			}
			retVal += count * factorial;
			factorial *= num;
			num++;
			counter--;
		}
		return retVal;
	}

}
