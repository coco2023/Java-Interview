package Lecture17;

public class LinkedList<T extends Comparable<T>> {
	public class Node {
		T data;
		Node next;

		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private LinkedList(Node head, Node tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public T getFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		return this.head.data;
	}

	public T getLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		return this.tail.data;
	}

	public T getAt(int idx) throws Exception {
		Node node = getNodeAt(idx);
		return node.data;
	}

	public Node getNodeAt(int idx) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}
		if (idx < 0 || idx >= this.size()) {
			throw new Exception("Invalid arguments");
		}
		int counter = 0;
		Node node = this.head;
		while (counter < idx) {
			node = node.next;
			counter++;
		}
		return node;
	}

	public T removeFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		T retVal = this.head.data;
		if (this.size() == 1) {
			this.head = this.tail = null;
		} else {
			this.head = this.head.next;
		}
		this.size--;
		return retVal;
	}

	public T removeLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		T retVal = this.tail.data;
		if (this.size() == 1) {
			this.head = this.tail = null;
		} else {
			Node nm1 = getNodeAt(this.size() - 2);
			nm1.next = null;
			this.tail = nm1;
		}
		this.size--;
		return retVal;
	}

	public T removeAt(int idx) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}
		if (idx < 0 || idx > this.size()) {
			throw new Exception("Invalid Arguments");
		}
		if (idx == 0) {
			return removeFirst();
		} else if (idx == this.size() - 1) {
			return removeLast();
		} else {
			Node nm1 = getNodeAt(idx - 1);
			Node n = nm1.next;

			nm1.next = n.next;

			this.size--;

			return n.data;

		}
	}

	public void AddFirst(T item) {
		Node node = new Node(item, this.head);
		this.head = node;

		if (this.size() == 0) {
			this.tail = node;
		}

		this.size++;
	}

	public void AddLast(T item) {
		Node node = new Node(item, null);

		if (this.size() == 0) {
			this.head = this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
			//
		}

		this.size++;
	}

	public void AddAt(int idx, T item) throws Exception {
		if (idx < 0 || idx > this.size()) {
			throw new Exception("Invalid Arguments");
		}
		if (idx == 0) {
			this.AddFirst(item);
		} else if (idx == this.size()) {
			this.AddLast(item);
		} else {
			Node nm1 = getNodeAt(idx - 1);
			Node node = new Node(item, nm1.next);
			nm1.next = node;
			this.size++;
		}
	}

	public void display() {
		System.out.println(this);
	}

	public void reverse_iteratively_pointers() throws Exception {
		Node prev = this.head;
		Node curr = prev.next;
		while (curr.next != null) {
			Node tp = prev;
			Node tc = curr;
			prev = curr;
			curr = curr.next;
			tc.next = tp;
		}
		Node temp = this.head;// swapping head and tail;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
		this.head.next = prev;
	}

	public void reverse_recursively_pointers(Node node) {
		if (node == this.tail) {
			return;
		}
		this.reverse_recursively_pointers(node.next);
		node.next.next = node;
	}

	// public void reverse_recursively_pointers(Node prev, Node curr) throws
	// Exception {
	// if (curr.next == null) {
	// Node temp = this.head;
	// this.head = this.tail;
	// this.tail = temp;
	// this.tail.next = null;
	// this.head.next = prev;
	// return;
	// }
	// Node tp = prev;
	// Node tc = curr;
	// tc.next = tp;
	// reverse_recursively_pointers(curr, curr.next);
	// return;
	// }
	public void reverse_recursively_pointer() {
		this.reverse_recursively_pointers(this.head);
		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
	}

	public void reverse_recursively_data() {
		Encapsulator enc = new Encapsulator();
		enc.left = this.head;
		this.reverse_recursively_Data_1(enc, this.head, 0);
	}

	private Node reverse_recursively_Data(Node node, int level) {
		if (node == null) {
			return this.head;
		}

		Node front = reverse_recursively_Data(node.next, level + 1);
		if (level >= this.size() / 2) {
			T temp = front.data;
			front.data = node.data;
			node.data = temp;
		}
		return front.next;
	}

	private void reverse_recursively_Data_1(Encapsulator enc, Node node, int level) {
		if (node == null) {
			return;
		}

		reverse_recursively_Data_1(enc, node.next, level + 1);
		if (level >= this.size() / 2) {
			T temp = enc.left.data;
			enc.left.data = node.data;
			node.data = temp;
			enc.left = enc.left.next;
		}
	}

	private class Encapsulator {
		Node left;
	}

	public T mid() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}
		return this.midData().data;
	}

	private Node midData() {
		Node slow = this.head, fast = this.head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public T kthitemfromend(int k) throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is Empty");
		}
		if (k < 1 || k > this.size()) {
			throw new Exception("Invalid Arguments");
		}
		Node slow = this.head, fast = this.head;
		int counter = 0;
		while (counter != k) {
			counter++;
			fast = fast.next;
		}
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow.data;
		// return this.kthitem(this.head, 0, k).data;
		// Encapsulator enc = new Encapsulator();
		// return enc.left.data;
	}

	// private Node kthitem(Node front, Encapsulator enc, int i, int k) {
	// if (front.next == null) {
	// return front;
	// }
	// Node temp = kthitem(front.next, enc, i, k);
	// if (i == k) {
	// enc.left = temp;
	// return temp;
	// }
	// i++;
	// return temp;
	// }
	public LinkedList<T> merge(LinkedList<T> other) {
		LinkedList<T> retval = new LinkedList<>();
		Node thishead = this.head, otherhead = other.head;
		while (thishead != null && otherhead != null) {
			if (thishead.data.compareTo(otherhead.data) < 0) {
				retval.AddLast(thishead.data);
				thishead = thishead.next;
			} else {
				retval.AddLast(otherhead.data);
				otherhead = otherhead.next;
			}
		}
		while (thishead != null) {
			retval.AddLast(thishead.data);
			thishead = thishead.next;
		}
		while (otherhead != null) {
			retval.AddLast(otherhead.data);
			otherhead = otherhead.next;
		}
		return retval;
	}

	public void mergeSort() {
		LinkedList<T> temp = this.mergeSortHelper();
		this.head = temp.head;
		this.size = temp.size;
		this.tail = temp.tail;
	}

	private LinkedList<T> mergeSortHelper() {
		if (this.size() == 1) {
			return this;
		}
		Node mid = this.midData();
		Node midnext = mid.next;
		mid.next = null;
		LinkedList<T> fhalf = new LinkedList<>(this.head, mid, (this.size() + 1) / 2);
		LinkedList<T> shalf = new LinkedList<>(midnext, this.tail, this.size() / 2);
		fhalf = fhalf.mergeSortHelper();
		shalf = shalf.mergeSortHelper();
		LinkedList<T> sorted = fhalf.merge(shalf);
		return sorted;
	}

	public boolean isLonger(LinkedList<?> other) {
		return this.size() > other.size();
	}

	public void swapdata(int k1, int k2) throws Exception// indices of the
															// elements
															// to be swapped
															// time complexity
															// is
															// O(N);
	{
		if (k1 < 0 || k1 >= this.size() || k2 < 0 || k2 >= this.size()) {
			throw new Exception("Invalid Arguments");
		}
		Node temp1 = this.getNodeAt(k1);
		Node temp2 = this.getNodeAt(k2);
		T data = temp1.data;
		temp1.data = temp2.data;
		temp2.data = data;
	}

	public void swap(int k1, int k2) throws Exception {
		if (k1 < 0 || k2 < 0 || k1 >= this.size() || k2 > this.size()) {
			throw new Exception("Invalid Arguments");
		}
		if (k2 < k1) {
			int temp = k1;
			k1 = k2;
			k2 = temp;
		}
		if (k1 == 0) {
			Node temp1 = this.head;
			Node temp2 = this.getNodeAt(k2 - 1);

		} else if (k2 == this.size() - 1) {

		} else {
		}
	}

	// public void swapPointers(int k1, int k2) throws Exception {
	// if (k1 < 0 || k1 >= this.size() || k2 < 0 || k2 >= this.size()) {
	// throw new Exception("Invalid Arguments");
	// }
	//
	// }
	// Question 2 Assignment 8
	public void eliminateDuplicates() {
		Node temp = this.head;
		while (temp.next != null) {
			if (temp.data == temp.next.data) {
				temp.next = temp.next.next;
			}
			temp = temp.next;
		}
	}

	public boolean checkLinkedList() {
		Encapsulator enc = new Encapsulator();
		enc.left = this.head.next;
		return this.checkLinkedList(enc, this.head, 0);
	}

	private boolean checkLinkedList(Encapsulator enc, Node node, int level) {
		if (node.next == null) {
			if (this.head.data == node.data) {
				return true;
			} else {
				return false;
			}
		}
		boolean flag = checkLinkedList(enc, node.next, level + 1);
		boolean temp = true;
		if (flag == false) {
			return false;
		}
		if (level >= this.size() / 2) {
			if (enc.left.data == node.data) {
				temp = true;
			} else {
				temp = false;
			}
			enc.left = enc.left.next;
		}
		return temp;

	}

	public void printreverse() {
		printreverse(this.head);
		System.out.println("END");
	}

	private void printreverse(Node node) {
		if (node == null) {
			return;
		}
		printreverse(node.next);
		System.out.print(node.data + ",");
	}

	public int find(T data) {
		int counter = 0;
		Node node = this.head;
		while (node != null) {
			if (node.data.equals(data)) {
				return counter;
			}
			counter++;
			node = node.next;
		}
		return -1;
	}

	@Override
	public String toString() {
		String retval = "";
		Node node = this.head;
		while (node != null) {
			retval += node.data + "=>";
			node = node.next;
		}
		retval += "END\n";
		return retval;

	}
	// // swapping pointers for two elements
	// public void swap(int x1, int x2) throws Exception {
	// if (x1 < 0 || x2 < 0 || x1 >= this.size() || x2 >= this.size()) {
	// throw new Exception("Invalid Arguements");
	// }
	// // Node node1 = this.getNodeAt(x1);
	// // Node node2 = this.getNodeAt(x2);
	// // swap(node1, node2);
	// if (x1 == 0 || x2 == 0) {
	// if (x1 == 0) {
	// Node temp1 = this.getNodeAt(x2);
	// Node temp = new Node(this.head.data, this.head.next);
	// this.head = temp1;
	// temp1 = temp;
	// } else {
	// Node temp1 = this.getNodeAt(x1);
	// Node temp = new Node(this.head.data, this.head.next);
	// this.head = temp1;
	// temp1 = temp;
	// }
	// }
	// }
	//
	//
	//// private void swap(Node node1, Node node2) {
	////
	//// }
}
