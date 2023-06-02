/**
 * Stack class. Last in, first out.
 * @param <T> The type of data to store in the stack.
 * @see LinkedList
 * @see Queue
 * @see Node
 */
public class Stack<T> {
    LinkedList<T> list;  // The list that stores the data.

    /**
     * Creates an empty stack.
     */
    public Stack() {
        this.list = new LinkedList<T>();
    }

    /**
     * Adds data to the top of the stack.
     * @param data The data to add.
     */
    public void push(T data) {
        this.list.add(data);
    }

    /**
     * Removes and returns the data at the top of the stack.
     * @return The data at the top of the stack.
     */
    public T pop() {
        T data = this.list.get(this.list.size - 1);
        this.list.remove(this.list.size - 1);
        return data;
    }

    /**
     * Returns the data at the top of the stack without removing it.
     * @return The data at the top of the stack.
     */
    public T peek() {
        return this.list.get(this.list.size - 1);
    }

    /**
     * Returns the number of elements in the stack.
     * @return The number of elements in the stack.
     */
    public int size() {
        return this.list.size;
    }

    /**
     * Returns whether the stack is empty.
     * @return Whether the stack is empty.
     */
    public boolean isEmpty() {
        return this.list.size == 0;
    }
}
