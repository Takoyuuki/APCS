// name:      date:
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
public class HuffmanPix_shell
{
   public static int WIDTH = 250;   // 500 x 500 is too big
   public static int HEIGHT = 250;

   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat image (including extension)? ");
      String pixName = keyboard.nextLine();
      ImageIcon i = new ImageIcon(pixName);
      BufferedImage bufImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
   
      JFrame f = new JFrame("HuffmanPix");
      f.setSize(500,500);    // width, height
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new DisplayPix(bufImg, i));
      f.setVisible(true);
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.nextLine();
   
      huffmanize( bufImg, middlePart );
      
      System.exit(0);
   }

   
   public static void huffmanize(BufferedImage bufImg, String middlePart) throws IOException
   {
      /*   your Huffman code goes  here  */
      
      Map<Integer, Integer> fmap = new HashMap<Integer, Integer>();
      for(int i = 0; i < WIDTH; i++){
         for(int j = 0; j < HEIGHT; j++){
            int rgb = bufImg.getRGB(i, j);
            if(fmap.containsKey(rgb))
               fmap.put(rgb, fmap.get(rgb)+1);
            else
               fmap.put(rgb, 1);
         }
      }
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
      for(int x: fmap.keySet())
         pq.add(new HuffmanTreeNode(x, fmap.get(x)));
      Map<Integer, String> paths = new HashMap<Integer, String>();
      for(int x: fmap.keySet())
         paths.put(x, "");
      while(pq.size() > 1){
         HuffmanTreeNode a = pq.remove();
         HuffmanTreeNode b = pq.remove();
         HuffmanTreeNode c = new HuffmanTreeNode(Integer.MAX_VALUE, a.getF() + b.getF(), a, b);
         update(a, '0', paths);
         update(b, '1', paths);
         pq.add(c); 
      }
      HuffmanTreeNode r = pq.remove();
      
      String code = "";
      for(int i = 0; i < WIDTH; i++){
         for(int j = 0; j < HEIGHT; j++){
            code += paths.get(bufImg.getRGB(i, j));
         }
      }
      
      String binaryFileName = "pix." + middlePart + ".txt";
      PrintWriter outfile = new PrintWriter(new FileWriter(binaryFileName));
      outfile.print(code);
      System.out.println("Pix done");
            			
      Map<String, String> huffmanScheme = new HashMap<String,String>();
      String schemeFile = "schemePix."+ middlePart + ".txt";
      PrintWriter outfile2 = new PrintWriter(new FileWriter(schemeFile));
      outfile2.println(""+ WIDTH +" " + HEIGHT);    //outputs the width x height
      for(int x : paths.keySet()){
         outfile2.println( x + " " + paths.get(x));
      }
      System.out.println("Scheme done");
      
      outfile.close(); 
      outfile2.close();  
   }
   
   /*  several Huffman methods go here  */
   public static void update(HuffmanTreeNode t, char c, Map<Integer, String> m){
      if(t.getI() != Integer.MAX_VALUE){
         m.put(t.getI(), c + m.get(t.getI()));
         return;
      }
      update(t.getLeft(), c, m);
      update(t.getRight(), c, m);
   }
   
}


  /*
  * This node stores two values.  
  * The compareTo method must ensure that the lowest frequency has the highest priority.
  */   
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private int f;
   private int i;
   private HuffmanTreeNode l, r;
      
   public HuffmanTreeNode(int i, int f){
      l = null;
      r = null;
      this.i = i;
      this.f = f;
   }
   public HuffmanTreeNode(int i, int f, HuffmanTreeNode l, HuffmanTreeNode r){
      this.i = i;
      this.f = f;
      this.l = l;
      this.r = r;
   }
   public String toString(){
      return this.i + ":" + this.f;
   }
   public int compareTo(HuffmanTreeNode h){
      return this.f - h.f;
   }
   public int getF(){
      return this.f;
   }
   public int getI(){
      return this.i;
   }
   public HuffmanTreeNode getLeft(){
      return this.l;
   }
   public HuffmanTreeNode getRight(){
      return this.r;
   }

}
  /*
  * Minimum code necessary to display a BufferedImage.    
  */ 
class DisplayPix extends JPanel
{
   private BufferedImage img;
   private Graphics g;
   public DisplayPix(BufferedImage bufImg, ImageIcon i)   //for Huffman
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