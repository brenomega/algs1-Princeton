public class ResizingArrayQueue<T> implements Queue<T> {

	private T[] queue;
	private int head = -1;
	private int tail = -1;
	private int size;
	private int modCount;

	private static final int MIN_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		queue = (T[]) new Object[MIN_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		T[] copy = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++) copy[i] = queue[(head + i)%queue.length];
		queue = copy;
		head = 0;
		tail = size-1;
	}

	@Override
	public void enqueue(T item) {
		if (item == null) throw new NullPointerException();
		if (isEmpty()) {
			head = 0; 
			tail = 0;
			size = 1;
			queue[tail] = item; 
			modCount++;
			return;
		}
		if (size == queue.length) resize(queue.length * 2);
		tail = (tail + 1) % queue.length;
		queue[tail] = item;
		size++;
		modCount++;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		T item = queue[head];
		queue[head] = null;
		if (tail == head) {
			head = tail = -1;
		} else {
			head = (head + 1)%queue.length;
		}
		size--;
		if (size <= queue.length/4 && queue.length >= MIN_CAPACITY*2) resize(queue.length/2);
		modCount++;
		return item;
	}

	@Override
	public T peek() {
		if (isEmpty()) throw new NoSuchElementException();
		return queue[head];
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
			private int index = head;
			private int count;
			private int expectedModCount = modCount;

			@Override
			public boolean hasNext() {
				if (expectedModCount != modCount) throw new ConcurrentModificationException();
				return count < size();
			}

			@Override
			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				T item = queue[index];
				index = (index + 1)%queue.length;
				count++;
				return item;
			}
		};
	}
}