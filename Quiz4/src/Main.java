import java.util.ArrayList;

public class Main {
    static OutputWriter ow = new OutputWriter("output.txt");

    public static void main(String[] args) {
        String inputFile = args[0]; 
        Main.writeArrayList(inputFile);
        Main.writeArrayListOrdered(inputFile);
        Main.writeHashSet(inputFile);
        Main.writeTreeSet(inputFile);
        Main.writeTreeSetOrdered(inputFile);
        Main.writeHashMap(inputFile);
    }

    public static void writeArrayList(String filename) {
        ArrayList<String> input = InputReader.readInputArrayList(filename);
        ow.setFilename("poemArrayList.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeArrayListOrdered(String filename) {
        ArrayList<String> input = InputReader.readInputArrayList(filename);
        idComparator comparator = new idComparator();
        input = comparator.sortArrayList(input);
        ow.setFilename("poemArrayListOrderByID.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeHashSet(String filename) {
        java.util.HashSet<String> input = InputReader.readInputHashSet(filename);
        ow.setFilename("poemHashSet.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeTreeSet(String filename) {
        java.util.TreeSet<String> input = InputReader.readInputTreeSet(filename);
        ow.setFilename("poemTreeSet.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeTreeSetOrdered(String filename) {
        java.util.TreeSet<String> input = InputReader.readInputTreeSet(filename);
        idComparator comparator = new idComparator();
        input = comparator.sortTreeSet(input);
        ow.setFilename("poemTreeSetOrderByID.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeHashMap(String filename) {
        java.util.HashMap<Integer, String> input = InputReader.readInputHashMap(filename);
        ow.setFilename("poemHashMap.txt");
        ow.clearOutput();
        for (Integer id : input.keySet()) {
            ow.writeOutput(id + "\t" + input.get(id));
        }
    }
}
