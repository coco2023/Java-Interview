package CodingBat;

public class withoutString {

	public static void main(String[] args) {
		System.out.println(withoutString("xxx", "xx"));
	}

	public static String withoutString(String base, String remove) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < base.length(); i++) {
			if ((i + remove.length()) <= base.length()
					&& base.substring(i, i + remove.length()).equalsIgnoreCase(remove)) {
				i = i + remove.length() - 1;
			} else {
				temp.append(base.charAt(i));
			}
		}
		return temp.toString();
	}
}
