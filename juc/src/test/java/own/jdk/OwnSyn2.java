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
    public void test7(){
        System.out.println(generate(3));
    }

    public static List<String> generate(int n) {
        List<String> result = new ArrayList<>();

        if (n <= 0) {
            return result;
        }

        generate("", 2, result);

        return result;
    }

    private static void generate(String item, int n, List<String> result) {
        if (item.length() == 2 * n) {

            result.add(item);
            return;
        }

        generate(item + "(", n, result);
        generate(item + ")", n, result);
    }
}

