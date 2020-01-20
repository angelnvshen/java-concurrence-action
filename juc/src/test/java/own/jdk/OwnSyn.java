package own.jdk;

import org.junit.Test;

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
            TreeNodeHence nodeHence = stack.peek();
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
    static class TreeNodeHence {
        TreeNode node;
        boolean visited;

        TreeNodeHence(TreeNode node, boolean visited) {
            this.node = node;
            this.visited = visited;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
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
                TreeNode node = list.pop();
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
        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
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


    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        // find first element <= target （找到第一个 <= 最右的元素就是最小元素）
        while(start + 1 < end){
            int mid = start + (end - start) /2;
            if(nums[mid] <= target){
                end = mid;
            }else{
                start = mid;
            }
        }

        return Math.min(nums[start], nums[end]);
    }
}

