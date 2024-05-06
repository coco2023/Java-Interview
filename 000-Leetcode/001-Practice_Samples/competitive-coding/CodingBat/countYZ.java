package CodingBat;
public class countYZ {

	public static void main(String[] args) {
		System.out.println(countYZ("day fyyyz"));
	}

	public static int countYZ(String str) {
		  int retval=0;
				for(int i=0;i<str.length();i++)
				{
					if((str.charAt(i)=='Y'||str.charAt(i)=='y'||str.charAt(i)=='Z'||str.charAt(i)=='z')&&(i==str.length()-1||!Character.isLetter(str.charAt(i+1))))
					{
						retval++;
					}
				}
				return retval;
		}

}
