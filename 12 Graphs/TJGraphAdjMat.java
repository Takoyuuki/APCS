//name: Omkar Kulkarni  date:   
// resource classes and interfaces
// for use with Graphs0: Intro
//              Graphs1: Wardhall
//              Graphs2: Floyd
import java.util.*;
import java.io.*;

interface AdjacencyMatrix
{
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public void displayGrid();
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   
}

interface Warshall
{
   //User-friendly functionality
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   //Actual Warshall Algorithm
   public void allPairsReachability();
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class TJGraphAdjMat implements AdjacencyMatrix, Warshall, Floyd //,Warshall,Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;
   
   public TJGraphAdjMat(int num){
      grid = new int[num][num];
      vertices = new TreeMap<String, Integer>();
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to){
      return grid[from][to] < 9999 && grid[from][to] != 0;
   }
   public void displayGrid(){
      for(int[] i: grid){
         for(int j: i){
            System.out.print(j + " ");
         }
         System.out.println();
      }
   }
   public int edgeCount(){
      int count = 0;
      for(int[] i: grid){
         for(int j: i){
           count = (j < 9999 && j != 0) ? count+1 : count; 
         }
      }
      return count;
   }
   public List<Integer> getNeighbors(int source){
      List<Integer> l = new ArrayList<Integer>();
      for(int i = 0; i < grid[source].length; i++){
         boolean b = isEdge(source, i) ? l.add(i) : false;
      }
      return l;
   }  
   /*  enter your code here  */  
   public boolean isEdge(String from, String to){
      return isEdge(vertices.get(from), vertices.get(to));
   }
   public Map<String, Integer> getVertices(){
      return vertices;
   }
   public void readNames(String filename) throws FileNotFoundException {
      Scanner f = new Scanner(new File(filename));
      int xd = f.nextInt();
      f.nextLine();
      for(int i = 0; i < xd; i++){
         vertices.put(f.next(), vertices.size());
      }
   }
   public void readGrid(String filename) throws FileNotFoundException {
      Scanner f = new Scanner(new File(filename));
      int xd = f.nextInt();
      f.nextLine();
      for(int i = 0; i < xd; i++){
         String[] row = f.nextLine().split(" ");
         for(int j = 0; j < xd; j++){
            grid[i][j] = Integer.parseInt(row[j]);
         }
      }
   }
   public void displayVertices(){
      for(String s : vertices.keySet()){
         System.out.println(vertices.get(s) + "-" + s);
      }
   }
   public void allPairsReachability(){
      for(int v = 0; v < grid.length; v++){
         for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
               if(grid[i][v] == 1 && grid[v][j] == 1){
                  grid[i][j] = 1;
               }
            }
         }
      }
   }
   public int getCost(int from, int to){
      return grid[from][to];
   }
   public int getCost(String from, String to){
      return grid[vertices.get(from)][vertices.get(to)];
   }
   public void allPairsWeighted(){
      for(int v = 0; v < grid.length; v++){
         for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
               if(grid[i][j] > grid[i][v] + grid[v][j]){
                  grid[i][j] = grid[i][v] + grid[v][j];
               }
            }
         }
      }
   }
   
}
