public class RPS
{
    private static final int MAX_WINS = 3;
    private RPSPlayer p1;
    private RPSPlayer p2;
    
    public RPS(RPSPlayer player1, RPSPlayer player2)
    {
        p1 = player1;
        p2 = player2;
    }
    
    public void play()
    {
        while (!(p1.getWins() == MAX_WINS || p2.getWins() == MAX_WINS))
        {
            String s1 = p1.move();
            String s2 = p2.move();
            System.out.print("player 1 says " + s1 + "; player 2 says " + s2);
            if (winner(s1, s2))
            {
                p1.won();
                System.out.println("; player 1 wins");
            }   
            else if (winner(s2, s1))
            {
                p2.won();
                System.out.println("; player 2 wins");
            }
            else
                System.out.println("; tie, go again!");
        }
        System.out.println("Player " + ((p1.getWins() == MAX_WINS)?"1":"2") + " wins!");
        if ((p1 instanceof HumanPlayer && p1.getWins() != MAX_WINS) ||
            (p2 instanceof HumanPlayer && p2.getWins() != MAX_WINS))
            System.err.println("**** F*CK ****!");
    }
    
    private boolean winner(String s1, String s2)
    {
        return (s1.equals("rock") && s2.equals("scissors") ||
                s1.equals("scissors") && s2.equals("paper") ||
                s1.equals("paper") && s2.equals("rock"));
    }
    
    public static void main(String[] args)
    {
        RPS game = new RPS(new HumanPlayer(),new RandomPlayer());
        game.play();
        // some code to show that an RPSPlayer can be assigned any object that implements the interface
        RPSPlayer p = new RandomPlayer();
        System.out.println(p instanceof RandomPlayer);
        System.out.println(p instanceof RPSPlayer);  // ok, but you still can't make (new) an RPSPlayer() object
        //RPSPlayer p = new RPSPlayer();  // nope, can't instantiate an object of an Interface!!
        System.out.println(p instanceof HumanPlayer);
    }
}