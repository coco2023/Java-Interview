package HackerBlocks;

import java.math.BigInteger;
import java.util.Scanner;

public class BaseConverter {
	public static void main(String[] args) {
		Scanner scn = new Scanner (System.in);
		int sb=scn.nextInt(),db=scn.nextInt();
		String temp=scn.next();
		BigInteger b1=new BigInteger(temp, sb);
		System.out.println(b1.toString(db));
		scn.close();
	}
}
