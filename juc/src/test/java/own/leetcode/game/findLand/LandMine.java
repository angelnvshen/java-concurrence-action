package own.leetcode.game.findLand;

import own.Printer;

import java.util.LinkedList;
import java.util.Queue;

public class LandMine {


    public static void main(String[] args) {
        LandMine landMine = new LandMine();

        char[][] chars = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        landMine.updateBoard(chars, new int[]{3, 0});
        Printer.print(chars);
    }


    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0].length == 0 || click == null || click.length != 2) {
            return board;
        }
        int row = board.length;
        int col = board[0].length;

        int r = click[0], c = click[1];
        if (r < 0 || r >= row || c < 0 || c >= col) return board;

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        if (board[r][c] == 'B') {
            return board;
        }
        /*宽搜*/
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int i = pair[0], j = pair[1];
            if(board[i][j] == 'B') continue;
            board[i][j] = 'B';

            int countMine = countMine(board, i, j, row, col);
            if (countMine > 0) {
                board[i][j] = (char) (countMine + '0');
                continue;
            }

            for (int d = 0; d < dx.length; d++) {
                int x = i + dx[d];
                int y = j + dy[d];
                if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                if (board[x][y] == 'E') {
                    queue.add(new int[]{x, y});
                }
            }
        }
        return board;
    }

    private int countMine(char[][] board, int i, int j, int row, int col) {
        int countLand = 0;

        for (int d = 0; d < dx.length; d++) {
            int x = i + dx[d];
            int y = j + dy[d];
            if (x < 0 || x >= row || y < 0 || y >= col) {
                continue;
            }
            if (board[x][y] == 'M') countLand++;
        }
        return countLand;
    }

    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
}
