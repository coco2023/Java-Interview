public class ListNode
{
    private String data;
    private ListNode next;
    
    public ListNode()
    {
        data = "";
        next = null;
    }
    
    public ListNode(String d, ListNode n)
    {
        data = d;
        next = n;
    }
    
    public String getData()
    {
        return data;
    }
    
    public ListNode getNext()
    {
        return next;
    }
    
    public void setNext(ListNode x)
    {
        next = x;
    }
    
    public String toString()
    {
        String result = "";
        ListNode current = this;
        while (current != null)
        {
            result += current.data + " ";  // have to qualify data and next with current.
            current = current.next;
        }
        return result;
        // compare the above to the one-line solution below...
        //return data + " " + next;  // recursion is cool!
    }
    
    public static void main(String[] args)
    {
        ListNode joe = new ListNode("joe",null);
        System.out.println(joe);
        ListNode roly = new ListNode("pita",joe);
        System.out.println(roly);
        
        joe.setNext(roly);
        roly.setNext(null);
        System.out.println(joe);
        System.out.println("done");
        
        ListNode start = new ListNode("pita1",null);
        ListNode curr = start;
        for(int i = 0; i < 9; i++)
        {
            // forward..
            /*
            ListNode p = new ListNode("pita" + (i+2),null);
            curr.setNext(p);
            curr = curr.getNext();
            */
            // and backward...
            ListNode p = new ListNode("pita" + (i+2),curr);
            curr = p;
        }
        start = curr;
        System.out.println(start);
    }
}
