package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class MatchThem {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		ArrayList<String> subsets = new ArrayList<>();
		printsub(str, 0, "", subsets);
		System.out.println(subsets);
		String check = "";
		int t = scn.nextInt();
		while (t-- != 0) {
			check += scn.next();
		}
		int count = 0;
		for (String s : subsets) {
			int i = 0;
			for (; i < check.length(); i++) {
				if (!s.contains(check.charAt(i) + "")) {
					break;
				}
			}
			if (i == check.length()&&!s.equals("")) {
				count++;
			}
		}
		System.out.println(count);
		scn.close();
	}

	static void printsub(String a, int i, String ans, ArrayList<String> subsets) {
		if (i == a.length()) {
//			if (!subsets.contains(ans))
				subsets.add(ans);
			return;
		}
		printsub(a, i + 1, ans + a.charAt(i) + "", subsets);
		printsub(a, i + 1, ans, subsets);
		return;
	}
}
