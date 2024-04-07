import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner scn = new Scanner(System.in);
        int N=scn.nextInt();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=scn.nextInt();
        }
        int[] retval=new int[1];
        Boolean[] flag=new Boolean[1];
        flag[0]=false;
        retval[0]=-1;
        lastIndex(arr,scn.nextInt(),0,flag,retval);
        System.out.println(retval[0]);
        scn.close();
    }
    public static void lastIndex(int[] arr,int M,int i,Boolean[] flag,int[] retval){
        if(i==arr.length){
            retval[0]=-1;
            return;
        }
        lastIndex(arr,M,i+1,flag,retval);
        if(!flag[0]&&arr[i]==M){
            flag[0]=true;
            retval[0]=i;
        }
    }
}