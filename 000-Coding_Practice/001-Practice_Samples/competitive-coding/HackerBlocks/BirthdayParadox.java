package HackerBlocks;

import java.util.Scanner;

public class BirthdayParadox {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		double p = scn.nextDouble();
		double currProbability=1.0;
		int num = 1;
		double multiplier = 364.0;
		while((1-currProbability)<p){
			num++;
			currProbability*=(multiplier)/365;
			multiplier--;
		}
		System.out.println(num);
		scn.close();
	}

}
