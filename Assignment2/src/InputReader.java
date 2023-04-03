import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader{
    public static List<String> ReadInput() throws Exception{
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader("input.txt")));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            String[] split = line.split("\t");
            for (String s : split) {
                lines.add(s);
            }
        }
        br.close();
        return lines;
    }
}
