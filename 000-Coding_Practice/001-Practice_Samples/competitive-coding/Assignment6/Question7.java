package Assignment6;

public class Question7 {

	public static void main(String[] args) {
		System.out.println(stringChecker("abba", true, 0));
	}

	public static boolean stringChecker(String str, boolean flag, int count) {

		if (str.length() == 0) {
			if (count != 0) {
				return flag;
			} else {
				return false;
			}
		}
		if (flag == false) {
			return flag;
		}
		String ros = new String();
		if (str.length() >= 1) {
			ros = str.substring(1);
		} else {
			return false;
		}
		if (str.charAt(0) == 'a') {
			if ((count == 0 && str.charAt(0) == 'a') || str.length() == 1 || str.charAt(1) == 'a'
					|| (str.length() > 2 && str.substring(1, 3).equals("bb"))) {
				flag = true;
				count++;
			} else {
				flag = false;
				count++;
			}
		}
		if (str.length() >= 2 && str.substring(0, 2).equals("bb")) {
			if (str.length() == 2 || (str.length() >= 2 && str.charAt(2) == 'a')) {
				ros = str.substring(2);
				flag = true;
				count++;
			} else {
				flag = false;
				count++;
			}
		}
		boolean rflag = stringChecker(ros, flag, count);
		return rflag;
	}
}
