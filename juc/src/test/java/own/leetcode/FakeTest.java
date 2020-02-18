package own.leetcode;

import org.junit.Test;
import own.leetcode.other.uf.QuickUnion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FakeTest {

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

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        int i = 0, j = 0, k = 0, x = 0, y = 0;

        init(m * n);
        int ans = 0;

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {

                if (grid[i][j] != '1') {
                    ans -= 1;
                    continue;
                }

                for (k = 0; k < 4; k++) {
                    x = i + dx[k];
                    y = j + dy[k];
                    if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != '1') {
                        continue;
                    }
                    union(i * m + j, x * m + y);
                }
            }
        }
        for (i = 0; i < n * m; i++) {
            if (id[i] == i) ans += 1;
        }
        return ans;
    }

    int[] id;

    void init(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
//        return id[p] == p ? p : (id[p] = find(id[p]);
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return;

        id[pid] = qid;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;
    }

    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].length() == 0) {
            return 0;
        }
        /*
            将一个格子分为4个三角形:上 - 0，右 - 1，下 -2，左 - 3
         */
        int n = grid.length;
        int m = grid[0].split("").length;
        init(4 * n * m);
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                index = 4 * (i * n + j);
                //单个格子之间的合并
                switch (grid[i].charAt(j)) {
                    case '/':
                        union(index + 0, index + 3);
                        union(index + 1, index + 2);
                        break;
                    case '\\':
                        union(index + 0, index + 1);
                        union(index + 2, index + 3);
                        break;
                    case ' ':
                        union(index + 0, index + 1);
                        union(index + 1, index + 2);
                        union(index + 2, index + 3);
                        break;
                }
                // 相邻格子之间的合并：只合并右边和下边即可
                if (j != m - 1) {
                    union(index + 1, index + 4 + 3);// 合并左右相邻的两个三角部分
                }
                if (i != n - 1) {
                    union(index + 2, index + 4 * m + 0);// 合并上下相邻的两个三角部分
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 4 * m * n; i++) {
            if (id[i] == i) ans += 1;
        }
        return ans;
    }

    public int largestComponentSize(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int nums = 0;
        for (int i = 0; i < n; i++) {
            if (nums < A[i]) {
                nums = A[i];
            }
        }
        init(nums + 1);

        for (int a : A) {
            for (int k = 2; k <= Math.sqrt(a); k++) {
                if (a % k == 0) {
                    union(a, k);
                    union(a, a / k);
                }
            }
        }
        int ans = 0;
        int temp = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            temp = find(a);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            ans = Math.max(ans, map.get(temp));
        }

        return ans;
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int n = board.length, m = board[0].length;
//        int[] dx = {0, 0, -1, 1};//上下左右
//        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, -1}; // 向下，向右
        int[] dy = {1, 0};
        int x = 0, y = 0;
        //多出一个顶点，与所有边界union，如果边界是O的话
        init(n * m + 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != 'O') {
                    continue;
                }
                //将边界union 虚拟的节点
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    union(i * m + j, n * m);
                }
                for (int k = 0; k < dx.length; k++) {
                    x = i + dx[k];
                    y = j + dy[k];
                    if (x < 0 || x >= n || y < 0 || y >= m
                            || board[x][y] != 'O') {
                        continue;
                    }
                    union(i * m + j, x * m + y);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 'O' && !connected(i * m + j, n * m)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList();
        if (accounts == null || accounts.size() == 0) {
            return result;
        }

        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        QuickUnion uf = new QuickUnion(1000 * 10);
        String name = "";
        int index = 0;
        for (List<String> account : accounts) {
            if (account.size() == 0) continue;
            name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                emailToId.putIfAbsent(account.get(i), index++);
                emailToName.putIfAbsent(account.get(i), name);
                if (i == 1) continue;
                uf.union(emailToId.get(account.get(i - 1)),
                        emailToId.get(account.get(i)));
            }
        }

        Map<Integer, List<String>> ans = new HashMap();
        for (String email : emailToId.keySet()) {
            index = uf.find(emailToId.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList<>())
                    .add(email);
        }

        for (List<String> list : ans.values()) {
            Collections.sort(list);
            list.add(0, emailToName.get(list.get(0)));
        }

        return new ArrayList(ans.values());
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0, ans = 0;
        Map<Integer, Integer> valueToIdx = new HashMap<>();
        for (int i : nums) {
            valueToIdx.putIfAbsent(i, index++);
        }

        Set<Integer> visited = new HashSet<>();
        init(index);
        Integer temp = null;
        for (int i : nums) {
            if (visited.contains(i)) {
                continue;
            }
            visited.add(i);
            temp = valueToIdx.get(i + 1);
            if (temp != null) {
                union(valueToIdx.get(i), temp);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : id) {
            index = find(i);
            map.put(index, map.getOrDefault(index, 0) + 1);
            ans = Math.max(ans, map.get(index));
        }
        return ans;
    }

    private int[] getNumsFromSources() {
        String fileName = "/Users/my/IdeaProjects_own/core/juc/src/test/resources/leetcode-sources/nuns.txt";
        int[] nums = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();

            List<Integer> list = new ArrayList<>();
            String str = null;
            while (line != null) {
                str = line.trim();
                list.add(Integer.valueOf(str.substring(0, str.length() - 1)));
                line = reader.readLine();
            }

            System.out.println(list);
            nums = list.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return nums;
    }


    @Test
    public void test1() {
//        System.out.println(maxProfit(new int[]{4, 4, 6, 1, 1, 4, 2, 5}));
        System.out.println(maxProfit(new int[]{2,1}));
    }


    public int maxProfit(int[] prices) {
        // write your code here
        if (prices == null || prices.length == 0) {
            return 0;
        }

        //分阶段 未持有股票|第一次持有股票|第一次卖出未买入|第二次持有股票|
        // 第二次卖出
        int n = prices.length, k = 5;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][1] = 0;
        for (int i = 2; i <= k; i++) {
            dp[0][i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) { // 第 1 ~ n天
            for (int j = 1; j <= k; j += 2) { // 第 1， 3 ，5阶段，未持有股票
                dp[i][j] = dp[i - 1][j];
                if (i >= 2 && j > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            for (int j = 2; j <= k; j += 2) {
                // 第 2， 4 阶段，持有股票
                dp[i][j] = dp[i - 1][j - 1];
                if (i >= 2 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= k; i++) {
            if ((i & 1) == 1) {
                ans = Math.max(ans, dp[n][i]);
            }
        }

        return ans;
    }

}
