import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class for the Quiz.
 * Drives the program.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = args[0];  // Input file path
        String outputFile = args[1];  // Output file path

        // Read from input file
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        // Write to output file
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");  // Split line by tab
            String operation = parts[0];  // First part is the operation
            String argument = parts[1];  // Second part is the argument

            switch (operation) {
                case "Convert from Base 10 to Base 2:":
                    int number = Integer.parseInt(argument);  // Convert argument to integer
                    String binary = BaseConverter.convert(number);  // Convert number to binary using BaseConverter
                    BaseConverter.writeOutput(number, binary, writer);  // Write output using BaseConverter
                    break; 
                case "Count from 1 up to n in binary:":
                    int n = Integer.parseInt(argument);  
                    BinaryCounter.writeOutput(n, writer);  // Write output using BinaryCounter
                    BinaryCounter.countTo(n, writer);  // Count from 1 up to n in binary using BinaryCounter
                    break;
                case "Check if following is palindrome or not:":
                    boolean isPalindrome = Palindrome.isPalindrome(argument);  // Check if argument is palindrome using Palindrome
                    Palindrome.writeOutput(argument, isPalindrome, writer);
                    break;
                case "Check if following expression is valid or not:":
                    boolean isValid = BalancedParenthesis.isBalanced(argument);  // Check if argument is valid expression using BalancedParenthesis
                    BalancedParenthesis.writeOutput(argument, isValid, writer);
                    break;
            }
        }

        reader.close();
        writer.close();
    }
}
