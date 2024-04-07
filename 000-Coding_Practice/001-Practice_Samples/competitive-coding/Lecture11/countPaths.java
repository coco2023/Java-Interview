package Lecture11;

public class countPaths {

	public static void main(String[] args) {

	}
	public static int countKnightPaths(int N,int cc,int cr,int[][] storage)
	{
		int count=0;
		if(count>N)
		{
			return 0;
		}
		if(count==N)
		{
			return 1;
		}
		if(storage[cc][cr]!=0)
		{
			return storage[cc][cr];
		}
		count+=countKnightPaths(N, cc+2, cr+1, storage);
		count+=countKnightPaths(N, cc+1, cr+2, storage);
		storage[cc][cr]=count;
		return count;
	}
}
