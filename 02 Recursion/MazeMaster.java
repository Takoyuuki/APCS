//name:    date:
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename: ");
      char[][] retArr = buildCharArr(sc.next());
      Maze m = new Maze(retArr);
      m.display();
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("2: Mark all paths, and display the count of all steps.");
      System.out.println("3: Show only the correct path.");
      System.out.println("4: Show only the correct path. If no path exists, display 'No path exists'.");
      System.out.println("5: Show only the correct path, and display the count of steps.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();  
   } 
   //take in a filename, and return a char[][]
   public static char[][] buildCharArr(String fileName)
   {
      try{
         Scanner in = new Scanner(new File(fileName + ".txt"));
         int numRows = in.nextInt();
         int numColumns = in.nextInt();
         char[][] grid = new char[numRows][numColumns];
         for(int i = 0; i < numRows; i++){
            grid[i] = in.next().toCharArray();
         }
         return grid;
      }
      catch(FileNotFoundException e){
         System.out.println("File Not Found.");
         System.exit(0); // may be changed later
      }
      return new char[0][0];
   }
}


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char PATH = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   private final char MARKER = 'x';
   //fields
   private char[][] maze;
   private int startRow, startCol;
   private boolean S_Exists=false, E_Exists=false;
   //constructor initializes all the fields
   public Maze(char[][] inCharArr)    
   {
      this.maze = inCharArr;
      for(int i = 0; i < maze.length; i++){
         for(int j = 0; j < maze[i].length; j++){
            if(maze[i][j] == this.START){
               this.S_Exists = true;
               this.startRow = i;
               this.startCol = j;
            }
            else if(maze[i][j] == this.EXIT){
               this.E_Exists = true;
            }
         }
      }
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   public void solve(int n)
   {
      if(n==1)
      {
         markAllPaths(startRow, startCol);
      }
      else if(n==2)
      {
         int count = markAllPathsAndCountStars(startRow, startCol);
         System.out.println("Number of steps = " + count);
      }
      else if(n==3)
      {
         displayTheCorrectPath(startRow, startCol);
      }
      else if(n==4)   //use maze3 here
      {
         if( !displayTheCorrectPath(startRow, startCol) )
            System.out.println("No path exists");   
      }     
      else if(n==5)
      {
         displayCorrectPathAndCountStars(startRow, startCol, 0);
      }
      else System.out.println("invalid submission");
      
   }
   private void markAllPaths(int r, int c)
   {
      if(this.S_Exists && this.E_Exists && r < maze.length && r >= 0 && c < maze[r].length && c >= 0 && (this.PATH == maze[r][c] || this.START == maze[r][c] || this.EXIT == maze[r][c])){
         if(this.START != maze[r][c] && this.EXIT != maze[r][c])
            maze[r][c] = this.STEP;
         markAllPaths(r+1, c);
         markAllPaths(r-1, c);
         markAllPaths(r, c+1);
         markAllPaths(r, c-1);
      }
   }
        
   private int markAllPathsAndCountStars(int r, int c)
   {
      if(this.S_Exists && this.E_Exists && r < maze.length && r >= 0 && c < maze[r].length && c >= 0 && (this.PATH == maze[r][c] || this.START == maze[r][c] || this.EXIT == maze[r][c])){
         if(this.EXIT == maze[r][c]){
            return 0; 
         }
         if(this.START == maze[r][c]){
            return markAllPathsAndCountStars(r, c+1) + markAllPathsAndCountStars(r, c-1) + markAllPathsAndCountStars(r+1, c) + markAllPathsAndCountStars(r-1, c);
         }
         maze[r][c] = this.STEP;
         return 1 + markAllPathsAndCountStars(r, c+1) + markAllPathsAndCountStars(r, c-1) + markAllPathsAndCountStars(r+1, c) + markAllPathsAndCountStars(r-1, c);
      }
      return 0;
   }

   private boolean displayTheCorrectPath(int r, int c)
   {
      if(r >= 0 && r < maze.length && c >= 0 && c < maze[r].length){
         if(maze[r][c] == this.PATH || maze[r][c] == this.START){
            maze[r][c] = this.MARKER;
            if(displayTheCorrectPath(r-1, c) || displayTheCorrectPath(r+1, c) || displayTheCorrectPath(r, c+1) || displayTheCorrectPath(r, c-1)){
               maze[r][c] = this.STEP;
               return true;
            }
            else{
               maze[r][c] = this.PATH;
               return false;
            }
         }
         if(maze[r][c] == this.EXIT){
            return true;
         }
      }
      return false;
   }
   
   private boolean displayCorrectPathAndCountStars(int r, int c, int count)
   {
      if(r >= 0 && r < maze.length && c >= 0 && c < maze[r].length){
         if(maze[r][c] == this.PATH || maze[r][c] == this.START){
            maze[r][c] = this.MARKER;
            if(displayCorrectPathAndCountStars(r-1, c, count+1) || displayCorrectPathAndCountStars(r+1, c, count+1) || displayCorrectPathAndCountStars(r, c+1, count+1) || displayCorrectPathAndCountStars(r, c-1, count+1)){
               maze[r][c] = this.STEP;
               return true;
            }
            else{
               maze[r][c] = this.PATH;
               return false;
            }
         }
         if(maze[r][c] == this.EXIT){
            System.out.println("Number of steps = " + count + "\n");
            return true;
         }
      }
      return false;
   }
   
   //This is for testing purposes. Do not change or remove this method.
   public char[][] getMaze()
   {
      return maze;
   }
}
