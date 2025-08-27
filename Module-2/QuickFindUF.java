public class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	// Worst case: O(N); Average case: O(N)
	public void union(int p, int q) {
		if (id[p] == id[q]) return;
		for (int i = 0; i < id.length; i++) {
			if (id[i] == id[p]) id[i] = id[q];
		}
	}

	// Worst case: O(1)
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
}