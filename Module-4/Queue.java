public interface Queue<T> extends Iterable<T> {
	void enqueue(T item);
	T dequeue();
	T peek();
	boolean isEmpty();
	int size();
}