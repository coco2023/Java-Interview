package Miscellaneous;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimesLessThanX {
	public static final int x=600;
	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
//		float temp=(float)Math.sqrt(x);
//		int sqroot=Math.round(temp)+1;
//		System.out.println(sqroot);
//		ArrayList<Integer> intList=primesLessThanX(sqroot);
//		System.out.println(intList);
//		long StartTime=System.currentTimeMillis();
//		double[] sum=sum(1000083647);
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(System.currentTimeMillis()-StartTime);
//		System.out.println(sum[0]+" "+sum[1]);
		System.out.println(Math.PI);
	}
	private static ArrayList<Integer> primesLessThanX(int sqroot) {
		boolean[] arr=new boolean[x+1];
		for(int i=0;i<arr.length;i++){
			arr[i]=true;
		}
		for(int i=2;i<=sqroot;i++){
			for(int j=0;j<arr.length;j++){
				if(j%i==0&&j!=i){
					arr[j]=false;
				}
			}
		}
		ArrayList<Integer> retval=new ArrayList<>();
		for(int i=0;i<arr.length;i++){
			if(arr[i]){
				retval.add(i);
			}
		}
		return retval;
	}
	private static double[] sum(int n){
		int i=0;
		double sum[]=new double[2];
		sum[0]=0.0;
		sum[1]=0.0;
		while(i<=n){
			double add=1/(1000*i +Math.PI);
			sum[0]+=add;
			i++;
		}
		while(i>=0){
			double add=1/(1000*i +Math.PI);
			sum[1]+=add;
			i--;
		}
		return sum;
	}
}
