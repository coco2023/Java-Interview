package HackerBlocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class ChewbaccaAndNumber {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		BigInteger N=scn.nextBigInteger();
		ArrayList<Integer> arrayList=new ArrayList<>();
		while(N.compareTo(BigInteger.ZERO)!=0){
			arrayList.add(0,(N.remainder(BigInteger.TEN)).intValue());
			N=N.divide(BigInteger.TEN);
		}
		for(int i=0;i<arrayList.size();i++){
			if(arrayList.get(i)>4){
				arrayList.set(i, 9-arrayList.get(i));
			}
		}
		BigInteger result=BigInteger.ZERO;
		for(int i=0;i<arrayList.size();i++){
			result=result.multiply(BigInteger.TEN).add(BigInteger.valueOf(arrayList.get(i)));
		}
		System.out.println(result);
	}

}
