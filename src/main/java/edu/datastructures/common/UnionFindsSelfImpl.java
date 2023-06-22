package edu.datastructures.common;

public class UnionFindsSelfImpl {

    private int size; // The number of elements in this union find
    private int[] sz; // Size  of the components
    private int[] id; // id[i] points to the parent of i, if id[i] = i then i is a root node
    private int numComponents; // Track the number of components in the union find

    public UnionFindsSelfImpl(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");
        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i; // Link to itself (self root)
            sz[i] = 1; // Each component is originally of size one
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time.
    public int find(int p) {
        // Find the root of the component/set
        int root = p;
        while (root != id[root]) root = id[root];

        // Compress the path leading back to the root.
        // Doing this operation is called "path compression"
        // and is what gives us amortized constant time complexity.
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        } return root;
    }

    // Return whether the elements 'p' and
    // 'q' are in the same components/set.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Return the size of the components/set 'p' belongs to
    public int sizeOfComponent(int p) {
        return sz[find(p)];
    }

    public int size() {
        return size;
    }

    // Returns the number of remaining components/sets
    public int components() {
        return numComponents;
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q){
        int p_root = find(p), q_root = find(q); // get roots of a both groups
        if (p_root == q_root) return;

        // Merge small group into the large group
        if (sz[p_root] < sz[q_root]) {
            sz[q_root] += sz[q_root];
            id[p_root] = id[q_root];
        } else {
            sz[q_root] += sz[p_root];
            id[q_root] = id[p_root];
        }
        // Since the roots found are different we know that the
        // number of components/sets has decreased by one
        numComponents--;
    }

}
