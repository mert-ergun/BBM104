import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Smart> smartList = new ArrayList<Smart>();
    public static SwitchChecker switchChecker = new SwitchChecker();
    public static TimeChecker timeChecker = new TimeChecker();
    public static void main(String[] args) throws Exception {
        OutputSender sender = new OutputSender("output.txt");
        try {
            InputReader.ReadInput("input1.txt", sender);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
