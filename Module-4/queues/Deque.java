import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private class Node {
		Item value;
		Node next;
		Node prev;
	}

	private Node first;
	private Node last;
	private int size;

	public Deque() {
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node temp = new Node();
		temp.value = item;
		temp.prev = null;
		if (isEmpty()) {
			temp.next = null;
			first = temp;
			last = temp;
			size = 1;
			return;
		}
		first.prev = temp;
		temp.next = first;
		first = temp;
		size++;
		return;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node temp = new Node();
		temp.value = item;
		temp.next = null;
		if (isEmpty()) {	
			temp.prev = null;
			first = temp;
			last = temp;
			size = 1;
			return;
		}
		last.next = temp;
		temp.prev = last;
		last = temp;
		size++;
		return;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Node temp = first;
		Item item = temp.value;
		first = first.next;
		size--;
		if (isEmpty()) {
			last = null;
		} else {
			first.prev = null;
		}
		// defensive programming
		temp.value = null;
		temp.next = null;
		temp.prev = null;
		return item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Node temp = last;
		Item item = temp.value;
		last = last.prev;
		size--;
		if (isEmpty()) {
			first = null;
		} else {
			last.next = null;
		}
		// defensive programming
		temp.value = null;
		temp.next = null;
		temp.prev = null;
		return item;
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			
			private Node current = first;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				if (!hasNext()) throw new java.util.NoSuchElementException();
				Item item = current.value;
				current = current.next;
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(1);
		deque.addLast(2);
		System.out.println("Size: " + deque.size());
		for (Integer i : deque) {
			System.out.println(i);
		}
		deque.removeFirst();
		deque.removeLast();
		System.out.println("Is Empty?: " + deque.isEmpty());
	}
}