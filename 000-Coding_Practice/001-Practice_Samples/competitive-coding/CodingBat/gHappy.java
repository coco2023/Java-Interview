package CodingBat;

public class gHappy {

	public static void main(String[] args) {
		System.out.println(gHappy("g"));
	}

	public static boolean gHappy(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'g') {
				if (i <= str.length() - 2 && str.substring(i, i + 2).equals("gg")) {
					flag = true;
					i++;
				} else if (i >= 1 && str.charAt(i - 1) == 'g') {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

}
