package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CountPermutations {

	public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        ArrayList<String> permutations = new ArrayList<>();
        String s = scn.next();
        printPermutations(s,"",permutations);
        Collections.sort(permutations);
        for(String y : permutations){
            System.out.println(y);
        }
        scn.close();
    }
    public static void printPermutations(String str, String osf, ArrayList<String> permutations) {
		int n = str.length();
		if (n == 0) {
			if(!permutations.contains(osf)){
			    permutations.add(osf);
			}
		} else {
			for (int i = 0; i < n; i++) {
				printPermutations(str.substring(0, i) + str.substring(i + 1, n), osf + str.charAt(i), permutations);
			}
		}
	}

}
