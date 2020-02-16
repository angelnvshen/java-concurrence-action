package own.leetcode.other.uf;

public class QuickFind {
    private int[] id; //分量id
    private int count;//分量数量

    public QuickFind(int n) {
        count = n;
        id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        // 将 p，q归并到相同的分量中
        int pid = find(p);
        int qid = find(q);

        if (pid == qid)
            return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }

        count--;
    }
}
