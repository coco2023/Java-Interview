package CodingBat;

public class notReplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(notReplace("is-is"));
	}

	public static String notReplace(String str) {
		StringBuilder retVal = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'i') {
				if ((i + 1 < str.length() && str.charAt(i + 1) == 's')
						&& (i == 0 || !Character.isLetter(str.charAt(i - 1))) && (i + 1 == str.length() - 1
								|| (i + 2 < str.length()) && !Character.isLetter(str.charAt(i + 2)))) {
					retVal.append("is not");
					i++;
				} else {
					retVal.append(str.charAt(i) + "");
				}
			} else {
				retVal.append(str.charAt(i) + "");
			}
		}
		return retVal.toString();
	}
}
