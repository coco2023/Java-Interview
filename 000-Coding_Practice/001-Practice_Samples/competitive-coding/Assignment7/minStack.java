package Assignment7;


import Lecture12.Stack;

public class minStack {
	private Stack primaryS;
	private Stack secondaryS;
	private int min = Integer.MAX_VALUE;

	public minStack() throws Exception {
		this.primaryS = new Stack();
		this.secondaryS = new Stack();
	}

	public void minPush(int item) throws Exception {

		if (this.primaryS.isEmpty()) {
			this.primaryS.push(item);
			this.secondaryS.push(item);
			min = item;
		} else {
			this.primaryS.push(item);
			if (min > item) {
				min = item;
				this.secondaryS.push(min);
			}
		}
	}

	public int minPop() throws Exception {
		return this.secondaryS.pop();
	}

	public int getMinimum() throws Exception {
		return this.secondaryS.top();
	}
	
}
