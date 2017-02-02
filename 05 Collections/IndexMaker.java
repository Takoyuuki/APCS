//  Name: Omkar Kulkarni     date:
//  This program takes a text file, creates an index (by line numbers)
//  for all the words in the file and writes the index
//  into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   
   /** calls foundOrInserted, which returns an index.  At that position,  
   updates that IndexEntry's list of line numbers with num. */
   public void addWord(String word, int num)
   {
      int index = foundOrInserted(word);
      this.get(index).add(num);
   }
      
    /** extracts all the words from str, skipping punctuation and whitespace 
    and for each word calls addWord(word, num).  A good way to skip punctuation 
    and whitespace is to use String's split method, e.g., split("[., \"!?]") */
   public void addAllWords(String str, int num) 
   {
      for(String word: str.split("[., \"!?]"))
         if(!word.trim().equals(""))
            addWord(word.trim(), num);
   }
      
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      for(int i = 0; i < this.size(); i++){
         if(this.get(i).getWord().compareTo(word.toUpperCase()) == 0)
            return i;
         else if(this.get(i).getWord().compareTo(word.toUpperCase()) > 0){
            this.add(i, new IndexEntry(word));
            return i;
         }
         else{
            continue;
         }
      }
      this.add(new IndexEntry(word));
      return this.size()-1;
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numsList;
  
   
     //constructors
   public IndexEntry(String w){
      word = w.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
   
   
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(!numsList.contains(num))
         numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
   
   public int compareTo(IndexEntry x){
      return this.word.compareTo(x.getWord());
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String s = "";
      s += word;
      s += " ";
      for(int x : numsList)
         s += (x + ", ");
      return s.substring(0, s.length()-2);
   }
}

