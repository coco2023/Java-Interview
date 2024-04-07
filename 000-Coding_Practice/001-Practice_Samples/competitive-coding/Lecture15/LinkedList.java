package Lecture15;


public class LinkedList {
	public class Node {
		int data;
		Node next;

		public Node(int data, Node next) {
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

	public int getFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		return this.head.data;
	}

	public int getLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		return this.tail.data;
	}

	public int getAt(int idx) throws Exception {
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

	public int removeFirst() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		int retVal = this.head.data;
		if (this.size() == 1) {
			this.head = this.tail = null;
		} else {
			this.head = this.head.next;
		}
		this.size--;
		return retVal;
	}

	public int removeLast() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("List is empty");
		}
		int retVal = this.tail.data;
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

	public int removeAt(int idx) throws Exception {
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

	public void AddFirst(int item) {
		Node node = new Node(item, this.head);
		this.head = node;

		if (this.size() == 0) {
			this.tail = node;
		}

		this.size++;
	}

	public void AddLast(int item) {
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

	public void AddAt(int idx, int item) throws Exception {
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
		Node node = this.head;
		while (node != null) {
			System.out.print(node.data + ",");
			node = node.next;
		}
		System.out.println("END!");
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
		this.reverse_recursively_Data(enc, this.head, 0);
	}

// using a return type as node
//	private Node reverse_recursively_Data(Node node, int level) {
//		if (node == null) {
//			return this.head;
//		}
//
//		Node front = reverse_recursively_Data(node.next, level + 1);
//		if (level >= this.size() / 2) {
//			int temp = front.data;
//			front.data = node.data;
//			node.data = temp;
//		}
//		return front.next;
//	}

	private void reverse_recursively_Data(Encapsulator enc, Node node, int level) {
		if (node == null) {
			return;
		}

		reverse_recursively_Data(enc, node.next, level + 1);
		if (level >= this.size() / 2) {
			int temp = enc.left.data;
			enc.left.data = node.data;
			node.data = temp;
			enc.left = enc.left.next;
		}
	}

	private class Encapsulator {
		Node left;
	}

	public int mid() throws Exception {
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

	public int kthitemfromend(int k) throws Exception {
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

	public LinkedList merge(LinkedList other) {
		LinkedList retval = new LinkedList();
		Node thishead = this.head, otherhead = other.head;
		while (thishead != null && otherhead != null) {
			if (thishead.data < otherhead.data) {
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
		LinkedList temp = this.mergeSortHelper();
		this.head = temp.head;
		this.size = temp.size;
		this.tail = temp.tail;
	}

	private LinkedList mergeSortHelper() {
		if (this.size() == 1) {
			return this;
		}
		Node mid = this.midData();
		Node midnext = mid.next;
		mid.next = null;
		LinkedList fhalf = new LinkedList(this.head, mid, (this.size() + 1) / 2);
		LinkedList shalf = new LinkedList(midnext, this.tail, this.size() / 2);
		fhalf = fhalf.mergeSortHelper();
		shalf = shalf.mergeSortHelper();
		LinkedList sorted = fhalf.merge(shalf);
		return sorted;
	}

	public void orderList() throws Exception {
		Node temp1 = this.head, temp2 = this.getNodeAt(this.size() / 2), temp3 = new Node(temp2.data, temp2.next);
		while (temp1 != temp3 && temp2 != null) {
			if (temp1.data % 2 == 1) {
				int data = temp1.data;
				temp1.data = temp2.data;
				temp2.data = data;
			}
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
	}

	public void appendlist(int N) throws Exception {
		Node temp = this.getNodeAt(this.size() - 1 - N);
		Node temp1 = temp.next;
		temp.next = null;
		this.tail.next = this.head;
		this.tail = temp;
		this.head = temp1;
	}

	public void kreverse(int k) {
		Encapsulator data = new Encapsulator();
		data.left = this.head;
		kreverse(k, 0, 0, this.head, data);
	}

	private void kreverse(int k, int i, int j, Node tempdata, Encapsulator data) {
		if (j == (this.size() / k)) {
			return;
		}
		if (i == k) {
			return;
		}
		int temp = tempdata.data;
		tempdata = tempdata.next;
		kreverse(k, i + 1, j, tempdata, data);
		data.left.data = temp;
		data.left = data.left.next;
		if (j != this.size() / k && i == 0) {
			kreverse(k, 0, j + 1, tempdata.next.next, data);
		}
	}

}
