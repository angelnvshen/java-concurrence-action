package own.leetcode.array;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductExceptSelf {

    public static void main(String[] args) {
        ProductExceptSelf self = new ProductExceptSelf();
        System.out.println(
                IntStream.of(self.productExceptSelf(new int[]{1, 2, 3, 4})).boxed().collect(Collectors.toList()));

    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        // cur 乘积  = left 乘积 * right 乘积

        int n = nums.length;

        int[] result = new int[n];
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int[] right = new int[n];
        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
            result[i] = result[i] * right[i];
        }

        return result;
    }
}