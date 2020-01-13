package own.leetcode.other;

import java.util.HashMap;

public class DPSolutionIV {

    // ================================ 双序列型动态规划 ========================

    /**
     * 77. 最长公共子序列
     * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
     * <p>
     * 样例
     * 输入:  "ABCD" and "EDCA"
     * 输出:  1
     * <p>
     * 解释:
     * LCS 是 'A' 或  'D' 或 'C'
     * <p>
     * dp[i][j] 表示 A的前i个字符和B的前j个字符的最长公共子序列长度
     * dp[i][j] = max(d[i][j-1], dp[i-1][j] { 前两个公式表示A[i] != B[j]} , dp[i-1][j-1] + 1 {表示A[i] = B[j]} )
     * <p>
     * init
     * dp[0][j] = dp[i][0] = 0; //空串和任何长度字符串的公共子序列长度都是0
     *
     * @param A
     * @param B
     * @return
     */
    public static int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        char[] ca = A.toCharArray();
        char[] cb = B.toCharArray();
        int n = ca.length;
        int m = cb.length;
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if (ca[i - 1] == cb[j - 1]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    }
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 29. 交叉字符串
     * 中文English
     * 给出三个字符串:s1、s2、s3，判断s3是否由s1和s2交叉构成。
     * <p>
     * 样例
     * 样例 1：
     * <p>
     * 输入:
     * "aabcc"
     * "dbbca"
     * "aadbbcbcac"
     * 输出:
     * true
     * <p>
     * 前提：s3.len = s1.len + s2.len
     * s3 的最后一个字符是s1的最后一个字符 or s2的最后一个字符
     * dp[s][i][j] 表示 s3 前s个字符能够有由s1 的前 i个字符和 s2的前j个字符构成
     * 因为 i + j 必须等于s 所以可以省略s 一维数组： dp[i][j] 表示 s3 前 i+j个字符能够有由s1 的前 i个字符和 s2的前j个字符构成
     * dp[i][j] = dp[i-1][j] {s3[i+j-1] == s1[i-1]} or dp[i][j-1] {s3[i+j-1] == s1[j-1]}
     * <p>
     * init
     * dp[0][0] = true;表示 空串和空串可以组成空串
     * i=0；只考虑 s 和 j 的关系，反之，已然
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        char[] cs3 = s3.toCharArray();
        if (cs3.length != cs1.length + cs2.length) {
            return false;
        }

        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = true;
                    continue;
                }
                if (i - 1 >= 0 && cs3[i + j - 1] == cs1[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - 1 >= 0 && cs3[i + j - 1] == cs2[j - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 119. 编辑距离
     * 中文English
     * 给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
     * <p>
     * 你总共三种操作方法：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * 样例
     * 样例 1:
     * <p>
     * 输入:
     * "horse"
     * "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (替换 'h' 为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * <p>
     * 可以考虑 w1 转为w2时，w2的最后一个字符w1怎么转换而来：
     * w2 添加一个字符
     * w2 替换一个字符
     * w2 删除一个字符
     * w2最后一个字符和w1最后一个字符相等
     * <p>
     * dp[i][j]表示w1的前i个字符转为w2前j个字符时，的最小编辑距离
     * dp[i][j] =
     * min(
     * dp[i][j-1] + 1 {w1 加了一个字符，该字符跟w2最后一个字符一样}，
     * dp[i-1][j] + 1 {w1 删了了一个字符，w1[0 ... i -1] 来维持最小编辑距离}，
     * dp[i-1][j-1] + 1  {w1 将最后一个字符替换为跟w2最后一个字符一样}，
     * dp[i-1][j-1] => 条件： {w1 和w2的最后一个字符一样}
     * )
     * <p>
     * init
     * dp[0][j] = j;
     * dp[i][0] = i;
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1));
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 118. 不同的子序列
     * 中文English
     * 给定字符串 S 和 T, 计算 S 的所有子序列中有多少个 T.
     * <p>
     * 子序列字符串是原始字符串删除一些(或零个)字符之后得到的字符串, 并且要求剩下的字符的相对位置不能改变. (比如 "ACE" 是 ABCDE 的一个子序列, 而 "AEC" 不是)
     * <p>
     * 样例
     * 样例 1:
     * <p>
     * 输入: S = "rabbbit", T = "rabbit"
     * 输出: 3
     * 解释: 你可以删除 S 中的任意一个 'b', 所以一共有 3 种方式得到 T.
     * <p>
     * 以T为目标，求再S中共有多少个T
     * dp[i][j]
     * S的最后一个字符如果在是T的最后一个字符 则子问题是 0..i-2 中出现 0..j-2的次数就是 答案
     * S的最后一个字符如果不是T的最后一个字符 则子问题是 0..i-2 中出现 0..j-1的次数就是 答案
     * <p>
     * dp[i][j] = dp[i-1][j-1] {s[i-1] == t[j-1] } + dp[i-1][j]
     * <p>
     * init
     * dp[0][j] S是空串：则 dp[0][j] {j in 0 .. t.len} = 0;
     * dp[i][0] A是空串：怎么计算
     * 如果 S=T={a}
     * 目测 dp[1][1] = 1 = dp[0][0] + dp[0][1] => dp[0][0] = 1
     * 如果 S={aa}, T={a}
     * 目测 dp[2][1] = 2 = dp[1][0] + dp[1][1] => dp[1][0] = 1
     * ...
     * dp[i][0] = 1
     *
     * @param S
     * @param T
     * @return
     */
    public static int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }
        int ls = S.length();
        int lt = T.length();
        if (ls == 0) {
            return 0;
        }
        if (lt == 0) {
            return 1;
        }
        int[][] dp = new int[ls + 1][lt + 1];

        for (int i = 0; i <= ls; i++) {
            for (int j = 0; j <= lt; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = dp[i - 1][j];
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }

        return dp[ls][lt];
    }

    /**
     * 154. 正则表达式匹配
     * <p>
     * 实现支持'.'和'*'的正则表达式匹配。
     * <p>
     * '.'匹配任意一个字母。
     * <p>
     * '*'匹配零个或者多个前面的元素。
     * <p>
     * 匹配应该覆盖整个输入字符串，而不仅仅是一部分。
     * <p>
     * 需要实现的函数是：bool isMatch(string s, string p)
     * <p>
     * isMatch("aa","a") → false
     * isMatch("aa","aa") → true
     * isMatch("aaa","aa") → false
     * isMatch("aa", "a*") → true
     * isMatch("aa", ".*") → true
     * isMatch("ab", ".*") → true
     * isMatch("aab", "c*a*b") → true
     * <p>
     * 样例
     * <p>
     * 输入："aa"，"a"
     * 输出：false
     * 解释：
     * 无法匹配
     * <p>
     * dp[i][j] s的前i个字符是否和p的前j个字符相匹配
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        int n = cs.length;
        int m = cp.length;

        boolean[][] dp = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = true;
                    continue;
                }
                // 模式串 p 是空串 ,则dp[i][0] 为false
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                // 模式串 最后一位为 非 . 非 *的字符
                if (cp[j - 1] != '*' && cp[j - 1] != '.') {
                    if (i > 0 && cs[i - 1] == cp[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                        continue;
                    }
                }
                // 模式串 最后一位为 . ,可以配着 s的任意一位字符
                if (i > 0 && cp[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                // 模式串 最后一位为 *
                if (j >= 2 && cp[j - 1] == '*') {
                    if (cp[j - 2] != '.') {
                        // 当模式串倒数第二位的字符和 s的最后一位不等，则dp[i][j] = dp[i][j-2]
                        // 如果相等时，dp[i][j] = dp[i-1][j] ,s的最后一个字符可以去掉，在继续匹配
                        dp[i][j] = dp[i][j - 2];
                        if (i > 0 && cs[i - 1] == cp[j - 2]) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                        continue;
                    } else {
                        dp[i][j] = dp[i][j - 2];
                        if (i > 0) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                        continue;
                    }
                }
            }
        }
        /*for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (cp[j - 1] != '*') {
                    if (i > 0 && (cp[j - 1] == '.' || cs[i - 1] == cp[j - 1])) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    if (j >= 2) {
                        dp[i][j] = dp[i][j - 2];
                    }
                    if (i > 0 && j >= 2) {
                        if (cp[j - 2] == '.' || cs[i - 1] == cp[j - 2]) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }*/

        return dp[n][m];
    }

    /**
     * 192. 通配符匹配
     * 中文English
     * 判断两个可能包含通配符“？”和“*”的字符串是否匹配。匹配规则如下：
     * <p>
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个串完全匹配才算匹配成功。
     * <p>
     * 样例
     * 样例1
     * <p>
     * 输入:
     * "aa"
     * "a"
     * 输出: false
     * <p>
     * 和上一题（regular pattern matching ）的区别在于 * 不在考虑* 前边的一个字符
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch_wildCard(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        int n = cs.length;
        int m = cp.length;

        boolean[][] dp = new boolean[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {

                /*// 都为空串时可以匹配
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                // 仅模式串为空串时，不能匹配
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (i > 0 && cp[j - 1] != '?' && cp[j - 1] != '*') {
                    if (cs[i - 1] == cp[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    continue;
                }

                if (i > 0 && cp[j - 1] == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                if (cp[j - 1] == '*') {
                    // 模式串最后一个字符是 * ,可以匹配0个或者多个字符，
                    // 如果匹配0个字符 dp[i][j] = dp[i][j-1]，
                    // 如果匹配多个字符 dp[i][j] = dp[i-1][j] 表示匹配当前i字符，继续进行 0..i-2 和 0..j-1 匹配
                    dp[i][j] = dp[i][j - 1];
                    if (i > 0) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }*/
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                // 仅模式串为空串时，不能匹配
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (cp[j - 1] != '*') {
                    if (i > 0 && (cs[i - 1] == cp[j - 1] || cp[j - 1] == '?')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // 模式串最后一个字符是 * ,可以匹配0个或者多个字符，
                    // 如果匹配0个字符 dp[i][j] = dp[i][j-1]，
                    // 如果匹配多个字符 dp[i][j] = dp[i-1][j] 表示匹配当前i字符，继续进行 0..i-2 和 0..j-1 匹配
                    dp[i][j] = dp[i][j - 1];
                    if (i > 0) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 668. 一和零
     * <p>
     * 在计算机世界中, 由于资源限制, 我们一直想要追求的是产生最大的利益.
     * 现在，假设你分别是 m个 0s 和 n个 1s 的统治者. 另一方面, 有一个只包含 0s 和 1s 的字符串构成的数组.
     * 现在你的任务是找到可以由 m个 0s 和 n个 1s 构成的字符串的最大个数. 每一个 0 和 1 均只能使用一次
     * <p>
     * 样例
     * <p>
     * 输入：
     * ["10", "0001", "111001", "1", "0"]
     * 5
     * 3
     * 输出： 4
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0 || (m == 0 && n == 0)) {
            return 0;
        }

        int L = strs.length;
        int[][][] dp = new int[L + 1][m + 1][n + 1];

        // init
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[0][i][j] = 0;
            }
        }

        for (int l = 1; l <= L; l++) {
            int zero_num = 0;
            int one_num = 0;
            for (int i = 0; i < strs[l - 1].length(); i++) {
                if (strs[l - 1].charAt(i) == '1') {
                    one_num++;
                }
                if (strs[l - 1].charAt(i) == '0') {
                    zero_num++;
                }
            }
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    // 对于最后一个字符，如果没有使用
                    dp[l][i][j] = dp[l - 1][i][j];
                    if (i >= zero_num && j >= one_num) {

                        // 对于最后一个字符，如果使用了, 分别减去当前字符的0，1的长度。并dp[l-1][i-num0(str(i-1))][j-num1(str(j-1))]
                        dp[l][i][j] = Math.max(dp[l][i][j], dp[l - 1][i - zero_num][j - one_num] + 1);
                    }
                }
            }
        }

        return dp[L][m][n];
    }

    private static void recordZeroAndOne(HashMap<String, Integer> oneMap, HashMap<String, Integer> zeroMap, String[] strs) {
        for (String str : strs) {
            recordZeroAndOne(oneMap, zeroMap, str);
        }
    }

    private static void recordZeroAndOne(HashMap<String, Integer> oneMap, HashMap<String, Integer> zeroMap, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        if (oneMap.get(str) != null && zeroMap.get(str) != null) {
            return;
        }

        int num_zero = 0;
        int num_one = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                num_one++;
            }
            if (str.charAt(i) == '0') {
                num_zero++;
            }
        }
        oneMap.putIfAbsent(str, num_one);
        zeroMap.putIfAbsent(str, num_zero);
    }


    public static void main(String[] args) {
        // ========================================
        /*System.out.println(longestCommonSubsequence("ABCD", "EDCA"));
        System.out.println(longestCommonSubsequence("ABCD", "EACB"));*/

        // ========================================
        /*System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("", "", "1"));*/

        // ========================================
        /*System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("intention", "execution"));*/

        // ========================================
        /*System.out.println(numDistinct("bbb", "bb"));*/

        // ========================================
        /*System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aa", "*"));*/
        // ----------------------------------------
        /*System.out.println(isMatch_wildCard("aa", "*"));
        System.out.println(isMatch_wildCard("aa", "a"));
        System.out.println(isMatch_wildCard("aa", "aa"));
        System.out.println(isMatch_wildCard("aaa", "aa"));
        System.out.println(isMatch_wildCard("aa", "a*"));*/

        // ========================================
        String strs[] = new String[]{"10","0001","111001","1","0"};
        System.out.println(findMaxForm(strs, 5, 3));

//        String strs[] = new String[]{"10", "0001", "111001", "1", "0"};
//        System.out.println(findMaxForm(strs, 7, 7));
        // ========================================
    }
}
