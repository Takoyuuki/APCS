public class NotChecker implements Checker {
   private Checker checker;
   public NotChecker(Checker check){
      checker = check;
   }
   
   public boolean accept(String text){
      return !(checker.accept(text));
   }
}