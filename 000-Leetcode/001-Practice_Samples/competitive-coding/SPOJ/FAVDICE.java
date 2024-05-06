package SPOJ;

import java.math.BigDecimal;
import java.util.Scanner;

public class FAVDICE {

	public static void main(String[] args) {
		Scanner scn =new Scanner(System.in);
		int t=scn.nextInt();
		for(int i=1;i<=t;i++){
			int N=scn.nextInt();
			float sum=0;
			for(int j=1;j<=N;j++){
				sum+=(1.0/j);
			}
			BigDecimal bigDecimal=new BigDecimal((N*sum));
			bigDecimal=bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			System.out.println(bigDecimal);
		}
	}

}
