package UnionFind;

public interface IUnionFind {
    void union(int p, int q);                   // unite point q and p
    int find(int p);                            // given point p, return set of p
    default boolean connected(int p, int q){    // are the points p and q in the same set
        return find(p) == find(q);
    }
    int count();                                // how many sets are there
}
