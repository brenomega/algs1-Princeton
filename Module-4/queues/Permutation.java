import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

   	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);

		if (k == 0) return;

		RandomizedQueue<String> memory = new RandomizedQueue<>();
		int n = 0;

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			n++;

			if (n <= k) {
				memory.enqueue(item);
				continue;
			}
			
			if (StdRandom.uniformInt(n) < k) {
				memory.dequeue();
				memory.enqueue(item);
			}
		}

		for (String s : memory) {
			StdOut.println(s);
		}
	}
}