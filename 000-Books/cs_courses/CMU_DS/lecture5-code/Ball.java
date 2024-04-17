public class Ball 
{
    // instance variables (or fields)
    private double radius;
    private String color;
    private double elasticity;
    
    // default constructor (no parameters); there can only be one
    public Ball()
    {
        radius = 2;  // I would use a vaule other than 2 :-)
        color = null;
        elasticity = 0.0;
    }
    
    // a parameterized constructor
    public Ball(double r, String color, double e)
    {
        radius = r;
        this.color = color;  // better than color = c; ??
        elasticity = e;
    }
    
    // an accessor method (or getter)
    public String getColor()
    {
        return this.color;  // this is an implicit reference to the object on which this method was called
    }
    
    // a mutator method (or setter)
    public void changeColor(String c)
    {
        color = c;
    }
    
    // a method
    public double bounceHeight(int height, int numBounces)
    {
        double h = height;
        for (int i = 0; i < numBounces; i++)
            h *= elasticity;
        return h;
    }        
    
    // always write a toString to control how your data is displayed
    public String toString()
    {
        return color + " ball of radius " + radius + " with elasticity of " + elasticity;
    }
    
    
    public static void main(String[] args)
    {
        Ball b2 = new Ball();
        Ball b = new Ball(10, "red",.5);
        // b.color works in the println below even though it is private 
        // because we are inside the class.
        System.out.println("X" + b.color + " " + b.radius + b.getColor());
        System.out.println(b);
        System.out.println(b.bounceHeight(100, 10));
    }
}
