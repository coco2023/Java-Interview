package Lecture11;

public class CountBoard {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		System.out.println(countBoardbtr(0,10,new int[21]));
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	public static int countBoardbtr(int cs,int ls,int[] storage)
	{
		
		if(cs>=ls)
		{
			if(cs==ls)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		
		if(storage[cs]!=0)
		{
			return storage[cs];
		}
		int count=0;
		for(int i=1;i<=6;i++)
		{
			count+=countBoardbtr(cs+i,ls,storage);
		}
		storage[cs]=count;
		return count;
	}
	public static int countBoard2(int cs,int ls)
	{
		int count=0;
		if(cs>=ls)
		{
			if(cs==ls)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		for(int i=1;i<=6;i++)
		{
			count+=countBoard2(cs+i, ls);
		}
		return count;
	}
}
