public LinkedQueue<T> implements Queue<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;

	private static class Node<T> {
		T item;
		Node<T> next;
	}

	@Override
	public void enqueue(T item) {
		if (item == null) throw new NullPointerException();
		Node<T> temp = last;
		last = new Node<>();
		last.item = item;
		if(isEmpty()) { first = last; size++; return; }
		temp.next = last;
		size++;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) throw new NoSuchElementException();
		T item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		size--;
		return item;
	}

	@Override
	public T peek() {
		if (isEmpty()) throw new NoSuchElementException();
		return first.item;
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
			private Node<T> current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				if (!hasNext()) throw new NoSuchElementException();
				T item = current.item;
				current = current.next;
				return item;
			}
		};
	}
}