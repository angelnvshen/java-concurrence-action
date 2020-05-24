package own.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreatThan {

    public static void main(String[] args) {
        NextGreatThan than = new NextGreatThan();

//        than.nextGreaterElement(new int[]{1, 3, 5, 2, 4}, new int[]{5, 4, 3, 2, 1});

        String x = "1234567890ASDasd";
        for(char c : x.toCharArray()){
            System.out.println((int)c);
        }

        System.out.println(than.changeWord('Z'));
        System.out.println(than.changeWord('z'));
        char c = 'a';
        char[] result = new char[]{'a'};
        result[0] = (c = than.changeWord(c));
        System.out.println(c);
        System.out.println(result[0]);

    }

    private char changeWord(char c){
        int distance = c - 'a';
        if(distance >= 0 && distance <= 26){
            return (char)(distance + 'A');
        }else{
            distance = c - 'A';
            return (char)(distance + 'a');
        }
    }


    private boolean isNum(char c){
        return c - '0' >= 0 && c - '0' <= 9;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }

        if (nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();

        int n = nums2.length, m = nums1.length;

        // 1， 3， 4， 2 （ 0 -> n - 1)
        // 1
        // 3
        // 4
        // 4, 2
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i); // 索引和值都可
        }
        System.out.println(map);
        for (int i = 0; i < m; i++) {
            nums1[i] = map.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
