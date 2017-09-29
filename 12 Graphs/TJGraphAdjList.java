//name:    date:
//resource classes and interfaces
//for use with Graphs3: EdgeList
//             Graphs4: DFS-BFS
//             Graphs5: EdgeListCities

import java.io.*;
import java.util.*;
/*********************  Graphs 3:  EdgeList *******************************/
interface VertexInterface
{
   public String toString();     //just return the name
   public String getName();
   public ArrayList<Vertex> getAdjacencies();
   public void addEdge(Vertex v);
}  

interface TJGraphAdjListInterface 
{ 
   public List<Vertex> getVertices();
   public Vertex getVertex(int i) ;
   public Vertex getVertex(String vertexName);
   public Map<String, Integer> getVertexMap();
   public void addVertex(String v);
   public void addEdge(String source, String target);
   public String toString();
  
}

  
   /*********************Graphs 4:  DFS and BFS ****************************/
interface DFSAndBFS
{
   public List<Vertex> depthFirstSearch(String name);
   public  List<Vertex> breadthFirstSearch(String name);
   public  List<Vertex> depthFirstRecur(String name);
}

   /****************Graphs 5:  EdgeList with Cities  *********/
interface EdgeListWithCities
{
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   public int edgeCount();
   public boolean isReachable(String source, String target);
   public boolean isConnected();
}
/***********************************************************/
class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
   
   public Vertex(String n){
      name = n;
      adjacencies = new ArrayList<Vertex>();
   }
   
   public String toString(){
      return getName();
   }     //just return the name
   public String getName(){
      return name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return adjacencies;
   }
   public void addEdge(Vertex v){
      adjacencies.add(v);
   }
  
  /* enter your code here  */
  
}  
/*******************************************************/
public class TJGraphAdjList implements TJGraphAdjListInterface, DFSAndBFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
 /* enter your code here  */
   public List<Vertex> getVertices(){
      return vertices;
   }
   public Vertex getVertex(int i){
      return vertices.get(i);
   };
   public Vertex getVertex(String vertexName){
      return getVertex(nameToIndex.get(vertexName));
   };
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   };
   public void addVertex(String v){
      if(nameToIndex.get(v) != null) return;
      nameToIndex.put(v, vertices.size());
      vertices.add(new Vertex(v));
      
   };
   public void addEdge(String source, String target){
      if(nameToIndex.get(source) == null) addVertex(source);
      if(nameToIndex.get(target) == null) addVertex(target);
      vertices.get(nameToIndex.get(source)).addEdge(vertices.get(nameToIndex.get(target)));
   }
   public String toString(){
      String o = "";
      for(Vertex v : vertices){
         o += v.toString() + " " + v.getAdjacencies().toString() + "\n";
      }
      return o;
   }
   public List<Vertex> depthFirstSearch(String name){
      List<Vertex> l = new ArrayList<Vertex>();
      Stack<Vertex> s = new Stack<Vertex>();
      Vertex v = vertices.get(nameToIndex.get(name));
      s.add(v);
      while(!s.isEmpty()){
         Vertex v1 = s.pop();
         if(!l.contains(v1)){
            for(Vertex v2: v1.getAdjacencies()){
               if(!s.contains(v2))
                  s.add(v2);
            }
            l.add(v1);
         }
         
      }
      return l;
   }
   public  List<Vertex> breadthFirstSearch(String name){
      List<Vertex> l = new ArrayList<Vertex>();
      Queue<Vertex> s = new LinkedList<Vertex>();
      Vertex v = vertices.get(nameToIndex.get(name));
      s.add(v);
      while(!s.isEmpty()){
         Vertex v1 = s.remove();
         if(!l.contains(v1)){
            for(Vertex v2: v1.getAdjacencies()){
               if(!s.contains(v2))
                  s.add(v2);
            }
            l.add(v1);
         }
         
      }
      return l;
   };
   public  List<Vertex> depthFirstRecur(String name){
      return depthFirstSearch(name);
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner f = new Scanner(new File(fileName));
      while(f.hasNext()){
         addEdge(f.next(), f.next());
      }
   }
   public int edgeCount(){
      return vertices.stream().map(Vertex::getAdjacencies).mapToInt(List::size).sum();
   }
   public boolean isReachable(String source, String target){
      return depthFirstSearch(source).contains(vertices.get(nameToIndex.get(target)));
   };
   public boolean isConnected(){
      for(Vertex v: vertices){
         if(depthFirstSearch(v.getName()).size() < vertices.size() - 1) return false;
      }
      return true;
   };
       
}


