import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// No treatment for backwash
public class Percolation {

	private boolean[] grid;
	private WeightedQuickUnionUF uf;
	private int n;
	private int topVirtual;
	private int bottomVirtual;
	private int openSites = 0;

	public Percolation(int n) {
		this.n = n;
		topVirtual = n * n;
		bottomVirtual = topVirtual + 1;
		if (n <= 0) throw new IllegalArgumentException();
		grid = new boolean[n*n];
		uf = new WeightedQuickUnionUF(n*n + 2);
	}
	
	private int convertCoordinates(int row, int col) {
		return ((row - 1) * n) + col - 1;
	}

	public void open(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
		int current = convertCoordinates(row, col);
		if (grid[current]) return;
		int down = -1;
		int up = -1;
		int left = -1;
		int right = -1;
		if (row < n) down = convertCoordinates(row + 1, col);
		if (row > 1) up = convertCoordinates(row - 1, col);
		if (col > 1) left = convertCoordinates(row, col - 1);
		if (col < n) right = convertCoordinates(row, col + 1);
		grid[current] = true;
		openSites++;
		if (down != -1 && grid[down]) {
			uf.union(current, down);
			if (row == n - 1) uf.union(current, bottomVirtual);
			if (row == 1) uf.union(down, topVirtual);
		}
		if (up != -1 && grid[up]) {
			uf.union(current, up);
			if (row == 2) uf.union(current, topVirtual);
			if (row == n) uf.union(up, bottomVirtual);
		}
		if (left != -1 && grid[left]) uf.union(current, left);
		if (right != -1 && grid[right]) uf.union(current, right);
	}

	public boolean isOpen(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
		int current = convertCoordinates(row, col);
		return grid[current];
	}

	public boolean isFull(int row, int col) {
		if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
		int current = convertCoordinates(row, col);
		if (row == 1 && grid[current]) return true;
		return uf.find(current) == uf.find(topVirtual);
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		if (n == 1) return grid[0];
		return uf.find(bottomVirtual) == uf.find(topVirtual);
	}
}