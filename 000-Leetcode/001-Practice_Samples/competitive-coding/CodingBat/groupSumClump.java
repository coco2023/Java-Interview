package CodingBat;

public class groupSumClump {

	public static void main(String[] args) {

	}

	public static boolean groupSumClump(int start, int[] nums, int target) {
		if (start >= nums.length) {
			return (target == 0);
		}
		int temp = nums[start];
		int counter = 1;
		for (int i = start + 1; i < nums.length; i++) {
			if (nums[i] == nums[start]) {
				temp += nums[i];
				counter++;
			} else {
				break;
			}
		}
		if (groupSumClump(start + counter, nums, target - temp)) {
			return true;
		}
		if (groupSumClump(start + counter, nums, target)) {
			return true;
		}
		return false;
	}

}
