//name:    date:  

import java.io.*;      //the File class
import java.util.*;    //the Scanner class

public class Widget_Driver
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a filename (including '.txt'): ");
      Comparable[] array = input(in.next());
      Selection.sort(array);
      print(array);
   }
       	
   public static Comparable[] input(String filename) throws Exception
   {  
      Scanner infile = new Scanner(new File(filename));
      Comparable[] temp = new Comparable[numberOfWidgets];
      for(int i=0; i < temp.length; i++){
         temp[i] = new Widget(infile.nextInt(), infile.nextInt());
      }
      return temp;
   }
	  
   public static void print(Object[] mango)
   {
      for(Object m: mango)
         System.out.println(m.toString());
      System.out.println();
   }
}
/////////////////////////////////////////////////////
//name: Omkar Kulkarni   date: 11-02-2016

class Widget implements Comparable<Widget>
{
   private int myPounds, myOunces;
   public Widget(int p, int o){
      myPounds = p;
      myOunces = o;
   }
   public int getPounds(){
      return myPounds;
   }
   public void setPounds(int p){
      myPounds = p;
   }
   public int getOunces(){
      return myOunces;
   }
   public void setOunces(int o){
      myOunces = o;
   }
   public String toString(){
      return myPounds + " lbs., " + myOunces + " oz.";
   }
   public boolean equals(Widget other){
      return (this.toString().equals(other.toString()));
   }
   public int compareTo(Widget other){
      if (this.equals(other)) 
         return 0;
      if (this.myPounds > other.getPounds()) 
         return 1;
      if (this.myPounds < other.getPounds()) 
         return -1;
      if (this.myOunces > other.getOunces()) 
         return 1;
      if (this.myOunces < other.getOunces()) 
         return -1;
      return 0;
   }
}