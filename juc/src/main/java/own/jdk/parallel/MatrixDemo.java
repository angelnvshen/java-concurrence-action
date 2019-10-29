package own.jdk.parallel;

import java.util.Random;

public class MatrixDemo {

    private static Random random = new Random(1000);

    int[][] geneMatrix(int seed, int x, int y) {
        random = new Random(seed);
        int[][] matrix = new int[x][y];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(100_000);
            }
        }
        return matrix;
    }

//    void deal()
}
