import java.util.Locale;

/**
 * Palindrome class to check if a sentence is palindrome or not.
 * Uses a stack to check if the sentence is palindrome or not.
 * @see Stack
 */
public class Palindrome {
    /**
     * Checks if the given sentence is palindrome or not.
     * @param sentence The sentence to check.
     * @return Whether the sentence is palindrome or not.
     */
    public static boolean isPalindrome(String sentence) {
        // Remove all non-alphanumeric characters and convert to lowercase. For "I" we need to use Locale.ENGLISH to convert to lowercase.
        sentence = sentence.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ENGLISH);  
        Stack<Character> stack = new Stack<>();  // The stack to store the characters.
        for (int i = 0; i < sentence.length(); i++) {  // Push the characters onto the stack.
            char ch = sentence.charAt(i);
            stack.push(ch);
        }
        for (int i = 0; i < sentence.length(); i++) {  // Pop the characters off the stack and compare with the characters in the sentence.
            char ch = sentence.charAt(i);
            if (ch != stack.pop()) {
                return false;
            }
        }
        return true;
    }
}