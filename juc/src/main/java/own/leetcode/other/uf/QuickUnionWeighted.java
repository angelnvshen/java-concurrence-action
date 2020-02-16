package own.leetcode.other.uf;

import java.util.Arrays;

public class QuickUnionWeighted {
    private int[] id; //分量id
    private int count;//分量数量
    private int[] size;//分量的大小

    public QuickUnionWeighted(int n) {
        count = n;
        id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
        }

        Arrays.fill(size, 1);
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p; // p == id[p]
    }

    public void union(int p, int q) {
        // 将 p，q归并到相同的分量中
        int pid = find(p);
        int qid = find(q);

        if (pid == qid)
            return;

        id[pid] = qid;
        if (size[pid] > size[qid]) {
            id[qid] = pid;
            size[pid] += size[qid];
        } else {
            id[pid] = qid;
            size[qid] += size[pid];
        }
        count--;
    }
}
