package HackerBlocks;

import java.math.BigInteger;

public class InterestingPrime {

	public static void main(String[] args) {
		System.out.println(retPrimeCount(BigInteger.valueOf(4), BigInteger.ZERO));

	}
	public static boolean checkPrime(BigInteger N){
		for(BigInteger i=BigInteger.valueOf(2);i.compareTo(N.divide(BigInteger.valueOf(2)))<=0;i=i.add(BigInteger.ONE)){
			if(N.remainder(i)==BigInteger.ZERO){
				return false;
			}
		}
		return true;
	}
	
	public static BigInteger retPrimeCount(BigInteger N, BigInteger result){
		if(N.compareTo(BigInteger.ZERO)==0){
			return BigInteger.ONE;
		}
		for(BigInteger i=BigInteger.valueOf(2);i.compareTo(N)<=0;i=i.add(BigInteger.ONE)){
			if(checkPrime(i)){
				result=result.add(retPrimeCount(N.subtract(i),result.add(BigInteger.ONE)));
			}
		}
		return result;
	}
}
