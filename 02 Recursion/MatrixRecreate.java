// Name:   Date:

public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.display(matrix, rowcount, colcount);
      TheMatrix.re_create(rowcount, colcount);
      TheMatrix.display(TheMatrix.getRecreatedMatrix(), rowcount, colcount);
   }
}
class TheMatrix
{
	//do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix;
   
   public static int[][] getRecreatedMatrix()
   {
      return recreatedMatrix;
   }
   public static int[][] create()
   {
      int r = (int)(Math.random()*5+2);
      int c = (int)(Math.random()*5+2);
      int[][] m = new int[r][c];
      for(int i = 0; i < m.length; i++){
         for (int j = 0; j < m[i].length ; j++) {
            m[i][j] = (int)(Math.random() * 2);
         }
      }
      return m;
   }
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
      for (int i = 0; i < rowcount.length; i++ ) {
         for (int j = 0; j < colcount.length; j++ ) {
            if(matrix[i][j] == 1){
               rowcount[i]++;
               colcount[j]++;
            } 
         }
      }
   }
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {  
      System.out.print("    ");
      for(int i = 0; i < colcount.length; i++){
         System.out.print(colcount[i] + " ");
      }
      System.out.println();
      for(int i = 0; i <= (colcount.length - 1) * 4; i++){
         System.out.print("-");
      }
      System.out.println();
      for(int i = 0; i < rowcount.length; i++){
         String toPrint = "";
         toPrint += rowcount[i] + " | ";
         for(int j = 0; j < colcount.length; j++){
            toPrint += matrix[i][j] + " ";
         }
         System.out.println(toPrint);
      }
      System.out.println();
   }
	//should call recur.
   public static void re_create(int[] rowcount, int[] colcount)
   {
      int[][] m = new int[rowcount.length][colcount.length];
      for(int i = 0; i < m.length; i++){
         for(int j = 0; j < m[i].length; j++){
            m[i][j] = 0;
         }
      }
      count(m, rowcount, colcount);
      recur(m, rowcount, colcount, 0, 0);
   }
   private static void recur(int[][] m, int[] rowcount, int[] colcount, int row, int col)
   {
      if(compare(m, rowcount, colcount))    //base case: if new matrix works, then copy over to recreatedMatrix
      {
      	//copy over from m to recreatedMatrix (not just references)
         recreatedMatrix = new int[m.length][];
         for(int i = 0; i < m.length; i++)
         {
            recreatedMatrix[i] = new int[m[i].length];
            for (int j = 0; j < m[i].length; j++)
            {
               recreatedMatrix[i][j] = m[i][j];
            }
         }    //we're done!
         
      }
      if(row >=0 && row < m.length){
         if(col>= 0 && col < m[row].length){
            m[row][col] = 0;
            recur(m, rowcount, colcount, row, col+1);
            m[row][col] = 1;
            recur(m, rowcount, colcount, row, col+1);
         }
         else{
            recur(m, rowcount, colcount, row+1, 0);
         }
      }
   	//ENTER YOUR PERMUTATION CODE HERE            
     
   }
   private static boolean compare(int[][] m, int[] rowcount, int[] colcount)
   {
      int[] originalrcount = new int[rowcount.length];
      int[] originalccount = new int[colcount.length];
      count(m, originalrcount, originalccount);
      for(int i = 0; i < rowcount.length; i++){
         if(rowcount[i] != originalrcount[i])
            return false;
      }
      for(int i = 0; i < colcount.length; i++){
         if(colcount[i] != originalccount[i])
            return false;
      }
      return true;
   }
}
