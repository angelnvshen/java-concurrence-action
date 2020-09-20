package own.leetcode.game.tictactoe;

public class TictactoeGame {

    public static void main(String[] args) {
        TictactoeGame game = new TictactoeGame();
        System.out.println(game.tictactoe(new int[][]{
                {0, 0},
                {2, 0},
                {1, 1},
                {2, 1},
                {2, 2},
        }));
    }

    private static String A_WIN = "A";
    private static String B_WIN = "B";
    private static String DRAW = "Draw";
    private static String PENDING = "Pending";

    public String tictactoe(int[][] moves) {
        int n = moves.length;
        char[][] row = new char[3][3];
        char[][] col = new char[3][3];
        char[][] dia = new char[2][3];
        String ans = null;
        if (9 == n) {
            ans = DRAW;
        } else {
            ans = PENDING;
        }

        int step = 0;
        for (int[] m : moves) {
            int i = m[0];
            int j = m[1];
            char c = ' ';
            if ((step & 1) == 0) {
                c = 'X';
            } else {
                c = 'O';
            }
            step ++;

            row[i][j] = c;
            col[j][i] = c;
            int disIndx = -1;
            if (i == j) {
                disIndx = 0;
                dia[0][i] = c;
            } else if (i + j == 3 - 1) {
                disIndx = 1;
                dia[1][j] = c;
            }

            if (step >= 3) {
                int count = 0;
                for (int s = 0; s < 3; s++) {
                    if (row[i][s] == '\u0000' || col[j][s] == '\u0000')
                        break;
                    else{
                        count += 2;
                    }

                    if (disIndx != -1) {
                        if (dia[disIndx][s] == c)
                            count++;
                    }
                }
                if (disIndx == -1 && count == 6)
                    return c == 'X' ? A_WIN : B_WIN;
                if (disIndx != -1 && count == 9)
                    return c == 'X' ? A_WIN : B_WIN;
            }
        }
        return ans;
    }
}
