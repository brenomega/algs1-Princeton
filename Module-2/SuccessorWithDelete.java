// Specialized class for removing and finding immediate successors of elements

public class SuccessorWithDelete {
	private int[] id;
	private int[] sz;
	private int[] max;
	private boolean[] removed;
	
	public SuccessorWithDelete(int N) {
		id = new int[N];
		sz = new int[N];
		max = new int[N];
		removed = new boolean[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			max[i] = i;
		}
	}
	
	private int root(int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	private void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		if (pRoot == qRoot) return;
		if (max[pRoot] < max[qRoot]) {
			max[pRoot] = max[qRoot];
		} else {
			max[qRoot] = max[pRoot];
		}
		if (sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
			return;
		}
		id[qRoot] = pRoot;
		sz[pRoot] += sz[qRoot];	
	}

	public void remove(int i) {
		removed[i] = true;
		if (i > 0 && removed[i - 1]) union(i - 1, i);
		if (i < id.length - 1 && removed[i + 1]) union(i, i + 1);
	}

	public int successor(int i) {
		if (!removed[i]) return i;
		int successor = max[root(i)] + 1;
		if (successor < id.length) return successor;
		return -1;
	}
}