package own.jdk;

import org.junit.Test;
import own.leetcode.Codec;
import own.leetcode.TreeNode;

import java.util.*;

public class OwnTest {

    @Test
    public void test1() {
        int[] nums = {2, 0, 2, 1, 1, 0};

        int p0 = 0, cur = 0;
        int p2 = nums.length - 1;

        while (cur <= p2) {
            if (nums[cur] == 0) {
                swap(nums, cur, p0);
                cur++;
                p0++;
            } else if (nums[cur] == 2) {
                swap(nums, cur, p2);
                p2--;
            } else {
                cur++;
            }
        }

        /*TreeMap<Integer, Integer> count = new TreeMap<>();
        for(int i = 0; i < nums.length; i ++){
            Integer integer = count.getOrDefault(nums[i], new Integer(0));
            integer += 1;
            count.put(nums[i], integer);
        }
        System.out.println(count);

        int p = 0;
        for(Map.Entry<Integer, Integer> entry : count.entrySet()){
            Integer key = entry.getKey();
            for(int i = 0; i< entry.getValue(); i++){
                nums[p ++] = key;
            }
        }*/

        System.out.println(nums);
    }

    private static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    @Test
    public void test3() {
//        System.out.println(findPeakElement(new int[]{1,20,11,10,7,6,3}));
//        System.out.println(findPeakElement(new int[]{1,20}));
        System.out.println(findPeakElement(new int[]{-2147483648, -2147483647}));
    }


    public static int findPeakElement(int[] nums) {
        if (nums == null) {
            return Integer.MIN_VALUE;
        }
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int[] dummy = new int[n + 2];
        System.arraycopy(nums, 0, dummy, 1, n);
        dummy[0] = Integer.MIN_VALUE;
        dummy[n + 1] = Integer.MIN_VALUE;

        int start = 0;
        int end = n + 1;
        int mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (dummy[mid] > dummy[mid - 1] && dummy[mid] > dummy[mid + 1]) {
                return mid - 1;
            }
            if (dummy[mid + 1] > dummy[mid]) { // mid 处于上升一侧
//            if(dummy[mid] > dummy[mid - 1]){ // mid 处于上升一侧
                start = mid;
            } else if (dummy[mid] > dummy[mid + 1]) {// mid 处于下降一侧
                end = mid;
            } else {
                end = mid;
            }
        }
        System.out.println(start + ", " + end);
        return Math.min(start, end) - 1;
    }

    @Test
    public void test4() {
        /*int [][] matrix = {{}};
        System.out.println(searchMatrix(matrix, 1));*/

        /*int[][] matrix =
                {
                        {1, 3, 5, 7},
                        {10, 11, 16, 20},
                        {23, 30, 34, 50},
                };
        System.out.println(searchMatrix(matrix, 13));*/

        int[][] matrix =
                {
                        {1},
                        {3},
                };
        System.out.println(searchMatrix(matrix, 3));
    }


    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int start = 0;
        int end = matrix.length - 1;
        int mid = 0;
        // 确定一行
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        int p = 0;
        if (matrix[start][0] <= target) {
            p = start;
        }
        if (matrix[end][0] <= target) {
            p = end;
        }

        start = 0;
        end = matrix[p].length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[p][mid] == target) {
                return true;
            } else if (matrix[p][mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return matrix[p][start] == target || matrix[p][end] == target;
    }

    @Test
    public void test5() {
        int n = 3;
        int left = (1 << n) - 1;
        /*System.out.println(left);
        for (int i = 0; i < n; i++) {
            left ^= 1 << i;
            System.out.println("i - " + left);
//            left |= 1 << i;
//            System.out.println("i - " + left);
        }
        System.out.println(left);*/

        System.out.println(left & (1 << 2));

    }

    @Test
    public void test6() {
        int[][] merge = {
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };

        int[][] merge1 = merge(merge);
        System.out.println(merge1);

    }

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0
                || intervals[0].length == 0) {
            return intervals;
        }

        // 按照第一个数字排序
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<int[]> list = new ArrayList<>();

        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // intervals[i] 跟 cur 不重合
            if (intervals[i][0] > cur[1]) {
                list.add(cur);
                cur = intervals[i];
            } else { // 重合，合并两个元素
                cur[1] = Math.max(cur[1], intervals[i][1]);
            }
        }
        int[][] result = new int[list.size()][2];
        return list.toArray(result);
    }

    @Test
    public void test7() {
//        subarraySum(new int[]{1, 2, 3, 4}, 1);
//        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
//        System.out.println(findMaxAverage(new int[]{0,4,0,3,2}, 1));
//        System.out.println(findMaxAverage(new int[]{-1}, 1));
//        System.out.println(findMaxAverage(new int[]{4,2,1,3,3}, 2));
//        System.out.println(Double.NEGATIVE_INFINITY > -1.0d );
    }

    public static int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }
        //printArray(prefixSum);


        return -1;
    }

    public static <T> void printArray(T[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println();
    }

    public static double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0.0;
        }

        double pre = 0.0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        double result = pre;

        for (int i = k; i < nums.length; i++) {
            pre = pre - nums[i - k] + nums[i];
            result = Math.max(result, pre);
        }

        return result / Double.valueOf(k);
    }

    public static double findMaxAverage_ii(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0.0;
        }
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }
        double result = Double.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            if (i + k > n) {
                break;
            }
            result = Math.max((prefixSum[i + k] - prefixSum[i]) / Double.valueOf(k), result);
        }

        return result;
    }

    @Test
    public void test8() {
        Codec codec = new Codec();
        own.leetcode.TreeNode deserialize = codec.deserialize("1,2,3,#,4");
        String s = tree2str(deserialize);
        System.out.println(s);
    }

    public static String tree2str(own.leetcode.TreeNode t) {
        if (t == null) {
            return "";
        }
        String result = Integer.toString(t.val);
        if (t.left != null && t.right == null) {
            result += append(t.left);
            result += append(t.right);
        }

        if (t.left == null && t.right != null) {
            result += append(t.left);
            result += append(t.right);
        }

        if (t.left != null && t.right != null) {
            result += append(t.left);
            result += append(t.right);
        }

        return result;
    }

    private static String append(own.leetcode.TreeNode node) {
        if (node == null) {
            return "()";
        } else {
            String str = tree2str(node);
            return "(" + str + ")";
        }
    }

    /*

    // 递归终止条件
        if(t==null)
        return "";
        // 情况1
        if(t.left==null && t.right==null)
            return t.val+"";
         // 情况2
        if(t.right==null)
            return t.val+"("+tree2str(t.left)+")";
         // 情况3和4
        return t.val+"("+tree2str(t.left)+")("+tree2str(t.right)+")";

    * */

    @Test
    public void test9() {
//        System.out.println(checkInclusion("a", "ab"));
//        System.out.println(findAnagrams("apb", "ab"));

        System.out.println(maxSlidingWindow(new int[]{1, -1}, 1));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int p = 0;

        // build window
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }
        result[p++] = heap.peek();

        // slide window
        for (int i = k; i < n; i++) {
            heap.remove(nums[i - k]);
            heap.add(nums[i]);

            result[p++] = heap.peek();
        }

        return result;
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] map = new int[26];
        int count = 0;
        int n = s.length(), m = p.length();

        for (int i = 0; i < m; i++) {
            map[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i < m; i++) {
            map[s.charAt(i) - 'a']--;
            if (map[s.charAt(i) - 'a'] >= 0)
                count++;

        }
        if (count == m) {
            result.add(0);
        }

        for (int i = m; i < n; i++) {
            map[s.charAt(i) - 'a']--;
            if (map[s.charAt(i) - 'a'] >= 0)
                count++;

            map[s.charAt(i - m) - 'a']++;
            if (map[s.charAt(i - m) - 'a'] > 0)
                count--;

            if (count == m) {
                result.add(i - m + 1);
            }
        }

        return result;
    }


    public static boolean checkInclusion(String s1, String s2) {
        // 通过map 来比较字符 s1(slip window str) 和 s2的子串是否相等
        if (s1 == null || s2 == null || s2.length() < s1.length()) {
            return false;
        }

        int n = s1.length(), m = s2.length();
        int[] map1 = new int[26];
        int[] map2 = new int[26];

        // window str
        for (int i = 0; i < n; i++) {
            // 输入的字符串只包含小写字母
            map1[s1.charAt(i) - 'a']++;
            map2[s2.charAt(i) - 'a']++;
        }

        if (equal(map1, map2)) {
            return true;
        }

        for (int i = n; i < m; i++) {
            map2[s2.charAt(i) - 'a']++;
            map2[s2.charAt(i - n) - 'a']--;
            if (equal(map1, map2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean equal(int[] map1, int[] map2) {
        for (int i = 0; i < 26; i++) {
            if (map2[i] != map1[i]) {
                return false;
            }
        }

        return true;
    }

    public static int findSubstring(String str, int k) {
        return 0;
    }

    @Test
    public void test10() {
//        maxSlidingWindow_ii(new int[]{1,3,-1,-3,5,3,6,7}, 3);
//        maxSlidingWindow_ii(new int[]{1, -1}, 1);
    }

    public static int[] maxSlidingWindow_ii(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deq = new ArrayDeque<>();
        int n = nums.length, p = 0;
        int[] result = new int[n - 1];
        for (int i = 0; i < k; i++) {
            inQueue(deq, nums[i]);
        }
        result[p++] = deq.peekFirst();

        for (int i = k; i < n; i++) {
            inQueue(deq, nums[i]);
            outQueue(deq, nums[i - k]);
            System.out.println(p + ", " + i);
            result[p++] = deq.peekFirst();
        }

        return result;
    }

    private static void inQueue(Deque<Integer> deq, int value) {

        while (deq.size() > 0 && deq.peekLast() < value) {
            deq.removeLast();
        }

        deq.addLast(value);
    }

    private static void outQueue(Deque<Integer> deq, int value) {
        if (deq.peekFirst() == value)
            deq.removeFirst();
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return new int[0];
        }

        Set<Integer> result = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[result.size()];
        int idx = 0;
        for (int k : result) {
            res[idx++] = k;
        }

        return res;
    }

    @Test
    public void test11() {
//        System.out.println(summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = 0;

        for (int i = 0; i < nums.length; i++) {
            if ((i < nums.length - 1) && nums[i + 1] - nums[i] == 1) {
                continue;
            } else {
                if (i == start) {
                    result.add(Integer.toString(nums[start]));
                } else {
                    result.add(nums[start] + "->" + nums[i]);
                }
                start = i + 1;
            }
        }
        return result;
    }

    @Test
    public void test12() {
//        System.out.println(findPoisonedDuration(new int[]{1, 2}, 2));
        int[] nums = new int[]{2, 3, 4};
        /*int[] ints = Arrays.copyOfRange(nums, 0, nums.length - 1);
        printArray(ints);*/

        System.out.println(robII(nums));

    }

    private static int robII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int pre = 0, temp = 0, cur = 0;
        for (int i : nums) {
            temp = cur;
            // dp[i] = Math.max(dp[i-1], dp[i - 2] + nums[i-1])
            cur = Math.max(pre + i, cur);
            pre = temp;
        }
        return cur;
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration <= 0) {
            return 0;
        }
        // 将攻击时间序列和持续时间生成一个 区间数组。对区间数组求交集即可。

        int n = timeSeries.length;
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i] = new int[]{timeSeries[i], timeSeries[i] + duration};
        }

        // 并集
        List<int[]> result = new ArrayList<>();
        int ans = 0;

        int[] pre = null;
        for (int[] i : intervals) {
            if (pre == null || pre[1] < i[0]) {
//                ans += i[1] - i[0];
                result.add(i);
                pre = i;
            } else {
                pre[1] = Math.max(pre[1], i[1]);
            }
        }

        /*int ans = 0;*/
        for (int[] i : result) {
            ans += i[1] - i[0];
        }
        return ans;
    }

    @Test
    public void test13() {
        char c = ' ';
        Codec cod = new Codec();
        TreeNode deserialize = cod.deserialize("10,5,#,1,100");
        System.out.println(isValidBST(deserialize));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validBST(root).bst;
    }

    static class Node {
        boolean bst;
        int max; // 子树中的最大值
        int min; // 子树中的最小值

        Node(boolean bst, int max, int min) {
            this.bst = bst;
            this.max = max;
            this.min = min;
        }
    }

    private static Node validBST(TreeNode node) {
        if (node == null) return new Node(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Node left = validBST(node.left);
        Node right = validBST(node.right);

        if (!left.bst || !right.bst) {
            return new Node(false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        if (((node.left != null && node.val <= left.max)
                || (node.right != null && node.val >= right.min))) {
            return new Node(false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        return new Node(true,
                Math.max(right.max, node.val),
                Math.min(left.min, node.val));
    }
}