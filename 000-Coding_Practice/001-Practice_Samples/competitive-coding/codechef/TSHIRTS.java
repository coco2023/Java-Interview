package codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TSHIRTS {

	public static int[][] dp = new int[1025][102];

	public static int MOD = 1000000007;

	public static int ALL_PERSON;

	public static HashMap<Integer, LinkedList<Integer>> hashMap;

	public static int num_of_ways(int mask, int shirt) {
		if (mask == ALL_PERSON) {
			return 1;
		}
		if (shirt == 101) {
			return 0;
		}
		if (dp[mask][shirt] != -1) {
			return dp[mask][shirt];
		}
		int ans = 0;
		ans = num_of_ways(mask, shirt + 1);
		if (hashMap.containsKey(shirt)) {
			for (int p : hashMap.get(shirt)) {
				if ((mask & (1 << p)) == 0) {
					ans += num_of_ways((mask | (1 << p)), shirt + 1);
				}
			}
		}
		ans %= MOD;
		dp[mask][shirt] = ans;
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t-- != 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] temp = new int[N][];
			for (int i = 0; i < N; i++) {
				temp[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			// creating a reverse mapping now
			// currently the 2d array has person to shirt mapping
			hashMap = new HashMap<>();
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[i].length; j++) {
					LinkedList<Integer> list;
					if (hashMap.containsKey(temp[i][j])) {
						list = hashMap.get(temp[i][j]);
					} else {
						list = new LinkedList<>();
					}
					if (!hashMap.containsKey(temp[i][j]) || !hashMap.get(temp[i][j]).contains(i)) {
						list.add(i);
						hashMap.put(temp[i][j], list);
					}
				}
			}
			ALL_PERSON = (1 << N) - 1;
			for (int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], -1);
			}
			System.out.println(num_of_ways(0, 1));
		}
	}

}
