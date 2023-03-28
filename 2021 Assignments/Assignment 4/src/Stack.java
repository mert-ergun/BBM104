public class Stack<T> {
    private Node<T> first_node;
    private Node<T> last_node;
    private String name;

    Stack (String name) {
        first_node = null;
        last_node = null;
        this.name = name;
    }

    public boolean is_empty () {
        return first_node == null;
    }

    public void push (T object) {
        if (is_empty()) {
            first_node = new Node<T>(object);
            last_node = first_node;
        }

        else {
            Node<T> new_item = new Node<T>(object, first_node);
            first_node = new_item;
        }
    }

    public void pop () {
        if (first_node == last_node) {
            first_node = null;
            last_node = null;
        }

        else {
            first_node = first_node.get_next_node();
        }
    }

    public Node<T> get_first_node() {
        return first_node;
    }

    public Node<T> get_last_node() {
        return last_node;
    }

    public String getName() {
        return name;
    }
}
