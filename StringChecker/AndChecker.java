public class AndChecker implements Checker {
   
   private Checker checkerOne, checkerTwo;
   
   public AndChecker(Checker one, Checker two){
      checkerOne = one;
      checkerTwo = two;
   }
   
   public boolean accept(String text){
      return (checkerOne.accept(text) && checkerTwo.accept(text));
   }
}