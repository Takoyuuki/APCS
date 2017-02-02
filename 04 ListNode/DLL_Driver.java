// name:    date:
public class DLL_Driver
{
   public static void main(String args[])
   {
      DLL list = new DLL();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Durian");
      list.add("Eggplant");
      
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(2);
      System.out.println("Remove index 2: "+ obj);
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
   
      list.add(2,"Carrot");
      System.out.println("Add Carrot at index 2:   " + list);
      
      try
      {
         list.add(16,"Kiwi");    //out-of-bounds
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
       
       
      System.out.println("Get values at index 0 and First: " + list.get(0)+" and " + list.getFirst());
      System.out.println("No change in list: " +list);
         
      list.removeFirst();
      System.out.println( "Remove the First:  " + list); 
       
      list.addFirst("Artichoke");
      System.out.println("Add First:  " + list);
      System.out.println("Size: " + list.size());
       
      list.set(1, "Broccoli");
      System.out.println("Set value at index 1:  " + list);
   }
}

//////////////////////////////////////////////////////////
    
class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   
   public int size()
   {
      return this.size;
   }
   
  /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 
   	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode toAdd = new DLNode(obj, null, null);
      DLNode current = head;
      for(int i = 0; i < index; i++){
         current = current.getNext();
      }      
      toAdd.setNext(current.getNext());
      current.getNext().setPrev(toAdd);
      current.setNext(toAdd);
      toAdd.setPrev(current);
      this.size++;           
   }
   
   /* return obj at position index.  
   	*/
   public Object get(int index)
   {
      DLNode current = head;
      for(int i=0; i <= index; i++)
         current = current.getNext();
      return current.getValue();
   }
   
   /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
      DLNode current = head;
      for(int i=0; i <= index; i++)
         current = current.getNext();
      current.setValue(obj);
   }
   
   /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
   public Object remove(int index)
   {
      DLNode current = head;
      for(int i=0; i <= index; i++)
         current = current.getNext();
      DLNode prev = current.getPrev();
      prev.setNext(current.getNext());
      current.getNext().setPrev(prev);
      current.setNext(null);
      current.setPrev(null);
      this.size--;
      return current.getValue();
   }
   
  /* inserts obj at front of list; increases size;
     */
   public void addFirst(Object obj)
   {
      DLNode newNode = new DLNode(obj, head, null);
      newNode.setNext(head.getNext());
      head.setNext(newNode);
      newNode.getNext().setPrev(newNode);
      this.size++;
   }
   
   /* appends obj to end of list; increases size;
       */
   public void addLast(Object obj)
   {
      DLNode newNode = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(newNode);
      head.setPrev(newNode);
      this.size++;
   }
   
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   public Object getLast()
   {
      return head.getPrev().getValue();
         
   }
   
   public Object removeFirst()
   {
      DLNode twoForward = head.getNext().getNext();
      twoForward.setPrev(head);
      DLNode removed = head.getNext();
      removed.setPrev(null);
      removed.setNext(null);
      head.setNext(twoForward);
      this.size--;
      return removed.getValue();
   }
   
   public Object removeLast()
   {
      DLNode twoBack = head.getPrev().getPrev();
      twoBack.setNext(head);
      DLNode removed = head.getPrev();
      removed.setPrev(null);
      removed.setNext(null);
      head.setPrev(twoBack);
      this.size--;
      return removed.getValue();
   }
   
   public String toString()
   {
      String ret = "[";
      DLNode current = head.getNext();
      while(current != head){
         ret += current.getValue();
         if(current.getNext() != head)
            ret += ", ";
         current = current.getNext();
      }
      return ret + "]";
   }
}
	
//////////////////////////////////////

class DLNode 
{
   private Object value;
   private DLNode prev;
   private DLNode next;
   public DLNode(Object arg, DLNode p, DLNode n)
   {
      value=arg;
      prev=p;
      next=n;
   }
   public DLNode()
   {
      value=null;
      next=this;
      prev=this;
   }
   public void setValue(Object arg)
   {
      value=arg;
   }
   public void setNext(DLNode arg)
   {
      next=arg;
   }
   public void setPrev(DLNode arg)
   {
      prev=arg;
   }
   public DLNode getNext()
   {
      return next;
   }
   public DLNode getPrev()
   {
      return prev;
   }
   public Object getValue()
   {
      return value;
   }
}
