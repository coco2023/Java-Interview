public class Person
{
    private String firstName;
    private String lastName;
    private int age;
    private String nationality;
    
    // default constructor - not very interesting!
    public Person()
    {
        firstName = lastName = nationality = "";
        age = 0;
    }
    
    // constructs a "real" person from "real" data
    public Person(String f, String l, String nat, int age)
    {
        firstName = f;
        lastName = l;
        nationality = nat; 
        this.age = age;
    }
    
    public void birthday()
    {
        age++;
    }
    
    // always provide a toString() method for every class you write
    public String toString()
    {
        return firstName + " " + lastName + " " + nationality + " " + age;
    }
    
    public static void main(String[] args)
    {
        final int NUM_CONTACTS = 5;
        Person mark = new Person("Mark","Stehlik","USA",60);
        System.out.println(mark);
        mark.birthday();
        System.out.println(mark);
        Person[] contacts = new Person[NUM_CONTACTS];
        // your code to test your new methods and insert 5 people into the contacts array goes here
    }
}