import java.util.*;

public class HumanPlayer implements RPSPlayer
{
    private int wins;
    
    public HumanPlayer()
    {
        wins = 0;
    }
    
    public String move()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter rock-paper-scissor: ");
        return in.next();
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public void won()
    {
        wins++;
    }
    
}