package own.stu.algorithms_4th.string.search;

public class SubStringSearch {

  public static int search_one_2(String text, String pat) {
    int m = text.length();
    int n = pat.length();

    for (int i = 0; i <= m - n; i++) {
      int k = 0;
      for (int j = 0; j < n; j++) {
        if (text.charAt(i + j) == pat.charAt(j)) {
          k++;
          if (k == n - 1) {
            return i;
          }
        } else {
          break;
        }
      }
    }
    return -1;
  }

  public static int search_one(String text, String pat) {
    int m = text.length();
    int n = pat.length();

    for (int i = 0; i <= m - n; i++) {

      for (int j = 0; j < n; j++) {
        if (text.charAt(i + j) != pat.charAt(j)) {
          break;
        }

        if (j == n - 1) {
          return i;
        }
      }
    }
    return -1;
  }

  public static int search_two(String text, String pat) {

    int i, m = text.length();
    int j, n = pat.length();

    for (i = 0, j = 0; i < m && j < n; i++) {
      if (text.charAt(i) == pat.charAt(j)) {
        j++;
      } else {
        i = i - j;
        j = 0;
      }
    }
    if (j == n) {
      return i - j;
    }

    return -1;
  }

  public static void main(String[] args) {

  }
}
