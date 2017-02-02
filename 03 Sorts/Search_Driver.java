//name: Omkar Kulkarni   date: 10-30-2016
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
import javax.swing.*;  //the JOptionPane
public class Search_Driver  {
   public static void main(String[] args) throws Exception
   {
      String[] array = input("declaration.txt");
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a word: ");
      String target = in.next();
      System.out.println("Linear Search found '" + target + "' at location " + Searches.linear(array, target) + " in " + Searches.linearCount + " comparisons.");
      System.out.println("Binary Search found '" + target + "' at location " + Searches.binary(array, target) + " in " + Searches.binaryCount() + " comparisons.");
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner(new File(filename));
      String[] temp = new String[1325];
      int count = 0;
      while(count < 1325){
         temp[count] = infile.next();
         count++;
      }
      Insertion.sort(temp); 
      return temp;
   }
}
/////////////////////////////////////////////////////////
class Searches
{
   public static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int binaryCount()
   {
      return binaryCount;
   }
   public static int linear(Comparable[] array, Comparable target)
   { 
      for(int i=0; i < array.length; i++){
         linearCount++;
         if(array[i].compareTo(target) == 0)
            return i;
      }
      return -1;
   }
   public static int binary(Comparable[] array, Comparable target)
   {
      binaryCount++;
      return binaryhelper(array, target, 0, array.length-1);
   }
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      binaryCount++;
      if(start >= end)
         return -1;
      int middle = (start + end) / 2;
      if(target.compareTo(array[middle]) == 0)
         return middle;
      else if(target.compareTo(array[middle]) < 0)
         return binaryhelper(array, target, start, middle);
      else
         return binaryhelper(array, target, middle+1, end);
   }
}