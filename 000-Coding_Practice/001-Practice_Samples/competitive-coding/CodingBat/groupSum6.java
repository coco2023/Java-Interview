package CodingBat;

public class groupSum6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean groupSum6(int start, int[] nums, int target) {
		if (start >= nums.length) {
			return (target == 0);
		}
		if (nums[start] == 6) {
			if (groupSum6(start + 1, nums, target - nums[start])) {
				return true;
			}
		} else {
			if (groupSum6(start + 1, nums, target)) {
				return true;
			}
			if (groupSum6(start + 1, nums, target - nums[start])) {
				return true;
			}
		}
		return false;
	}

}
