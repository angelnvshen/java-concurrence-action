package own.leetcode.math.matrix;

public class PrintZMatrix_II {

    public static void main(String[] args) {
        PrintZMatrix_II matrix_ii = new PrintZMatrix_II();
        matrix_ii.printZMatrix(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 1}
        });
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
        
        int dx = -1;
        int dy = 1;
        
        int count = n * m;
        int[] ans = new int[count];
        ans[0] = matrix[0][0];
        int idx = 1;
        int x = 0;
        int y = 0;
        while(idx < count){
            if(x + dx >= 0 && x + dx < n && y + dy >= 0 && y + dy < m){
                x += dx;
                y += dy;
            }else{
                if(dx == -1 && dy == 1){
                    if(y + 1 < m){
                        y += 1;
                    }else{
                        x += 1;
                    }
                    dx = 1; dy = -1;
                    System.out.println("x = " + x + " | y = " + y);
                }else{
                    if(x + 1 < n){
                        x += 1;
                    }else{
                        y += 1;
                    }
                    dx = -1; dy = 1;
                    System.out.println("x = " + x + " - y = " + y);
                }
            }
            
            ans[idx] = matrix[x][y];
            idx += 1;
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