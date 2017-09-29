//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
   public String toString(){
      return this.target.getName() + "|" + this.weight;
   }
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;
   
   public wVertex(String n){
      name = n;
      adjacencies = new ArrayList<Edge>();
   }
 
    /*  enter your code here   */ 
   public String toString(){
      return getName();
   }
   
   public String getName(){
      return name;
   }
   
   public double getMinDistance(){
      return minDistance;
   }
   
   public void setMinDistance(double m){
      minDistance = m;
   }
   
   public wVertex getPrevious(){
      return previous;
   }        //Graphs 7
    
   public void setPrevious(wVertex v){
      previous = v;
   }   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   
   public int compareTo(wVertex other){
      return (int)(minDistance - other.minDistance);
   }
      
}

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   public wVertex getPrevious();         //Graphs 7
    
   public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}


public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   /*  enter your code here   */ 
   
   public List<wVertex> getVertices(){
      return vertices;
   }
   
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   
   public wVertex getVertex(String vertexName){
      return getVertex(nameToIndex.get(vertexName));
   }
   
   public void addVertex(String v){
      if(nameToIndex.containsKey(v)) 
         return;
      nameToIndex.put(v, vertices.size());
      vertices.add(new wVertex(v));
   }
   
   public void addEdge(String source, String target, double weight){
      addVertex(source);
      addVertex(target);
      getVertex(source).getAdjacencies().add(new Edge(getVertex(target), weight));
   }
     
   public void minimumWeightPath(String vertexName){
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex source = getVertex(vertexName);
      source.setMinDistance(0);
      pq.add(source);
      while(!pq.isEmpty()){
         wVertex v = pq.remove();
         for(Edge e: v.getAdjacencies()){
            if(v.getMinDistance() + e.weight < e.target.getMinDistance()){
               e.target.setMinDistance(v.getMinDistance() + e.weight);
               e.target.setPrevious(v);
               pq.add(e.target);
            }  
         }
      }
   }
   
   public List<wVertex> getShortestPathTo(wVertex v){
      LinkedList<wVertex> l = new LinkedList<wVertex>();
      l.addFirst(v);
      wVertex p = v.getPrevious();
      if(p == null) 
         return l;
      l.addFirst(p);
      while(p.getMinDistance() != 0.0){
         p = p.getPrevious();
         l.addFirst(p);
      }
      return l;
   }
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException {
      Scanner names = new Scanner(vertexNames);
      Scanner eData = new Scanner(edgeListData);
      names.next();
      while(names.hasNext()){
         addVertex(names.next());
      }
      while(eData.hasNext()){
         addEdge(eData.next(), eData.next(), eData.nextInt());
         eData.nextLine();
      }
      return this;
   }
   
}    
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);   //Dijkstra's
   
   /*  Graphs 7 */
       
   public List<wVertex> getShortestPathTo(wVertex v);
    
   public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException;
 
}