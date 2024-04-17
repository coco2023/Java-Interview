public class Dice
{
    private int sides;
    private int rollCount;
    
    // default constructor (default dice has 6 sides)
    public Dice()
    {
        sides = 6;
        rollCount = 0;
    }
    
    // constructor for an n-sided dice
    public Dice(int s)
    {
        sides = s;
        rollCount = 0;
    }
    
    // rolls a dice - generates an appropriate random number
    // in [1..sides]
    public int roll()
    {
        rollCount++;
        return (int)(Math.random() * sides) + 1;
    }
    
    public int numRolls()
    {
        return rollCount;
    }
    
    // every class needs a toString
    public String toString()
    {
        return "This die has " + sides + " sides and was rolled " + rollCount + " times";
    }
    
    public static void main(String[] args)
    {
        Dice d = new Dice();
        System.out.println(d.rollCount);
    }
}