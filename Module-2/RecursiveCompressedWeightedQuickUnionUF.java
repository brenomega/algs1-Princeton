public class RecursiveCompressedWeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	
	public RecursiveCompressedWeightedQuickUnionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	// Updates all nodes in path to point to the root
	private int root(int i) {
		if (i != id[i]) id[i] = root(id[i]);
		return id[i];
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