// In practice, complexity is linear, limited to 5 in a 2^65536 elements context
   
public class CompressedWeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	
	public CompressedWeightedQuickUnionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	// Updates current node to point to the grandparent
	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		if (pRoot == qRoot) return;
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
			return;
		}
		id[qRoot] = pRoot;
		sz[pRoot] += sz[qRoot];	
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
}