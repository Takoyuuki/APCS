//Author:  Omkar Kulkarni
//Date:    2016-09-30
import java.util.Scanner;
  
public class Hailstone
{
   public static void main(String[] args)
   {
      System.out.println("Hailstone Numbers!");
      System.out.print("Enter the start value: ");
      Scanner sc = new Scanner(System.in);
      int start = sc.nextInt();
      int count = hailstone(start);
      System.out.println(" takes " + count + " steps." );
      int count2 = hailstone(start, 1);
      System.out.println(" takes " + count2 + " steps." );
   }
   //recursive, counts the steps with a variable
   public static int hailstone(int n, int count)
   {
      if(n == 1){
         System.out.print("1");
         return count;
      }
      System.out.print(n + "-");
      if(n % 2 == 0)
         return hailstone(n/2, count+1);
      else
         return hailstone(n*3+1, count+1);
   
   }
	//recursive, counts the steps without a variable
   public static int hailstone(int n)
   {
   if(n == 1){
      System.out.print("1");
      return 1;
   }
   System.out.print(n + "-");
      if(n % 2 == 0)
         return 1 + hailstone(n/2);
      else
         return 1 + hailstone(n*3+1);
   
   }
}