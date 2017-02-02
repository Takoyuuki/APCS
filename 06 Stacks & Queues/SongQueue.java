///name:    date:
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.next().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         System.out.print(prompt);
         str = infile.next().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      songQueue = new LinkedList<String>();
      Scanner file = new Scanner(new File("songs.txt"));
      while(file.hasNextLine()){
         songQueue.add(file.nextLine().split("-")[0].trim());
      }
   }
   public static void processRequest(String str)
   {
      if(str.trim().equals("A")) add();
      else if(str.trim().equals("P")) play();
      else if(str.trim().equals("D")) delete();
      printSongList();
   }
   public static void add()
   {
      String song = System.console().readLine("Song Name: ");
      songQueue.add(song);
   }
   public static void play()
   {
      if(songQueue.isEmpty())
         System.out.println("No songs in queue!");
      else
         System.out.println("Now Playing: " + songQueue.remove());
   }
   public static void delete()
   {
      if(songQueue.isEmpty())
         System.out.println("No songs in queue!");
      String song = System.console().readLine("Song Name: ");
      if(!songQueue.contains(song))
         System.out.println("Queue does not contain this song!");
      else
      {
         Queue<String> newQueue = new LinkedList<String>();
         while(!songQueue.isEmpty()){
            if(!songQueue.peek().equals(song))
               newQueue.add(songQueue.peek());
            songQueue.remove();
         }
         songQueue = newQueue;
      }
   }
   public static void printSongList()
   {
      if(songQueue.isEmpty())
         System.out.println("Queue is empty!");
      else{
         System.out.println();
         System.out.println(songQueue);
         System.out.println();
      }
   }
}