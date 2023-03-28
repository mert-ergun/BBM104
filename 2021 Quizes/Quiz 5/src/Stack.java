public class Stack<T> {
    private Node<T> first_node;
    private Node<T> last_node;
    private int size = 0;

    Stack () {
        first_node = last_node = null;
    }

    public boolean isFull () {
        return size > 20;
    }

    public boolean isEmpty() {
        return first_node == null;
    }

    public void push (T item) {
        if (isEmpty()) {
            first_node = last_node = new Node<T>(item);
        }
        else {
            first_node = new Node<T>(item, first_node);
        }

        this.size +=1;
    }

    public void pop () {
        if (first_node == last_node) {first_node = last_node = null;}
        else {first_node = first_node.getNext_node();}
        this.size -=1;
    }

    public String top () {
        return Integer.toString((Integer) first_node.getData());
    }

    public int getSize() {
        return size;
    }
}
