package own.leetcode.math.matrix;

import java.util.stream.IntStream;

public class ToeplitzMatrix {

    public static void main(String[] args) {
        ToeplitzMatrix matrix = new ToeplitzMatrix();
        System.out.println(matrix.isToeplitzMatrix(new int[][]{
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        }));
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[] tmp = matrix[0].clone();

        for (int i = 1; i < n; i++) {
            for (int j = m - 1; j >= 1; j--) {
                if (tmp[j - 1] == matrix[i][j]) {
                    tmp[j] = matrix[i][j];
                } else {
                    System.out.println(tmp[j - 1] + ", " + matrix[i][j] + " | " + i + ", " + j);
                    return false;
                }
            }
            tmp[0] = matrix[i][0];
            print(tmp);
        }
        return true;
    }

    private void print(int[] a) {
        IntStream.of(a).boxed().forEach(b -> {
            System.out.print(b + "  ");
        });
        System.out.println();
    }
}