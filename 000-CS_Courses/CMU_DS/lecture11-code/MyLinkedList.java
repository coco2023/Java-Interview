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
        // these 2 lines run in constant time, but don't null references to allow garbage collection
        //start = null;
        //size = 0;
        ListNode curr = start;
        while (curr != null)
        {
            //System.out.println("Data of node to be cleared: " + ((Contact)curr.data).getID());
            curr.data = null;  // releases the reference to the data
            start = curr;  // "hold onto" curr before you move it so you can release the next field
            curr = curr.next;
            start.next = null; // releases the reference to the next node
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
    
    public int indexOf(DataType d)
    {
        int count = 0;
        for (ListNode curr = start; curr != null; curr = curr.next)
        {
            if (curr.data.equals(d))
               return count;
            count++;
        }
        return -1;
    }
    
    public DataType get(int index)
    {
        if (index < 0 || index >= size)
            return null;
        
        ListNode curr = start;
        for (int i = 0; i < index; i++)
            curr = curr.next;
        return curr.data;
    }
    
    // adding at end of list
    public boolean add(DataType value)
    {
        if (isEmpty())  // must handle this special case in isolation
            addFirst(value);
        else
        {
            ListNode curr = start;
            while (curr.next != null)
                curr = curr.next;
            curr.next = new ListNode(value,null);
            size++;
        }
        return true;
    }
        
    public boolean remove(DataType value)
    {
        if (isEmpty())  // must handle this special case in isolation
            return false;
        if (start.data.equals(value))  // must handle this special case in isolation
        {
            start = start.next;
            size--;
            return true;
        }
        // middle (general) case (also handles deleting last node)
        ListNode curr = start;
        while (curr.next != null)
        {
            if (curr.next.data.equals(value))
            {
                curr.next.data = null;
                curr.next = curr.next.next;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    
    public void print()
    {
        // since we need to recur on ListNodes we need a helper that takes a ListNode parameter!
        print(start);
        
        /*
        // or we could do it this way, but this feels slimy, continually creating and modifying Lists...
        if (isEmpty())
            System.out.println("null");
        else
        {// bad bad bad
            System.out.print(start.data + " --> ");
            MyLinkedList<DataType> joe = new MyLinkedList<DataType>();
            joe.start = start.next;
            joe.size = size-1;  // need to do this as well to make size consistent!
            joe.print();
        }
        */
    }
    
    public void print(ListNode roly)
    {
        if (roly == null)
            System.out.println("null");
        else
        {
            System.out.print(roly.data + " --> ");
            print(roly.next);
        }
    }
    
    public String toString()
    {// better than appending strings (cause n-1 strings to be created and garbage collected...
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
        //c.clear();
        System.out.println(c);
        System.out.println(c.size());
        c.print();
    }
}
    