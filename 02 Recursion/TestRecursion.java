public class TestRecursion {
   public static void main(String[] args){
      System.out.println(prepare("LEMONADE"));
   }
   public static String prepare(String s)
   {
      int k = s.length() / 2;
      if(k <= 1)
         return s;
      System.out.println(s);
      return s.charAt(k - 1) +
          prepare(s.substring(0, k - 1) +
                s.substring(k + 1, 2*k)) +
                   s.charAt(k);
   }

}