import java.util.*;
import java.io.*;
public class SetsOfLetters
{
  public static void main(String[] args) throws FileNotFoundException
  {
    fillTheSets("declarationLast.txt");
  }
  public static void fillTheSets(String fn) throws FileNotFoundException
  {
    ArrayList a = new ArrayList<ArrayList<Object>>();
    Scanner s = new Scanner(new File(fn));
    while(s.hasNextLine()){
      ArrayList aList = new ArrayList<Object>();
      Set lSet = new HashSet<Character>();
      Set gSet = new HashSet<Character>();
      Set oSet = new HashSet<Character>();
      String line = s.nextLine();
      for(char c: line.toCharArray())
        if(Character.isLowerCase(c)) lSet.add(c);
        else if(Character.isUpperCase(c)) gSet.add(c);
        else oSet.add(c);
      aList.add(line);
      aList.add(lSet);
      aList.add(gSet);
      aList.add(oSet);
      a.add(aList);
    }
    for(Object l: a) System.out.println(l);
    for(Object o: a){
      ArrayList<Object> l = (ArrayList) o;
      System.out.println(l.get(0));
      System.out.println("Lower Case: " + l.get(1));
      System.out.println("Upper Case: " + l.get(2));
      System.out.println("Other: " + l.get(3));
      System.out.println();
    }
    Set lInt = new HashSet<Character>(((Set)((ArrayList)a.get(0)).get(1)));
    Set uInt = new HashSet<Character>(((Set)((ArrayList)a.get(0)).get(2)));
    Set oInt = new HashSet<Character>(((Set)((ArrayList)a.get(0)).get(3)));
    for(Object o: a){
      ArrayList<Object> l = (ArrayList) o;
      lInt.retainAll((Set)l.get(1));
      uInt.retainAll((Set)l.get(2));
      oInt.retainAll((Set)l.get(3));
    }
    System.out.println("Intersection for Lower Case: " + lInt.toString());
    System.out.println("Intersection for Upper Case: " + uInt.toString());
    System.out.println("Intersection for Other: " + oInt.toString());
  }
}
