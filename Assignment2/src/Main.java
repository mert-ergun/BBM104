import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Smart> smartList = new ArrayList<Smart>();
    public static TimeChecker timeChecker = new TimeChecker();
    public static void main(String[] args) {
        try {
            InputReader.ReadInput("input1.txt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
