package Lecture12;

public class DynamicStack extends Stack {
	public DynamicStack() throws Exception {
		this(DEFAULT_CAPACITY);
	}

	public DynamicStack(int capacity) throws Exception {
		super(capacity);
	}

	@Override
	public void push(int item) throws Exception {
		if (this.size() == this.data.length) {
			int[] temp = new int[2 * this.data.length];
			for (int i = 0; i < this.data.length; i++) {
				temp[i] = this.data[i];
			}
			this.data = temp;// points to temp array
		}
		super.push(item);
	}

	@Override
	public int pop() throws Exception {
		if (this.size() <= (this.data.length / 4)) {
			int[] temp = new int[this.data.length / 2];
			for (int i = 0; i < this.size(); i++) {
				temp[i] = this.data[i];
			}
			this.data = temp;
		}
		int x = super.pop();
		return x;
	}

	
}
