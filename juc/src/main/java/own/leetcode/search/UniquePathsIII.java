package own.leetcode.search;

public class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII path = new UniquePathsIII();
        System.out.println(path.uniquePathsIII(new int[][]{
                /*{1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1},*/

                /*{1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2},*/

                {0, 1},
                {2, 0},
        }));
    }

    private static int START = 1;
    private static int END = 2;
    private static int BLANK = 0;
    private static int BARRIER = -1;

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;

        this.row = row;
        this.col = col;
        this.grid = grid;

        int countBlank = 0;
        int sr = 0, sc = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == BLANK) {
                    countBlank += 1;
                } else if (grid[i][j] == START) {
                    sr = i;
                    sc = j;
                } else if (grid[i][j] == END) {
                    endRow = i;
                    endCol = j;
                    grid[i][j] = BLANK;
                    countBlank += 1;
                }
            }
        }

        helper(sr, sc, countBlank);
        return pathNum;
    }

    private int row;
    private int col;
    private int[][] grid;
    private int pathNum = 0;
    private int endRow = 0;
    private int endCol = 0;

    private void helper(int sr, int sc, int countBlank) {
        if (countBlank == 0 && sr == endRow && sc == endCol) {
            pathNum += 1;
            return;
        }

        for (int d = 0; d < dx.length; d++) {
            int x = dx[d] + sr;
            int y = dy[d] + sc;
            if (!inArea(x, y) || grid[x][y] != BLANK) {
                continue;
            }
            grid[x][y] = BARRIER;
            helper(x, y, countBlank - 1);
            grid[x][y] = BLANK;
        }
    }

    private boolean inArea(int r, int c) {
        if (r < 0 || c < 0 || r >= row || c >= col) {
            return false;
        }
        return true;
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
}