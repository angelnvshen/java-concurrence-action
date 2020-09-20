package own.leetcode.math.matrix;

public class PrintZMatrix {

    public static void main(String[] args) {
        PrintZMatrix matrix = new PrintZMatrix();
        System.out.println(matrix.printZMatrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 1}
        }));
    }

    /**
     * @param matrix: An array of integers
     * @return: An array of integers
     */
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[] dx = {1, 0};
        int[] dy = {0, 1};

        int len = n * m;
        int[] ans = new int[len];
        int index = 0;

        int i = 0;
        int j = 0;
        int row = 0;
        int col = 0;
        int k = 0;
        while (index <= len) {
            row = i;
            col = j;

            while (row >= 0 && row < n && col >= 0 && col < m) {
                System.out.println(row + ", " + col);
                ans[index++] = matrix[row++][col--];
            }

            if(j + dx[0] >= m){
                if(i >= n) break;
                i += dy[1];
                j += dx[1];
            }else{
                i += dy[0];
                j += dx[0];
            }

            System.out.println(i + ", " + j);
        }
        println(ans);
        return ans;
    }

    private void println(int[] a){
        for(int i = 0; i < a.length; i ++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}