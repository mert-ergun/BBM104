/**
 * Queue class. First in, first out.
 * @param <T> The type of data to store in the queue.
 * @see LinkedList
 * @see Stack
 * @see Node
 */
public class Queue<T> {
    LinkedList<T> list;  // The list that stores the data.

    /**
     * Creates an empty queue.
     */
    public Queue() {
        this.list = new LinkedList<T>();
    }

    /**
     * Adds data to the end of the queue.
     * @param data The data to add.
     */
    public void enqueue(T data) {
        this.list.add(data);
    }

    /**
     * Removes and returns the data at the front of the queue.
     * @return The data at the front of the queue.
     */
    public T dequeue() {
        T data = this.list.get(0);
        this.list.remove(0);
        return data;
    }

    /**
     * Returns the data at the front of the queue without removing it.
     * @return The data at the front of the queue.
     */
    public T peek() {
        return this.list.get(0);
    }

    /**
     * Returns the number of elements in the queue.
     * @return The number of elements in the queue.
     */
    public int size() {
        return this.list.size;
    }

    /**
     * Returns whether the queue is empty.
     * @return Whether the queue is empty.
     */
    public boolean isEmpty() {
        return this.list.size == 0;
    }
}
