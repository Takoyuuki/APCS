// name:        date: 
   import java.util.*;
   import java.io.*;
   public class Huffman
   {
      public static void main(String[] args) throws IOException
      {
      	//Read the string
         Scanner i = new Scanner(System.in);
         System.out.print("Enter filename (middle only): ");
         String o = i.next();
         i.nextLine();
         System.out.print("Enter text to encode: ");
         String e = i.nextLine();
         //Make a frequency table of the letters
         Map<Character, Integer> m = new HashMap<Character, Integer>();
         for(char x : e.toCharArray()){
            if(m.containsKey(x)){
               m.put(x, m.get(x)+1);
            }
            else{
               m.put(x, 1);
            }
         }
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
			//        node into a priority queue (or a min-heap). 
         PriorityQueue<HuffmanTreeNode> p = new PriorityQueue<HuffmanTreeNode>();
         for(char k : m.keySet())
            p.add(new HuffmanTreeNode(k, m.get(k)));
      	//Use the priority queue of nodes to build the Huffman tree
         Map<Character, String> h = new HashMap<Character, String>();
         for(char k : m.keySet())
            h.put(k, "");
         while(p.size() > 1){
            HuffmanTreeNode a = p.remove();
            HuffmanTreeNode b = p.remove();
            HuffmanTreeNode c = new HuffmanTreeNode('*', a.getF()+b.getF(), a, b);
            update(a, '0', h);
            update(b, '1', h);
            p.add(c);
         }
         HuffmanTreeNode r = p.remove();
      	//Process the string letter-by-letter and search the tree for the 
			//       letter.  As you go, build the binary path, where going 
			//       left is 0 and going right is 1.  
      	//Write the binary path to the hard drive as message.xxx.txt
      	//Write the scheme to the hard drive as scheme.xxx.txt
         PrintStream s = new PrintStream(new File("scheme." + o + ".txt"));
         for(char k: h.keySet())
            s.println(k + h.get(k));
         s = new PrintStream(new File("message." + o + ".txt"));
         for(char x: e.toCharArray()){
            s.print(h.get(x));
         }
      }
      public static void update(HuffmanTreeNode r, char c, Map<Character, String> m){
         if(r.getC() != '*'){
            m.put(r.getC(), c + m.get(r.getC()));
            return;
         }
         update(r.getLeft(), c, m);
         update(r.getRight(), c, m);
      }
   }
	/*
	  * This node stores two values.  
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
   class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
   {
      private int f;
      private char c;
      private HuffmanTreeNode l, r;
      
      public HuffmanTreeNode(char c, int f){
         l = null;
         r = null;
         this.c = c;
         this.f = f;
      }
      public HuffmanTreeNode(char c, int f, HuffmanTreeNode l, HuffmanTreeNode r){
         this.c = c;
         this.f = f;
         this.l = l;
         this.r = r;
      }
      public String toString(){
         return this.c + ":" + this.f;
      }
      public int compareTo(HuffmanTreeNode h){
         return this.f - h.f;
      }
      public int getF(){
         return this.f;
      }
      public char getC(){
         return this.c;
      }
      public HuffmanTreeNode getLeft(){
         return this.l;
      }
      public HuffmanTreeNode getRight(){
         return this.r;
      }
   }
