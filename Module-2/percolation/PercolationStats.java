import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int trials;
	private double[] thresholds;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

		this.trials = trials;
		thresholds = new double[trials];

		for (int t = 0; t < trials; t++) {
			Percolation p = new Percolation(n);

			int opened = 0;
			while (!p.percolates()) {
				int row = StdRandom.uniformInt(1, n + 1);
				int col = StdRandom.uniformInt(1, n + 1);
				if (!p.isOpen(row, col)) {
					p.open(row, col);
					opened++;
				}
			}
			thresholds[t] = (double) opened / (n*n);
		}
		
	}

	public double mean() {
		return StdStats.mean(thresholds);
	}

	public double stddev() {
		return StdStats.stddev(thresholds);
	}

	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(trials);
	}

	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.printf("%-24s= %s%n", "mean", ps.mean());
		System.out.printf("%-24s= %s%n", "stddev", ps.stddev());
		System.out.printf("%-24s= [%s, %s]%n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
	}
}