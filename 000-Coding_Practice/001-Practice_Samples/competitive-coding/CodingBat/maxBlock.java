package CodingBat;

public class maxBlock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int MaxBlock(String str) {
		if (str.length() == 0) {
			return 0;
		}
		int[] temp = new int[str.length()];
		int counter = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			counter = 0;
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(j) == c) {
					temp[i]++;
					counter++;
				} else if (counter != 0) {
					i = j - 1;
					break;
				} else {
					break;
				}
			}
		}
		int big = temp[0];
		for (int i = 1; i < temp.length; i++) {
			if (temp[i] > big) {
				big = temp[i];
			}
		}
		return big + 1;
	}
}
