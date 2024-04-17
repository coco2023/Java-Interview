import java.util.*;
import java.io.*;

public class Contacts
{
    private static final int MAX_CONTACTS = 1000;
    private Person[] contacts;
    private int numContacts;
    
    
    // helper methods to open files for reading/writing
    public static Scanner getFileScanner(String filename)  // throws FileNotFoundException
    {
        Scanner myFile = null;
        
        try {myFile = new Scanner(new FileReader(filename));}  // if no try..catch, must use throws as above
        catch (Exception e)
        {
            System.out.println("File not found: " + filename);
            return null;
        }
        return myFile;
    }
    
    public Contacts(String filename)
    {
        contacts = new Person[MAX_CONTACTS];
        numContacts = 0;
        Scanner s = getFileScanner(filename);
        if (s != null)
        {
            while (s.hasNext())
            {
                Person p = new Person(s.next(),s.next(),"",s.nextInt());
                contacts[numContacts] = p;
                numContacts++;
            }
        }
    }
    
    public String toString()
    {   // there are more efficient ways to do this; lots of "garbage" created (n strings!)
        String result = "There are " + numContacts + " contacts:\n";
        for (int i = 0; i < numContacts; i++)
        {
            result += i + " " + contacts[i] + "\n";  //Note .toString() not needed after contacts[i]!!
        }
        return result;
    }
    
    
    public static void main(String[] args)
    {
        Contacts myContacts = new Contacts("family.txt");
        System.out.println(myContacts);
        // since contacts is private, I can only do this because main is inside the class
        Person p = myContacts.contacts[2];  // p is an alias for contacts[2]
        System.out.println(p);
        p.birthday();  // changes *both* p and contacts[2] since it is a shared reference
        System.out.println(p);
        myContacts.contacts[4] = myContacts.contacts[2];
        System.out.println(myContacts);
        p.birthday();  // examine result!
        System.out.println(myContacts);
    }
}