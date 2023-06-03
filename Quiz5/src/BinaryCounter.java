import java.io.BufferedWriter;
import java.io.IOException;

/**
 * BinaryCounter class to count from 1 up to n in binary.
 * Uses a queue to count from 1 up to n in binary.
 * @see Queue
 */
public class BinaryCounter {
    /**
     * Counts from 1 up to n in binary.
     * @param n The number to count up to.
     * @param writer The BufferedWriter to write to.
     * @throws IOException If an I/O error occurs.
     */
    public static void countTo(int n, BufferedWriter writer) throws IOException {
        Queue<String> queue = new Queue<String>();  // Queue to store binary numbers
        queue.enqueue("1");

        // Count from 1 up to n in binary using queue
        for (int i = 0; i < n; i++) {
            String s1 = queue.dequeue(); // Dequeue the first element (it will be 1 at first)
            writer.write("\t"+ s1); // Write the dequeued element to the output file
            String s2 = s1; // Store the dequeued element in another variable
            queue.enqueue(s1 + "0"); // Enqueue the dequeued element with 0 appended to it
            queue.enqueue(s2 + "1"); // Enqueue the dequeued element with 1 appended to it
        }
        writer.newLine(); // Write a new line
    }

    public static void writeOutput(int number, BufferedWriter writer) {
        try {
            writer.write("Counting from 1 up to " + number + " in binary:");  // Write to output file
        } catch (Exception e) {
            System.out.println("Error writing output.");
        }
    }
}
