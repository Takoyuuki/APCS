public class TestPQ {
   public static void main(String[] args){
      HeapPriorityQueue h = new HeapPriorityQueue<Integer>();
      h.add(1);
      h.add(2);
      h.add(3);
      h.add(5);
      h.add(4);
      h.add(10);
      h.add(11);
      h.add(4);
      while(!h.isEmpty())
         System.out.println(h.remove());
   }
}