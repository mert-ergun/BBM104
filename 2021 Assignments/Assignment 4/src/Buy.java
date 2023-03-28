import java.util.ArrayList;

public class Buy {
    public static void buy (String[] commands, ArrayList<Stack<String>> stacks, Queue queue) {
        String name = commands[0];
        int buying = Integer.parseInt(commands[1]);

        // When the program calls this method, first the method removes the given number of items.

        for (Stack<String> stack : stacks) {
            if (stack.getName().equals(name)) {
                for (int i = 0; i < buying; i++) {
                    // The loop runs the given number of times. The number is the buying quantity.
                    stack.pop();
                }
                break;
            }
        }

        ArrayList<Node> search = new ArrayList<Node>();

        search.addAll(queue.get_tokens());

        for (Node node : search) {
            if (node.get_name().equals(name)) {
                // If the biggest token is enough to buy the item, the capacity of token is decreased and reordered.
                if (node.get_data() >= buying) {
                    node.set_data(buying * -1);
                    queue.re_order(node);
                    queue.order();
                    break;
                }

                // If the token is not enough, first token will be removed and the execution is continue for next token.
                else {
                    buying -= node.get_data();
                    queue.remove(node);
                }
            }
        }
    }
}

// ArrayList<Node> search = (ArrayList<Node>) queue.get_tokens().clone();