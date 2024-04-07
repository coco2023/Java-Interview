package HackerBlocks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ImportanceOfTime {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int N=scn.nextInt();
		ArrayList<Integer> idealOrder = new ArrayList<>();
		Queue<Integer> callingOrder= new LinkedList<>();
		for(int i=0;i<N;i++){
			callingOrder.offer(scn.nextInt());
		}
		for(int i=0;i<N;i++){
			idealOrder.add(scn.nextInt());
		}
		int totalTime=0;
		for(int i=0;i<N;i++){
			if(callingOrder.peek()==idealOrder.get(i)){
				totalTime++;
				callingOrder.poll();
			}
			else{
				while(callingOrder.peek()!=idealOrder.get(i)){
					callingOrder.offer(callingOrder.poll());
					totalTime++;
				}
				totalTime++;
				callingOrder.poll();
			}
		}
		System.out.println(totalTime);
		scn.close();
	}

}
