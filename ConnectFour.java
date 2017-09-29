import java.util.*;
import java.util.stream.Collectors;
public class ConnectFour {
   public static void main(String[] args){
      char[][] m = {{'O', 'X', 'X', 'X'},{'O', 'O', 'X', 'O'},{' ', 'X', 'O', 'X'},{'X', ' ', 'O', ' '}};
      System.out.println(check(m));
   }
   public static char check(char[][] g){
      for(char[] row : g) if(String.valueOf(row).contains("XXXX")) return 'X';
      for(char[] row : g) if(String.valueOf(row).contains("OOOO")) return 'O';
      for(int i = 0; i < g[0].length; i++){
         final int f = i;
         if(Arrays.stream(g).map(x -> x[f]).collect(Collectors.toList()).toString().contains("X, X, X, X")) return 'X';
         if(Arrays.stream(g).map(x -> x[f]).collect(Collectors.toList()).toString().contains("O, O, O, O")) return 'O';
      }
      for(int i = -1*Math.max(g.length, g[0].length); i < Math.max(g.length, g[0].length); i++){
         String s = "";
         String p = "";
         for(int r = 0; r < g.length; r++) for(int c = 0; c < g[0].length; c++) if(r+i == c) s += g[r][c];
         for(int r = 0; r < g.length; r++) for(int c = 0; c < g[0].length; c++) if(r+c == i+Math.max(g.length, g[0].length)) p += g[r][c];
         if(s.contains("XXXX") || p.contains("XXXX")) return 'X';
         if(s.contains("OOOO") || p.contains("OOOO")) return 'O';
      }
      return ' ';
   }
}