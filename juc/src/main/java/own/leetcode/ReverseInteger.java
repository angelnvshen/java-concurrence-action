package own.leetcode;

/**
 * 123 -> 321
 *
 * -123 -> -321
 *
 * overflow -> 0
 */
public class ReverseInteger {

  public static int reverse(int x) {
    int rev = 0;

    while (x != 0) {
      int newRev = rev * 10 + x % 10;
      if ((newRev - x % 10) / 10 != rev) {
        return 0;
      }
      rev = newRev;
      x = x / 10;
    }

    return rev;
  }

  public static void main(String[] args) {
    System.out.println(reverse(123));
    System.out.println(reverse(-123));
    System.out.println(reverse(Integer.MAX_VALUE));
  }
}
