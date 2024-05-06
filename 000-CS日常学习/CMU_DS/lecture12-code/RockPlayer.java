public class RockPlayer implements RPSPlayer
{
    private int wins;
    
    public RockPlayer()
    {
        wins = 0;
    }
    
    public String move()
    {
        return "rock";
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