package own.leetcode.complexStruct.unionFindSets;

import lombok.Data;

/**
 * 模拟tree 并查集
 */
@Data
public class DisJoinSet {

    private int[] nums;
    private int[] sets;
    private int count;

    public DisJoinSet(int n) {

        nums = new int[n];
        sets = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
            sets[i] = 1;
        }
        count = n;
    }

    public int find(int p) {

        while (p != nums[p]) {
            nums[p] = nums[nums[p]]; // 链路压缩 a -> b -> c  ==> a | b -> c
            p = nums[p];
        }

        return p;
    }

    public void union(int p, int q) {
        int p_set_index = find(p);
        int q_set_index = find(q);

        // p,q同一个集合
        if(p_set_index == q_set_index){
            return;
        }
        if (sets[p] > sets[q]) {
            nums[q_set_index] = p_set_index;
            sets[p] += sets[q];
        } else {
            nums[p_set_index] = q_set_index;
            sets[q] += sets[p];
        }
        count--;
    }

}
