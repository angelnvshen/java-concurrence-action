package own.leetcode.complexStruct.unionFindSets;

import lombok.Data;

@Data
public class DisJoinSetSimple {

    private int[] nums;

    public DisJoinSetSimple(int n) {
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
    }

    public int find(int p) {
        return nums[p];
    }

    public void union(int p, int q) {
        int p_set_index = find(p);
        int q_set_index = find(q);
        if (p_set_index == q_set_index) {
            return;
        }
        // 将所有属于p 集合的下标修改为 q集合下标
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == p_set_index) {
                nums[i] = q_set_index;
            }
        }
    }

    public void print_set(int[] nums) {
        System.out.print("元素：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("集合：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DisJoinSetSimple simple = new DisJoinSetSimple(8);
        simple.print_set(simple.nums);
        System.out.println(String.format("union(%d, %d)", 2, 5));
        simple.union(0, 5);
        simple.print_set(simple.nums);

        System.out.println(String.format("union(%d, %d)", 2, 4));
        simple.union(2, 4);
        simple.print_set(simple.nums);

        System.out.println(String.format("union(%d, %d)", 0, 4));
        simple.union(0, 4);
        simple.print_set(simple.nums);
    }
}
