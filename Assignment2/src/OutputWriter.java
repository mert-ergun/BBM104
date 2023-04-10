import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Class for output writer.
 * It contains the file name.
 * It also contains methods to write and clear the output.
 */
public class OutputWriter {
    String fileName;  // file name of the output file

    /**
     * Constructor for the class.
     * @param fileName - file name of the output file
     */
    public OutputWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method writes the output.
     * @param output - output to be written
     * @throws Exception if the file is not found
     */
    public void writeOutput(String output) throws Exception {
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, true)));
        bw.write(output + "\n");
        bw.close();
    }

    /**
     * This method clears the output file.
     * @throws Exception if the file is not found
     */
    public void clearOutput() throws Exception {
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, false)));
        bw.write("");
        bw.close();
    }
    
    /**
     * This method writes the "ZReport:" text.
     * @param filename - name of the output file
     * @throws Exception
     */
    public static void writeZReport(String filename) throws Exception {
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(filename, true)));
        bw.write("ZReport:\n");
        bw.close();
    }
}
