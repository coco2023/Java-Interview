package HackerBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class HOLI {
	public static class Graph {
		private int V;
		private LinkedList<Integer>[] adj;
		private HashMap<Integer, Integer> weights;

		private class Edge {
			int x, y, weight;
		}

		private ArrayList<Edge> edgeList;

		Graph(int v) {
			weights = new HashMap<>();
			edgeList = new ArrayList<>();
			V = v;
			adj = new LinkedList[V];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList();
		}

		void addEdge(int v, int w, int weight) {
			adj[v].add(w);
			adj[w].add(v);// Add w to v's list.
			Edge edge = new Edge();
			edge.weight = weight;
			edge.x = v;
			edge.y = w;
			edgeList.add(edge);
			weights.put(v, weight);
			// if (adj[w].size() != 1) {
			weights.put(w, weight);
			// }
		}

		public int nodeCount = 1;

		void DFSUtil(int v, boolean visited[], HashMap<Integer, Integer> count) {
			visited[v] = true;
			Iterator<Integer> i = adj[v].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n])
					DFSUtil(n, visited, count);
			}
			count.put(v, nodeCount++);
		}

		public int weightCount = 0;

		void DFS(int v) {
			boolean visited[] = new boolean[1000000];
			HashMap<Integer, Integer> hashmap = new HashMap<>();
			DFSUtil(v, visited, hashmap);
			Set<Map.Entry<Integer, Integer>> allEntries = hashmap.entrySet();
			Set<Map.Entry<Integer, Integer>> testEntries = weights.entrySet();
			for (Map.Entry<Integer, Integer> entry : allEntries) {
				System.out.println(entry.getKey()+" : "+entry.getValue()+"\t");
			}
			System.out.println();
//			weightCount = 0;
//			for (int i = 0; i < edgeList.size(); i++) {
//				weightCount += 2 * min((V - 1) - (hashmap.get(edgeList.get(i).x)), (hashmap.get(edgeList.get(i).y)))
//						* weights.get(edgeList.get(i).weight);
//			}
//			System.out.println(weightCount);
		}

		int min(int x, int y) {
			return (x > y) ? y : x;
		}
	}

	public static void main(String[] args) {
		// 6
		// 1 2 3
		// 2 3 4
		// 2 4 1
		// 4 5 8
		// 5 6 5
		Graph g = new Graph(7);
		g.addEdge(1, 2, 3);
		g.addEdge(2, 3, 4);
		g.addEdge(2, 4, 1);
		g.addEdge(4, 5, 8);
		g.addEdge(5, 6, 5);
		g.DFS(1);
	}

}
