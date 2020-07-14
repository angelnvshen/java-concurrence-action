package own.leetcode.math.matrix;

import java.util.ArrayList;
import java.util.List;

public class LuckyNumbers {

    public static void main(String[] args) {
        LuckyNumbers numbers = new LuckyNumbers();
        System.out.println(numbers.luckyNumbers(new int[][]{
                {3, 7, 8},
                {9, 11, 13},
                {15, 16, 17}
        }));
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        // find min in rows
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] mins = new int[rows];
        // Arrays.fill(mins, -1);
        for (int i = 0; i < rows; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    mins[i] = j;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            int col = mins[i];
            int idx_row = -1;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < rows; j++) {
                if (max < matrix[j][col]) {
                    max = matrix[j][col];
                    idx_row = j;
                }
            }
            if (matrix[idx_row][col] == matrix[i][col]) {
                ans.add(max);
            }
        }
        return ans;
    }
}