package own.leetcode;

public class PalindromeNumber {

  public static boolean isPalindromeNumber(int num) {

    if (num < 0) {
      return false;
    }

    int div = 1, tmp = num;
    while (tmp / div >= 10) {
      div *= 10;
    }

    while (num != 0) {
      int left = num / div;
      int right = num % 10;
      if (left != right) {
        return false;
      }
      num = (num - left * div) / 10;
      div /= 100;
    }

    return true;
  }


  public static void main(String[] args) {
    System.out.println(isPalindromeNumber(100));
    System.out.println(isPalindromeNumber(101));
    System.out.println(isPalindromeNumber(4321234));
    System.out.println(isPalindromeNumber(-1));
  }
}
