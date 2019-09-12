package own.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长子序列 长度 1 3 2 3 1 4 -> 1 2 3 4 -> result(4)
 */
public class LongestOfLIS {

  // stack -> stack[i] 代表 所有最长子序列i+1的最后一个元素的最小值。
  // stack 的长度 即为最长子序列的长度
  public static int longestOfLIS_2(int arrays[]) {
    List<Integer> list = new ArrayList<>();

    if (arrays.length == 0) {
      return 0;
    }
    list.add(arrays[0]);

    for (int i = 1; i < arrays.length; i++) {
      if (list.get(list.size() - 1) < arrays[i]) {
        list.add(arrays[i]);
      } else {
        for (int j = 0; j < list.size(); j++) {
          if (list.get(j) > arrays[i]) {
            list.set(j, arrays[i]);
            break;
          }
        }
      }
    }

    return list.size();
  }

  // dp -> dp[i]表示 以array[i]结尾的最长子序列的长度。 result 即 dp[0] -> dp[i] 中最大值
  public static int longestOfLIS(int arrays[]) {

    if (arrays.length == 0) {
      return 0;
    }

    int dp[] = new int[arrays.length]; //已 arrays中每一个元素结尾的 最长子序列的长度；
    dp[0] = 1;
    int longest = 1;
    for (int i = 1; i < arrays.length; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arrays[i] > arrays[j] && dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
        }
      }
      if (longest < dp[i]) {
        longest = dp[i];
      }
    }

    return longest;
  }

  public static void main(String[] args) {
    int[] arrays = new int[]{1, 3, 2, 3, 1, 4};
    System.out.println(longestOfLIS(arrays));
    System.out.println(longestOfLIS_2(arrays));
  }
}
