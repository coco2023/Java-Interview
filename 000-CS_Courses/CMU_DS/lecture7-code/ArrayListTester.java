import java.util.*;

public class ArrayListTester
{
    public static void main(String[] args)
    {
        //ArrayList<int> arr = new ArrayList<int>();  // cannot create an ArrayList of primitive
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        intArray.add(1);
        intArray.add(2);
        intArray.add(3);
        intArray.add(4);
        System.out.println(intArray);
        intArray.remove(2);  // removes the item at index 2 (which is the value 3)
        System.out.println(intArray);
        intArray.remove(new Integer(2));  // but you can "box" a primitive int into an Integer!
        System.out.println(intArray);
        for (int i : intArray)
            System.out.println(i);  // and you can "unbox" the Integers back to ints!
        
        ArrayList<String> strArray = new ArrayList<String>();
        strArray.add(0,"mark");
        System.out.println(strArray.size());
        strArray.add(0,"roly");
        System.out.println(strArray);
        strArray.add("fred");
        System.out.println(strArray);
        System.out.println(strArray.get(1));  // must use get to access an ArrayList element, cannot access ArrayList with []
        System.out.println(strArray.contains("ark"));  // false, no substring search here...
        
        ArrayList<ArrayList<Integer>> arr2 = new ArrayList<ArrayList<Integer>>();
        System.out.println(arr2);
        arr2.add(intArray);
        System.out.println(arr2);
        System.out.println(arr2.size());
        arr2.add(new ArrayList<Integer>());
        System.out.println(arr2);
        System.out.println(arr2.size());
        arr2.get(1).add(45);
        System.out.println(arr2);
        System.out.println(arr2.size());
        
        // can create an ArrayList of any class...
        ArrayList<Person> peeps = new ArrayList<Person>();
        Person p = new Person("Mark", "Stehlik", "Czech", 42);
        peeps.add(p);
        System.out.println(peeps);
        peeps.add(p);  // stores another reference to the *same* p
        peeps.add(peeps.get(0));  // gets, and stores, another reference to the *same* p
        p.birthday();
        System.out.println(peeps);// all 3 Person objects in peeps are now 43!  Ahh, aliasing...
    }
}
