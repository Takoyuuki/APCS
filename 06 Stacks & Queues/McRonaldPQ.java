import java.util.*;
public class McRonaldPQ {
   public static void main(String[] args) {
      Map<String, Integer> stats = new HashMap<String, Integer>();
      for(String s: Arrays.asList("10", "11", "12", "20", "21", "22", "30", "31", "32", "40", "41", "42")) stats.put(s, 0);
      PriorityQueue<Customer> q = new PriorityQueue<Customer>();
      for(int i = 0; i < 1079 || !q.isEmpty(); i++) runLoop(q, stats, (i < 1079 && Math.random() < 0.2), i);
      System.out.println("Customer\t\tTotal Served\t\t\t\tLongest Wait\t\t\t\tAverage Wait");
      System.out.println("Senior\t\t\t" + stats.get("10") + "\t\t\t\t\t" + stats.get("12") + "\t\t\t\t\t" + (((double)stats.get("11"))/stats.get("10")));
      System.out.println("Junior\t\t\t" + stats.get("20") + "\t\t\t\t\t" + stats.get("22") + "\t\t\t\t\t" + (((double)stats.get("21"))/stats.get("20")));
      System.out.println("Sophomore\t\t" + stats.get("30") + "\t\t\t\t\t" + stats.get("32") + "\t\t\t\t\t" + (((double)stats.get("31"))/stats.get("30")));
      System.out.println("Freshman\t\t" + stats.get("40") + "\t\t\t\t\t" + stats.get("42") + "\t\t\t\t\t" + (((double)stats.get("41"))/stats.get("40")));
   }
   public static void runLoop(Queue<Customer> q, Map<String, Integer> stats, boolean addUser, int current){
      if(addUser) q.add(new Customer(current));
      if(!q.isEmpty() && q.peek().serveTime-- == -1){
         stats.put((q.peek().gradYear + "0"), stats.get((q.peek().gradYear+ "0"))+1);
         stats.put((q.peek().gradYear + "1"), stats.get((q.peek().gradYear+ "1")) + (current-q.peek().timeArrived));
         if(stats.get((q.peek().gradYear+ "2")) < (current-q.peek().timeArrived)) stats.put((q.peek().gradYear+ "2"), (current-q.peek().timeArrived));
         q.remove();
      }
   }
}
class Customer implements Comparable<Customer> {
   public int timeArrived, gradYear = ((int)(Math.random() * 4) + 1), serveTime = (int)(Math.random() * 6) + 2;
   public Customer(int t) {
      timeArrived = t;
   }
   public int compareTo(Customer c){
      return gradYear - c.gradYear;
   }
}
