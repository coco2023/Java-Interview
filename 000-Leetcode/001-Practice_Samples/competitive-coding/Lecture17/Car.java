package Lecture17;

public class Car implements Comparable<Car> {
	int speed;
	int price;

	public Car(int speed, int price) {
		this.speed = speed;
		this.price = price;
	}

	@Override
	public int compareTo(Car o) {
		return this.speed - o.speed;
	}

	public String toString() {
		String retVal = "";
		retVal += "[Price: " + this.price;
		retVal += "; Speed: " + this.speed + "]\n";
		return retVal;
	}

}
