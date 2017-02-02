//name: Omkar Kulkarni     date:  September 17, 2016
import java.util.*;
import java.util.regex.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) throws IOException
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println("***** " + p + " *****");
      }		
   }
   public static String pig(String s)
   {
      String newString = "";
      Pattern startPunct = Pattern.compile("^([\\-\\.\\?\\,\\!\\:\\;\"\\(\\)\\{\\}\\[\\]\\<\\>]+)");
      Pattern endPunct = Pattern.compile("([\\-\\.\\?\\,\\!\\:\\;\"\\(\\)\\{\\}\\[\\]\\<\\>]+)$");
      
      Matcher startPunctMatcher = startPunct.matcher(s);
      Matcher endPunctMatcher = endPunct.matcher(s);
      String startPunctString = "";
      String endPunctString = "";
      String firstVowel = "";
      boolean isCapital = false;
      while(startPunctMatcher.find()){
         startPunctString = startPunctMatcher.group();
      }
      while(endPunctMatcher.find()){
         endPunctString = endPunctMatcher.group();
      }
      s = s.substring(startPunctString.length());
      s = s.substring(0, s.length() - endPunctString.length());
      Pattern vowels = Pattern.compile("([aeiouyAEIOUY])");
      Matcher vowelMatcher = vowels.matcher(s);
      while(vowelMatcher.find()){
         firstVowel = vowelMatcher.group();
         if(firstVowel.toLowerCase().matches("y") && s.indexOf(firstVowel) == 0)
            continue;
         break;
      }
      firstVowel = firstVowel.toLowerCase();
      if(firstVowel == "")
         return "INVALID";
      if(Character.isUpperCase(s.charAt(0))){
         isCapital = true;
         s = s.substring(0, 1).toLowerCase() + s.substring(1);
      }
      int firstVowelIndex = s.toLowerCase().indexOf(firstVowel);
      if(firstVowelIndex == 0){
         if(isCapital)
            newString = startPunctString + s.substring(0, 1).toUpperCase() + s.substring(1) + "way" + endPunctString;
         else
            newString = startPunctString + s + "way" + endPunctString;
      }
      else {
         if(s.charAt(firstVowelIndex) == 'u' && s.charAt(firstVowelIndex - 1) == 'q')
            firstVowelIndex++;
         String partOne = s.substring(firstVowelIndex, s.length());
         String partTwo = s.substring(0, firstVowelIndex);
         newString += startPunctString;
         if(isCapital)
            newString += partOne.substring(0, 1).toUpperCase() + partOne.substring(1);
         else
            newString += partOne;
         newString += partTwo;
         newString += "ay";
         newString += endPunctString;
      }
      return newString;
   }

   public static void part_2_using_piglatenizeFile() throws IOException 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input Filename (Including .txt)? Example: PigLatin.txt:");
      String filename = sc.next();
      Scanner infile = new Scanner(new File(filename));  //PigLatin.txt
      System.out.print("Output Filename (Including .txt)? Example: PigLatinOut.txt:");
      String filenameOut = sc.next();
      piglatenizeFile( infile, filenameOut );
      System.out.println("Piglatin done!");
      sc.close();
   }
   /****************************** 
   *  take in a filename, and create a file that is the inputted file
   *  fully piglatenized. 
   *
   *  Note: filename will have .txt on it.
   ******************************/
   public static void piglatenizeFile(Scanner infile, String filename) throws IOException
   {
      try {
         PrintStream outputFile = new PrintStream(new FileOutputStream(filename));
         while(infile.hasNextLine()){
            String line = infile.nextLine();
            String[] words = line.split(" ");
            for(int i = 0; i < words.length; i++)
               if(!words[i].equals(""))
                  outputFile.print(pig(words[i].trim()) + " ");
            outputFile.println();
         }
      }
      catch(IOException e){
         System.out.println("Invalid File!");
      }
   }
}
