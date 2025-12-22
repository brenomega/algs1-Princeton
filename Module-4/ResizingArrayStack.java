public class ResizingArrayStack<T> implements Stack<T> {

	private T[] stack;
	private int current = -1;
	private int size;
	private int modCount;

	private static final int MIN_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public ResizingArrayStack() {
		stack = (T[]) new Object[MIN_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		T[] copy = (T[]) new Object[capacity];
		for (int i = 0; i <= current; i++) copy[i] = stack[i];
		stack = copy;
	}

	@Override
	public void push(T item) {
		if (item == null) throw new NullPointerException();
		if (size == stack.length) resize(2 * stack.length);
		stack[++current] = item;
		size++;
		modCount++;
	}

	@Override
	public T pop() {
		if (isEmpty()) throw new NoSuchElementException();
		T item = stack[current];
		stack[current--] = null;
		size--;
		if (size <= stack.length/4 && stack.length >= MIN_CAPACITY*2) resize(stack.length/2);
		modCount++;
		return item;
	}

	@Override T peek() {
		if (isEmpty()) throw new NoSuchElementException();
		return stack[current];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int index = current;
			private int expectedModCount = modCount;

			@Override
			public boolean hasNext() {
				if (expectedModCount != modCount) throw new ConcurrentModificationException();
				return index != -1;
			}

			@Override
			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				return stack[index--];
			}
		};
	}
}