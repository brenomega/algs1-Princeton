public class LinkedStack<T> implements Stack<T> {
	private Node<T> first;
	private int size;

	private static class Node<T> {
		T item;
		Node<T> next;
	}

	@Override
	public void push(T item) {
		if (item == null) throw new NullPointerException();
		Node<T> temp = first;
		first = new Node<>();
		first.item = item;
		first.next = temp;
		size++;
	}

	@Override
	public T pop() {
		if (isEmpty()) throw new NoSuchElementException();
		T item = first.item;
		first = first.next;
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
		return first == null;
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