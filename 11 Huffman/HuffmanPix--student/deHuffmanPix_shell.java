// Name:   date:   
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class deHuffmanPix_shell
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary picture file (middle part) ? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("pix."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffScheme = new Scanner(new File("schemePix."+middlePart+".txt")); 
      int width = huffScheme.nextInt();   //  read the size of the image
      int height = huffScheme.nextInt();    
      
      TreeNode root = huffmanTree(huffScheme);
      BufferedImage bufImg = dehuff(binaryCode, root, height, width);
   	
      JFrame f = new JFrame("HuffmanPix");
      f.setSize(500,500);    // width, height
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new DisplayPix(bufImg));
      f.setVisible(true);
      
      sc.close();
      huffScheme.close();
      keyboard.nextLine();  //press 'enter'
      keyboard.nextLine(); 
      System.exit(0);
   }
   
   public static TreeNode huffmanTree(Scanner huffScheme)
   {
      TreeNode root = new TreeNode(null);
      TreeNode p = root;
      while(huffScheme.hasNext()){
         int n = Integer.parseInt(huffScheme.next());
         String x = huffScheme.next();
         for(int i = 0;i < x.length(); i++){
            switch(x.charAt(i)){
               case '0': 
                  if(p.getLeft() == null){p.setLeft(new TreeNode(null));} p = p.getLeft(); 
                  break;
               case '1': 
                  if(p.getRight() == null){p.setRight(new TreeNode(null));} p = p.getRight(); 
                  break;
            }
         }
         p.setValue(n);
         p = root;
      }
      return root;
   }
   
   public static BufferedImage dehuff(String text, TreeNode root, int height, int width)
   {
      BufferedImage bufImg = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB);
      /*  your code goes here */
      TreeNode t = root;
      for(int i = 0; i < width; i++){
         for(int j = 0; j < height; j++){
            for(int x = 0; x < text.length(); x++){
               char c = text.charAt(x);
               if(c == '0'){
                  t = t.getLeft();
               }
               else{
                  t = t.getRight();
               }
               if(t.getValue() != null){
                  bufImg.setRGB(i, j, (int)t.getValue());
                  text = text.substring(x+1, text.length());
                  t = root;
                  break;
               }
            }
         }
      }
      
      return bufImg;
   }
}
   

 /* normal AP-style TreeNode class  */
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

  /*
  * Minimum code necessary to show a BufferedImage.   
  * 
  */ 
class DisplayPix extends JPanel
{
   private BufferedImage img;
   private Graphics g;

   public DisplayPix(BufferedImage bufImg, ImageIcon i)   //for Huffman Coding
   {
      int w = bufImg.getWidth();
      int h = bufImg.getHeight();
      img = bufImg;
      g = bufImg.getGraphics();
      g.drawImage( i.getImage() , 0 , 0 , w , h, null );
   }
   public DisplayPix(BufferedImage bufImg)              //for deHuffman
   {
      img = bufImg;
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage( img , 0 , 0 , getWidth() , getHeight() , null );
   }
}