//Ms. Galanos aka @cscheerleader
//version 12.6.2016

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.2.jar
import java.io.*;
import java.util.*;

public class Twitter_Driver
{
   private static PrintStream consolePrint;
   
   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out;
      
      // PART 1
      // set up classpath and properties file
             
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
      // create message to tweet, then call the tweetOut method
      //String message = "dank memes";
      //bigBird.tweetOut(message);
     
      // PART 2
      // Choose a public Twitter user's handle 
         
      Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
   //          
      while (!twitter_handle.equals("done"))
      {
      //          // Print the most popular word they tweet
         bigBird.investigate(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord());
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      }
         
         
         
   }//end main         
         
}//end driver        
         
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> sortedTerms;
   
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      twitter = TwitterFactory.getSingleton(); //connects to Twitter and performs authorizations
      consolePrint = console;
      statuses = new ArrayList<Status>();
      sortedTerms = new ArrayList<String>();   
   }
   
   /******************  Part 1 *******************/
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
   }
   @SuppressWarnings("unchecked")
   /******************  Part 2 *******************/
   public void makeSortedListOfWordsFromTweets(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      sortedTerms.clear();
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); // Creates file for dedebugging purposes
      Paging page = new Paging(1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      int numberTweets = statuses.size();
      fileout.println("Number of tweets = " + numberTweets);
      
      fileout = new PrintStream(new FileOutputStream("garbageOutput.txt"));
   
      int count=1;
      for (Status j: statuses)
      {
         fileout.println(count+".  "+j.getText());
         count++;
      }		
         	
     	//Makes a list of words from user timeline
      for (Status status : statuses)
      {			
         String[] array = status.getText().split(" ");
         for (String word : array)
            sortedTerms.add(removePunctuation(word).toLowerCase());
      }	
   					
      // Remove common English words, which are stored in commonWords.txt
      sortedTerms = removeCommonEnglishWords(sortedTerms);
      sortAndRemoveEmpties();
      
   }
   
   // Sort words in alpha order. You should use your old code from the Sort/Search unit.
   // Remove all empty strings ""
   @SuppressWarnings("unchecked")
   private void sortAndRemoveEmpties()
   {
      Sorter.sort(sortedTerms);
      for(int i=0;i<sortedTerms.size();i++){
         if(sortedTerms.get(i).trim().equals("")){
            sortedTerms.remove(i);
            i--;
         }
      }
   }
      
   // Removes common English words from list
   // Remove all words found in commonWords.txt  from the argument list.
   // The count will not be given in commonWords.txt. You must count the number of words in this method.
   // This method should NOT throw an exception. Use try/catch.
   @SuppressWarnings("unchecked")
   private List removeCommonEnglishWords (List<String> list)
   {	
      try{
         Scanner infile = new Scanner(new File("commonWords.txt"));
         ArrayList<String> commonWords = new ArrayList<String>();
         while(infile.hasNext())
            commonWords.add(infile.next());
         for(int i=0; i < list.size(); i++){
            if(commonWords.contains(list.get(i)) || commonWords.contains(list.get(i).toUpperCase())){
               list.remove(i);
               i--;
            }
         }
         return list;
      }
      catch(IOException e){
         System.exit(1);
      }
      return null;
   }
   
   //Remove punctuation - borrowed from prevoius lab
   //Consider if you want to remove the # or @ from your words. They could be interesting to keep (or remove).
   private String removePunctuation( String s )
   {
      return s.replaceAll("[\\.\\?\\,\\!\\:\\;\"\\(\\)\\{\\}\\[\\]\\<\\>\\']", "");
   }
   //Should return the most common word from sortedTerms. 
   //Consider case. Should it be case sensitive? The choice is yours.
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      String popular = sortedTerms.get(0);
      String current = sortedTerms.get(0);
      int maxCount = 1;
      int count = 1;
      for(String word: sortedTerms){
         if(word.equals(current)){
            count++;
         }
         else{
            if(maxCount <= count){
               popular = current;
               maxCount = count;         
            }
            current = word;
            count = 1;
         }
      }
      System.out.println("Most common word occurred " + maxCount + " times.");
      return popular;
   }
   

   /******************  Part 3 *******************/
   public void investigate (String handle)throws TwitterException
   {
      statuses.clear();
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
   
      System.out.println("Number of Sentences: " + numSentences(statuses));
      System.out.println("Number of Words: " + numWords(statuses));
      System.out.println("Number of Syllables: " + numSyllables(statuses));
      System.out.println("Flesch-Kincaid Grade Level: " + calcGrade(statuses));
      System.out.println("--------------------------------------------------------");
   } 
   private int numSentences(List<Status> array){
      //periods, ?, new lines, !
      int count = 0;
      for(Status s : array){
         String[] t = s.getText().split("[\\.|\\?|\\n|\\!]");
         int numBlank = 0;
         for(String p : t)
            if(p.trim().equals("")){numBlank++;}
         count += t.length-numBlank;
      }
      return count;
   }
   private int numSyllables(List<Status> array){
      //periods, ?, new lines, !
      String vowel = "aeioyu";
      int count = 0;
      for(Status s : array){
         String[] a = s.getText().split(" ");
         int co = 0;
         for(String p : a){
            char cur = 'b';
            co = 0;
            for(char c : p.toCharArray()){
               if(vowel.contains(c+"") && (!vowel.contains(cur+"") || !Character.isLetter(cur))){
                  co++;
               }
               if(!Character.isLetter(c) && cur == 'e'){
                  co--;
               }
               cur = c;
            }  
            if(p.length()-1 >= 0 && p.charAt(p.length()-1) == 'e'){
               co--;
            }
            if(co == 0){
               co++;
            }
            count+= co;
         }
      }
      return count;
   }
   private int numWords(List<Status> array){
      int count = 0;
      for(Status s : array){
         String[] t = s.getText().split("[\\n||' ']");
         int numBlank = 0;
         for(String p : t)
            if(p.trim().equals("")){numBlank++;}
         count += t.length-numBlank;
      }
      return count;
   }
   private double calcGrade(List<Status> array){
      double words = numWords(array)*1.0;
      double sentences = numSentences(array)*1.0;
      double syllables = numSyllables(array)*1.0;
      double result = 0.39*(words/sentences) + 11.8*(syllables/words) - 15.59;
      return Math.round(result*100.0)/100.0;
   }
   // A sample query to determine how many people in Arlington, VA tweet about the Miami Dolphins
 }  

// Consider adding a sorter class here
class Sorter {

   public static void sort(List<String> array)
   {
      int maxPos;
      for(int k = 0; k < array.size(); k++)		
      {
         maxPos = findMax(array, array.size() - k - 1);
         swap(array, maxPos, array.size() - k - 1);
      }
   }
   public static int findMax(List<String> array, int upper)
   {
      int maxPos = 0;
      for(int j = 1; j <= upper; j++)			
         if(array.get(j).compareTo(array.get(maxPos)) > 0)	
            maxPos = j;					
      return maxPos;
   }
   public static void swap(List<String> array, int a, int b)
   {
      String temp = array.get(a);				
      array.set(a, array.get(b));
      array.set(b, temp);
   }
}