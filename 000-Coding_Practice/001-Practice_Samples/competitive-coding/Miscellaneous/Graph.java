package Miscellaneous;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


public class Graph<T extends Comparable<T>> {

	HashMap<T, LinkedList<T>> adjacencyList;

	public Graph() {
		this.adjacencyList = new HashMap<>();
	}

	public void addEdge(T start, T end) {
		this.addEdge(start, end, true);
	}

	public void addEdge(T start, T end, boolean bidir) {
		this.helperFunc(start, end);
		if (bidir) {
			this.helperFunc(end, start);
		}
	}

	private void helperFunc(T start, T end) {
		LinkedList<T> neighbourList;
		if (this.adjacencyList.containsKey(start)) {
			neighbourList = this.adjacencyList.get(start);
		} else {
			neighbourList = new LinkedList<>();
		}
		// no need to check in case of multiple edges from the same pair of vertices
		neighbourList.add(end);
		this.adjacencyList.put(start, neighbourList);
	}


	public boolean isBipartite() {
		HashMap<T, Boolean> visitedMap = new HashMap<>();
		Stack<T> stack = new Stack<>();
		boolean flag = true;
		Set<Map.Entry<T, LinkedList<T>>> entrySet = this.adjacencyList.entrySet();
		visitedMap.put(entrySet.iterator().next().getKey(), flag);
		Map.Entry<T, LinkedList<T>> firstNode = entrySet.iterator().next();
		stack.push(firstNode.getKey());
		while (!stack.isEmpty()) {
			T v = stack.pop();
			for(Map.Entry<T, LinkedList<T>> entry : entrySet) {
				if(entry.getKey() == v) {
					firstNode = entry;
					break;
				}
			}
			LinkedList<T> nbrs = firstNode.getValue();
			for (T vtx : nbrs) {
				if (!visitedMap.containsKey(vtx)) {
					flag = !visitedMap.get(v);
					visitedMap.put(vtx, flag);
					stack.push(vtx);
				} else {
					if (visitedMap.get(vtx) != !visitedMap.get(v)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String retValue = "";
		Set<Map.Entry<T, LinkedList<T>>> entrySet = this.adjacencyList.entrySet();
		for (Map.Entry<T, LinkedList<T>> entry : entrySet) {
			retValue += entry.getKey() + "->";
			for (T neighbour : entry.getValue()) {
				retValue += neighbour + ",";
			}
			retValue += "END\n";
		}
		return retValue;
	}

}
