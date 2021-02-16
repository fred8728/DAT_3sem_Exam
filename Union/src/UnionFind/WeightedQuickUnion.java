package UnionFind;

public class WeightedQuickUnion implements IUnionFind{

    private int [] id;
    private int [] size; //size of component for root
    private int count;

    public WeightedQuickUnion(int n) {
        count = n;
        size = new int[n];
        id = new int[n];

        for (int i =0; i<n; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if(i == j) return;

        // make smaller root point to larger one
        if(size[i] < size[q]){
            id[i] = j;
            size[j] += size[i];
        }
        else {
            id[j] = i;
            size[i] += size[j];
        }
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
