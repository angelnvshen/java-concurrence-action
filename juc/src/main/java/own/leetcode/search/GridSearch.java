package own.leetcode.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.Data;

/**
 * grid :
 *
 * 1 1 1 0 0
 *
 * 1 1 0 0 0
 *
 * 0 0 0 0 0
 *
 * 0 0 0 0 0
 */
public class GridSearch {

  static final int[] dx = {-1, 1, 0, 0};
  static final int[] dy = {0, 0, -1, 1};

  static void DFS(int[][] mark, char[][] grid, int x, int y) {
    mark[x][y] = 1;
    for (int i = 0; i < 4; i++) {
      int newX = dx[i] + x;
      int newY = dy[i] + y;
      if ((newX < 0 || newX > grid.length - 1) ||
          (newY < 0 || newY > grid[newX].length - 1)) {
        continue;
      }
      if (mark[newX][newY] == 0 && grid[newX][newY] == '1') {
        DFS(mark, grid, newX, newY);
      }
    }
  }

  static void BFS(int[][] mark, char[][] grid, int x, int y) {
    Queue<Pair> queue = new LinkedList<>();
    mark[x][y] = 1;
    queue.add(new Pair<>(x, y));
    while (!queue.isEmpty()) {
      Pair pair = queue.remove();
      x = (int) pair.f;
      y = (int) pair.s;
      for (int i = 0; i < 4; i++) {
        int newX = dx[i] + x;
        int newY = dy[i] + y;
        if ((newX < 0 || newX > grid.length - 1) ||
            (newY < 0 || newY > grid[newX].length - 1)) {
          continue;
        }
        if (mark[newX][newY] == 0 && grid[newX][newY] == '1') {
          mark[newX][newY] = 1;
          queue.add(new Pair(newX, newY));
        }
      }
    }

  }

  public static int islandNum(char[][] gird) {
    int islandNum = 0;
    int[][] mark = new int[gird.length][];
    for (int i = 0; i < gird.length; i++) {
      mark[i] = new int[gird[i].length];
    }
    for (int i = 0; i < gird.length; i++) {
      for (int j = 0; j < gird[i].length; j++) {
        if (mark[i][j] == 0 && gird[i][j] == '1') {
          BFS(mark, gird, i, j);
//          DFS(mark, gird, i, j);
          islandNum++;
        }
      }
    }
    return islandNum;
  }

  public static void main(String[] args) {
    char[][] grid = {{'1', '1', '1', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}};
    System.out.println(islandNum(grid));
  }

  @Data
  public static class Pair<T, D> {

    T f;
    D s;

    public Pair(T f, D s) {
      this.f = f;
      this.s = s;
    }
  }
}
