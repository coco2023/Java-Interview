public class Contact
{
    private String firstName;
    private String lastName;
    private String andrewID;
    private String phone;
    private String group;
    private int zipcode;  // added S18
    
    // default constructor - not very interesting!
    public Contact()
    {
        firstName = lastName = andrewID = phone = group = "";
        zipcode = 0;
    }
    
    // constructs a "real" contact from "real" data
    public Contact(String f, String l, String id, String ph, int zip, String grp)
    {
        firstName = f;
        lastName = l;
        andrewID = id; 
        phone = ph;
        group = grp;
        zipcode = zip;
    }
    
    public String getID()
    {
        return andrewID;
    }
    
    public String getGroup()
    {
        return group;
    }
    
    public int getZip()
    {
        return zipcode;
    }
    
    public void setPhone(String ph)
    {
        phone = ph;
    }
    
    public boolean equals(Object o)
    {
        if (!(o instanceof Contact))
            return false;
        
        Contact c = (Contact)o;
        return andrewID.equals(c.andrewID);
    }
    
    // always provide a toString() method for every class you write
    public String toString()
    {
        return firstName + " " + lastName + " " + andrewID + " " + phone + " " + 
            zipcode + " " + group;
    }
    
    public static void main(String[] args)
    {
        Object p = new Contact("Mark","Stehlik","mjs","412-111",12345,"x");
        System.out.println(p instanceof Contact);
        System.out.println(p);
        Contact x = null;
        System.out.println(x instanceof Contact);
        //System.out.println(x instanceof String);  // error!
    }
}