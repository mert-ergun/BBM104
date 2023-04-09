import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputWriter {
    String fileName;

    public OutputWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeOutput(String output) throws Exception {
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, true)));
        bw.write(output + "\n");
        bw.close();
    }

    public void clearOutput() throws Exception {
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, false)));
        bw.write("");
        bw.close();
    }
}
