package own.leetcode.tree.binaryIndexTree;

public class BinaryIndexTree {

    private int preSum(int i) {
        for (; i >= 0; i -= lowBit(i)) {

        }
        return 0;
    }

    private int lowBit(int x) {
        return x & -x;
    }
}
