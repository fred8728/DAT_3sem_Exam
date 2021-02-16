package UnionFind;

public class QuickUnion implements IUnionFind{
    private int [] id;
    private int count;

    public QuickUnion(int n) {
        count = n;
        id = new int[n];
        for (int i =0; i<n; i++) id[i] = i;
    }

    @Override
    public void union(int p, int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot==qRoot) return; // do nothing if already in the same component

        id[pRoot] =qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    @Override
    public int count() {
        return count;
    }
}
