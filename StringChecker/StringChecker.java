public class StringChecker{
   public static void main(String[] args){
      Checker aChecker = new SubstringChecker("artichokes");
      Checker kChecker = new SubstringChecker("kale");
      Checker yummyChecker;
      yummyChecker = new AndChecker(new NotChecker(aChecker), new NotChecker(kChecker));
      print(yummyChecker.accept("chocolate truffles"));
      print(yummyChecker.accept("kale is great"));
      print(yummyChecker.accept("Yuck: artichokes & kale"));
      
   }
   
   public static void print(Object thing){
      System.out.println(thing);
   }
}