package own.leetcode.sort;

import java.util.stream.IntStream;

public class LargestNumber {

    public static void main(String[] args) {
        LargestNumber number = new LargestNumber();
        System.out.println(number.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        IntStream.of(nums).boxed().sorted((a, b) -> {
            return (b + "" + a).compareTo(a + "" + b);
        }).forEach(a -> sb.append(a));


        return sb.toString();
    }
}