package own.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SuperPow {
    int base = 1337;

    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) return 1;
        List<Integer> list = new LinkedList<>();
        for (int i : b) list.add(i);

        return superPow(a, list);
    }

    private int superPow(int a, List<Integer> b) {
        if (b == null || b.size() == 0) return 1;

        int last = b.remove(b.size() - 1);

        // (a*b) % k = (a % k) * (b % k) % k
        int part1 = helper(a, last);
        int part2 = helper(superPow(a, b), 10);

        return (part1 * part2) % base;
    }

    private int helper(int a, int k) {
        a %= base;

        int res = 1;
        for (int i = 0; i < k; i++) {
            res *= a;
            res %= base;
        }

        return res;
    }

    public static void main(String[] args) {
        SuperPow superPow = new SuperPow();
//        superPow.removeDuplicates(new int[]{1, 1, 2});
//        superPow.removeDuplicates_2(new int[]{0,0,1,1,1,1,2,3,3});
//        System.out.println(superPow.removeDuplicateLetters("cbacdcbc"));

//        System.out.println(superPow.minEatingSpeed(new int[]{30,11,23,4,20}, 6));
//        System.out.println(superPow.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));

//        System.out.println(superPow.longestPalindrome("cbbd"));

        System.out.println(superPow.jump(new int[]{2,3,1,1,4}));
    }

    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int len = nums.length;
        int[] distance = new int[len];
        for(int i = 0; i < len; i ++){
            distance[i] = i + nums[i];
        }

        int steps = 0;
        int maxIndex = 0;
        int end = 0;
        for(int i = 0; i < len - 1; i ++){
            // 贪心算法
            maxIndex = Math.max(maxIndex, distance[i]);
            if(end == i){
                steps += 1;
                end = maxIndex;
            }
        }

        return steps;
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        // 先求出每个元素最远跳至的位置
        // 2, 3, 1, 1, 4
        // 2, 4, 3, 4, 8
        int len = nums.length;
        int[] distance = new int[len];

        for (int i = 0; i < len; i++) {
            distance[i] = i + nums[i];
        }

        // 3 2 1 0 4
        // 3 3 3 3 4
        int maxIndex = 0;
        for (int i = 0; i < len; i++) {
            if (i > maxIndex) {
                return false;
            }
            maxIndex = Math.max(maxIndex, distance[i]);
        }
        return true;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;

        String ans = "";
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 以i为中心的回文子串
            String s1 = palindrome(chars, i, i);
            // 以i, i + 1为中心的回文子串
            String s2 = palindrome(chars, i, i + 1);

            ans = ans.length() < s1.length() ? s1 : ans;
            ans = ans.length() < s2.length() ? s2 : ans;
        }

        return ans;
    }

    private String palindrome(char[] chars, int i, int j) {
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            i--;
            j++;
        }

        return new String(chars, i + 1, j - i - 1);
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index;
    }

    public int removeDuplicates_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int index = 0, count = 2;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[++index] = nums[i];
                count = 2;
            } else {
                count--;
                if (count > 0) {
                    nums[++index] = nums[i];
                }
            }
        }
        return index;
    }

    public String removeDuplicateLetters(String s) {

        if (s == null || s.length() == 0) return s;

        Stack<Character> stack = new Stack<>();
        //统计字符个数
        int[] count = new int[26];
        //标记字符是否在栈中
        boolean[] mark = new boolean[26];

        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }

        for (char c : chars) {
            //对于栈中已存在，重复出现的字符跳过
            if (mark[c - 'a']) {
                count[c - 'a']--;
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                mark[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            count[c - 'a']--; // 字符第一次入栈
            mark[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    /*public int minEatingSpeed(int[] piles, int H) {
        if(piles == null || piles.length == 0) return 0;

        // 核心:二分查找 max(piles(i)) ~ sum(piles(i)), 要求最小速度
        int length = piles.length;
        int max = 0, sum = 0;
        for(int i : piles){
            max = Math.max(max, i);
            sum += i;
        }

        if(length > H) return max;

        int left = 1, right = max, mid = 0;

        while(left + 1 < right){
            mid = left + (right - left) / 2;
            if(valid(mid, piles, H)){
                right = mid;
            }else{
                left = mid;
            }
        }
        if(valid(left, piles, H)) return left;
        if(valid(right, piles, H)) return right;
        return 0;
    }

    private boolean valid(int speed, int[] piles, int h){
        int need = 0; //需要的时间
        for(int i : piles){
            if(i > speed){
                need += i / speed + ((i % speed > 0) ? 1 : 0);
            }else{
                need += 1;
            }
        }
        return need <= h;
    }*/

    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length == 0) return 0;

        int max = 0, sum = 0;
        for (int i : weights) {
            max = Math.max(max, i);
            sum += i;
        }
        int left = max, right = sum, mid = 0;

        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (valid(mid, weights, D)) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (valid(left, weights, D)) return left;
        if (valid(right, weights, D)) return right;
        return 0;
    }

    private boolean valid(int delivery, int[] weights, int day) {
        int needDay = 1;
        int sum = 0;
        for (int i : weights) {
            if (sum + i <= delivery) {
                sum += i;
            } else {
                needDay += 1;
                sum = i;
            }
        }
        return needDay <= day;
    }
}
