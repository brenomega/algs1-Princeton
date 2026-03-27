// Adding allConnected(), find()
   
public class CompressedWeightedQuickUnionUF2 {
	private int[] id;
	private int[] sz;
	private int rm;
	private int[] bg;
	
	public CompressedWeightedQuickUnionUF2(int N) {
		id = new int[N];
		sz = new int[N];
		bg = new int[N];
		rm = N;
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			bg[i] = i;
		}
	}
	
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
		if (bg[pRoot] < bg[qRoot]) {
			bg[pRoot] = bg[qRoot];
		} else {
			bg[qRoot] = bg[pRoot];
		}
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
		rm--;
	}

	// In practice, returns the biggest element connected to another in constant time
	public int find(int i) {
		int iRoot = root(i);
		return bg[iRoot];
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	// Returns true if "id" is a connected graph in constant time
	public boolean allConnected() {
		return rm == 1;
	}
}