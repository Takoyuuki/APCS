//name:     date:
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter tests here  */  
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");  
      
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      Stack<Integer> integers = new Stack<Integer>();
      for(char c : postfix.toCharArray())
         if(!isOperator(c))
            integers.push(Integer.parseInt(""+c));
         else
            integers.push(eval(integers.pop(), integers.pop(), c));
      return integers.pop();
   }
   public static int eval(int b, int a, char ch)
   {
      switch(ch) {
         case '+': 
            return a + b;
         case '-': 
            return a - b;
         case '/': 
            return a / b;
         case '*': 
            return a * b;
      }
      return 0;   
   }
   public static boolean isOperator(char ch)
   {
      return "()+-*/".contains(""+ch);
   }
}

/*
 Postfix  -->  Evaluate
 345*+		23
 34*5+		17
 45+53*-		-6
 34+56+*		77
 345+*2-5/		5
 812*+93/-		7  
 */