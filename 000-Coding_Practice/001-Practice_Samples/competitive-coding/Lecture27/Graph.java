package Lecture27;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	private class Edge {
		Vertex one;
		Vertex two;

		public Edge(Vertex one, Vertex two) {
			this.one = one;
			this.two = two;
		}
	}

	private class Vertex {
		HashMap<Vertex, Edge> neighbours;
		String name;

		public Vertex(String name) {
			this.neighbours = new HashMap<>();
			this.name = name;
		}

		@Override
		public boolean equals(Object obj) {
			Vertex tbc = (Vertex) obj;
			return this.name.equals(tbc.name);
		}

		public int hashCode() {
			return this.name.hashCode();
		}

		public boolean isAdjacentTo(Vertex ov) {
			return this.neighbours.containsKey(ov);
		}

		public void removeEdgeWith(Vertex ov) {
			if (!this.isAdjacentTo(ov)) {
				return;
			}
			this.neighbours.remove(ov);
		}

		public void addEdge(Edge edge) {

			Vertex tb = null;
			if (edge.one.equals(this)) {
				tb = edge.two;
			} else {
				tb = edge.one;
			}
			if (this.isAdjacentTo(tb)) {
				return;
			}
			this.neighbours.put(tb, edge);
		}

		public ArrayList<Vertex> getNeighbours() {
			return new ArrayList<>(this.neighbours.keySet());
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private ArrayList<Vertex> vertices;

	public Graph() {
		this.vertices = new ArrayList<>();
	}

	public int numVertices() {
		return this.vertices.size();
	}

	public void addVertex(String vname) {
		if (this.containsVertex(vname)) {
			return;
		}
		Vertex tb = new Vertex(vname);
		this.vertices.add(tb);
	}

	public void removeVertex(String vname) {
		if (!this.containsVertex(vname)) {
			return;
		}
		Vertex tbr = this.getVertex(vname);
		ArrayList<Vertex> neighbour = tbr.getNeighbours();
		for (Vertex vtx : neighbour) {
			// vtx.neighbours.remove(tbr);
			vtx.removeEdgeWith(tbr);
		}
		this.vertices.remove(tbr);

	}

	public boolean containsVertex(String vname) {
		return this.getVertex(vname) != null;
	}

	private Vertex getVertex(String vname) {
		for (int i = 0; i < vertices.size(); i++) {
			if (this.vertices.get(i).name.equals(vname)) {
				return this.vertices.get(i);
			}
		}
		return null;
	}

	public int numEdges() {
		int retval = 0;
		for (Vertex vtx : this.vertices) {
			retval += vtx.neighbours.size();
		}
		retval /= 2;
		return retval;
	}

	public void AddEdge(String v1, String v2) {
		Vertex vtx1 = this.getVertex(v1), vtx2 = this.getVertex(v2);
		if (vtx1 == null || vtx2 == null || vtx1.isAdjacentTo(vtx2)) {
			return;
		}
		Vertex tb1 = this.getVertex(v1);
		Vertex tb2 = this.getVertex(v2);
		Edge edge = new Edge(tb1, tb2);
		tb1.addEdge(edge);
		tb2.addEdge(edge);
		// tb1.neighbours.put(tb2, edge);
		// tb2.neighbours.put(tb1, edge);
	}

	public void removeEdge(String v1, String v2) {
		Vertex vtx1 = this.getVertex(v1), vtx2 = this.getVertex(v2);
		if (vtx1 == null || vtx2 == null || !vtx1.isAdjacentTo(vtx2)) {
			return;
		}
		vtx1.removeEdgeWith(vtx2);
		vtx2.removeEdgeWith(vtx1);
	}

	public boolean containsEdge(String v1, String v2) {
		Vertex vtx1 = this.getVertex(v1), vtx2 = this.getVertex(v2);
		if (vtx1 == null || vtx2 == null || !vtx1.isAdjacentTo(vtx2)) {
			return false;
		}
		return true;
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		String retval = "";
		for (Vertex vtx : vertices) {
			retval += vtx + " => " + vtx.getNeighbours() + "\n";
		}
		return retval;
	}

	public boolean hasPath(String v1, String v2) {
		Vertex vtx1 = this.getVertex(v1), vtx2 = this.getVertex(v2);
		if (vtx1 == null || vtx2 == null) {
			return false;
		}
		// HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		// visitedMap.put(vtx1, true);
		return this.hasPathBFS(vtx1, vtx2);

	}

	@SuppressWarnings("unused")
	private boolean hasPathDFS(Vertex v1, Vertex v2, HashMap<Vertex, Boolean> visitedMap) {
		if (v1.isAdjacentTo(v2)) {
			return true;
		}
		ArrayList<Vertex> nbrs = v1.getNeighbours();
		for (Vertex vtx : nbrs) {
			if (!visitedMap.containsKey(vtx)) {
				visitedMap.put(vtx, true);
				if (this.hasPathDFS(vtx, v2, visitedMap)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasPathBFS(Vertex v1, Vertex v2) {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();
		visitedMap.put(v1, true);
		queue.offer(v1);
		while (!queue.isEmpty()) {
			Vertex v = queue.poll();
			if (v.isAdjacentTo(v2)) {
				return true;
			}
			ArrayList<Vertex> nbrs = v.getNeighbours();
			for (Vertex vtx : nbrs) {
				if (!visitedMap.containsKey(vtx)) {
					visitedMap.put(vtx, true);
					queue.offer(vtx);
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean hasPathDFSI(Vertex v1, Vertex v2) {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();
		visitedMap.put(v1, true);
		stack.push(v1);
		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			if (v.isAdjacentTo(v2)) {
				return true;
			}
			ArrayList<Vertex> nbrs = v.getNeighbours();
			for (Vertex vtx : nbrs) {
				if (!visitedMap.containsKey(vtx)) {
					visitedMap.put(vtx, true);
					stack.push(vtx);
				}
			}
		}
		return false;
	}

	public void BFT() {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Queue<Vertex> queue = new LinkedList<>();

		for (Vertex vtx1 : this.vertices) {
			if (!visitedMap.containsKey(vtx1)) {
				queue.offer(vtx1);
				visitedMap.put(vtx1, true);

				while (!queue.isEmpty()) {
					Vertex v = queue.poll();
					System.out.print(v + ",");
					ArrayList<Vertex> nbrs = v.getNeighbours();
					for (Vertex vtx : nbrs) {
						if (!visitedMap.containsKey(vtx)) {
							visitedMap.put(vtx, true);
							queue.offer(vtx);
						}
					}
				}
			}
		}
		System.out.println("END");
	}

	public void DFT() {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();

		for (Vertex vtx1 : this.vertices) {
			if (!visitedMap.containsKey(vtx1)) {
				stack.push(vtx1);
				visitedMap.put(vtx1, true);

				while (!stack.isEmpty()) {
					Vertex v = stack.pop();
					System.out.print(v + ",");
					ArrayList<Vertex> nbrs = v.getNeighbours();
					for (Vertex vtx : nbrs) {
						if (!visitedMap.containsKey(vtx)) {
							visitedMap.put(vtx, true);
							stack.push(vtx);
						}
					}
				}
			}
		}
		System.out.println("END");
	}

	public boolean isConnected() {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();
		Vertex vtx1 = this.vertices.get(0);
		if (!visitedMap.containsKey(vtx1)) {
			stack.push(vtx1);
			visitedMap.put(vtx1, true);
		}
		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			ArrayList<Vertex> nbrs = v.getNeighbours();
			for (Vertex vtx : nbrs) {
				if (!visitedMap.containsKey(vtx)) {
					visitedMap.put(vtx, true);
					stack.push(vtx);
				}
			}
		}
		if (visitedMap.size() == this.vertices.size()) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<ArrayList<String>> getCC() {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();
		ArrayList<ArrayList<String>> retval = new ArrayList<>();
		for (Vertex vtx1 : this.vertices) {
			if (!visitedMap.containsKey(vtx1)) {
				stack.push(vtx1);
				visitedMap.put(vtx1, true);

				ArrayList<String> temp = new ArrayList<>();
				while (!stack.isEmpty()) {
					Vertex v = stack.pop();
					temp.add(v.name);
					ArrayList<Vertex> nbrs = v.getNeighbours();
					for (Vertex vtx : nbrs) {
						if (!visitedMap.containsKey(vtx)) {
							visitedMap.put(vtx, true);
							stack.push(vtx);
						}
					}
				}
				retval.add(temp);
			}
		}
		return retval;
	}

	public boolean isB() {
		HashMap<Vertex, Boolean> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();
		boolean flag = true;
		visitedMap.put(this.vertices.get(0), flag);
		stack.push(this.vertices.get(0));
		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			ArrayList<Vertex> nbrs = v.getNeighbours();
			for (Vertex vtx : nbrs) {
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

	public boolean isAcyclic() {
		HashMap<Vertex, Vertex> visitedMap = new HashMap<>();
		Stack<Vertex> stack = new Stack<>();

		for (Vertex vtx1 : this.vertices) {
			if (!visitedMap.containsKey(vtx1)) {
				stack.push(vtx1);
				visitedMap.put(vtx1, null);
				while (!stack.isEmpty()) {
					Vertex v = stack.pop();
					ArrayList<Vertex> nbrs = v.getNeighbours();
					for (Vertex vtx : nbrs) {
						if (!visitedMap.containsKey(vtx)) {
							visitedMap.put(vtx, v);
							stack.push(vtx);
						} else {
							if (visitedMap.get(v) == vtx) {
								continue;
							} else {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
}