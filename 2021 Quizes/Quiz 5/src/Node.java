public class Node<T> {
    private T data;
    private Node<T> next_node;

    Node (T data) {
        this(data, null);
    }

    Node (T data, Node<T> next_node) {
        this.data = data;
        this.next_node = next_node;
    }

    public T getData () {
        return data;
    }

    public Node<T> getNext_node() {
        return next_node;
    }
}
