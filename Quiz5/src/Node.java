/**
 * Node class for the LinkedList, Stack, and Queue classes.
 * @param <T> The type of data to store in the node.
 * @see LinkedList
 * @see Stack
 * @see Queue
 */
public class Node<T> {
    T data;  // The data stored in the node.
    Node<T> next;  // The next node in the list.
    Node<T> prev;  // The previous node in the list.

    public Node(T data) {
        this.data = data;
    }
}
