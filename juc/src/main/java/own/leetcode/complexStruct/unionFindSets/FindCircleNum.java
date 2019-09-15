package own.leetcode.complexStruct.unionFindSets;

public class FindCircleNum {
    public static int findCircleNum(int[][] nums) {

        int[] visited = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                DFS(nums, i, visited);
                count++;
            }
        }

        return count;
    }

    private static void DFS(int[][] nums, int u, int[] visited) {

        visited[u] = 1;
        for (int i = 0; i < nums[u].length; i++) {
            if (visited[i] == 0 && nums[u][i] == 1) {
                DFS(nums, i, visited);
            }
        }
    }

    public static int findCircleNum2(int[][] nums) {
        DisJoinSet set = new DisJoinSet(nums.length);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums[i].length; j++) {
                if (nums[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }
        return set.getCount();
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(nums));
        System.out.println(findCircleNum2(nums));
    }
}
