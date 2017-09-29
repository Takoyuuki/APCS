//Name:     Date:

public class HeapSort
{
  public static int SIZE;  //9 or 100

  public static void main(String[] args)
  {
    //Part 1: Given a heap, sort it. Do this part first.
    SIZE = 9;
    double heap[] = {-1,99,80,85,17,30,84,2,16,1};
    display(heap);
    sort(heap);
    display(heap);

    //Part 2:  Generate 100 random numbers, make a heap, sort it.
     SIZE = 100;
     heap = new double[SIZE + 1];
     heap = createRandom(heap);
     display(heap);
     makeHeap(heap, SIZE);
     display(heap);
     sort(heap);
     display(heap);
  }

  //******* Part 1 ******************************************
  public static void display(double[] array)
  {
    for(int k = 1; k < array.length; k++)
    System.out.print(array[k] + "    ");
    System.out.println("\n");
  }
  public static void sort(double[] array)
  {
    /* enter your code here */
    int size = SIZE;
    while(size > 2){
      swap(array, 1, size);
      size--;
      heapDown(array, 1, size);
    }
    if(array[1] > array[2])   //just an extra swap, if needed.
    swap(array, 1, 2);
  }
  public static void swap(double[] array, int a, int b)
  {
    double t = array[a];
    array[a] = array[b];
    array[b] = t;
  }
  public static void heapDown(double[] array, int k, int size)
  {
    if(k >= size || k*2 >= size) return;
    int maxChild = k*2;
    if(k*2+1 <= size && array[k*2] < array[k*2+1]) maxChild++;
    if(array[k] < array[maxChild]){
      swap(array, k, maxChild);
      heapDown(array, maxChild, size);
    }
  }

  // ****** Part 2 *******************************************

  //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places)
  public static double[] createRandom(double[] array)
  {
    array[0] = -1;   //because it will become a heap
    for(int i=1; i < array.length; i++){
      array[i] = ((int)(Math.random() * 9900 + 100))/100.0;
    }
    return array;
  }
  //turn the random array into a heap
  public static void makeHeap(double[] array, int size)
  {
    for(int k=size/2; k >= 1; k--) {
      heapDown(array, k, size);
    }
  }
}
