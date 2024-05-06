package CodingBat;

public class countTriple {
	public static void main(String[] args) {
		System.out.println(countTriple("XXXabc"));
	}

	public static int countTriple(String str) {
		int triple = 0;
		for (int i = 0; i < str.length(); i++) {
			if (i + 3 <= str.length() && str.substring(i + 1, i + 3).equals(str.charAt(i) + ""+str.charAt(i)+"")) {
				triple++;
			}
		}
		return triple;
	}
}
