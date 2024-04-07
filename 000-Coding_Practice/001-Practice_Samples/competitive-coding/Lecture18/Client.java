package Lecture18;

public class Client {
	// 10 3 20 2 30 0 40 0 50 2 60 0 70 0 80 2 90 0 100 0 
	public static void main(String[] args) throws Exception {
		IntTree tree1 = new IntTree();
		//IntTree tree2 = new IntTree();
//		tree.display();
//		System.out.println(tree.max());
//		System.out.println(tree.find(30));
//		System.out.println(tree.size2());
//		System.out.println(tree.find(3));
//		System.out.println(tree.height());
//		tree.printAtLevel(2);
//		System.out.println(tree.justLarger(105));
//		System.out.println(tree.kthsmallestvalue(2));
//		System.out.println(tree.maxSonc());
//		System.out.println(tree.maxSoncbtr());
//		tree.preOrder();
//		tree.preOrderIterative();
//		tree.LevelOrder();
		tree1.display();
		//tree2.display();
		//System.out.println(tree1.IdenticalTrees(tree2));
		System.out.println(tree1.leafnodes());
//		tree1.changeTree2();
//		tree1.display();
	}
}
