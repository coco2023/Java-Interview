package CodingBat;

public class split53 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean split53(int[] nums) {
		return sidesare53(nums, 0, 0, 0);
	}

	public static boolean sidesare53(int[] nums, int i, int group1, int group2) {

		if (i >= nums.length) {
			return group1 == group2;
		}

		if (nums[i] % 3 == 0 && nums[i] % 5 != 0) {
			group1 += nums[i];
			i++;
		}
		if (i >= nums.length) {
			return group1 == group2;
		}
		if (nums[i] % 3 != 0 && nums[i] % 5 == 0) {
			group2 += nums[i];
			i++;
		}
		if (i >= nums.length) {
			return group1 == group2;
		}
		if (sidesare53(nums, i + 1, group1, group2 + nums[i])) {
			return true;
		}
		if (sidesare53(nums, i + 1, group1 + nums[i], group2)) {
			return true;
		}
		return false;
	}
}
