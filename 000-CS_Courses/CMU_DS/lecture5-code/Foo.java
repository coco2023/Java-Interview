import java.util.*;

public class Foo
{
    public static void main(String[] args)
    {
        Ball b = new Ball();
        // b.color does not work in this println because we are outside the Ball class
        //System.out.println("X" + b.color + "X");
        System.out.println("X" + b.getColor() + "X");
        System.out.println(b);
        
        // using the Dice class
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        int[] freq = new int[13];
        int numTrials = 36000;
        for (int i = 0; i < numTrials; i++)
        {
            int sum = d1.roll() + d2.roll();
            freq[sum] += 1;
        }
        System.out.println(Arrays.toString(freq));
        
    }
}