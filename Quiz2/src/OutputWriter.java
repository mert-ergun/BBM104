import java.io.*;
/**
 * OutputWriter class writes the output file. It can clear the output file or write to it.
 * @param clearOutput() Clears the output file. It is used when the program is run for the first time.
 * @param writeOutput() Writes to the output file. It uses append mode.
 * @throws Exception
 * 
 * @author Mert ERGÜN
 * @version 1.0
 * @since 2023-03-31
 */
public class OutputWriter {
    public static void clearOutput(String outputFile) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("");
        }
    }

    public static void writeOutput(String output, String outputFile) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            bw.write(output);
            bw.newLine();
        }
    }
}
