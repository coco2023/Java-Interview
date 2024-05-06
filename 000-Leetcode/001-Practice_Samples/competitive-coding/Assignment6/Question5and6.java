package Assignment6;

import java.util.ArrayList;

public class Question5and6 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		int count=(int) Math.pow(2,arr.length);
		System.out.println(subsets(arr,0,4));
	}

	public static ArrayList<ArrayList<Integer>> subsets(int[] arr, int si, int li) {
		if (li == si) {
			ArrayList<ArrayList<Integer>> baseResult = new ArrayList<>();
			ArrayList<Integer> arr1 = new ArrayList<>();
			baseResult.add(arr1);
			return baseResult;
		}
		ArrayList<ArrayList<Integer>> temp = subsets(arr, si, li - 1);
		int N = temp.size();
		int x = arr[li - 1];
		ArrayList<ArrayList<Integer>> temp2 = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			temp2.add(temp.get(i));
		}

		for (int i = 0; i < N; i++) {
			int k = temp2.get(i).size();
			ArrayList<Integer> toBeAdded = new ArrayList<>();
			for (int j = 0; j < k; j++) {
				toBeAdded.add(temp2.get(i).get(j));
			}
			toBeAdded.add(x);
			temp2.add(toBeAdded);
		}
		return temp2;
	}
	//Doubt
	public static void printsubsets(int[] arr, int N,int i) {
		
	}
	public static ArrayList<ArrayList<Integer>> subsetssumtoK(int[] arr, int si, int li, int K) {
		ArrayList<ArrayList<Integer>> temp = subsets(arr, si, li);
		ArrayList<ArrayList<Integer>> retVal = new ArrayList<>();
		for (int i = 0; i < temp.size(); i++) {
			int sum = 0;
			for (int j = 0; j < temp.get(i).size(); j++) {
				sum = sum + temp.get(i).get(j);
			}
			if (sum == K) {
				retVal.add(temp.get(i));
			}
		}
		return retVal;
	}
	
}
