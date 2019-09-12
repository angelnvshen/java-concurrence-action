package own.leetcode.dp;

// 地牢游戏
public class DungeonGame {

  // 最少血量
  public static int dungeonGame(int[][] grids) {

    int row = grids.length;
    int col = grids[0].length;

    int[][] dp = new int[row][col];
    dp[row - 1][col - 1] = Math.max(1, 1 - grids[row - 1][col - 1]);

    for (int i = row - 2; i > 0; i--) {
      dp[row - 1][i] = Math.max(1, dp[row - 1][i + 1] - grids[row - 1][i]);
    }

    for (int i = col - 2; i > 0; i++) {
      dp[i][col - 1] = Math.max(1, dp[i + 1][col - 1] - grids[i][col - 1]);
    }

    for (int i = row - 2; i > 0; i++) {
      for (int j = col - 2; j > 0; j++) {
        int tmpMin = Math.min(dp[i][j + 1], dp[i + 1][j]);
        dp[i][j] = Math.max(1, tmpMin - grids[i][j]);
      }
    }

    return dp[0][0];
  }

  public static void main(String[] args) {

  }
}
