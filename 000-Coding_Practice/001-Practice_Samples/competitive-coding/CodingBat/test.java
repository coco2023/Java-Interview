package CodingBat;

public class test {

	public static void main(String[] args) {
		int[] nums = new int[100000];
		for (int i = 1; i <= 100000; i++) {
			nums[i - 1] = i;
		}
		long start = System.currentTimeMillis();
		System.out.println(splitOdd10(nums));
		long end = System.currentTimeMillis();
		System.out.println((end - start));
	}

	public static boolean splitOdd10(int[] nums) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if ((sum + 1) % 2 != 0) {
			return false;
		}
		int temp = (sum + 1) / 2;
		for (int i = 0; i <= 45; i++) {
			if (temp == i) {
				return true;
			}
		}
		return false;
	}

}
