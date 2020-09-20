package own.leetcode.math.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumMagicSquaresInside {

    public static void main(String[] args) {
        NumMagicSquaresInside inside = new NumMagicSquaresInside();
        System.out.println(inside.numMagicSquaresInside(new int[][]{{4, 3, 8, 4}, {9, 5, 1, 9}, {2, 7, 6, 2}}));
        ;
    }

    private static List<Integer> list = Arrays.asList(
            4, 3, 8, 1, 6, 7, 2, 9, 4, 3, 8, 1, 6, 7, 2, 9);

    // 八个方向
    private static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public int numMagicSquaresInside(int[][] grid) {
        if (grid == null || grid.length < 3 || grid[0].length < 3) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;
        int x = 0;
        int y = 0;
        int ans = 0;

        for (int i = 1; i <= n - 2; i++) {
            for (int j = 1; j <= m - 2; j++) {
                // 幻方中心一定是5
                if (grid[i][j] != 5) continue;
                List<Integer> nums = new ArrayList<>();
                for (int k = 0; k < 8; k++) {
                    x = dx[k] + i;
                    y = dy[k] + j;
                    // System.out.println(dx[k] + ", " + dy[k]);
                    nums.add(grid[x][y]);
                }
                if (isMagic(nums)) {
                    ans += 1;
                }
            }
        }
        return ans;
    }

    private boolean isMagic(List<Integer> nums) {
        // System.out.println(nums);
        for (int i = 0; i < 8; i += 2) {
            if (list.get(i) == nums.get(0)) {

                // 正向
                boolean flag = true;
                for (int j = 1; j < 8; j++) {
                    if (nums.get(j) != list.get(i + j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return true;

                // 反方向
                flag = true;
                i += 8;
                for (int j = 1; j < 8; j++) {
                    if (nums.get(j) != list.get(i - j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) return true;
            }
        }

        return false;
    }
}