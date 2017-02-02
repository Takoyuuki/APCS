 //name:     date:   
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
     
      SmartCard jimmy = new SmartCard(20.00); //bought with $20.00 
      jimmy.board(center);            		    //boarded in zone 1
      jimmy.disembark(suburbia);					 //disembark in zone 4
      jimmy.disembark(uptown);					 //disembark without having boarded
      jimmy.board(suburbia);
      jimmy.disembark(downtown);
      jimmy.board(center);
      jimmy.disembark(downtown);
      jimmy.board(uptown);
      jimmy.disembark(suburbia);
      jimmy.board(downtown);
      jimmy.disembark(suburbia);
      jimmy.board(center);                      //boarded in zone 1
      jimmy.disembark(suburbia);              //disembark in zone 4
      jimmy.disembark(uptown);                //disembark without having boarded
      jimmy.board(suburbia);
      jimmy.disembark(downtown);
      jimmy.board(center);
      jimmy.disembark(downtown);
      jimmy.board(uptown);
      jimmy.disembark(suburbia);
      jimmy.board(downtown);
      jimmy.disembark(suburbia);
      jimmy.board(uptown);
   	
   	//lots more test cases!				
   }
}
class SmartCard 
{
   private Station boardedStation;
   private boolean boarded;
   private double balance;
   private DecimalFormat decFormat = new DecimalFormat("'$'0.00");

   public SmartCard(double b){
      balance = b;
      boarded = false;
   }

   public void addMoney(double d){
      balance += d;
      System.out.println("New Balance: " + decFormat.format(balance));
   }

   public double getBalance(){
      return balance;
   }

   public boolean isBoarded(){
      return boarded;
   }

   public void board(Station s){
      if (boarded) {
         System.out.println("Already on train!");
      }
      else if (balance < 0.5) {
         System.out.println("You don't have enough to pay the minimum fare!");
      }
      else{
         boarded = true;
         boardedStation = s;
         System.out.println("Boarded at: " + boardedStation.getName());
      }
   }

   public double cost(Station s){
      if (!boarded) {
         return 0;
      }
      double costNeeded = 0.50;
      int zonesSwitched = Math.abs(boardedStation.getZone() - s.getZone());
      costNeeded += (0.75 * zonesSwitched);
      return costNeeded;
   }

   public void disembark(Station s){
      if(!boarded){
         System.out.println("Please see the station manager!");
      }
      else{
         double toDeduct = this.cost(s);
         double checkNegative = balance - toDeduct;
         if (checkNegative < 0) {
            System.out.println("You don't have enough money to pay for your trip. Please see the station manager to refill your card.\nYour current balance is " + decFormat.format(balance) + ". You would have " + decFormat.format(checkNegative) + " after this trip.\nPlease note you have not been marked as having disembarked at " + s.getName() + ".");
         }
         else {
            balance -= toDeduct;
            boarded = false;
            System.out.println("From " + boardedStation.getName() + " to " + s.getName() + " costs " + decFormat.format(toDeduct) + "\nBalance: " + decFormat.format(balance));
            boardedStation = null;
         }
      }
   }
}
class Station
{
   private String name;
   private int zone;

   public Station(String n, int z){
      name = n;
      zone = z;
   }

   public String getName(){
      return name;
   }

   public int getZone(){
      return zone;
   }

   public String toString(){
      return name;
   }
}

 
