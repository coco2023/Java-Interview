package HackerBlocks;

import java.util.Scanner;

public class OddEven {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N=scn.nextInt();
		while(N--!=0){
			String carNo=scn.next();
			int oddSum=0,evenSum=0;
			for(int i=0;i<carNo.length();i++){
				if((carNo.charAt(i)-'0')%2==1){
					oddSum+=carNo.charAt(i)-'0';
				}
				else{
					evenSum+=carNo.charAt(i)-'0';
				}
			}
			if(evenSum%4==0){
				System.out.println("Yes");
			}
			else if(oddSum%3==0){
				System.out.println("Yes");
			}
			else{
				System.out.println("No");
			}
		}
		scn.close();
	}

}
