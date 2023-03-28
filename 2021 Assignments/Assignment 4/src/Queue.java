import java.util.ArrayList;

// The token class that is designed using Queue data structure.

public class Queue {
    private ArrayList<Node> tokens = new ArrayList<Node>();

    Queue () {}

    public void enqueue (Node object) {
        tokens.add(object);
        order();
    }

    public void dequeue () {
        tokens.remove(0);
    }

    public void remove (Node token) {tokens.remove(token);}  // If the token is run out of, the method deletes the token.

    public void clear () {
        tokens.removeIf(node -> node.get_data() == 0);
    } // Checks the tokens and deletes the tokens that are run out of.

    public void order () {
        clear();

        // This method orders all elements in queue by using bubble sort algorithm.

        for (int x = 0; x < tokens.size() - 1; x++) {
            for (int y = 0; y < tokens.size() - 1 - x; y++) {
                if (tokens.get(y).get_data() < tokens.get(y + 1).get_data()) {
                    Node changing = tokens.get(y);
                    tokens.set(y, tokens.get(y + 1));
                    tokens.set(y + 1, changing);
                }
                // If the token was used before, the program will move it back rows.
                else if (tokens.get(y).get_data() == tokens.get(y + 1).get_data() && tokens.get(y).is_change() > tokens.get(y + 1).is_change()) {
                    tokens.get(y).set_change(0);
                    tokens.get(y+1).set_change(0);
                    Node changing = tokens.get(y);
                    tokens.set(y, tokens.get(y + 1));
                    tokens.set(y + 1, changing);
                }
            }
        }
    }

    public void re_order (Node node) {
        // If the capacity of token was decreased, this method will change the order of the token.
        tokens.remove(node);
        boolean check = false;

        for (int k = 0; k < tokens.size(); k++) {
            if (tokens.get(k).get_data() < node.get_data()) {
                tokens.add(k, node);
                check = true;
                break;
            }
        }
        if (!check) { tokens.add(node); }
    }

    public ArrayList<Node> get_tokens() {
        return tokens;
    }
}
