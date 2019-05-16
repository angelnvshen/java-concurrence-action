package own.stu.algorithms_4th.string;

public class LSD {

  /**
   * Rearranges the array of W-character strings in ascending order.
   */
  public static void sort(String[] a, int w) {
    sort(a, w, null);
  }

  public static void sort(String[] a, int w, Integer alphabetSize) {
    int n = a.length;
    int R;
    if (alphabetSize == null) {
      R = 256;   // extend ASCII alphabet size
    } else {
      R = alphabetSize;
    }
    String[] aux = new String[n];

    for (int d = w - 1; d >= 0; d--) {
      int[] count = new int[R + 1];
      // compute frequency counts
      for (int i = 0; i < n; i++) {
        count[a[i].charAt(d) + 1]++;
      }
      // compute cumulates
      for (int i = 0; i < R; i++) {
        count[i + 1] += count[i];
      }
      // move data
      for (int i = 0; i < n; i++) {
        aux[count[a[i].charAt(d)]++] = a[i];
      }
      // copy back
      for (int i = 0; i < n; i++) {
        a[i] = aux[i];
      }
    }
  }
}
