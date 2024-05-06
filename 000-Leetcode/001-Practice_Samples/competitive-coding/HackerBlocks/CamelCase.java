package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class CamelCase {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		ArrayList<Integer> indiceArr = new ArrayList<>();
		String value = scn.next();
		indiceArr.add(0);
		for (int i = 0; i < value.length(); i++) {
			if ((int) value.charAt(i) >= 65 && (int) value.charAt(i) <= 90) {
				indiceArr.add(i);
			}
		}
		indiceArr.add(value.length());
		for (int i = 0; i < indiceArr.size()-1; i++) {
			System.out.println(value.substring(indiceArr.get(i), indiceArr.get(i + 1)));
		}
		scn.close();

	}

}
