import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class InputReader {
    public static ArrayList<String> readInputArrayList(String filename) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static HashSet<String> readInputHashSet(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            HashSet<String> input = new HashSet<String>();
            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
            reader.close();
            return input;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TreeSet<String> readInputTreeSet(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            TreeSet<String> input = new TreeSet<String>();
            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
            reader.close();
            return input;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<Integer, String> readInputHashMap(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            HashMap<Integer, String> input = new HashMap<Integer, String>();
            while (line != null) {
                String[] parts = line.split("\t");
                input.put(Integer.parseInt(parts[0]), parts[1]);
                line = reader.readLine();
            }
            reader.close();
            return input;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
