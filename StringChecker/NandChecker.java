public class NandChecker implements Checker {
   
   private Checker checkerOne, checkerTwo;
   
   public NandChecker(Checker one, Checker two){
      checkerOne = one;
      checkerTwo = two;
   }
   
   public boolean accept(String text){
      return !(checkerOne.accept(text) && checkerTwo.accept(text));
   }
}