package Lecture23;

import Lecture17.LinkedList;

public class HashTable<K, V> {
	private class HTNode implements Comparable<HTNode> {
		K key;
		V value;

		public HTNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(HashTable<K, V>.HTNode o) {
			return 0;
		}

		@Override
		public boolean equals(Object obj) {
			@SuppressWarnings("unchecked")
			HTNode temp = (HTNode) obj;
			return this.key.equals(temp.key);
		}

		@Override
		public String toString() {
			String retval = "";
			retval += " " + this.key + " : " + this.value;
			return retval;
		}
	}

	private int n;
	private LinkedList<HTNode>[] bucketArray;
	public static final int DEFAULT_CAPACITY = 13;

	public HashTable() throws Exception {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public HashTable(int capacity) throws Exception {
		if (capacity == 0) {
			throw new Exception("Invalid size");
		}
		this.bucketArray = (LinkedList<HashTable<K, V>.HTNode>[]) new LinkedList[capacity];
		this.n = 0;
	}

	public int size() {
		return this.n;
	}

	public boolean isEmpty() {
		return this.n == 0;
	}

	public int hashFunction(K key) {
		return Math.abs(key.hashCode()) % this.bucketArray.length;

	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		String retval = "";
		for (int i = 0; i < this.bucketArray.length; i++) {
			if (this.bucketArray[i] != null) {
				retval += this.bucketArray[i];
			} else {
				retval += "NULL\n";
			}
		}
		return retval;
	}

	public V get(K key) throws Exception {
		int bi = this.hashFunction(key);
		LinkedList<HTNode> bucket = this.bucketArray[bi];
		if (bucket == null) {
			return null;
		} else {
			HTNode nodeFound = new HTNode(key, null);
			int foundAt = bucket.find(nodeFound);
			if (foundAt == -1) {
				return null;
			} else {
				return bucket.getAt(foundAt).value;
			}
		}
	}

	public V remove(K key) throws Exception {
		int bi = this.hashFunction(key);
		LinkedList<HTNode> bucket = this.bucketArray[bi];
		HTNode nodeFound = new HTNode(key, null);

		if (bucket == null) {
			return null;
		} else {

			int foundAt = bucket.find(nodeFound);
			if (foundAt == -1) {
				return null;
			} else {
				V value = bucket.getAt(foundAt).value;
				this.n--;
				bucket.removeAt(foundAt);
				return value;
			}
		}
	}

	public void put(K key, V value) throws Exception {
		int bi = this.hashFunction(key);
		LinkedList<HTNode> bucket = this.bucketArray[bi];
		HTNode node = new HTNode(key, value);
		if (bucket == null) {
			bucket = new LinkedList<>();
			bucket.AddLast(node);
			this.bucketArray[bi] = bucket;
			this.n++;

		} else {

			int foundAt = bucket.find(node);
			if (foundAt == -1) {
				bucket.AddLast(node);
				this.n++;
			} else {
				HTNode temp = bucket.getAt(foundAt);
				temp.value = value;
			}
		}
		double loadFactor = this.n * 1.0 / this.bucketArray.length;
		if (loadFactor > 2.0) {
			this.rehash();
		}

	}

	private void rehash() throws Exception {
		LinkedList<HTNode>[] oba = this.bucketArray;
		this.bucketArray = (LinkedList<HashTable<K, V>.HTNode>[]) new LinkedList[this.bucketArray.length * 2];
		this.n = 0;
		for (int i = 0; i < oba.length; i++) {
			LinkedList<HTNode> ol = oba[i];

			while (ol != null && !ol.isEmpty()) {
				HTNode on = ol.removeFirst();
				this.put(on.key, on.value);
			}
		}
	}

}
