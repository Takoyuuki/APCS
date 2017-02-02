//Name:
//Date:
import java.util.Arrays;
public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      display(tally);
      int[] modes = calculateModes(tally);
      display(modes);
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
   }
   public static int[] calculateModes(int[] tally)
   {
      int max = findMax(tally);
      int[] modes = new int[0];
      for (int i = 0; i < tally.length; i++) {
         if (tally[i] == max) {
            modes = addElement(modes, i);
         } 
      } 
      return modes;
   }

   private static int[] addElement(int[] array, int newInt){
      array = Arrays.copyOf(array, array.length + 1);
      array[array.length - 1] = newInt;
      return array;
   }

   public static int kthDataValue(int[] tally, int k)
   {
      for (int i = 0; i < tally.length; i++ ) {
         k -= tally[i];
         if (k <= 0) {
            return i;
         }
      }
      return -1;
   }
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   public static void display(int[] args)
   {
      for(int k = 0; k < args.length; k++)
         System.out.print(args[k] + " ");
      System.out.println();
      System.out.println();
   }
}
