package own.leetcode.array;

public class BitOperator {

    public static void main(String[] args) {
        BitOperator operator = new BitOperator();
//        System.out.println(operator.singleNumber(new int[]{4, 1, 2, 1, 2}));
//        System.out.println(operator.singleNumber_ii(new int[]{0, 1, 0, 1, 0, 1, 99}));
//        System.out.println(operator.singleNumber_ii(new int[]{0, 0, 0, 9}));


        StringBuilder sb = new StringBuilder("234");
        System.out.println(sb.reverse().toString());
        /*int[] bits = new int[32];
        int op = 1;
        int num = 15;
        for (int j = 31; j >= 0; j--) {
            op = 1 << j;
            if ((op & num) != 0)
                bits[j] += 1;
        }
        System.out.println(bits);*/
    }

    /**
     * 除了某个元素只出现一次以外，其余每个元素均出现了三次
     * <p>
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     *
     * @param nums
     * @return
     */
    public int singleNumber_ii(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // 按位统计个数（二进制）
        int[] bits = new int[32];
        for (int i : nums) {
            int op = 1;
            for (int j = 31; j >= 0; j--) {
                op = 1 << j;
                if ((op & i) != 0)
                    bits[j] += 1;
            }
        }
        int ans = 0;
        int op = 1;
        for (int i = 0; i < 32; i++) {
            op = 1 << i;
            if ((bits[i] % 3) == 1) {
                ans |= op;
            }
        }

        return ans;
    }

    /**
     * 除了某个元素只出现一次以外，其余每个元素均出现两次
     * <p>
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        for (int i : nums)
            ans ^= i;

        return ans;
    }

}
