package CodingBat;

public class splitArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] x = { 1, 2, 3 };
		System.out.println(splitArray(x));
	}

	public static boolean splitArray(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if (sum % 2 == 1) {
			return false;
		} else {
			return sidesAreEqual(nums, 0, sum / 2);
		}
	}

	// public static boolean sidesAreEqual(int[] nums, int i, int balance) {
	// if (i == nums.length) {
	// return (balance == 0); }
	// if (sidesAreEqual(nums, i + 1, balance + nums[i])) {
	// return true;
	// }
	// return sidesAreEqual(nums, i + 1, balance - nums[i]);
	// }
	public static boolean sidesAreEqual(int[] nums, int i, int sum) {
		if (i >= nums.length) {
			return sum == 0;
		}
		if (sidesAreEqual(nums, i + 1, sum - nums[i])) {
			return true;
		}
		if (sidesAreEqual(nums, i + 1, sum)) {
			return true;
		}
		return false;
	}
}
