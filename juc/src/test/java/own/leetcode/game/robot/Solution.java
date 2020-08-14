package own.leetcode.game.robot;

import own.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        /*System.out.println(solution.pathWithObstacles(new int[][]{
         *//*{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},*//*
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
        }));*/

        int[] nums = new int[]{5, 3, 1, 2, 3};
        solution.wiggleSort(nums);
        Printer.print(nums);
    }

    public void wiggleSort(int[] nums) {
        /*     对数组排序 O(nlogn)
        int[] res = Arrays.copyOf(nums, nums.length);
        Arrays.sort(res);
        int index = res.length - 1;
        for(int i = 0;i < res.length;i += 2) {
            nums[i] = res[index--];
        }
        for(int i = 1;i < res.length;i += 2) {
            nums[i] = res[index--];
        }
        */
        // 直接遍历 O(n)
        /*for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i] > nums[i - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                }
            } else {
                if (nums[i] < nums[i - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[i - 1];
                    nums[i - 1] = temp;
                }
            }
        }*/

        for (int i = 1; i < nums.length; i++) {
            if (((i & 1) == 0 && nums[i] < nums[i - 1]) ||
                    ((i & 1) == 1 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        /*
        深搜+回溯
        */
        List<List<Integer>> ans = new ArrayList<>();
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return ans;
        }

        if (obstacleGrid[0][0] == 1) return ans;

        this.obstacleGrid = obstacleGrid;
        this.row = obstacleGrid.length;
        this.col = obstacleGrid[0].length;
        this.visited = new boolean[row][col];

        if (obstacleGrid[row - 1][col - 1] == 1) return ans;

        visited[0][0] = true;
        dfs(0, 0, ans, new ArrayList<>());

        return ans;
    }

    private void dfs(int r, int c, List<List<Integer>> ans, List<List<Integer>> result) {
        if (r == row - 1 && c == col - 1) {
            ans.add(0, Arrays.asList(0, 0));
            ans.addAll(result);
            find = true;
            return;
        }

        for (int d = 0; d < dx.length; d++) {
            int x = r + dx[d];
            int y = c + dy[d];
            if (x < 0 || y < 0 || x >= row || y >= col || visited[x][y] || obstacleGrid[x][y] == 1) {
                continue;
            }

            visited[x][y] = true;
            result.add(Arrays.asList(x, y));
            dfs(x, y, ans, result);
            if (find) return;
            visited[x][y] = false;
            result.remove(result.size() - 1);
        }

    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private int[][] obstacleGrid;
    private int row = 0;
    private int col = 0;
    private boolean[][] visited;
    private boolean find = false;
}