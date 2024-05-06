package CodingBat;

public class makebrick {
	public static void main(String[] args){
		System.out.println(evenlySpaced(4,6,2));
	}
	public boolean makeBricks(int small, int big, int goal) {
		if (goal > small + big * 5)
			return false;
		else if (goal - small % 5 == 0) {
			return true;
		} else {
			return goal % 5 <= small;
		}
	}

	public static boolean evenlySpaced(int a, int b, int c) {
		int large = -1, medium = -1, small = -1;
		if (a < b && a < c) {
			small = a;
		} else if (b < a && b < c) {
			small = b;
		} else {
			small = c;
		}
		if (a == small) {
			if (b < c) {
				medium = b;
				large = c;
			} else {
				medium = c;
				large = b;
			}
		}
		if (b == small) {
			if (a < c) {
				medium = a;
				large = c;
			} else {
				medium = c;
				large = a;
			}

		}
		if (c == small) {
			if (b < a) {
				medium = b;
				large = a;
			} else {
				medium = a;
				large = b;
			}
		}
		System.out.println(small+" "+medium+" "+large);
		return (small - medium == medium - large);
	}

}
