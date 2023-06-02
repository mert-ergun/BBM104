/**
 * A doubly linked list.
 * @param <T> The type of data to store in the list.
 * @see Stack
 * @see Queue
 * @see Node
 */
public class LinkedList<T> {
    Node<T> head;  // The first node in the list.
    Node<T> tail;  // The last node in the list.
    int size;  // The number of nodes in the list.

    /**
     * Creates an empty list.
     */
    public LinkedList() {
        this.size = 0;
    }

    /**
     * Adds a node to the end of the list.
     * @param data The data to store in the node.
     */
    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    /**
     * Gets the data stored in the node at the given index.
     * @param index The index of the node to get.
     * @return The data stored in the node at the given index.
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();  // The index is out of bounds.
        }
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes the node at the given index.
     * @param index The index of the node to remove.
     */
    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            this.head = this.head.next;
            // If head is null, then the list is empty.
            if (this.head == null) {
                this.tail = null;
            } else {
                this.head.prev = null;
            }
        } else if (index == this.size - 1) {
            this.tail = this.tail.prev;
            this.tail.next = null;
        } else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            current.next.prev = current;
        }
        this.size--;
    }
}
