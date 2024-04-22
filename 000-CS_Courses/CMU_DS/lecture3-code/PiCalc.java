import java.util.*;

public class PiCalc
{
    
    public static double calcPi(int n)
    {
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            double x = randomReal(-1,1);
            double y = randomReal(-1,1);
            if (isInCircle(x,y,1))
                count++;
        }
        //System.out.println(i);  // the scope of i is the for loop it is delared in
        
        return (double)(count * 4)/n;  // could also have done: return (count * 4.0)/n;
    }
    
    // added this after lecture for completeness
    public static int randomIntInRange(int low, int hi)
    {
        int range = hi - low + 1;  // number of ints between low and hi, inclusive
        return (int)(Math.random() * range) + low;  // translate scale if needed by adding low
    }
    
    
    public static double randomReal(double low, double hi)
    {
        return Math.random() * (hi - low) + low;
    }
    
    public static boolean isInCircle(double x, double y, double r)
    {
        return (x * x + y * y) <= r * r;
    }
        
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of pearls? ");
        int numPearls = sc.nextInt();
        System.out.println("Having thrown " + numPearls + " pearls, PI = " + calcPi(numPearls));
    }
}