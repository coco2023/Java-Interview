public class MyLinkedList<DataType>
{
    
    private class ListNode
    {
        private DataType data;
        private ListNode next;
        
        public ListNode()
        {
            data = null;
            next = null;
        }
        
        public ListNode(DataType d, ListNode nxt)
        {
            data = d;
            next = nxt;
        }
    }
    
    private ListNode start;
    private int size;
    
    public MyLinkedList()
    {
        start = null;
        size = 0;
    }
    
    public void addFirst(DataType d)
    {
        start = new ListNode(d,start);
        size++;
    }
    
    public boolean isEmpty()
    {
        return size == 0;  // return start == null;
    }
    
    public int size()
    {
        return size;
    }

    public void clear()
    {
        // these lines run in constant time, but doesn't null references
        //start = null;
        //size = 0;
        ListNode curr = start;
        while (curr != null)
        {
            //System.out.println("Data of node to be cleared: " + ((Contact)curr.data).getID());
            start = curr;
            curr.data = null;
            curr = curr.next;
            start.next = null;
        }
        start = null;
        size = 0;
    }
    
    public boolean contains(DataType d)
    {
        for (ListNode curr = start; curr != null; curr = curr.next)
            if (curr.data.equals(d))
               return true;
        return false;
    }
    
    public String toString()
    {
        StringBuilder result = new StringBuilder();  //String result = "";
        for (ListNode curr = start; curr != null; curr = curr.next)
            result.append(curr.data + " --> ");  //result += curr.data + " --> ";
        result.append("null");  //result += null;
        return result.toString();
    }
    
    public static void main(String[] args)
    {
        MyLinkedList<Contact> c = new MyLinkedList<Contact>();
        System.out.println(c);
        c.addFirst(new Contact("Mark","Stehlik","mjs","412-268-6273",12345,"wizrd"));
        System.out.println(c);
        c.addFirst(new Contact("Joe","Segel","jsegel","412-268-2000",15213,"student"));
        System.out.println(c.size());
        System.out.println(c.contains(new Contact("Joe","Segel","jsegel","412-111",15213,"student")));
        
        c.addFirst(new Contact("Mary","Smith","msmith","412-268-1111",15213,"student"));
        c.clear();
        System.out.println(c);
        System.out.println(c.size());
    }
}
    