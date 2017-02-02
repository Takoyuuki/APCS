  //name:   date: 
import java.util.*;
public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  -->  Postfix  -->  Evaluate");
      /*enter code here  */
      ArrayList<String> infixExp = new ArrayList<String>();
       
      infixExp.add("3+4*5");
      infixExp.add("3*4+5");
      infixExp.add("(4+5)-5*3");
      infixExp.add("(3+4)*(5+6)");
      infixExp.add("(3*(4+5)-2)/5");
      infixExp.add("8+1*2-9/3");
   
   
      for( String s : infixExp )
      {
         String pf = infixToPostfix(s);
         System.out.println(s + "       " + pf + "       " + Postfix.eval(pf));
      }
   }
   public static String infixToPostfix(String infix)
   {
      String result = "";
      Stack<Character> operators = new Stack<Character>();
      for(char c: infix.toCharArray()){
         if(!Postfix.isOperator(c))
            result += c;
         else if(c == '(')
            operators.push(c);
         else if(c == ')'){
            while(operators.peek() != '(')
               result += operators.pop();
            operators.pop();
         }
         else {
            while(!operators.empty() && isLower(c, operators.peek()))
               result += operators.pop();
            operators.push(c);
         }
         
      }
      while(!operators.empty())
         result += operators.pop();
      return result;
   }
	//returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      switch(c1){
         case '+': 
            return !(c2 != '-' && c2 != '/' && c2 != '*');
         case '-': 
            return !(c2 != '+' && c2 != '/' && c2 != '*');
      }
      return false;
   }
}
	
	/*
 Infix  -->  Postfix  -->  Evaluate
 3+4*5       345*+       23
 3*4+5       34*5+       17
 (4+5)-5*3       45+53*-       -6
 (3+4)*(5+6)       34+56+*       77
 (3*(4+5)-2)/5       345+*2-5/       5
 8+1*2-9/3       812*+93/-       7
	*/
