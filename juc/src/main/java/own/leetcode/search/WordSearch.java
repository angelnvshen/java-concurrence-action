package own.leetcode.search;

public class WordSearch {

    public static void main(String[] args) {
        WordSearch search = new WordSearch();
        System.out.println(search.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
//        }, "ABCCED"));
        }, "ABCB"));
//        }, "SEE"));
    }

    private char[] cw;
    private int row;
    private int col;
    private char[][] board;

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        this.row = board.length;
        this.col = board[0].length;
        this.cw = word.toCharArray();
        this.board = board;

        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == cw[0]) {
                    visited[i][j] = true;
                    if (helper(i, j, 0, visited)) {
                        return true;
                    } else {
                        visited[i][j] = false;
                    }
                }
            }
        }

        return false;
    }

    private boolean helper(int i, int j, int index, boolean[][] visited) {
        if (index == cw.length - 1) {
            return board[i][j] == cw[index];
        }

        for (int d = 0; d < dx.length; d++) {
            int x = dx[d] + i;
            int y = dy[d] + j;
            if (!inArea(x, y, row, col) || visited[x][y]) {
                continue;
            }

            if (cw[index + 1] != board[x][y]) {
                continue;
            }
            visited[x][y] = true;
            if (helper(x, y, index + 1, visited)) {
                return true;
            }
            visited[x][y] = false;
        }
        return false;
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private boolean inArea(int i, int j, int row, int col) {
        if (i < 0 || j < 0 || i >= row || j >= col) {
            return false;
        }
        return true;
    }
}