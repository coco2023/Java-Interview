package Lecture23;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Client {

	public static void main(String[] args) throws Exception {

		int[] arr = { 2, 12, 9, 16, 10, 5, 3, 20, 25, 11, 1, 8, 6 };
		HashTable<Integer, Boolean> ht=new HashTable<>();
		for(int i=0;i<arr.length;i++)
		{
			ht.put(arr[i], false);
			ht.display();
			System.out.println("********************************");
		}
		//System.out.println(longestSS(arr));
	}

	public static ArrayList<Integer> longestSS(int[] arr) {
		Hashtable<Integer, Boolean> ht = new Hashtable<>();
		for (int i = 0; i < arr.length; i++) {
			if (ht.containsKey(arr[i] + 1)) {
				ht.put(arr[i + 1], false);
			}
			ht.put(arr[i], !ht.containsKey(arr[i] - 1));
		}
		ArrayList<ArrayList<Integer>> retval = new ArrayList<>();
		Set<Map.Entry<Integer, Boolean>> allEntries = ht.entrySet();
		for (Map.Entry<Integer, Boolean> value : allEntries) {
			if (value.getValue()) {
				int x = value.getKey() + 1;
				ArrayList<Integer> temp = new ArrayList<>();
				temp.add(x - 1);
				while (ht.containsKey(x)) {
					temp.add(x);
					x++;
				}
				retval.add(temp);
			}
		}
		int max = 0, maxsize = retval.get(0).size();
		for (int i = 1; i < retval.size(); i++) {
			if (retval.get(i).size() > maxsize) {
				maxsize = retval.get(i).size();
				max = i;
			}
		}
		return retval.get(max);
	}

}
