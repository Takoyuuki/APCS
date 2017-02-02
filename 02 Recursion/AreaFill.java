//name:
//date:

import java.util.Scanner;
import java.io.*;
public class AreaFill
{
   public static char[][] grid = null;
   public static String filename = null;
      
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner sc = new Scanner(System.in);
      while(true) 
      {
         System.out.print("Filename: ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            System.exit(0);
         }
         grid = read(filename);
         System.out.println( display(grid) );
         System.out.print("\nEnter ROW COL to fill from: ");
         int row = sc.nextInt();
         int col = sc.nextInt(); 
         grid = fill(grid, row, col, grid[row][col]);
         System.out.println( display(grid) );
      //  Extension
      // int count = fillAndCount(grid, row, col, grid[row][col]);
      // System.out.println( display(grid) );
      // System.out.println("count = " + count);
         System.out.println();
      }
   }
   public static char[][] read(String filename)throws FileNotFoundException
   {
      Scanner in = new Scanner(new File(filename));
      int numRows = in.nextInt();
      int numColumns = in.nextInt();
      char[][] grid = new char[numRows][numColumns];
      for(int i = 0; i < numRows; i++){
         grid[i] = in.next().toCharArray();
      }
      return grid;
   }
   
   public static String display(char[][] g)
   {
      String s = "";
      for(int i = 0; i < g.length; i++){
         for(int j = 0; j < g[0].length; j++){
            s += g[i][j];
         }
         s += "\n";
      } 
      return s;
   }
   public static char[][] fill(char[][] g, int r, int c, char ch) //recursive method
   {
      if(r < g.length && r >= 0 && c < g[0].length && c >= 0 && ch == g[r][c]){
         g[r][c] = '*';
         fill(g, r+1, c, ch);
         fill(g, r-1, c, ch);
         fill(g, r, c+1, ch);
         fill(g, r, c-1, ch);
      }
      return g;
   }
	
	// Extension-- count and return the number of asterisks
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      return 0;
   }
}