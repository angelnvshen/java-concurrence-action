package own.jdk;

import org.junit.Test;
import own.TreeNode;
import own.jdk.OwnSyn.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class OwnSyn2 {

    @Test
    public void test1() {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void test2() {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("ccc"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] map = new char[128];
        int result = 0;
        int max_result = 0;

        char[] chars = s.toCharArray();
        int p = 0;

        for (int i = 0; i < chars.length; i++) {
            map[chars[i]]++;
            if (map[chars[i]] == 1) {
                max_result = Math.max(max_result, i - p + 1);
            }
            if (map[chars[i]] == 2) {
                while (p <= i) {
                    map[chars[p]]--;
                    if (chars[p] == chars[i]) {
                        p++;
                        break;
                    }
                    p++;
                }
            }
        }
        return max_result;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String result = "";

        char[] chars = s.toCharArray();
        int len = chars.length;
        int i, j;
        // 以字符i为中心，向两边扩展，如果 chars[i + 1] != char[i - 1], break;
        for (int k = 0; k < len; k++) {
            i = k;
            j = k;
            while (j >= 0 && i <= len - 1) {
                if (chars[j] == chars[i]) {
                    if (result.length() < i + 1 - j) {
                        result = s.substring(j, i + 1);
                    }
                    j--;
                    i++;
                } else {
                    break;
                }
            }
        }

        // 以字符间隙为中心，向两边扩展，如果 chars[i] != char[i - 1], break;
        for (int k = 1; k < len; k++) {
            i = k;
            j = k - 1;
            while (j >= 0 && i <= len - 1) {
                if (chars[j] == chars[i]) {
                    if (result.length() < i + 1 - j) {
                        result = s.substring(j, i + 1);
                    }
                    j--;
                    i++;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    @Test
    public void test3() {
//        System.out.println(lengthOfLongestPalindrome("abccccdd"));
        System.out.println(lengthOfLongestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }

    public int lengthOfLongestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] map = new char[128];
        char[] chars = s.toCharArray();

        for (char c : chars) {
            map[c]++;
        }

        int total_even = 0;
        int total_odd = 0;
        boolean hadOdd = false;
        for (int i = 0; i < map.length; i++) {
            if ((map[i] & 1) == 0) {
                total_even += map[i];
            } else {
                hadOdd = true;
                total_odd += Math.max(0, map[i] - 1);
            }
        }

        return total_odd + total_even + ((hadOdd) ? 1 : 0);
    }

    @Test
    public void test4() {

        /*//2 -> 4 -> 3) + (5 -> 6 -> 4
        ListNode l1 = new ListNode(2);
        l1.next(new ListNode(4)).next(new ListNode(5));
        ListNode l2 = new ListNode(5);
        l2.next(new ListNode(6)).next(new ListNode(4));*/

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next(new ListNode(9));

        ListNode addTwoNumbers = addTwoNumbers(l1, l2);
        ListNode.print(addTwoNumbers);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode result = new ListNode(0);
        ListNode cur = result;

        boolean incr = false;
        while (l1 != null && l2 != null) {

            int carry = incr ? 1 : 0;
            cur.next = new ListNode((carry + l1.val + l2.val) % 10);
            incr = (carry + l1.val + l2.val) / 10 > 0;

            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int carry = incr ? 1 : 0;
            cur.next = new ListNode((carry + l1.val) % 10);
            incr = (carry + l1.val) / 10 > 0;

            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int carry = incr ? 1 : 0;
            cur.next = new ListNode((carry + l2.val) % 10);
            incr = (carry + l2.val) / 10 > 0;

            cur = cur.next;
            l2 = l2.next;
        }

        if (incr) {
            cur.next = new ListNode(1);
            cur = cur.next;
        }

        return result.next;
    }

    @Test
    public void test5() {
        /*ListNode l1 = new ListNode(1);
        l1.next(new ListNode(2)).next(new ListNode(3))
                .next(new ListNode(4)).next(new ListNode(5));

        ListNode oddEvenList = oddEvenList(l1);
        ListNode.print(oddEvenList);*/

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(3);


    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //输入: 2->1->3->5->6->4->7->NULL
        //输出: 2->3->6->7->1->5->4->NULL
        ListNode odd = head; // index 1
        ListNode even = odd.next;// index 2
        ListNode cur = even.next;
        int index = 3;
        ListNode temp = null;
        while (cur != null) {
            ListNode.print(head);
            temp = cur.next;
            if ((index & 1) == 1) {
                even.next = cur.next; // 断掉 cur的前驱节点
                cur.next = odd.next;  // 将 cur节点放置在odd节点之后，并odd重新定位到cur节点
                odd.next = cur;
                odd = cur;
            } else {
                even = even.next;
            }
            cur = temp;
            index += 1;
        }
        return head;
    }

    @Test
    public void test6() {
        System.out.println(permute(new int[]{1, 2, 3}));

    }

    public static int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a);

        addToQueue(root, queue);

        return queue.peek();
    }

    private static void addToQueue(TreeNode<Integer> root, PriorityQueue<Integer> queue) {
        if (root == null) {
            return;
        }
        addToQueue(root.left, queue);
        queue.add(root.val);
        addToQueue(root.right, queue);
    }

    /*public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }

        List<Integer> data = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        generate(0, 0, nums, data, result);

        return result;
    }

    private static void generate(int index, int count, int[] nums, List<Integer> data, List<List<Integer>> result){

        if(data.size() == nums.length){
            result.add(new ArrayList<>(data));
            return;
        }

        index = index % nums.length;
        System.out.println(index);
        data.add(nums[index]);
        generate(index + 1, count + 1, nums, data, result);

        data.remove(data.size() - 1); // 回溯
        generate(index + 1, count, nums, data, result);

    }*/

    public List<List<Integer>> permute(int[] nums) {
        // 交换法 回溯
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        int len = nums.length;
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());

        perm(numsList, 0, len - 1, result);

        return result;
    }

    private void perm(List<Integer> nums, int s, int e, List<List<Integer>> result) {

        if (s == e) {
            result.add(new ArrayList<>(nums));
            return;
        }

        for (int i = s; i <= e; i++) {
            // swap(nums, s, i);
            Collections.swap(nums, s, i);
            perm(nums, s + 1, e, result);
            // swap(nums, s, i);
            Collections.swap(nums, s, i);
        }
    }

    @Test
    public void test7() {
        System.out.println(generate(3));
    }

    public static List<String> generate(int n) {
        List<String> result = new ArrayList<>();

        if (n <= 0) {
            return result;
        }

        generate("", 2, result, 0, 0);

        return result;
    }

    // 左括号要先于右括号放入，并且左括号和右括号数量相等
    private static void generate(String item, int n, List<String> result, int leftNum, int rightNum) {
        if (rightNum > leftNum) {
            return;
        }
        if (item.length() == 2 * n) {
            result.add(item);
            return;
        }
        // 剪枝：
        if (leftNum < n) {
            generate(item + "(", n, result, leftNum + 1, rightNum);
        }
        if (leftNum >= rightNum) {
            generate(item + ")", n, result, leftNum, rightNum + 1);
        }
    }

    private static void generate(String item, int n, List<String> result, Stack<String> stack) {
        if (item.length() == 2 * n) {
            if (stack.isEmpty()) {
                result.add(item);
            }
            return;
        }
        // 剪枝：
        if (stack.isEmpty() || stack.peek().equals("(")) {
            stack.push("(");
            generate(item + "(", n, result, stack);
        }
        if (!stack.isEmpty() && stack.peek().equals("(")) {
            stack.pop();
            generate(item + ")", n, result, stack);
        }
    }

    @Test
    public void test8() {

        /*List<String> list = Arrays.asList(1 + "", 2 + "", 3 + "");
        String join = String.join("->", list);
        System.out.println(join);*/

        int[][] mark = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        putTheQueue(3, 5, mark);

        int[][] tempMark = cloneMark(mark);
        printMatrix(mark);
        System.out.println();
        putTheQueue(1, 4, mark);
        printMatrix(tempMark);
        System.out.println();
        printMatrix(mark);
        System.out.println();
    }

    private static int[][] cloneMark(int[][] mark) {
        int len = mark.length;
        int[][] result = new int[len][len];

        for (int i = 0; i < len; i++) {
            result[i] = Arrays.copyOf(mark[i], mark[i].length);
        }

        return result;
    }


    private static List<String> formatResult(String [][] position){
        List<String> result = new ArrayList();
        for(int i = 0; i < position.length; i++){
            result.add(String.join("", position[i]));
        }
        return result;
    }

    // 八个方向的方向数组
    public static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    public static int[] dy = {1, -1, 0, 0, 1, -1, -1, 1};

    public static void putTheQueue(int x, int y, int[][] mark) {
        mark[x][y] = 1;
        for (int i = 1; i < mark.length; i++) { // 每个方向需要向外延伸 1 至 N - 1
            for (int j = 0; j < 8; j++) {
                int newX = x + i * dx[j];
                int newY = y + i * dy[j];
                if (newX >= 0 && newX < mark.length
                        && newY >= 0 && newY < mark[i].length) {
                    mark[newX][newY] = 1;
                }
            }
        }
    }

    public static void printMatrix(int[][] mark) {
        for (int i = 0; i < mark.length; i++) {
            for (int j = 0; j < mark[i].length; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if(n <= 0){
            return result;
        }

        // init： mark 表示格子是否是皇后的攻击范围；position 表示皇后的位置
        int[][] mark = new int[n][n];
        String[][] position = new String[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < mark[i].length; j ++){
                mark[i][j] = 0;
                position[i][j] = ".";
            }
        }

        solution(n, 0, mark, position, result);

        return result;
    }

    // k 表示第k个皇后
    // 解决第k个皇后放置的位置
    private static void solution(int n, int k, int[][] mark, String[][] position, List<List<String>> result){
        if(n == k){
            result.add(formatResult(position));
            return;
        }

        // 尝试 0 至 n - 1列
        for(int i = 0; i < n; i++){
            if(mark[k][i] == 0){
                int[][] tempMark = cloneMark(mark);
                position[k][i] = "Q";
                putTheQueue(k, i, mark);

                solution(n, k + 1, mark, position, result);
                // 回溯
                mark = tempMark;
                position[k][i] = ".";
            }
        }
    }

    @Test
    public void test9(){
        System.out.println(solveNQueens(4));
    }
}

