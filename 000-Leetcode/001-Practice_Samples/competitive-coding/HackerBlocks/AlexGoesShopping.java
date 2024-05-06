package HackerBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AlexGoesShopping {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N = scn.nextInt();
		ArrayList<Long> temp = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			temp.add(scn.nextLong());
		}
//		ArrayList<Long> arr = new ArrayList<>();
//		Collections.sort(temp);
		long t = scn.nextLong();
		for (int i = 0; i < t; i++) {
			int moneyVal = scn.nextInt();
			int query = scn.nextInt();
			int itemCount=0;
			for (int j = 0; j < temp.size(); j++) {
				if (temp.get(j) <= moneyVal && moneyVal % temp.get(j) == 0) {
					itemCount++;
				}
				else if(moneyVal % temp.get(j) != 0){
					continue;
				}
				else{
					break;
				}
			}
			if (itemCount == query) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
//			arr.clear();
		}
		scn.close();
	}

}
