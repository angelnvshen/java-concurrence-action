package own.leetcode.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MakeSquareBitOperateImpl {

  public static boolean makeSquare(int[] nums) {

    if (nums.length < 4) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();
    if (sum % 4 != 0) {
      return false;
    }

    int target = sum / 4;
    int all = 1 << nums.length;

    List<Integer> ok_sub = new ArrayList<>();
    List<Integer> ok_half = new ArrayList<>();

    for (int i = 0; i < all; i++) {
      int tmpSum = 0;
      for (int j = 0; j < nums.length; j++) {
        if ((i & (1 << j)) != 0) {
          tmpSum += nums[j];
        }
      }
      if (tmpSum == target) {
        ok_sub.add(i);
      }
    }

    for (int i = 0; i < ok_sub.size(); i++) {
      for (int j = i + 1; j < ok_sub.size(); j++) {
        if ((ok_sub.get(i) & ok_sub.get(j)) == 0) {
          ok_half.add(ok_sub.get(i) | ok_sub.get(j));
        }
      }
    }

    for (int i = 0; i < ok_half.size(); i++) {
      for (int j = i + 1; j < ok_half.size(); j++) {
        if ((ok_half.get(i) & ok_half.get(j)) == 0) {
          return true;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 1, 2, 3};
    System.out.println(makeSquare(nums));
  }
}
