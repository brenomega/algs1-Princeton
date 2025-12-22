import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private static final int MIN_CAPACITY = 16;

	private Item[] stack;
	private int size;

	public RandomizedQueue() {
		stack = (Item[]) new Object[MIN_CAPACITY];
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) copy[i] = stack[i];
		stack = copy;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(Item item) {
		if (item == null) throw new IllegalArgumentException();
		if (size == stack.length) resize(2 * stack.length);
		
		stack[size++] = item;
	}

	public Item dequeue() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int idx = StdRandom.uniformInt(size);
		Item item = stack[idx];
		stack[idx] = stack[size-1];
		stack[size-1] = null;
		size--;
		if (size <= stack.length/4 && stack.length >= MIN_CAPACITY*2) resize(stack.length/2);
		return item;
	}

	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		return stack[StdRandom.uniformInt(size)];
	}

	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i = 0;
		private Item[] copy;
		
		public RandomizedQueueIterator() {
			copy = (Item[]) new Object[size];
			for (int j = 0; j < size; j++) copy[j] = stack[j];
			StdRandom.shuffle(copy);
		}

		@Override
		public boolean hasNext() {
			return i < copy.length;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			return copy[i++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<>();
		rq.enqueue(1);
		rq.enqueue(2);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.enqueue(5);

		System.out.println("Size: " + rq.size());
		System.out.println("Sample: " + rq.sample());
		System.out.println("Dequeue: " + rq.dequeue());

		for (Integer value : rq) {
			System.out.print(value + " ");
		}
	
		for (Integer value : rq) {
			System.out.print(value + " ");
		}
	}
}