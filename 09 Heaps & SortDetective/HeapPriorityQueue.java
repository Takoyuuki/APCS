//Name: Omkar Kulkarni   Date:

//implement the API for java.util.PriorityQueue
//test this class by using it in McRonaldPQ_working.java.
//add(E) and remove()  must work in O(log n) time

import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>>
{
   private ArrayList<E> myHeap;
   public HeapPriorityQueue(){
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   public E peek(){
      if(isEmpty()) 
         return null;
      return myHeap.get(1);
   }
   public boolean isEmpty(){
      return size() == 0;
   }
   public int size(){
      return myHeap.size() - 1;
   }
   public boolean add(E obj){
      myHeap.add(obj);
      heapUp();
      return true;
   }
   private void heapUp(){
      int bottom = myHeap.size() - 1;
      while(bottom > 1){
         int parent = (bottom / 2);
         if(myHeap.get(parent).compareTo(myHeap.get(bottom)) > 0){
            swap(parent, bottom);
            bottom = parent;
         }
         else {
            break;
         }
      }
   }
   private void swap(int a, int b){
      E obj = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, obj);
   }
   public E remove() {
      E removed = myHeap.remove(1);
      if(size() != 0){
         myHeap.add(1, myHeap.remove(size()));
         heapDown(1);
      }
      return removed;
   }
   private void heapDown(int k){
      if(k > size() || k*2 > size()) 
         return;
      int mc = k*2;
      if(mc+1 <= size() && myHeap.get(mc).compareTo(myHeap.get(mc+1)) > 0) mc++;
      if(myHeap.get(k).compareTo(myHeap.get(mc)) > 0){
         swap(k, mc);
         heapDown(mc);
      }
   }
   public String toString(){
      String ret = "[";
      if(size() > 0){
         for(int i=1; i < size(); i++) ret += myHeap.get(i).toString() + ", ";
         ret += myHeap.get(myHeap.size()-1).toString();
      }
      ret += "]";
      return ret;
   }
}
