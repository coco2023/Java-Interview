public class Student extends Person
{
    private String major;
    private String andrewID;
    private String visa;
    
    // always good to provide a default constructor
    public Student()
    {
        
    }
    
    public Student(String f, String l, String nat, int age, String major, String id)
    {
        //super(f,l,nat,age);  // has to be first line in subclass constructor
        // if you don't call the super constructor, Java will implicitly call super()
        // so the superclass had better have a default constructor...
        this.major = major;
        andrewID = id;
        if (nationality.equals("USA"))
            visa = "";  // could also be null
        else
            visa = "F1";
    }
    
    // no need to qualify firstName or lastName as they are inherited from Person
    // i.e., since there isn't a firstName or lastName field in Student, Java goes
    // up the inheritance chain to find one
    // where does it "stop" if it doesn't find one?
    public String getNameAndMajor()
    {
        return firstName + " " + lastName + " " + major;
    }
    
    // have to qualify toString() call below, otherwise infinite recursion
    public String toString()
    {
        return super.toString() + " " + visa + " " + andrewID + " " + major;
    }
    
    public static void main(String[] args)
    {
        Student s = new Student("Joe","Roly","complicated",20,"Information Systems", "joly");
        Student s2 = new Student();
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s.getNameAndMajor());
        Person p = new Student("Darien","Weems","USA",20,"Information Systems","dweems");
        System.out.println(p);  // uses Student's toString()
        System.out.println(((Student)p).getNameAndMajor());  // without the cast, this would not compile
    }
}