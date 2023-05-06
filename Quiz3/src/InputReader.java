import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {
    public static ArrayList<String> readInput(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            ArrayList<String> input = new ArrayList<String>();
            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
            reader.close();
            return input;
        } catch (IOException e) {
            Main.ow.writeOutput("There should be an input file in the specified path");
            System.exit(0);
            return null;
        }
    }
}
