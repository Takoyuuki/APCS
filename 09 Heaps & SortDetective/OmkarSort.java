import java.util.Arrays;
public class OmkarSort {
   public static void main(String[] args){
      int[] a = {1, 2, 4, 3, 6, 5};
      sortArray(a);
      System.out.println(Arrays.toString(a));
   }
   public static void sortArray(int[] a){
      int count = 0;
      while(!inOrder(a)){
         for(int i=0; i < a.length; i++){
            swap(a, i, (int)(Math.random()*a.length));
         }
         count++;
      }
      System.out.println("Number of randomizes: " + count);
   }
   public static boolean inOrder(int[] a){
      for(int i=1; i < a.length; i++)
         if(a[i] < a[i-1]) return false;
      return true;
   }
   public static void swap(int[] a, int b, int c){
      int t = a[b];
      a[b] = a[c];
      a[c] = t;
   }
}