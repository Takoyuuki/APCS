// name:     date:

import java.util.*;
public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter tests here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
       parenExp.add("5+(7*3)");
       parenExp.add("((5+7)*3");
       parenExp.add("[(5+7]*3)");
       parenExp.add("[(5+7)*3])");

      parenExp.add("[(5+7)*3])");
      
                  
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   public static boolean checkParen(String exp)
   {
      Stack stuff = new Stack();
      for(char c : exp.toCharArray()){
         if(right.contains(""+c) && stuff.empty())
            return false;
         else if(left.contains(""+c))
            stuff.push(c);
         else if(right.contains(""+c) && left.indexOf(((char)stuff.peek())) == right.indexOf(c))
            stuff.pop();
      }
      return stuff.empty();
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
