// More intuitive way to see why the worst case is O(log n)
   
public class QuickUnionByRankUF {
	private int[] id;
	private int[] rk;
	
	public QuickUnionByRankUF(int N) {
		id = new int[N];
		rk = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			rk[i] = 1;
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

		if (rk[pRoot] < rk[qRoot]) {
			id[pRoot] = qRoot;
		} else if (rk[pRoot] > rk[qRoot]) {
			id[qRoot] = pRoot;
		} else {
			id[qRoot] = pRoot;
			rk[pRoot]++;
		}
	}

	// Worst case: O(log N); Average case: O(log N)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
}