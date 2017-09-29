//name:    date:
/*  Assignment:  This hashing program results in collisions.
You are to implement three different collision schemes: linear
probing, rehashing, and chaining.  Then implement a search
algorithm that is appropriate for each collision scheme.
*/
import java.util.*;
import javax.swing.*;
public class Hashing
{
  public static void main(String[] args)
  {
    int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
    "Hashing!\n"+
    "Enter the size of the array:  "));//20

    int numItems = Integer.parseInt(JOptionPane.showInputDialog(
    "Add n items:  "));               //15

    int scheme = Integer.parseInt(JOptionPane.showInputDialog(
    "The Load Factor is " + (double)numItems/arrayLength +
    "\nWhich collision scheme?\n"+
    "1. Linear Probing\n" +
    "2. Rehashing\n"+
    "3. Chaining"));
    Hashtable table = null;
    switch( scheme )
    {
      case 1:
      table = new HashtableLinearProbe(arrayLength);
      break;
      case 2:
      table = new HashtableRehash(arrayLength);
      break;
      case 3:
      table = new HashtableChaining(arrayLength);
      break;
      default:  System.exit(0);
    }
    for(int i = 0; i < numItems; i++)
    table.add("Item" + i);
    int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
    "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
    while( itemNumber != -1 )
    {
      String key = "Item" + itemNumber;
      int index = table.indexOf(key);
      if( index >= 0)    //found it
      System.out.println(key + " found  at index " + index);
      else
      System.out.println(key + " not found!");
      itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
      "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
    }
    System.exit(0);
  }
}
/*********************************************/
interface Hashtable
{
  void add(Object obj);
  int indexOf(Object obj);
}
/***************************************************/
class HashtableLinearProbe implements Hashtable
{
  private Object[] array;
  public HashtableLinearProbe(int size)
  {
    array = new Object[size];    //constructor
  }
  public void add(Object obj)
  {
    int code = obj.hashCode();
    int index = Math.abs(code % array.length);
    if( array[index] == null )  //empty
    {
      array[index] = obj;    //insert it
      System.out.println(obj + "\t" + code + "\t" + index);
    }
    else //collision
    {
      System.out.println(obj + "\t" + code + "\tCollision at "+ index);
      index = linearProbe(index);
      if(index == -1) System.out.println("Unable to find a spot in the table!");
      else {
        array[index] = obj;
        System.out.println(obj + "\t" + code + "\t" + index);
      }
    }
  }
  public int linearProbe(int index)
  {
    //be sure to wrap around the array.
    while(index < array.length - 1 && array[index] != null){
      index++;
    }
    if(array[index] != null){
      index = 0;
    }
    while(index < array.length - 1 && array[index] != null)
    index++;
    if(array[index] != null){
      return -1;
    }
    return index;
  }
  public int indexOf(Object obj)
  {
    int index = Math.abs(obj.hashCode() % array.length);
    while(array[index] != null)
    {
      if( array[index].equals(obj) )  //found it
      {
        return index;
      }
      else //search for it in a linear probe manner
      {
        while(index < array.length && !array[index].equals(obj)){
          index++;
        }
        if(index == array.length){
          index=0;
        }
        while(index < array.length && !array[index].equals(obj)){
          index++;
        }
        if(index == array.length){
          return -1;
        }
        return index;
      }
    }
    return -1;      //not found
  }
}
/*****************************************************/
class HashtableRehash implements Hashtable
{
  private Object[] array;
  private int constant = 2;
  public HashtableRehash(int size)
  {
    array = new Object[size];                       //constructor
    constant = relPrime(size);                       //find a constant that is relatively prime to the size of the array
  }
  private int relPrime(int s){
    if(Math.abs(s % 2) == 1){
      return 2;
    }
    else {
      return 3;
    }
  }
  public void add(Object obj)
  {
    int code = obj.hashCode();
    int index = Math.abs(code % array.length);
    if( array[index] == null )  //empty
    {
      array[index] = obj;    //insert it
      System.out.println(obj + "\t" + code + "\t" + index);
    }
    else //collision
    {
      System.out.println(obj + "\t" + code + "\tCollision at "+ index);
      index = rehash(index);
      array[index] = obj;
      System.out.println(obj + "\t" + code + "\t" + index);
    }
  }
  public int rehash(int index)
  {
    while(index < array.length-this.constant && array[index] != null){
      index += this.constant;
    }
    if(array[index] != null){
      index = (index - array.length) + this.constant;
    }
    while(index < array.length-this.constant && array[index] != null){
      index += this.constant;
    }
    if(array[index] != null){
      return -1;
    }
    return index;
  }
  public  int indexOf(Object obj)
  {
    int index = Math.abs(obj.hashCode() % array.length);
    while(array[index] != null)
    {
      if( array[index].equals(obj) )  //found it
      {
        return index;
      }
      else //search for it in a rehashing manner
      {
        while(index < array.length-this.constant && !array[index].equals(obj)){
          index += this.constant;
        }
        if(!array[index].equals(obj)){
          index = (index - array.length) + this.constant;
        }
        while(index < array.length-this.constant && !array[index].equals(obj)){
          index += this.constant;
        }
        if(!array[index].equals(obj)){
          return -1;
        }
        return index;
      }
    }
    return -1;     //not found
  }
}
/********************************************************/
class HashtableChaining implements Hashtable
{
  private LinkedList[] array;
  public HashtableChaining(int size)
  {
    array = new LinkedList[size];
    for (int i = 0; i < array.length; i++ ) {
      array[i] = new LinkedList();
    }
  }
  public void add(Object obj)
  {
    int code = obj.hashCode();
    int index = Math.abs(code % array.length);
    array[index].addFirst(obj);
    System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
  }
  public int indexOf(Object obj)
  {
    int index = Math.abs(obj.hashCode() % array.length);
    if( !array[index].isEmpty() )
    {
      if(array[index].getFirst().equals(obj))  //found it
      {
        return index;
      }
      else //search for it in a chaining manner
      {
        for(Object o: array[index]){
          if(o.equals(obj)){
            return index;
          }
        }
      }
    }
    return -1;       //not found
  }
}
