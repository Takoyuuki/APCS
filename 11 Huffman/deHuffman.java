// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode(null);
      TreeNode p = root;
      while(huffLines.hasNextLine()){
         String x = huffLines.nextLine();
         char d = x.charAt(0);
         for(int i = 1;i < x.length(); i++){
            switch(x.charAt(i)){
               case '0': 
                  if(p.getLeft() == null){p.setLeft(new TreeNode(null));} p = p.getLeft(); 
                  break;
               case '1': 
                  if(p.getRight() == null){p.setRight(new TreeNode(null));} p = p.getRight(); 
                  break;
            }
         }
         p.setValue(d);
         p = root;
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode t = root;
      String r = "";
      for(char c : text.toCharArray()){
         if(c == '0'){
            t = t.getLeft();
         }
         else{
            t = t.getRight();
         }
         if(t.getValue() != null){
            r += t.getValue();
            t = root;
         }
      }
      return r;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
