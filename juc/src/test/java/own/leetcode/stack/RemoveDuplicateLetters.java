package own.leetcode.stack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
//        RemoveDuplicateLetters letters = new RemoveDuplicateLetters();
//        System.out.println(letters.removeDuplicateLetters("bcabc"));

        int[] nums = new int[]{1, 20, 23, 4, 8};
        StringBuilder sb = new StringBuilder();
        IntStream.of(nums).boxed().sorted((a, b) -> (b + "" + a).compareTo(a + "" + b)).forEach(a -> sb.append(a));
        System.out.println(sb.toString());

        List<Integer> list = IntStream.of(nums).boxed().sorted((a, b) -> (b + "" + a).compareTo(a + "" + b)).collect(Collectors.toList());
        list.stream()
                .sorted(new Comparators())
                .collect(Collectors.toList());

        int time = 1;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            if(iterator.next() % 3 == 1){
                iterator.remove();

            }
        }

    }

    /**
     * @param s: a string
     * @return: return a string
     */
    public String removeDuplicateLetters(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] cs = s.toCharArray();
        int n = cs.length;

        Map<Character, Integer> map = new HashMap<>();
        boolean[] vis = new boolean[26];

        Stack<Character> stack = new Stack<>();
        for (char c : cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : cs) {
            map.put(c, map.get(c) - 1);

            if (vis[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && c < stack.peek() &&
                    map.get(stack.peek()) > 0) {

                char temp = stack.pop();
                vis[temp - 'a'] = false;
            }
            stack.push(c);
            vis[c - 'a'] = true;
        }
        System.out.println(stack);
        StringBuffer sb = new StringBuffer();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    static class Comparators implements Comparator<Integer>{
        public int compare(Integer o1, Integer o2){
            return (o2 + "" + o1).compareTo(o1 + "" + o2);
        }
    }
}