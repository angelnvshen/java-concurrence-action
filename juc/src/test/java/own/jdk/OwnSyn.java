package own.jdk;

import lombok.Data;
import org.junit.Test;
import own.TreeNode;

import java.util.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class OwnSyn extends AbstractQueuedSynchronizer {

    public void abd() {

    }

    static final int SHARED_SHIFT = 16;
    static final int SHARED_UNIT = (1 << SHARED_SHIFT);
    static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public static void main(String[] args) throws Exception {
    /*Class<?>[] declaredClasses = AbstractQueuedSynchronizer.class.getDeclaredClasses();
    for (Class aClass : declaredClasses) {
      if (aClass.getName().contains("AbstractQueuedSynchronizer$Node")) {
        Object newInstance = aClass.newInstance();
        System.out.println(newInstance);
      }
    }*/

        System.out.println(SHARED_SHIFT);
        System.out.println(SHARED_UNIT);
        System.out.println(MAX_COUNT);
        System.out.println(EXCLUSIVE_MASK);

        System.out.println(sharedCount(65536 * 8));
    }

    static int sharedCount(int c) {
        return c >>> SHARED_SHIFT;
    }

    @Test
    public void test1() {
//        System.out.println(isValid("([)"));
//        System.out.println(isValid("()[]{}"));
//        System.out.println(isValid("]"));

        /*LRUCache cache = new LRUCache(2);
//        LRULinkedHashMap cache = new LRULinkedHashMap(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

//        [null,null,null,1,null,-1,null,-1,3,4]*/

//        ["MyQueue","push","push","peek","push","peek"]
//[[],[1],[2],[],[3],[]]
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        queue.push(3);
        System.out.println(queue.peek());
    }

    public static boolean isValid2(String s) {
        return false;
    }

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        char[] chars = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();

        Character last = null;
        for (char c : chars) {
            if (!stack.isEmpty() && (last = stack.getLast()) != null &&
                    ((c == ')' && '(' == last) ||
                            (c == ']' && '[' == last) ||
                            (c == '}' && '{' == last))
            ) {
                stack.removeLast();
                last = null;
                continue;
            } else {
                stack.add(c);
            }
        }
        return stack.isEmpty();
    }

    static class LRUCache {
        LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };

        }

        public int get(int key) {
            Integer integer = cache.get(key);
            return integer == null ? -1 : integer;
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }

    static class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

        private int capacity;

        LRULinkedHashMap(int capacity) {
            // 初始大小，0.75是装载因子，true是表示按照访问时间排序
            super(capacity, 0.75f, true);
            //传入指定的缓存最大容量
            this.capacity = capacity;
        }

        /**
         * 实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    static class MyQueue {

        LinkedList<Integer> in;
        LinkedList<Integer> out;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            in = new LinkedList();
            out = new LinkedList();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            in.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            moveInToOut();
            return out.pop();
        }

        private void moveInToOut() {
            if (!out.isEmpty()) {
                return;
            }
            while (!in.isEmpty()) {
                int temp = in.pop();
                out.push(temp);
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            moveInToOut();
            return out.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return out.isEmpty() && in.isEmpty();
        }
    }

    @Test
    public void test2() {
        TreeNode node = new TreeNode(3);

        TreeNode node2 = new TreeNode(20);
        node2.left = new TreeNode(15);
        node2.right = new TreeNode(7);

        node.left = new TreeNode(9);
        node.right = node2;
//        preorderTraversal(node);

        List<List<Integer>> lists = zigzagLevelOrder(node);
        lists.stream().forEach(list -> {
            list.stream().forEach(ele -> {
                System.out.print(ele + ", ");
            });
            System.out.println();
        });
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();

        //recurision
        //preOrder(root, result);

        //iteration
        LinkedList<TreeNodeHence> stack = new LinkedList();
        stack.push(new TreeNodeHence(root, false));
        while (!stack.isEmpty()) {
            TreeNodeHence<Integer> nodeHence = stack.peek();
            // if(!nodeHence.visited){
            //     stack.pop();
            //     continue;
            // }
            TreeNode node = nodeHence.node;
            if (node == null) {
                stack.pop();
                continue;
            }
            nodeHence.visited = true;
            result.add(nodeHence.node.val);
            stack.pop();

            stack.push(new TreeNodeHence(node.right, false));
            stack.push(new TreeNodeHence(node.left, false));
        }

        return result;
    }

    //iteration
    static class TreeNodeHence<T> {
        TreeNode<T> node;
        boolean visited;

        TreeNodeHence(TreeNode node, boolean visited) {
            this.node = node;
            this.visited = visited;
        }
    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        int flag = 1; // 1: 从左往右；-1：从右往左
        while (!list.isEmpty()) {

            LinkedList<Integer> temp = new LinkedList<>();
            int current_level_size = list.size();
            for (int i = 0; i < current_level_size; i++) {
                TreeNode<Integer> node = list.pop();
                if (node == null) {
                    continue;
                }
                if (flag == 1) {
                    temp.add(node.val);
                } else {
                    temp.addFirst(node.val);
                }
                list.add(node.left);
                list.add(node.right);
            }
            flag = flag * -1;
            if (temp.size() > 0) {
                result.add(temp);
            }
        }

        return result;
    }

    static class MinStack {
        LinkedList<Integer> data;
        LinkedList<Integer> minData;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new LinkedList<>();
            minData = new LinkedList<>();
        }

        public void push(int x) {
            data.push(x);
            if (!minData.isEmpty()) {
                int minV = minData.peek();
                if (minV > x) {
                    minV = x;
                }
                minData.push(minV);
            } else {
                minData.push(x);
            }
        }

        public void pop() {
            data.pop();
            minData.pop();
        }

        public int top() {
            Integer value = data.peek();
            if (value == null) {
                throw new NoSuchElementException();
            }
            return value;
        }

        public int getMin() {
            Integer min = minData.peek();
            if (min == null) {
                throw new NoSuchElementException();
            }
            return min;
        }
    }

    @Test
    public void test3() {
        /*MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-1);
        System.out.println(stack.getMin());
        System.out.println(stack.top());
        stack.pop();
        System.out.println(stack.getMin());*/
//        System.out.println((char)97);
//        search(new int[]{5,7,7,8,8,10}, 8);

//        int[] range = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
//        System.out.println(range[0] + ", " + range[1]);

        System.out.println(Integer.toBinaryString(10));

        String binaryString2 = Integer.toBinaryString(Integer.reverseBytes(10));
        System.out.println(binaryString2.length());
        System.out.println(binaryString2);

        String binaryString = Integer.toBinaryString(Integer.reverse(10));
        System.out.println(binaryString.length());
        System.out.println(binaryString);

        int n = 100 - 1;

    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            // equals (start + end)/2;
            // 可以防止溢出
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        System.out.println(start);
        System.out.println(end);

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (nums == null || nums.length == 0) {
            return result;
        }

        int startPostion = -1;

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] == target) {
            startPostion = start;
        } else if (nums[end] == target) {
            startPostion = end;
        }
        result[0] = startPostion;


        int endPostion = -1;

        start = 0;
        end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[end] == target) {
            endPostion = end;
        } else if (nums[start] == target) {
            endPostion = start;
        }
        result[1] = endPostion;

        return result;
    }

    @Test
    public void test4() {
//        System.out.println(hammingWeight(-3));
//        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(findMin(new int[]{1, 3, 3}));
        System.out.println(findMin(new int[]{1, 3, 5}));
        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));
        System.out.println(findMin(new int[]{3, 3, 1, 3}));
        System.out.println(findMin(new int[]{10, 1, 10, 10, 10}));
    }

    public static int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {

            if ((n & 1) == 1) {// 偶数
                result += 1;
            }
            n >>>= 1;
        }
        return result;
    }

    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }


    public static int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        int target = nums[end];
        // find first element <= target （找到第一个 <= 最右的元素就是最小元素）
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return Math.min(nums[start], nums[end]);
    }

    @Test
    public void test5() {

//        HashMap map = new HashMap(75);
//        System.out.println(map.size());

//        new Pattern("[0-9]");s
//        System.out.println("hello".indexOf("lx"));

        //1->2->3->4->5
//        ListNode head = new ListNode(1);
//        head.next(new ListNode(2)).next(new ListNode(3)).next(new ListNode(4)).next(new ListNode(5));

//        removeNthFromEnd(head, 2);
//        removeNthFromEnd(head, 1);

//        1->2->4, 1->3->4

        /*ListNode l1 = new ListNode(1);
        l1.next(new ListNode(2)).next(new ListNode(4));
        ListNode l2 = new ListNode(1);
        l2.next(new ListNode(3)).next(new ListNode(4));
        ListNode listNode = mergeTwoLists(l1, l2);
        ListNode.print(listNode);*/

        /*ListNode l1 = new ListNode(1);
        l1.next(new ListNode(2)).next(new ListNode(1));

        System.out.println(isPalindrome(l1));*/

        System.out.println(11 & 1);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode next(ListNode next) {
            this.next = next;
            return next;
        }

        static void print(ListNode head) {
            while (head != null) {
                System.out.print(head.val + ", ");
                head = head.next;
            }
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        if (head != null && head.next == null) {
            return true;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        while (head != null) {
            if (!stack.isEmpty()) {
                Integer value = stack.peek();
                if (value == head.val) {
                    stack.pop();
                } else {
                    stack.push(head.val);
                }
            } else {
                stack.push(head.val);
            }
            head = head.next;
        }
        return stack.isEmpty() || stack.size() == 1;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            } else if (l2.val > l1.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;

                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }

        return newHead.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode cur = dummy;
        ListNode theNNode = dummy;
        // 给定的 n 保证是有效的。
        int count = -n;
        while (cur.next != null) {
            cur = cur.next;
            count += 1;
            if (count > 0) {
                theNNode = theNNode.next;
            }
        }
        // theNNode 是 需要寻找的节点的前一个节点
        if (theNNode.next != null) {
            theNNode.next = theNNode.next.next;
        }

        return dummy.next;
    }

    public static int arrangeCoins(int n) {

        if (n <= 1) {
            return n;
        }
        int target = 0;
        int i = 1;
        while (true) {

            if (target + i > n) {
                break;
            }
            target += i;
            i += 1;
        }

        return i;
    }

    @Test
    public void test6() {
        ListNode head = new ListNode(1);
        head
                .next(new ListNode(2))
                .next(new ListNode(3))
                /*.next(new ListNode(4))
                .next(new ListNode(5))
                .next(new ListNode(6))*/;
        /*ListNode middleNode = middleNode(head);
        ListNode.print(middleNode);*/

        ListNode cur = head;
        ListNode newHead = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }

        System.out.println(newHead);

    }

    public static ListNode middleNode(ListNode head) {
        int len = lenOfLinkedList(head);
        int mid;
        /*if ((len & 1) == 1) { //奇数
            mid = (len >> 1) + 1;
        } else {
            mid = len >> 1;
        }*/
        mid = len >> 1;

        while (mid > 0) {
            head = head.next;
            mid -= 1;
        }
        return head;
    }

    private static int lenOfLinkedList(ListNode list) {
        int length = 0;
        while (list != null) {
            length += 1;
            list = list.next;
        }

        return length;
    }

    @Test
    public void test7() {
        ListNode head = new ListNode(1);
        head
                .next(new ListNode(1))
                .next(new ListNode(1))
                .next(new ListNode(4))
                /*.next(new ListNode(5))
                .next(new ListNode(6))*/;

        // System.out.println(getDecimalValue(head));
        ListNode listNode = removeElements(head, 1);
        ListNode.print(listNode);

    }

    public static int getDecimalValue(ListNode head) {
        if (head == null) {
            return 0;
        }

        int result = 0;
        while (head != null) {
            result = (result << 1) + head.val;
            head = head.next;
        }

        return result;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            if (next == null) {
                break;
            }

            if (next.val == val) {
                cur.next = next.next;
            } else {
                cur = next;
            }
        }

        return dummyHead.next;
    }

    @Test
    public void test8() {
        //[1,2,3,4,null,null,5]
        TreeNode root = new TreeNode(5);
        TreeNode rl = new TreeNode(1);
        TreeNode rr = new TreeNode(4);
        root.left = rl;
        root.right = rr;


        TreeNode rrl = new TreeNode(3);
        rr.left = rrl;
        TreeNode rrr = new TreeNode(6);
        rr.right = rrr;

//        BTreePrinter.printNode(root);

//        System.out.println(maxDepth(root));

        BTreePrinter.printNode(root);
        System.out.println(isValidBST(root));

    }

    public static int maxDepth(TreeNode root) {

        int deep = 0;
        if (root == null) {
            return deep;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        while (list.size() > 0) {
            int cur_size = list.size();
            for (int i = 0; i < cur_size; i++) {
                TreeNode node = list.pop();
                System.out.println(node.val);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
            deep += 1;
            System.out.println(" ==== ");
        }

        return deep;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        /// 中序遍历，前一个节点元素小于后一个即可
        int cur = 0;
        int prev = 0;

        LinkedList<TreeNodeHence2> list = new LinkedList<>();
        list.push(new TreeNodeHence2(root, 0));

        List<Integer> values = new ArrayList();

        while (list.size() > 0) {
            TreeNodeHence2<Integer> temp = list.pop();
            System.out.println(temp);
            if (temp.node == null) {
                continue;
            }

            if (temp.operate == 1) {
                values.add(temp.node.val);
                continue;
            }

            list.push(new TreeNodeHence2(temp.node.right, 0));
            list.push(new TreeNodeHence2(temp.node, 1));
            list.push(new TreeNodeHence2(temp.node.left, 0));
        }

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) <= values.get(i - 1)){
                return false;
            }
        }

        return true;
    }

    @Data
    class TreeNodeHence2<T> {
        TreeNode<T> node;
        int operate; // 0 - visit ; 1 - print

        TreeNodeHence2(TreeNode node, int operate) {
            this.node = node;
            this.operate = operate;
        }
    }

    @Test
    public void test9(){
        /*int [] nums1 = new int[]{1,2,3,0,0,0};
        int [] nums2 = new int[]{2,5,6};
        merge(nums1, 3 , nums2, 3);*/

        int [] nums1 = new int[]{0};
        int [] nums2 = new int[]{1};
        merge(nums1, 0 , nums2, 1);
        System.out.println(nums1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1.length < m + n){
            return;
        }

        int i = m - 1;
        int j = n - 1;
        int p = m + n - 1;
        while(i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[p] = nums1[i];
                p --;
                i --;
            }else if(nums1[i] < nums2[j]){
                nums1[p] = nums2[j];
                p --;
                j --;
            }else{
                nums1[p] = nums1[i];
                nums1[p - 1] = nums2[j];
                i --;
                j --;
                p -= 2;
            }
        }

        while(i >= 0){
            nums1[p] = nums1[i];
            p --;
            i --;
        }

        while(j >= 0){
            nums1[p] = nums2[j];
            p --;
            j --;
        }
    }
}

