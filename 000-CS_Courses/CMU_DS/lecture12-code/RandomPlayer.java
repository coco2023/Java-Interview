public class RandomPlayer implements RPSPlayer
{
    private int wins;
    
    public RandomPlayer()
    {
        wins = 0;
    }
    
    public String move()
    {
        int i = (int)(Math.random() * 3);
        if (i == 0)
            return "rock";
        else if (i == 1)
            return "paper";
        else  // i must be 2
            return "scissors";
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