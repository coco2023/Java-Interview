package Lecture25;

public class Client {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.addWord("buy");
		trie.addWord("bull");
		trie.addWord("art");
		trie.addWord("are");
		trie.addWord("arts");
		trie.addWord("stop");
		trie.addWord("stock");
		trie.addWord("sea");
		trie.addWord("see");
		trie.addWord("bugs");
		trie.addWord("but");
		System.out.println(trie.remove("bugs"));
		System.out.println(trie.search("buts"));
		trie.display();
		trie.displayAllWords();
	}
}
