// In worst case, the tree structure can degrade into a linear chain.
public class QuickUnionUF {
	private int[] id;
	
	public QuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	// Worst case: O(N); Average case: O(log N)
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		id[pRoot] = qRoot;
	}

	// Worst case: O(N); Average case: O(log N)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
}