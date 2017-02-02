public class SubstringChecker implements Checker {
   private String keyword;
   public SubstringChecker(String key){
      keyword = key;
   }
   
   public boolean accept(String text){
      return text.contains(keyword);
   }
}