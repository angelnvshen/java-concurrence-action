package own.stu.algorithms_4th.fundamentals.unionFind;

public class WeightQuickUnionUF {
    private int[] id;

    private int[] sz; //各个根节点所对应的分量大小

    public WeightQuickUnionUF(int n) {

        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;

        sz = new int[n];
        for (int i = 0; i < n; i++)
            sz[i] = 1;
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
        int i = root(p);
        int j = root(q);
        if(i == j)
            return;
        if(sz[i] > sz[j]){
            id[j] = i;
            sz[i] += sz[j];
        }else {
            id[i] = j;
            sz[j] += sz[i];
        }

        id[i] = j;
    }
}
