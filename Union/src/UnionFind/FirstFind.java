package UnionFind;

public class FirstFind implements IUnionFind{

    private int [] id ;
    private int count;

    /**
     * ---> 0,1,2,3,4,5,6,7,8,9,10
     * id: [0,1,2,3,4,5,6,7,8,9,10 ]
     * count: 10
     *
     * Union(1,7)
     *
     * ---> 0,1,2,3,4,5,6,7,8,9,10
     * id: [0,1,2,3,4,5,6,1,8,9,10 ]
     * count: 9
     *
     * Union(8,7)
     *
     * ---> 0,1,2,3,4,5,6,7,8,9,10
     * id: [0,8,2,3,4,5,6,8,8,9,10 ]
     * count: 8
     */

    public FirstFind(int n) {
        count = n;
        id = new int[n];
        for (int i =0; i<n; i++) id[i] = i;
    }

    @Override
    public void union(int p, int q) {
        if(connected(p, q)) return;
        int idQ= id[q];
        int idP = id[p];

        for(int i = 0; i< id.length; i++) {
            if (id[i] == idQ) id[i] = idP;
        }
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
