import java.io.BufferedWriter;
import java.io.FileWriter;

public class OutputWriter {
    String fileName;  

    public OutputWriter(String fileName) {
        this.fileName = fileName;
    }

    public void changeOutputFile(String fileName) {
        this.fileName = fileName;
    }

    public void writeOutput(String output) {
        try {
            BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(fileName, true)));
            bw.write(output + "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
