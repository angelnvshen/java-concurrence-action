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
        int pid = find(p);
        int qid = find(q);

        // p,q同一个集合
        if(pid == qid){
            return;
        }
        if (sets[pid] > sets[qid]) {
            nums[qid] = pid;
            sets[pid] += sets[qid];
        } else {
            nums[pid] = qid;
            sets[qid] += sets[pid];
        }
        count--;
    }

}
