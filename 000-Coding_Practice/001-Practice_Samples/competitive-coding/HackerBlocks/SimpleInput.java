package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class SimpleInput {

	public static void main(String[] args) {
		int sum=0;
		int N;
		ArrayList<Integer> arrayList=new ArrayList<>();
		Scanner scn=new Scanner(System.in);
		while(sum>=0){
			N=scn.nextInt();
			arrayList.add(N);
			sum+=N;
		}
		for(int i=0;i<arrayList.size()-1;i++){
			System.out.println(arrayList.get(i));
		}
		scn.close();
	}

}
