package CodingBat;

public class splitOdd10 {

	public static void main(String[] args) {
		int[] nums=new int[40];
		for(int i=1;i<=40;i++)
		{
			nums[i-1]=i;
		}
		long start=System.currentTimeMillis();
		System.out.println(splitOdd10(nums));
		long end=System.currentTimeMillis();
		System.out.println((end-start));
	}

	public static boolean splitOdd10(int[] nums) {
		// int sum=0;
		// for(int i=0;i<nums.length;i++)
		// {
		// sum+=nums[i];
		// }
		// if((sum+1)%2!=0)
		// {
		// return false;
		// }
		// int temp=sum+1/2;
		// for(int i=0;i<=45;i++)
		// {
		// if(temp==i)
		// {
		// return true;
		// }
		// }
		// return false;
		return sidesAreOdd10(nums, 0, 0, 0);
	}

	public static boolean sidesAreOdd10(int[] nums, int i, int group1, int group2) {
		if (i >= nums.length) {
			return (group1 % 2 == 1 && group2 % 10 == 0 || group1 % 10 == 0 && group2 % 2 == 1);
		}
		if (sidesAreOdd10(nums, i + 1, group1 + nums[i], group2)) {
			return true;
		}
		if (sidesAreOdd10(nums, i + 1, group1, group2 + nums[i])) {
			return true;
		}
		return false;
	}
}
