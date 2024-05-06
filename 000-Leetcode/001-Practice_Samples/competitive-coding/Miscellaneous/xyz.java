package Miscellaneous;


public class xyz {

	public static void main(String[] args) {
		// int[] x = new int[]{1,2,3,4,5};
		//// for (int i = 0; i < x.length; i++) {
		//// x[i] = i;
		//// }
		// change2(x, 3);
		// for (int i = 0; i < x.length; i++) {
		// System.out.print(x[i] + ",");
		// }
		// System.out.println();
		// }
//		Scanner scn = new Scanner(System.in);
//		int N = scn.nextInt();
//		String[] arr = new String[N];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = scn.next();
//		}
//		int Q = scn.nextInt();
//		String[] queries = new String[Q];
//		for (int i = 0; i < queries.length; i++) {
//			queries[i] = scn.next();
//		}
//		int[] length = new int[Q];
//		for (int i = 0; i < queries.length; i++) {
//			String temp = queries[i];
//			for (int j = 0; j < arr.length; j++) {
//				if (arr[j].equals(temp)) {
//					length[i]++;
//				}
//			}
//		}
//		for (int i = 0; i < length.length; i++) {
//			System.out.println(length[i]);
//		}
//		factors(197593);
//		scn.close();
		Graph<Character> g = new Graph<>();
		g.addEdge('a', 'g');
		g.addEdge('a', 'f');
		g.addEdge('a', 'e');
//		g.addEdge('e', 'f');
		g.addEdge('a', 'c');
		g.addEdge('e', 'd');
		g.addEdge('e', 'b');
		g.addEdge('b', 'f');
		g.addEdge('b', 'c');
		g.addEdge('c', 'd');
		g.addEdge('d', 'f');
		g.addEdge('d', 'g');
		System.out.println(g.isBipartite());
	}

	// O(n^2)-time and O(1) space
	public static void change(int[] arr, int d) {
		for (int i = 1; i <= arr.length - d; i++) {
			{
				int temp = arr[arr.length - 1];
				for (int j = arr.length - 1; j > 0; j--) {
					arr[j] = arr[j - 1];
				}
				arr[0] = temp;
			}
		}
	}

	public static void factors(int n) {
		int div = 2;
		while (n != 0) {
			if (n % div == 0) {
				System.out.println(div);
				n /= div;
			} else {
				div++;
			}
		}
	}

	// O(n)-time and O(d) space
	public static void change2(int[] arr, int d) {
		d = arr.length - d;
		int[] temp = new int[d];
		for (int i = 0; i < d; i++) {
			temp[i] = arr[i];
		}
		for (int i = d; i < arr.length; i++) {
			arr[i - d] = arr[i];
		}
		int j = 0;
		for (int i = arr.length - d; i < arr.length; i++) {
			arr[i] = temp[j++];
		}
	}
}
