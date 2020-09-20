package own.leetcode;

import own.Printer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {

        Solution solution = new Solution();
        //"meituan2019","i2t"
//        System.out.println(solution.getMinString("meituan2019", "i2t"));
//        System.out.println(solution.getCombinations(6, 2));

        /*int[][] boards = {
                {0, 0, 1, 1},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
        };
        solution.flip(boards,
                new int[][]{
                        {2, 2},
                        {3, 3},
                        {4, 4}
                });

        Printer.print(boards);*/

//        Printer.print(solution.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 1));
//        Printer.print(solution.medianSlidingWindow(new int[]{2147483647, 2147483647}, 2));
//        Printer.print(solution.medianSlidingWindow(new int[]{1, 1, 1, 1}, 2));

//        Printer.print(solution.medianSlidingWindow(new int[]{7, 9, 3, 8, 0, 2, 4, 8, 3, 9}, 1));
//        Printer.print(solution.medianSlidingWindow(new int[]{-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648}, 3));

        Printer.print(solution.diagonalSort(new int[][]{
                /*{3, 3, 1, 1},
                {2, 2, 1, 2},
                {1, 1, 1, 2},*/

                {11, 25, 66, 1, 69, 7},
                {23, 55, 17, 45, 15, 52},
                {75, 31, 36, 44, 58, 8},
                {22, 27, 33, 25, 68, 4},
                {84, 28, 14, 11, 5, 50},
        }));
    }

    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int row = mat.length;
        int col = mat[0].length;
        int len = row * col;
        int[] arr = new int[len];
        int idx = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[idx++] = mat[i][j];
                mat[i][j] = -1;
            }
        }

        Arrays.sort(arr);
        print(arr);
        idx = 0;
        int i = row - 1, j = 0;
        int x = 0, y = 0, d = 0;
        int newColIndex = 0;
        int newRowIndex = 0;

        while (idx < len) {
            System.out.print("{ " + i + " ," + j + " } -> ");
            System.out.print(arr[idx] + " - ");
            mat[i][j] = arr[idx++];
            System.out.println(mat[i][j]);
            x = i + dx[d];
            y = j + dy[d];
            if (x < newRowIndex) {
                d = 1;
                j = j + 1;
            } else if (y >= col) {
                d = 0;
                newColIndex++;
                newRowIndex++;
                i = row - 1;
                j = newColIndex;
            } else {
                i = i + dx[d];
                j = j + dy[d];
            }
        }
        return mat;
    }

    private void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

    private static int[] dx = {-1, 0};
    private static int[] dy = {0, 1};

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return new double[0];
        }

        int n = nums.length;
        double[] ans = new double[n - k + 1];
        int idx = 0;
        //保存较大部分一方的值
        PriorityQueue<Integer> min = new PriorityQueue<>();
        //保存较小部分一方的值
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b > a ? 1 : -1);
        // 存放丢弃的数字
        Map<Integer, Integer> map = new HashMap<>();

        // init
        for (int i = 0; i < k; i++) {
            max.add(nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            min.add(max.poll());
        }

        Printer.print(nums);
        int i = k;
        while (true) {

            ans[idx++] = (k & 1) == 0 ? ((double) max.peek() + (double) min.peek()) / 2 : max.peek();
            if (idx >= n - k + 1) break;

            int in = nums[i];
            int out = nums[i - k];
            int balance = 0;
            i += 1;

            // out exists slip window
            balance += max.peek() >= out ? -1 : 1;
            map.put(out, map.getOrDefault(out, 0) + 1);

            // in enter slip window
            if (max.peek() >= in) {
                max.add(in);
                balance += 1;
            } else {
                min.add(in);
                balance -= 1;
            }

            if (balance > 0) { //左边多了
                min.add(max.poll());
                balance--;
            }

            if (balance < 0) {
                max.add(min.poll());
                balance++;
            }
            System.out.println(" ++++++++ ");
            System.out.println("left : " + max);
            System.out.println("right : " + min);
            discard(max, map);
            discard(min, map);

            System.out.println("left : " + max);
            System.out.println("right : " + min);
            System.out.println(" --------- ");
        }

        return ans;
    }

    private void discard(PriorityQueue<Integer> heap, Map<Integer, Integer> map) {
        if (heap.isEmpty()) return;
        while (map.get(heap.peek()) != null && map.get(heap.peek()) > 0) {
            int topV = heap.peek();
            map.put(topV, map.get(topV) - 1);
            heap.poll();
        }
    }

    //[[0,0,1,1],[1,0,1,0],[0,1,1,0],[0,0,1,0]]
    //[[2,2],[3,3],[4,4]]
    public void flip(int[][] board, int[][] position) {
        if (board == null || board.length == 0 || board[0].length == 0
                || position == null || position.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        int x = 0, y = 0;
        for (int[] p : position) {
            for (int d = 0; d < dx.length; d++) {
                x = p[0] - 1 + dx[d];
                y = p[1] - 1 + dy[d];

                if (x < 0 || y < 0 || x >= row || y >= col) {
                    continue;
                }
                board[x][y] = board[x][y] == 0 ? 1 : 0;
            }
        }
    }

//    private int[] dx = {-1, 0, 1, 0};
//    private int[] dy = {0, 1, 0, -1};

    int ans = 0;

    public int getCombinations(int n, int m) {
        if (n <= 0 || m <= 0) return 0;

        helper(n, m, 1, n);

        return ans;
    }

    private void helper(int sum, int nums, int index, int n) {
        if (sum == 0) {
            if (nums == 0) {
                ans += 1;
            }
            return;
        }
        for (int i = index; i <= n; i++) {
            if (sum < i) {
                break;
            }
            helper(sum - i, nums - 1, i, n);
        }

    }

    public String getMinString(String str1, String str2) {
        // write code here
        if (str1 == null || str2 == null) return null;
        if (str2.length() == 0) return str1;
        int[] map = new int[256];

        char[] cs1 = str1.toCharArray();
        char[] cs2 = str2.toCharArray();

        for (char c : cs2) {
            map[c]++;
        }

        int count = cs2.length;

        int len = cs1.length;
        char c = ' ';
        int L = 0, R = 0, minLen = Integer.MAX_VALUE;
        int l = 0, r = 0;

        //"meituan2019","i2t"
        // "ituan2"
        for (l = 0; l < len; l++) {
            while (count > 0 && r < len) {
                c = cs1[r++];
                map[c]--;
                if (map[c] >= 0) {
                    count -= 1;
                }
            }
            if (count <= 0) {
                if (minLen > r - l) {
                    minLen = r - l;
                    L = l;
                    R = r;
                }
                c = cs1[l];
                map[c]++;
                if (map[c] >= 1) {
                    count += 1;
                }
            }
        }

        return str1.substring(L, R);
    }

    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : answers) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            int val = en.getKey();
            int t = en.getValue();

            val += 1;
            ans += (t % val == 0 ? t : (t / val + 1) * val);

        }
        return ans;
    }

    public int numSubseq(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            int maxVal = target - nums[i];
            int maxValIndex = binarySearch(nums, maxVal, n);
            ans = (ans + ((maxValIndex >= i) ? f[maxValIndex - i] : 1)) % MOD;
        }
        return ans;
    }

    private int binarySearch(int[] nums, int val, int n) {
        int l = 0;
        int r = n - 1;
        int m = 0;
        while (l + 1 < r) {
            m = (r - l) / 2 + l;
            if (nums[m] > val) {
                r = m;
            } else {
                l = m;
            }
        }
        if (nums[r] == val) return r;
        if (nums[l] == val) return l;
        return -1;
    }

    private static int MOD = 1000000007;
    private static int MAX_LEN = 100001;
    private static int[] f = new int[MAX_LEN];

    static {
        f[0] = 1;
        for (int i = 1; i < MAX_LEN; i++) {
            f[i] = (f[i - 1] << 1) % MOD;
        }
    }
}