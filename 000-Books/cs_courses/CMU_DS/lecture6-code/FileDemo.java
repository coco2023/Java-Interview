import java.util.Scanner;
import java.io.*;

public class FileDemo
{
    // helper methods to open files for reading/writing
    public static Scanner getFileScanner(String filename)
    {
        Scanner myFile;
        try { myFile = new Scanner(new FileReader(filename)); }
        catch (Exception e)
        {
            System.out.println("File not found: " + filename);
            return null;
        }
        return myFile;
    }
    
    public static PrintWriter getFileWriter(String filename)
    {
        PrintWriter outFile;
        try { outFile = new PrintWriter(filename); }
        catch (Exception e)
        {
            System.out.println("Error opening file: " + filename);
            return null;
        }
        return outFile;
    }
    
    public static void main(String[] args)
    {
        Scanner roly = getFileScanner("family.txt");
        String f, l;
        int a = 0 ;
        if (roly != null)
        {
            while (roly.hasNext())
            {
                f = roly.next();
                l = roly.next();
                a = roly.nextInt();
                System.out.println(f + " " + l + " " + (a+1));
            }
        }
        
        PrintWriter joe = getFileWriter("joesfile.txt");
        joe.println("Hi joe, part 2 " + a);
        joe.close();
    }

}

            
