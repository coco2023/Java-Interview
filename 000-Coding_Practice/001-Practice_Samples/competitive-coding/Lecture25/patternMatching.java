package Lecture25;

public class patternMatching {

	public static void main(String[] args) {
		System.out.println(patternMatchingBruteForce("abcsgdjs", "bcs"));
	}

	public static int patternMatchingBruteForce(String Source, String pattern) {
		int i = 0;
		while (i <= Source.length() - pattern.length()) {
			int j = 0;
			while (j < pattern.length()) {
				if (Source.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
				j++;
			}
			if (j == pattern.length()) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
}
