import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputWriter {
    private String filename;

    public OutputWriter(String filename) {
        this.filename = filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void clearOutput() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOutput(String output) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(output + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
