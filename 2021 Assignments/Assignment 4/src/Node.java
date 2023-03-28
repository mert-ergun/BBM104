public class Node<T> {
    private T id;
    private String name;
    private int data;
    private Node<T> next_node;
    private int change = 0;

    Node (T id) {
        this(id, null);
    }

    Node (T id, Node<T> next_node) {
        this.id = id;
        this.next_node = next_node;
    }

    Node (T id, String name, int data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public String get_id () {
        return id.toString();
    }

    public Node<T> get_next_node () {
        return next_node;
    }

    public void set_next_node (Node<T> next_node) {
        this.next_node = next_node;
    }

    public int get_data() {
        return data;
    }

    public void set_data (int number) {
        data += number;
        this.change = 1;
    }

    public String get_name() {
        return name;
    }

    public int is_change() {
        return change;
    }

    public void set_change(int change) {
        this.change = change;
    }
}
