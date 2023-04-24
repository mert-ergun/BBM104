import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The OutputWriter class is responsible for writing output to a file.
 * It provides methods for changing the output file, writing output to the file,
 * and clearing the output file.
 */
public class OutputWriter {
    String fileName;  // The name of the file to write output to
    
    /**
     * Creates a new OutputWriter object.
     * @param fileName The name of the file to write output to.
     */
    public OutputWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Changes the output file.
     * @param fileName The name of the file to write output to.
     */
    public void changeOutputFile(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes the specified output to the output file.
     * @param output The output to write to the file.
     */
    public void writeOutput(String output) {
        try {
            BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, true)));
            bw.write(output + "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the output file.
     */
    public void clearOutput() {
        try {
            BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, false)));
            bw.write("");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
