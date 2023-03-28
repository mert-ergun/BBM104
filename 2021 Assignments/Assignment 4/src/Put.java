import java.util.ArrayList;

public class Put {
    public static void put (String[] commands, ArrayList<Stack<String>> stacks) {
        String name = commands[0];

        // When the program calls this method, the method finds the correct element in stack and push the new items into stack.

        for (Stack<String> stack : stacks) {
            if (stack.getName().equals(name)) {
                for (int m = 1; m < commands.length; m++) {
                    stack.push(commands[m]);
                }
            }
        }
    }
}
