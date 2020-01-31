package own.jdk;

import org.junit.Test;

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

}
