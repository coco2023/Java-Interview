package HackerBlocks;

import java.util.Scanner;
import java.util.TreeSet;

public class RecursiveSubsequences {
	static TreeSet<String> st = new TreeSet<String>();
	 
    static void subsequence(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            for (int j = str.length(); j > i; j--) {
                String sub_str = str.substring(i, j);
                if (!st.contains(sub_str))
                    st.add(sub_str);
                for (int k = 1; k < sub_str.length() - 1; k++) {
                    StringBuffer sb = new StringBuffer(sub_str);
                    sb.deleteCharAt(k);
                    if (!st.contains(sb.toString()))
                    subsequence(sb.toString());
                }
            }
        }
        st.add("");
    }
 
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        while(t--!=0){
            String s = scn.next();
            subsequence(s);
            for(String str : st){
            	System.out.println(str);
            }
            st = new TreeSet<String>();
        }
        scn.close();
    }
}
