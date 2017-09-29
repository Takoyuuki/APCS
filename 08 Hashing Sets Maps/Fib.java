//Name:       Date:
   import java.util.*;

    public class Fib
   {
      public static final int DEFAULT = 40;

       public static void main(String[] args)
      {
         int n = DEFAULT;
         System.out.println("Recursive");
         calculate(new Fib1(), n);
         System.out.println("Iterative, stored in an array");
         calculate(new Fib2(), n);
         System.out.println("Recursive, stored in an arrayList");
         calculate(new Fib3(), n);
         System.out.println("Recursive, stored in a hashMap");
         calculate(new Fib4(), n);
      }

       public static void calculate(Fibber fibber, int n)
      {
         long start = System.nanoTime();
         int f = fibber.fib(n);
         long finish = System.nanoTime();
         long time = finish - start;

         System.out.print("fib(" + n + ") = " + f);
         System.out.println(" (" + time + "nanoseconds)");
         System.out.println();
      }

       private static class Fib1 implements Fibber
      {
          public int fib(int n)
         {
            if(n == 1 || n == 2)
               return 1;
            else
               return fib(n - 1) + fib(n - 2);
         }
      }
       private static class Fib2 implements Fibber
      {
        private int[] a;
        public Fib2(){
          a = new int[DEFAULT];
          a[0] = 1;
          a[1] = 1;
          for(int i=2; i<a.length; i++){
            a[i] = a[i-1] + a[i-2];
          }
        }
        public int fib(int n){
          return a[n-1];
        }

      }
       private static class Fib3 implements Fibber
      {
        private ArrayList<Integer> a;
        public Fib3(){
          a = new ArrayList<Integer>();
          a.add(1);
          a.add(1);
        }
        public int fib(int n){
          System.out.println(n);
          if(a.size() >= n){
            return a.get(n-1);
          }
          a.add(fib(n-2) + fib(n-3));
          return a.get(n-1);
        }

      }
       private static class Fib4 implements Fibber
      {
        private HashMap<Integer, Integer> m;
        public Fib4(){
          m = new HashMap<Integer, Integer>();
          m.put(1, 1);
          m.put(2, 1);
        }
        public int fib(int n){
          if(m.get(n) != null){
            return m.get(n);
          }
          m.put(n, fib(n-1) + fib(n-2));
          return m.get(n);
        }

      }

       private interface Fibber
      {
          public abstract int fib(int n);
      }
   }
   	/*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)

    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)

    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)

    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)

   	*/
