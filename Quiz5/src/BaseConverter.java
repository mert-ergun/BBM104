/**
 * BaseConverter class that converts a number from base 10 to base 2.
 * @see Stack
 */
public class BaseConverter {
    /**
     * Converts a number from base 10 to base 2.
     * @param num The number to convert.
     * @return The number in base 2.
     */
    public static String convert(int num) {
        Stack<Integer> stack = new Stack<Integer>();  // The stack to store the binary digits.
        if (num == 0) {  // Special case.
            return "0";
        }

        // Check if the number is negative.
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        while (num > 0) {
            stack.push(num % 2);  // Push the remainder onto the stack.
            num /= 2; // Divide the number by 2.
        }
        String result = "";
        while (!stack.isEmpty()) {  // Pop the digits off the stack to get the binary number.
            result += stack.pop();
        }
        if (isNegative) {  // Add the negative sign if necessary.
            result = "-" + result;
        }
        return result;
    }
}
