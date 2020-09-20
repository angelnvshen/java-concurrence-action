package own.leetcode.game.robot;

public class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        System.out.println(uniquePathsIII.uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1},
        }));
    }

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int ans = 0;

        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
        this.visited = new boolean[row][col];
        init();

        dfs(start[0], start[1], 0);

        return total;
    }

    int total = 0;

    private void dfs(int r, int c, int count) {
        if (r == end[0] && c == end[1]) {
            if (count + 1== steps) {
                total += 1;
            }
            return;
        }
        for (int d = 0; d < dx.length; d++) {
            int x = dx[d] + r;
            int y = dy[d] + c;
            if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || grid[x][y] != 0) {
                continue;
            }
            visited[x][y] = true;
            dfs(x, y, count + 1);
            visited[x][y] = false;
        }
    }

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};

    private int[][] grid;
    private int row;
    private int col;
    private int steps;
    private int[] start;
    private int[] end;

    private boolean[][] visited;

    private static int BARRIER = -1;

    private void init() {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == BARRIER) {
                    count++;
                } else if (grid[i][j] == 1) {
                    start = new int[]{i, j};
                } else if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                    end = new int[]{i, j};
                }
            }
        }
        steps = row * col - count;
    }
}
