package own.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FakeTest {

    @Test
    public void test1() {
        System.out.println(calculateMinimumHP(new int[][]{
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        }));

        /*System.out.println(calculateMinimumHP(new int[][]{
                {0, 5},
                {-2, -3}
        }));*/
    }

    public String minWindow(String s, String t) {

        if (s == null || s.length() == 0) return "";
        if (t == null || t.length() == 0) return "";
        int n = s.length(), m = t.length();
        if (n < m) return "";

        int left = 0, right = 0, L = 0, R = 0, max = n + 1;
        int count = 0;

        char c = ' ';
        // num ： count
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //双指针
        for (left = 0; left < n; left++) {
            while (right < n && count < m) {
                c = s.charAt(right);
                map.put(c, map.getOrDefault(c, 0) - 1);
                if (map.get(c) >= 0) {
                    count += 1;
                }
                right++;
            }
            if (count >= m) {
                System.out.println(left + ", " + right);
                if (max > right - left) {
                    max = right - left;
                    L = left;
                    R = right;
                }

                // left ++
                c = s.charAt(left);
                map.put(c, map.get(c) + 1);
                if (map.get(c) > 0) {
                    count -= 1;
                }
            }
        }

        return s.substring(L, R);
    }

    public int numDecodings(String s) {
        // write your code here
        if (s == null || s.length() == 0) return 0;
        // dp[i] 表示前i个数字所有够编码的方式
        // dp[i] = dp[i-1]s[i - 1]对应一个字母 +
        //          dp[i - 2] s[i - 2][i-1]对应一个字母

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;//空串有一个中解密方式
        int temp = 0;
        char constant = '0';

        for (int i = 1; i <= n; i++) {
            temp = s.charAt(i - 1) - constant;
            if (temp >= 1 && temp <= 9) { //s[i - 1]对应一个字母
                dp[i] = dp[i - 1];
            }

            if (i >= 2) {
                temp += (s.charAt(i - 2) - constant) * 10;
                if (temp >= 10 && temp <= 26) {//s[i - 2][i-1]对应一个字母
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[n];
    }

    public int longestIncreasingContinuousSubsequence(int[] A) {
        // write your code here
        // 1 3 2 5 4 9 8
        // dp[i] 表示以i结尾的最长序列,默认 dp[i] = 1, 它自己就是一个自序列
        // dp[i] = Math.max(dp[j]), A[i - 1] < A[i] // core: 连续，只需要和前一个元素比较
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        int[][] dp = new int[n][2]; //dp[i][0] 递增 dp[i][i] 递减
        dp[0][0] = dp[0][1] = 1;
        int ans = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;

            if (A[i - 1] < A[i]) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][0] + 1);
            }
            if (A[i - 1] > A[i]) {
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][1] + 1);
            }

            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length; // row
        int n = grid[0].length; // col

        int[][] dp = new int[m + 1][n + 1];
        //init row = 0 dp[0][i] == 0, col = 0 dp[i][0] = 0
        for (int i = 0; i <= m; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = grid[0][0];
                    continue;
                }

                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1])
                        + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }

        int ans = 0;
        int m = dungeon.length, n = dungeon[0].length;
        // 进入位置 i*j需要的最低点数
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = dungeon[i][j] > 0 ? 1 : 1 - dungeon[i][j];
                    continue;
                }
                if (i == m - 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
                if (dungeon[i][j] < dp[i][j]) {
                    dp[i][j] = dp[i][j] - dungeon[i][j];
                } else { // 当前血量满足向下，或者向右。则进入该格子是只需1个点
                    dp[i][j] = 1;
                }
            }
        }

        printArray(dp);
        return dp[0][0];
    }

    void printArray(int[][] arrays) {
        for (int[] arr : arrays) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
