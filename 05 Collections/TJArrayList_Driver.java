//  name:        date: 
//  implements the List interface with a backing array of Objects. 
//	 also overrides toString().
    
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
   
      myList.add("Apple");
      myList.add("Banana");
      myList.add("Fig");
      myList.add(2, "Cucumber");
      myList.add(3, "Dates");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      try
      {
         myList.add(12, "Peach");
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      myList.set(2, "Cherry");
      System.out.println(myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Breadfruit\"?  " + myList.contains("Breadfruit"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      size = 0;
      myArray = new Object[10];
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
         must be an O(1) operation when size < array.length, 
            and O(n) when it doubles the length of the array.
	      @return true  */
   public boolean add(Object obj)
   {
      if(size < myArray.length)
         myArray[size] = obj;
      else{
         Object[] newArray = new Object[myArray.length*2];
         for(int i = 0; i < myArray.length; i++)
            newArray[i] = myArray[i];
         newArray[size] = obj;
         myArray = newArray;
      }
      size++;
      return true;    
   }
      /* inserts obj at position index.  increments size. 
   		*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if(size + 1 > myArray.length){
         Object[] newArray = new Object[myArray.length*2];
         for(int i = 0; i < myArray.length; i++)
            newArray[i] = myArray[i];
         myArray = newArray;
      }
      for(int i = size - 1; i >= index; i--)
         myArray[i+1] = myArray[i];
      size++;
      myArray[index] = obj;
   }
      /* return obj at position index.  
   		*/
   public Object get(int index)
   {
      return myArray[index];
   }
    /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
      myArray[index] = obj;  
   }
    /*  removes the node from position index. shifts elements 
        to the left.   Decrements size.
   	  @return the object at position index.
   	  */
   public Object remove(int index)
   {
      Object toReturn = myArray[index];
      for(int i = index; i < myArray.length-1; i++)
         myArray[i] = myArray[i+1];
      size--;
      return toReturn; 
   }
	 /*
      this method compares objects and should use .equals(), not ==
     	*/
   public boolean contains(Object obj)
   {
      for(Object o: myArray)
         if(obj.equals(o)) 
            return true;
      return false;
   }
      /*returns a String of Objects in the array with square brackets and commas
        	*/
   public String toString()
   {
      String s = "[";
      for(int i=0; i < size-1; i++)
         s += "" + myArray[i] + ", ";
      s += "" + myArray[size-1] + "]";
      return s;
   }
}