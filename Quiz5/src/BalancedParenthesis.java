import java.io.BufferedWriter;

/**
 * Balanced Parenthesis class to check if the given expression has balanced parenthesis or not.
 * Uses a stack to check if the parenthesis are balanced or not.
 * @see Stack
 */
public class BalancedParenthesis {
    /**
     * Checks if the given expression has balanced parenthesis or not.
     * @param expression The expression to check.
     * @return Whether the expression has balanced parenthesis or not.
     */
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();  // The stack to store the parenthesis.
        for (int i = 0; i < expression.length(); i++) {  // Loop through the expression.
            char ch = expression.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {  // Push the opening parenthesis onto the stack.
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {  // Check if the closing parenthesis matches the top of the stack.
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void writeOutput(String argument, boolean isValid, BufferedWriter writer) {
        try {
            writer.write("\"" + argument + "\" is " + (isValid ? "" : "not ") + "a valid expression.");
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error writing output.");
        }
    }
}
