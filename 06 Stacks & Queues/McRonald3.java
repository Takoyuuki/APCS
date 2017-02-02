//name :   date:

import java.util.*;
public class McRonald3
{
   public static final int TIME = 1079;  //18 hrs * 60 min
   public static final int QUEUES = 3;
   public static final double PROBABILITY = 0.5;
   public static void main(String[] args)
   {
      int totalWaitTime, totalCustomers, longestWait, longestQueue;
      totalWaitTime = totalCustomers = longestWait = longestQueue = 0;
      double averageWaitTime = 0.0;
      ArrayList<Line> lines = new ArrayList<Line>();
      for(int i = 0; i < QUEUES; i++)
         lines.add(new Line(new LinkedList<Customer>()));
      for(int i=0; i < TIME; i++){
         Customer c = new Customer(i);
         if(Math.random() < PROBABILITY){
            int lineNum = 0;
            for(int j = 0; j < lines.size(); j++)
               if(lines.get(j).customers().size() < lines.get(lineNum).customers().size())
                  lineNum = j;
            lines.get(lineNum).addToLine(c);
         }
         for(Line l: lines){
            l.processQueue();
         }
         // System.out.print(i + ": ");
         // display(linesToIntQueue(lines));
      }
      for(Line l: lines){
         // System.out.println("Finishing Line #" + (lines.indexOf(l)+1)); 
         l.finishQueue();
      }
      for(Line l: lines){
         totalCustomers += l.totalCustomersServed;
         totalWaitTime += l.totalTime;
         if(longestQueue < l.longestQueue)
            longestQueue = l.longestQueue;
         if(longestWait < l.longestWaitTime)
            longestWait = l.longestWaitTime;
      }
      averageWaitTime = (((double)totalWaitTime)/totalCustomers);
      System.out.println("Total Customers Served: " + totalCustomers);
      System.out.println("Average Wait Time: " + averageWaitTime);
      System.out.println("Longest Wait Time: " + longestWait);
      System.out.println("Longest Queue: " + longestQueue);
   }
   public static void display(Queue<Integer> q)
   {
      System.out.print(q + "\n");
   }
   public static Queue<Integer> linesToIntQueue(ArrayList<Line> lines){
      /* Yes, I am aware that this is inefficient. */
      Queue<Integer> q = new PriorityQueue<Integer>();
      for(Line l: lines)
         for(Customer c: l.customers())
            q.add(c.getTime());
      Queue<Integer> toReturn = new LinkedList<Integer>();
      while(!q.isEmpty())
         toReturn.add(q.remove());
      return toReturn;
   }
}

class Customer {
   private int minUntilServed, timeArrived;
   public Customer(int t){
      minUntilServed = 0;
      timeArrived = t;
   }
   public void incrementMins(){
      minUntilServed += 1;
   }
   public int getMins() {
      return minUntilServed;
   }
   public int getTime() {
      return timeArrived;
   }
   public String toString(){
      return (""+timeArrived);
   }
}

class Line{
   public int longestWaitTime, longestQueue, totalTime, totalCustomersServed, timeUntilNextServe;
   private Queue<Customer> customers;
   public Line(Queue<Customer> q){
      longestWaitTime = longestQueue = totalTime = totalCustomersServed = 0;
      timeUntilNextServe = ((int)(Math.random() * 6) + 2);
      customers = q;
   }
   public void addToLine(Customer c){
      customers.add(c);
   }
   public void processQueue(){
      if(timeUntilNextServe == 0){
         timeUntilNextServe = ((int) (Math.random() * 6) + 2);
         if(customers.peek().getMins() > longestWaitTime)
            longestWaitTime = customers.peek().getMins();
         totalTime += customers.remove().getMins();
         totalCustomersServed += 1;
      }
      else
         if(!customers.isEmpty())
            timeUntilNextServe -= 1;
      for(Customer c: customers)
         c.incrementMins();
      if(customers.size() > longestQueue)
         longestQueue = customers.size();
   }
   public void finishQueue(){
      while(!customers.isEmpty())
         this.processQueue();
   }
   public String toString(){
      return customers.toString();
   }
   public Queue<Customer> customers(){
      return this.customers;
   }
}