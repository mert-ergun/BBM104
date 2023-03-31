import java.io.*;

public class OutputWriter {
    public static void writeOutput(String output) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            bw.write(output);
            bw.newLine();
        }
    }
}
