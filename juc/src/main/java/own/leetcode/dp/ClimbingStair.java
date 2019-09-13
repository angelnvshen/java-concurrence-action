package own.leetcode.dp;

public class ClimbingStair {

  public static int count(int n) {
    int[] num = new int[n + 3];
    num[1] = 1;
    num[2] = 2;
    for (int i = 3; i <= n; i++) {
      num[i] = num[i - 1] + num[i - 2];
    }
    return num[n];
  }

  public static void main(String[] args) {
    System.out.println(count(0));
    System.out.println(count(1));
    System.out.println(count(2));
    System.out.println(count(3));
    System.out.println(count(4));
    System.out.println(count(5));
  }
}
