package own.leetcode;

public class FloodFill {

    public static void main(String[] args) {
        FloodFill floodFill = new FloodFill();
        floodFill.floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    //通过回溯来替代 dfs + 新开数组
    private void fill(int[][] image, int x, int y, int ori, int tar) {
        if (!inArea(image, x, y)) return;
        if (image[x][y] != ori) return; // 非目标
        //已探索过的 origColor 区域， 可以开数组来表示已经搜索
        if (image[x][y] == -1) return;
        image[x][y] = -1;
        fill(image, x + 1, y, ori, tar);
        fill(image, x - 1, y, ori, tar);
        fill(image, x, y - 1, ori, tar);
        fill(image, x, y + 1, ori, tar);
        image[x][y] = tar;   // unchoose：将标记替换为 newColor
    }

    private boolean inArea(int[][] image, int x, int y) {
        if (x >= 0 && image.length > x
                && y >= 0 && image[0].length > y) {
            return true;
        }
        return false;
    }
}