   //Name:     Date:
import java.io.*;
import java.util.*;
public class Dictionary
{
   public static void main(String[] args)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
        System.exit(1);
      }

      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);

      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
     Map<String, Set<String>> m = new TreeMap<String, Set<String>>();
     while(infile.hasNext()){
       add(m, infile.next(), infile.next());
     }
     return m;
   }
   private static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
     Set s = dictionary.get(word);
     if(s == null){
       s = new HashSet<String>();
     }
     s.add(translation);
     dictionary.put(word, s);

   }
   public static void display(Map<String, Set<String>> m)
   {
     Iterator it = m.entrySet().iterator();
     while(it.hasNext()){
       Map.Entry pair = (Map.Entry) it.next();
       System.out.println(pair.getKey() + " " + pair.getValue());
     }
     System.out.println();
   }
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
     Map<String, Set<String>> m = new TreeMap<String, Set<String>>();
     Iterator it = dictionary.entrySet().iterator();
     while(it.hasNext()){
       Map.Entry pair = (Map.Entry) it.next();
       HashSet<String> set = (HashSet<String>)pair.getValue();
       for(String s: set){
         add(m, s, (String)pair.getKey());
       }
     }
     return m;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  ***********************************
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/
