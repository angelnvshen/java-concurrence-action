package own.stu.algorithms_4th.fundamentals.unionFind;

public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    private int root(int i){
        while (i != id[i])
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int proot = root(p);
        int qroot = root(q);
        if(proot == qroot)
            return;
        id[proot] = qroot;
    }
}
