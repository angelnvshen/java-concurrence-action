package own.leetcode.math.matrix;

import java.util.HashSet;
import java.util.Set;

public class FindElementsInMatrix {

    public static void main(String[] args) {
        FindElementsInMatrix matrix = new FindElementsInMatrix();
        System.out.println(matrix.FindElements(new int[][]{{2, 5, 3}, {3, 2, 1}, {1, 3, 5}}));
    }

    /**
     * @param Matrix: the input
     * @return: the element which appears every row
     */
    public int FindElements(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int ans = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    set.add(matrix[i][j]);
                    continue;
                }

            }
            System.out.println(set);
            if (set.size() == 0) {
                return 0;
            }
        }

        return set.iterator().next();
    }
}