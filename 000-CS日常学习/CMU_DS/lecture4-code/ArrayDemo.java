import java.util.*;

public class ArrayDemo
{
    public static void swap(int x, int y)
    {
        int temp = x;
        x = y;
        y = temp;
    }
    
    public static void swap(int[] a, int x, int y)
    {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    public static int[] verifyRandom(int n)
    {
        int[] frequencies = new int[7];
        for (int i = 0; i < n; i++)
        {
            int roll = (int)(Math.random() * 6) + 1;
            frequencies[roll] ++;
        }
        System.out.println(Arrays.toString(frequencies));
        return frequencies;
    }
    
    public static void main(String[] args)
    {
        int[] arr = new int[10];
        System.out.println(arr);
        int[] fred = arr;
        System.out.println(fred);
        fred[1] = 42;
        System.out.println(arr[1]);
        System.out.println(Arrays.toString(arr));
        String s = Arrays.toString(arr);
        System.out.println(s);
        int[] a1 = {1,2,3};
        int[] a2 = {1,2,3};
        System.out.println(a1 + " " + a2);
        System.out.println(a1 == a2);  // not what you want 
        System.out.println(Arrays.equals(a1, a2));  // much better
        int a = 42;
        int b = 57;
        swap(a,b);  // nope!
        System.out.println(a + " " + b);
        swap(a1, 0, 2);  // success!
        System.out.println(Arrays.toString(a1));
        System.out.print("Enter a number of throws: ");
        Scanner in = new Scanner(System.in);
        int max = in.nextInt();
        a1 = verifyRandom(max);
        for (int i = 1; i < a1.length; i++)
             System.out.print(i + "\t\t");
        System.out.println();
        for (int i = 1; i < a1.length; i++)
             System.out.print(a1[i] + "\t\t");
        System.out.println();
        int[] a3 = {43, 5, -1};
        Arrays.sort(a3);
        System.out.println(Arrays.toString(a3));
    }
}