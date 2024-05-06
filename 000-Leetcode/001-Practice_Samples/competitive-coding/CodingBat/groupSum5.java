package CodingBat;

public class groupSum5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean groupSum5(int start, int[] nums, int target) {
		if (start >= nums.length) {
			return (target == 0);
		}
		if (nums[start] % 5 == 0) {
			if (start + 1 < nums.length && nums[start + 1] == 1) {
				if (groupSum5(start + 2, nums, target - nums[start])) {
					return true;
				}
			} else {
				if (groupSum5(start + 1, nums, target - nums[start])) {
					return true;
				}
			}
		} else {
			if (groupSum5(start + 1, nums, target)) {
				return true;
			}
			if (groupSum5(start + 1, nums, target - nums[start])) {
				return true;
			}
		}
		return false;
	}
}
