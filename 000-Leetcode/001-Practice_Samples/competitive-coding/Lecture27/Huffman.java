package Lecture27;

import java.util.HashMap;
import java.util.Set;

import Lecture19.BinaryTree;
import Lecture21.Heap;

public class Huffman {
	private HashMap<Character, String> encoder;
	private HashMap<String, Character> decoder;

	public Huffman(String sourceString) {
		HashMap<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < sourceString.length(); i++) {
			if (freqMap.containsKey(sourceString.charAt(i))) {
				freqMap.put(sourceString.charAt(i), freqMap.get(sourceString.charAt(i)) + 1);
			} else {
				freqMap.put(sourceString.charAt(i), 1);
			}
		}
		Heap<Integer, Pair> minHeap = new Heap<>(true);
		Set<Character> entrySet = freqMap.keySet();
		for (Character entry : entrySet) {
			BinaryTree btta = new BinaryTree(entry, null, null);
			Pair p = new Pair(freqMap.get(entry), btta);
			minHeap.add(p.score, p);
		}
		while (minHeap.size() != 1) {
			Pair p1 = minHeap.removeHP();
			Pair p2 = minHeap.removeHP();
			BinaryTree bt = new BinaryTree(0, p1.bt, p2.bt);
			Pair p = new Pair(p1.score + p2.score, bt);
			minHeap.add(p.score, p);
		}
		Pair fp = minHeap.removeHP();
		BinaryTree bt = fp.bt;
		this.encoder = new HashMap<>();
		this.decoder = new HashMap<>();
		bt.traversalforHC(encoder, decoder);
	}

	private class Pair {
		int score;
		BinaryTree bt;

		public Pair(int score, BinaryTree bt) {
			this.score = score;
			this.bt = bt;
		}
	}

	public String encode(String str) {
		String retval = "";
		for (int i = 0; i < str.length(); i++) {
			retval += this.encoder.get(str.charAt(i));
		}
		return retval;
	}

	public String decode(String str) {
		String retval = "";
		String key = "";
		for (int i = 0; i < str.length(); i++) {
			key += str.charAt(i);
			if (decoder.containsKey(key)) {
				retval += decoder.get(key);
				key = "";
			}
		}
		int x=Integer.parseInt(retval, 2);
		return retval;
	}
}
