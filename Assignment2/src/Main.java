import java.util.ArrayList;
import java.util.List;

/**
 * Main class. It contains the main method.
 * It also contains the list of smart devices and the switch checker and time checker objects.
 * It drives the program.
 * Gets the input file and output file from the command line.
 */
public class Main {
    public static List<Smart> smartList = new ArrayList<Smart>();
    public static SwitchChecker switchChecker = new SwitchChecker();
    public static TimeChecker timeChecker = new TimeChecker();
    public static void main(String[] args) throws Exception {
        String outputFile = "output.txt";
        String inputFile = "input1.txt";
        OutputSender sender = new OutputSender(outputFile);
        try {
            InputReader.readInput(inputFile, sender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
