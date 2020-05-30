package own.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static void main(String[] args) {
        SpiralMatrix matrix = new SpiralMatrix();
        matrix.spiralMatrixIII(5, 6, 1, 4);
    }

    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        if (R <= 0 || C <= 0 ||
                r0 < 0 || c0 < 0 || r0 >= R || c0 >= C) {
            return new int[0][0];
        }

        List<int[]> list = new ArrayList<>();
        int[][] ans = new int[R][C];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int d = 0;
        int x = r0;
        int y = c0;

        list.add(new int[]{x, y});
        int count = 1;
        int total = R * C - 1;
        while (total > 0) {
            // 1 , 2, 2, 3, 3 ...
            int len = (count - 1) / 2 + 1; // 保持两个len的步长
            for (int i = 0; i < len; i++) {
                x += dx[d];
                y += dy[d];
                if (x >= 0 && x < R && y >= 0 && y < C) {
                    list.add(new int[]{x, y});
                    total -= 1;
                }
            }
            d = (d + 1) % 4;
            count += 1;
        }
        return list.toArray(ans);
    }
}