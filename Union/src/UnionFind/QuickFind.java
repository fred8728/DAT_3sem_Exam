package UnionFind;

public class QuickFind implements IUnionFind {

    private int [] id;
    private int count;

    public QuickFind(int n) {
        count = n;
        id = new int[n];
        for (int i =0; i<n; i++) id[i] = i;
    }

    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qId = find(q);

        if(pID == qId) return; //do nothing if p and q already is in same component

        for(int i = 0; i < id.length; i++)
            if(id[i] == pID) id[i]= qId;
            count--;
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public int count() {
        return count;
    }
}
