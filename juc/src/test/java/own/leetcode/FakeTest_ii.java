package own.leetcode;

import org.junit.Test;
import own.jdk.OwnSyn.ListNode;

import java.util.*;
import java.util.stream.IntStream;

public class FakeTest_ii {

    @Test
    public void test2(){
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        System.out.println(operators);
    }


    public int nthSuperUglyNumber(int nth, int[] primes) {
        if (primes == null || primes.length == 0 || nth <= 0) {
            return 0;
        }
        int n = primes.length;
        List<Long> list = new ArrayList<>();
        list.add(1L);
        int[] index = new int[n];

        long[] next = new long[n];
        long num;
        while (list.size() < nth) {
            num = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                next[i] = list.get(index[i]) * primes[i];
                num = Math.min(next[i], num);
            }

            for (int i = 0; i < n; i++) {
                if (num == next[i]) index[i] += 1;
            }
            list.add(num);
        }
        return list.get(nth - 1).intValue();
    }

    @Test
    public void test() throws InterruptedException {
        nthSuperUglyNumber(12, new int[]{2,3,5});
    }

    //     todo
    public ListNode insertionSortList(ListNode head) {
        /*if (head == null || head.next == null) return head;

        ListNode dummyHead = head;
        dummyHead.next = null;

        ListNode next = head.next;
        while(next != null){
            ListNode cur = dummyHead;

            while(cur != null){
                if()
            }
        }*/
        return null;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        ListNode cur = new ListNode(0);
        ListNode dummyHead = cur;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left == null) ? right : left;
        return dummyHead.next;
    }

    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        // 同时往上走，直到找到lca
        if (root == null) return root;

        ParentTreeNode lower = A, heigher = B;
        int lenA = depth(lower);
        int lenB = depth(heigher);

        if (lenA > lenB) {
            lower = A;
            heigher = B;
        } else {
            lower = B;
            heigher = A;
        }

        while (Math.abs(lenA - lenB) > 0) {
            lower = lower.parent;
        }

        while (lower != null && heigher != null) {
            if (lower == heigher) {
                return lower;
            }
            lower = lower.parent;
            heigher = heigher.parent;
        }

        return null;
    }

    private int depth(ParentTreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth += 1;
        }
        return depth;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int toLeft = 1;
        int size = 0;
        TreeNode temp;
        while (!queue.isEmpty()) {
            size = queue.size();

            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                temp = queue.pop();
                if (toLeft == 1) {
                    list.add(temp.val);
                } else {
                    list.addLast(temp.val);
                }

                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            toLeft *= -1;
            ans.add(list);
        }
        return ans;
    }


    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;

        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (Objects.equals(strs[i], "")) {
                continue;
            }
            System.out.println(strs[i]);
            sb.append(strs[i]);
            sb.append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int start = 0, end = n - 1, mid = 0;

        // find fist positon of target , then find last position
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int l = -1, r = -1;
        if (nums[start] == target) {
            l = start;
        } else if (nums[end] == target) {
            l = end;
        } else {
            return 0;
        }

        start = 0;
        end = n - 1;
        mid = 0;

        //find last position
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[end] == target) {
            r = end;
        } else if (nums[start] == target) {
            r = start;
        }

        return r - l;
    }

    public int findFirstPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int start = 0, end = n - 1, mid = 0;

        // find fist positon of target , then find last position
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    public List<int[]> findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        if (target <= 0) return list;

        int l = 1, r = 1, half = (target + 1) / 2;
        int sum = 0, index = 0;
        for (l = 1; l <= half; l++) {
            while (sum < target && r <= half) {
                sum += r;
                r++;
            }
            if (sum == target) {
                int[] nums = new int[r - l];
                for (int i = l; i < r; i++) {
                    nums[index++] = i;
                }
                list.add(nums);
                index = 0;
            }
            if (sum >= target)
                sum -= l;
        }
        return list;
    }

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1, mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (mid == nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        System.out.println(start + "," + end);
        if (start != nums[start]) return start;
        if (end != nums[end]) return end;
        return nums.length;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        // 1: 快排的partition
        // 2: 最大堆
        if (arr == null || arr.length == 0 || k <= 0 || arr.length < k) {
            return new int[0];
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(
                Comparator.comparingInt((Integer a) -> a).reversed());

        for (int num : arr) {
            if (heap.size() < k) {
                heap.add(num);
            } else {
                if (heap.peek() > num) {
                    heap.poll();
                    heap.add(num);
                }
            }
        }
        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = heap.poll();
        }
        return ans;
    }

    public int fib(int n) {
        if (n < 0) return -1;
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int sumNums(int n) {
        boolean flag = (n > 0) && (n += (sumNums(n - 1))) > 0;
        return n;
    }

    public int minimumDeleteSum(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j == 0 ? 0 : c2[j - 1];
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = c1[i - 1];
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else {
                    // word2 添加一个元素，或者删除一个元素
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][j - 1] + c2[j - 1]);

                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][j] + c1[i - 1]);
                }
            }
        }
        printArray(dp);
        return dp[n][m];

    }

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int n = word1.length();
        int m = word2.length();
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = i;
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else {
                    // word2 添加一个元素，或者删除一个元素
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }

            }
        }
        printArray(dp);
        return dp[n][m];
    }

    private void printArray(int[][] nums) {
        for (int[] num : nums) {
            for (int i : num) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public boolean isOneEditDistance(String word1, String word2) {
        // write your code here
        if (word1 == null || word2 == null) {
            return false;
        }

        if (word1.length() == 0 && word2.length() == 0) {
            return false;
        }

        int n = word1.length();
        int m = word2.length();
        if (Math.abs(n - m) > 1) return false;

        if (n > m) {
            return isOneEditDistance(word2, word1);
        }
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        for (int i = 0; i < n; i++) {
            if (c1[i] != c2[i]) {
                if (m == n) {
                    return Objects.equals(word1.substring(i + 1),
                            word2.substring(i + 1));
                }
                return Objects.equals(word1.substring(i),
                        word2.substring(i + 1));

            }
        }
        return m != n;
    }

    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int n = s.length();
        int m = t.length();
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        //前i个字符能够出现前j个子序列的个数
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    dp[i][j] = 1; // target为空串，次数为1
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;// source字符为空，子串次数都是0
                    continue;
                }

                // i位置不等于j位置，和i位置等于j位置
                dp[i][j] += dp[i - 1][j];
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }


    public int getMinimunStringSubArray(String[] tagList, String[] allList) {
        if (allList == null || allList.length == 0 ||
                tagList == null || tagList.length == 0 ||
                tagList.length > allList.length) {
            return -1;
        }

        int n = allList.length, m = tagList.length;
        int l = 0, r = 0;
        int ans = n + 1, count = 0;

        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            countMap.put(tagList[i], countMap.getOrDefault(tagList[i], 0) + 1);
            count += 1;
        }

        String temp = null;
        for (l = 0; l < n; l++) {

            while (r < n && count > 0) {
                temp = allList[r];
                countMap.put(temp, countMap.getOrDefault(temp, 0) - 1);
                if (countMap.get(temp) >= 0) {
                    count -= 1;
                }
                r++;
            }

            if (count == 0) {
                temp = allList[l];
                ans = Math.min(ans, r - l);
                countMap.put(temp, countMap.get(temp) + 1);
                if (countMap.get(temp) > 0) {
                    count += 1;
                }
            }
        }

        return ans;
    }

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        //双指针
        int n = nums.length;
        int l = 0, r = n - 1;
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        while (l < n - 1) {
            if (nums[l] <= nums[l + 1]) {
                l++;
            } else {
                break;
            }
        }
        while (r > 0) {
            if (nums[r - 1] <= nums[r]) {
                r--;
            } else {
                break;
            }
        }
        System.out.println(l + ", " + r);
        if (r < l) return 0;
        return r - l + 1;
    }

    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // if(sum - global_min == 0) return global_max;
        // Math.max(sum - global_min, global_max);
        int n = A.length;
        int sum = A[0];
        int cur_max = A[0], global_max = cur_max;
        int cur_min = A[0], global_min = cur_min;

        for (int i = 1; i < n; i++) {
            sum += A[i];

            cur_max = Math.max(cur_max + A[i], A[i]);
            if (global_max < cur_max) {
                global_max = cur_max;
            }

            cur_min = Math.min(cur_min + A[i], A[i]);
            if (global_min > cur_min) {
                global_min = cur_min;
            }
        }
        System.out.println(global_min);
        if (sum - global_min == 0) return global_max;
        return Math.max(sum - global_min, global_max);
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        // Write your code here

        List<List<Integer>> ans = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return ans;
        }

        int n = s.length();
        int pre = 0;
        Integer[] pair = new Integer[2];
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (i - pre >= 3) {
                    pair[0] = pre;
                    pair[1] = i - 1;
                    ans.add(new ArrayList<>(Arrays.asList(pair)));
                }
                pre = i;
            }
        }

        if (n - pre >= 3) {
            pair[0] = pre;
            pair[1] = n - 1;
            ans.add(new ArrayList<>(Arrays.asList(pair)));
        }
        return ans;
    }

    /*
     * 求给出的树中如果子树是bst，求这种子树的最大节点数。
     * */
    public int largestBSTSubtree(TreeNode root) {


        return 0;
    }

   /* private Node help(TreeNode node){
        if(node == null ){
            return new Node(true, 0, 0);
        }

        Node left = help(node.left);
        Node right = help(node.right);

    }*/


    class Node {
        boolean isBST;
        int count;
        int max; // 最大

        public Node(boolean isBST, int count, int max) {
            this.isBST = isBST;
            this.count = count;
            this.max = max;
        }
    }

    class MaxQueue {

        private LinkedList<Integer> data;
        private LinkedList<Integer> max;

        public MaxQueue() {
            data = new LinkedList<>();
            max = new LinkedList<>();
        }

        public int max_value() {
            if (max.isEmpty()) {
                return -1;
            }
            return max.peek();
        }

        public void push_back(int value) {
            data.add(value);

            if (max.isEmpty()) {
                max.add(value);
                return;
            }
            while (!max.isEmpty() && max.getLast() < value) {
                max.removeLast();
            }
            max.add(value);
        }

        public int pop_front() {
            if (data.isEmpty()) {
                return -1;
            }
            int result = data.poll();
            if (max.peek().equals(result)) {
                max.poll();
            }
            return result;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    class ParentTreeNode {
        public ParentTreeNode parent, left, right;
    }

    public List<Integer> sortArray(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }


        return ans;
    }

    public void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] temp = nums.clone();
        mergeSort(nums, 0, nums.length - 1, temp);
        IntStream.of(temp).forEach(System.out::print);
    }

    public void mergeSort(int[] nums, int s, int e, int[] temp) {
        if (s >= e) return;

        int mid = (e - s) / 2 + s;
        mergeSort(nums, s, mid, temp);
        mergeSort(nums, mid + 1, e, temp);
        merge(nums, s, mid, e, temp);
    }

    private void merge(int[] nums, int s, int mid, int e, int[] temp) {
        if (s >= e) return;
        int i = s;
        int j = mid + 1;
        int index = s;
        while (i <= mid && j <= e) {
            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[index++] = nums[i++];
        }

        while (j <= e) {
            temp[index++] = nums[j++];
        }

        // copy temp back to A
        for (index = s; index <= e; index++) {
            nums[index] = temp[index];
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
