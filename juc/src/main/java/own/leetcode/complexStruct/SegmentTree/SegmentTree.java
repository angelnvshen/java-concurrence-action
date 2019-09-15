package own.leetcode.complexStruct.SegmentTree;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree {

    /**
     * 构建线段树
     *
     * @param nums   原数组
     * @param values 线段和
     * @param pos    线段和 下标
     * @param left   原数组 起始位置
     * @param right  原数组 结尾位置
     */
    public static void builtSegmentTree(int[] nums, int[] values, int pos, int left, int right) {
        if (left == right) {
            values[pos] = nums[left];
            return;
        }
        int mid = (left + right) / 2;
        builtSegmentTree(nums, values, pos * 2 + 1, left, mid);
        builtSegmentTree(nums, values, pos * 2 + 2, mid + 1, right);
        values[pos] = values[pos * 2 + 1] + values[pos * 2 + 2];
    }

    public static void print(int[] values, int pos, int left, int right, int layer) {
        for (int i = 0; i < layer; i++) {
            System.out.print("---");
        }

        System.out.print(String.format("[%d - %d] %d : %d", left, right, pos, values[pos]));
        System.out.println();
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        print(values, pos * 2 + 1, left, mid, layer + 1);
        print(values, pos * 2 + 2, mid + 1, right, layer + 1);
    }

    public static int sumRangeSegmentTree(int[] values, int pos, int left, int right, int qLeft, int qRight) {
        if (left >= qLeft && right <= qRight) {
            return values[pos];
        }
        if (qLeft > right || qRight < left) {
            return 0;
        }
        int mid = (left + right) / 2;
        return sumRangeSegmentTree(values, pos * 2 + 1, left, mid, qLeft, qRight)
                + sumRangeSegmentTree(values, pos * 2 + 2, mid + 1, right, qLeft, qRight);
    }

    public static void updateSegmentValue(int[] values, int pos, int left, int right, int index, int new_value) {
        if (left == right && left == index) {
            values[pos] = new_value;
            return;
        }
        int mid = (left + right) / 2;
        if (new_value <= mid) {
            updateSegmentValue(values, pos * 2 + 1, left, mid, index, new_value);
        } else {
            updateSegmentValue(values, pos * 2 + 2, mid + 1, right, index, new_value);
        }
        values[pos] = values[pos * 2 + 1] + values[pos * 2 + 2];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};
        int valueSiz = (int) (Math.pow(2, nums.length) - 1);
        int[] values = new int[valueSiz];
        builtSegmentTree(nums, values, 0, 0, nums.length - 1);
        print(values, 0, 0, nums.length - 1, 0);
        int sum = sumRangeSegmentTree(values, 0, 0, nums.length - 1, 2, 4);
        System.out.println(sum);
    }
}
