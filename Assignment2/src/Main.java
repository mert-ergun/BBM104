import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Smart> smartList = new ArrayList<Smart>();
    public static SwitchChecker switchChecker = new SwitchChecker();
    public static TimeChecker timeChecker = new TimeChecker();
    public static void main(String[] args) throws Exception {
        String outputFile = args[1];
        String inputFile = args[0];
        OutputSender sender = new OutputSender(outputFile);
        try {
            InputReader.readInput(inputFile, sender);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
