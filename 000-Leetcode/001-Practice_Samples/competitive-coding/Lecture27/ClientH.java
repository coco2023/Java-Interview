package Lecture27;

public class ClientH {

	public static void main(String[] args) {
		Huffman h = new Huffman("aaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbccccccccccccccccccccddddddddddddddddddddddyzzzztt");
		System.out.println(h.encode("abcdyzt"));
		System.out.println(h.decode("11100011011000110111001"));
	}
}
