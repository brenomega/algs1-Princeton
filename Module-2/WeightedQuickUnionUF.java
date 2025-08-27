/* 
   In worst case, complexity is limited in O(log2 N) due to a simple idea: to increase the height of a tree by 1, it must be united 
   with another tree of equal or greater size.
*/
   
public class WeightedQuickUnionUF {
	private int[] id;
	private int[] sz;
	
	public WeightedQuickUnionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	// Worst case: O(log N); Average case: O(log N)
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

	// Worst case: O(log N); Average case: O(log N)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
}