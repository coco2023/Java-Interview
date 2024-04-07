package Lecture27;

public class Client {

	public static void main(String[] args) {
		Graph G = new Graph();
		G.addVertex("A");
		G.addVertex("B");
		G.addVertex("C");
		G.addVertex("D");
		G.addVertex("E");
		G.addVertex("F");
		G.addVertex("G");
		G.AddEdge("A", "B");
		G.AddEdge("B", "C");
//		G.AddEdge("A", "C");
		G.AddEdge("C", "D");
		G.AddEdge("C", "E");
		G.AddEdge("E", "F");
		G.AddEdge("F", "G");
		G.AddEdge("E", "G");
		G.AddEdge("A", "D");
//		G.display();
//		System.out.println("**********************");
//		G.removeEdge("C", "E");
//		G.display();
//		System.out.println("**********************");
//		 G.AddEdge("C", "E");
//		// G.display();
//		// System.out.println("**********************");
//		 G.removeVertex("C");
		// G.display();
		// System.out.println("**********************");
		// G.BFT();
		// G.DFT();
		G.display();
		G.DFT();
//		System.out.println(G.isConnected());
//		System.out.println(G.getCC());
		System.out.println(G.isB());
		System.out.println(G.isAcyclic());
	}

}
