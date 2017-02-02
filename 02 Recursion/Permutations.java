//name:  Omkar Kulkarni            date: 9/28/2016
import java.util.Scanner;
public class Permutations
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      leftRight("", n);
      oddDigits("", n);
      superprime(n);
   }
   
   public static void leftRight(String s, int n)
   {
      if(s.length() < n){
         leftRight("L" + s, n);
         leftRight("R" + s, n);
      }
      else{
         System.out.println(s);
      }
        
   }
   
   
   public static void oddDigits(String s, int n)
   {
      if(s.length() == n){
         System.out.println(s);
         return;
      }
      oddDigits("1"+s, n);
      oddDigits("3"+s, n);
      oddDigits("5"+s, n);
      oddDigits("7"+s, n);
      oddDigits("9"+s, n);
     
   }
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }
   private static void recur(int k, int n)
   {
      if(isPrime(k)){
         String s = "" + k;
         if(s.length() == n){
            System.out.println(k);
            return;
         }
         for(int i = 0; i < 10; i++)
            recur(k*10+i, n);  
      }
      
     
   }
   private static boolean isPrime(int n)
   {
      if(n > 2 && (n&1) == 0)
         return false;
      for(int i=3; i*i <= n; i += 2)
         if (n%i == 0) 
            return false;
      return true;
   }
}
