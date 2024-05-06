/**
 * Implements a point (as an ordered pair of ints)
 */

import java.util.*;

public class PointXY implements Comparable<PointXY>
{
    private int x, y;
    
    /**
     * Constructs a PointXY at the specified location
     * @param xc the x coordinate of the PointXY object
     * @param yc the y coordinate of the PointXY object
     */
    public PointXY(int xc, int yc)
    {
        x = xc;
        y = yc;
    }
    
    /**
     * returns the x coordinate of this point
     @return the x coordinate
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * returns the y coordinate of this point
     @return the y coordinate
     */
    public int getY()
    {
        return y;
    }
    
    /** sets the x coordinate to the supplied value
      * @param xValue the new x coordinate
      */
    public void setX(int xValue)
    {
        x = xValue;
    }
    
    /** sets the y coordinate to the supplied value
      * @param yValue the new y coordinate
      */
    public void setY(int yValue)
    {
        y = yValue;
    }
    
    /** sets the x and y coordinates to the supplied values
      * @param xValue the new x coordinate
      * @param yValue the new y coordinate
      */
    public void setXY(int xValue, int yValue)
    {
        x = xValue;
        y = yValue;
    }
    
    /**
     * Returns the distance between two points
     * @return the distance between this point and one supplied
     * @param param the other point to find the distance to
     */
    public double distance2D(PointXY param)
    {
        double d1 = x - param.x;
        d1 *= d1;
        double d2 = y - param.y;
        d2 *= d2;
        return Math.sqrt(d1 + d2);
    }
    
    /**
     * Returns a string representing this point
     * @return the string representation
     */
    public String toString()
    {
        return "(" + x + "," + y +")";
    }
    
    public int compareTo(PointXY that)
    {
        if (that == null)  // this cannot be null, can't call method on null object!
            throw new IllegalArgumentException("cannot compare null object(s)");
        /*
        if (this.x < that.x)
            return -42;
        if (this.x > that.x)
            return 42;
        if (this.y < that.y)
            return -1;
        if (this.y > that.y)
            return 1;
        return 0;
        */
        if (x != that.x)
        {
            return x - that.x;
        }
        return y - that.y;
    }
    
    public boolean equals(Object o)
    {
        if (!(o instanceof PointXY))
            return false;
        
        PointXY other = (PointXY)o;
        return compareTo(other) == 0;  // might as well use compareTo - ensures consistency!
    }
    
    public static void main(String[] args)
    {
        PointXY p = new PointXY(2,3);
        PointXY p2 = new PointXY(4,5);
        System.out.println(p + "\t" + p2);
        PointXY[] arr = new PointXY[20];
        for (int i = 0; i < 20; i++)
            arr[i] = new PointXY((int)(Math.random() * 50), (int)(Math.random() * 50));
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}