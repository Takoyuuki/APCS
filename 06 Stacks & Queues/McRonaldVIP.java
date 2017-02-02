//name :   date:

import java.util.*;
public class McRonaldVIP
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
      Line vipLine = new Line(new LinkedList<Customer>());
      for(int i=0; i < TIME; i++){
         Customer c = new Customer(i);
         boolean vip = false;
         if(Math.random() < 0.05)
            vip = true;
         if(Math.random() < PROBABILITY && !vip){
            int lineNum = 0;
            for(int j = 0; j < lines.size(); j++)
               if(lines.get(j).customers().size() < lines.get(lineNum).customers().size())
                  lineNum = j;
            lines.get(lineNum).addToLine(c);
         }
         else if(vip){
            vipLine.addToLine(c);
         }
         
         if(vipLine.customers().size() > 0)
            vipLine.finishVIPQueue(lines);
         else{
            for(Line l: lines){
               l.processQueue();
            }
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
      totalCustomers += vipLine.totalVIPServed;
      totalWaitTime += vipLine.totalVIPWaitTime;
      longestQueue = (longestQueue < vipLine.longestQueue) ? vipLine.longestQueue : longestQueue;
      longestWait = (longestWait < vipLine.longestWaitTime) ? vipLine.longestWaitTime : longestWait;
      averageWaitTime = (((double)totalWaitTime)/totalCustomers);
      System.out.println("Total Customers Served: " + totalCustomers);
      System.out.println("Average Wait Time: " + averageWaitTime);
      System.out.println("Longest Wait Time: " + longestWait);
      System.out.println("Longest Queue: " + longestQueue);
      System.out.println("Number of VIP's Served: " + vipLine.totalVIPServed);
      System.out.println("Average VIP Wait Time: " + (((double)(vipLine.totalVIPWaitTime))/vipLine.totalVIPServed));
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
   public int longestWaitTime, longestQueue, totalTime, totalCustomersServed, timeUntilNextServe, totalVIPServed, totalVIPWaitTime;
   private Queue<Customer> customers;
   public Line(Queue<Customer> q){
      longestWaitTime = longestQueue = totalTime = totalCustomersServed = totalVIPServed = totalVIPWaitTime = 0;
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
   public void processVIPQueue(){
      if(timeUntilNextServe == 0){
         timeUntilNextServe = ((int) (Math.random() * 6) + 2);
         if(customers.peek().getMins() > longestWaitTime)
            longestWaitTime = customers.peek().getMins();
         totalVIPWaitTime += customers.remove().getMins();
         totalVIPServed += 1;
      }
      else
         if(!customers.isEmpty())
            timeUntilNextServe -= 1;
      for(Customer c: customers)
         c.incrementMins();
      if(customers.size() > longestQueue)
         longestQueue = customers.size();
   }
   public void addToWait(){
      for(Customer c: customers)
         c.incrementMins();
   }
   public void finishQueue(){
      while(!customers.isEmpty())
         this.processQueue();
   }
   public void finishVIPQueue(ArrayList<Line> lines){
      while(!customers.isEmpty()){
         for(Line l: lines)
            l.addToWait();
         this.processVIPQueue();
      }
   }
   public String toString(){
      return customers.toString();
   }
   public Queue<Customer> customers(){
      return this.customers;
   }
}