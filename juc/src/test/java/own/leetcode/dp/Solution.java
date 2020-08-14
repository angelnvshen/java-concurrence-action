package own.leetcode.dp;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /*ArrayList<Integer> list = new ArrayList<>();
        System.out.println(list.size());
        list.add(null);
        System.out.println(list.size());*/

        int oldCapacity = 4;
        System.out.println(oldCapacity + (oldCapacity >> 1));

        /*Solution solution = new Solution();
//        System.out.println(solution.numRollsToTarget(2, 6, 7));

        int[] ints = solution.loudAndRich(new int[][]{
                *//*        {1, 0},
                        {2, 1},
                        {3, 1},
                        {3, 7},
                        {4, 3},
                        {5, 3},
                        {6, 3}
                }, new int[]{3, 2, 5, 4, 6, 1, 7, 0});*//*

        }, new int[]{0, 1});
        Printer.print(ints);*/
    }

    private static int mod = 1000000007;

    public int numRollsToTarget(int d, int f, int target) {
        /*
        dp[d][i] 表示 d个撒子组合成i的所有情况
        dp[d][i] += dp[d - 1][i - j]] j in(1 ~ f);        
        */
        int size = d * f;
        if (target > size) return 0;

        int[][] dp = new int[d + 1][size + 1];
        // d = 0, 任何点数掷不出来 Arrays.fill(dp[0], 0;);
        // d = 2;

        int min = Math.min(f, target);

        for (int i = 1; i <= min; i++) {
            dp[1][i] = 1;
        }

        for (int k = 2; k <= d; k++) {
            for (int i = 1; i <= size; i++) {// i表示掷出来的点数
                for (int j = 1; j <= f; j++) {
                    if (i - j >= 0)
                        dp[k][i] = (dp[k][i] + dp[k - 1][i - j]) % mod;
                }
            }
        }
        return dp[d][target];
    }

    public int flipgame(int[] fronts, int[] backs) {
        if (fronts == null || backs == null || fronts.length != backs.length) return 0;

        int n = fronts.length;
        Set<Integer> same = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
                same.add(fronts[i]);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!same.contains(fronts[i])) {
                ans = Math.min(ans, fronts[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!same.contains(backs[i])) {
                ans = Math.min(ans, backs[i]);
            }
        }

        return ans;
    }

    //喧闹和富有
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        if (richer == null || quiet == null || quiet.length == 0) {
            return new int[0];
        }

        int n = quiet.length;
        int[] ans = new int[n];

        // 入度为0
        Queue<Integer> startList = new LinkedList<>();
        // 富有程度的树状结构，不可能出现环（a->b->c ,c不可能在指向a） 孩子节点 ： 父节点 list
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] r : richer) {
            map.putIfAbsent(r[0], new ArrayList<>());

            List<Integer> parentList = map.get(r[1]);
            if (parentList == null) {
                parentList = new ArrayList<>();
                map.put(r[1], parentList);
            }
            parentList.add(r[0]);

        }

        // 构造树形结构 父节点 ->孩子节点
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> en : map.entrySet()) {

            int child = en.getKey();
            List<Integer> parentList = en.getValue();

            if (parentList.size() == 0) {
                startList.add(child);
                continue;
            }

            int rigthParent = -1;
            int quiteTmp = Integer.MAX_VALUE;
            for (int p : parentList) {
                if (quiet[p] < quiteTmp) {
                    quiteTmp = quiet[p];
                    rigthParent = p;
                }
            }
            for (int p : parentList) {
                if (p == rigthParent) {
                    List<Integer> children = tree.get(rigthParent);
                    if (children == null) {
                        children = new ArrayList<>();
                        tree.put(rigthParent, children);
                    }
                    children.add(child);
                } else {
                    tree.put(p, new ArrayList<>());
                }
            }
        }

        // 宽搜
        while (!startList.isEmpty()) {
            int p = startList.poll();
            List<Integer> children = tree.get(p);

            if (ans[p] == 0)
                ans[p] = p;
            if (children == null || children.size() == 0) {
                continue;
            }
            for (int c : children) {
                if (quiet[c] > quiet[p]) {
                    quiet[c] = quiet[p];
                    ans[c] = ans[p];
                }
                startList.add(c);
            }
        }

        return ans;
    }
}