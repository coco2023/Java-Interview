package HackerBlocks;

import java.util.Scanner;

public class GoodWords {

	public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        String s1 = scn.next();
        int k=2;
        boolean isOver = false;
        int i;
        for(i=1;i<s1.length()-1;i+=k){
            if(!isVowel(s1.charAt(i-1))&&!isVowel(s1.charAt(i+1))){
                if(isVowel(s1.charAt(i))){
                    k=2;
                    continue;
                }
                else{
                    System.out.println("NO");
                    isOver =true;
                    break;
                }
            }
            else{
                k=1;
            }
        }
        if(s1.length()-1>=i&&!isOver)
        {
            System.out.println("YES");
        }
        else if(!isOver){
            System.out.println("NO");
        }
        scn.close();
    }
    public static boolean isVowel(char c){
        switch(c){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u': return true;
            default: return false;
        }
    }

}
