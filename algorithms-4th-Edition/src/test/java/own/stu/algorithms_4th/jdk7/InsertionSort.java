package own.stu.algorithms_4th.jdk7;

public class InsertionSort {

  public static void sort(Object[] dest, int low, int high) {
    for (int i = low; i < high; i++)
      for (int j = i; j > low && ((Comparable) dest[j - 1]).compareTo((Comparable) dest[j]) > 0; j--)
        swap(dest, j, j - 1);
  }

  private static void swap(Object[] dest, int j, int i) {
    Object o = dest[j];
    dest[j] = dest[i];
    dest[i] = o;
  }

  public static void main(String[] args) {
    Object [] dest = new Object[]{11,23,4,51,7,889,21};
    for (Object o : dest)
      System.out.print(o + " ");

    /*sort(dest, 1, dest.length-1);*/
    Object[] aux = dest.clone();

    aux[aux.length - 1] = 22;

    System.out.println();
    for (Object o : dest)
      System.out.print(o + " ");

    System.out.println();
    for (Object o : aux)
      System.out.print(o + " ");
  }

}
