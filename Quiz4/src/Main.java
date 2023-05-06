import java.util.ArrayList;

public class Main {
    static OutputWriter ow = new OutputWriter("output.txt");

    public static void main(String[] args) {
        Main.writeArrayList();
        Main.writeArrayListOrdered();
        Main.writeHashSet();
        Main.writeTreeSet();
        Main.writeTreeSetOrdered();
        Main.writeHashMap();
    }

    public static void writeArrayList() {
        ArrayList<String> input = InputReader.readInputArrayList("poem.txt");
        ow.setFilename("poemArrayList.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeArrayListOrdered() {
        ArrayList<String> input = InputReader.readInputArrayList("poem.txt");
        idComparator comparator = new idComparator();
        input = comparator.sortArrayList(input);
        ow.setFilename("poemArrayListOrderByID.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeHashSet() {
        java.util.HashSet<String> input = InputReader.readInputHashSet("poem.txt");
        ow.setFilename("poemHashSet.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeTreeSet() {
        java.util.TreeSet<String> input = InputReader.readInputTreeSet("poem.txt");
        ow.setFilename("poemTreeSet.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeTreeSetOrdered() {
        java.util.TreeSet<String> input = InputReader.readInputTreeSet("poem.txt");
        idComparator comparator = new idComparator();
        input = comparator.sortTreeSet(input);
        ow.setFilename("poemTreeSetOrderByID.txt");
        ow.clearOutput();
        for (String line : input) {
            ow.writeOutput(line);
        }
    }

    public static void writeHashMap() {
        java.util.HashMap<Integer, String> input = InputReader.readInputHashMap("poem.txt");
        ow.setFilename("poemHashMap.txt");
        ow.clearOutput();
        for (Integer id : input.keySet()) {
            ow.writeOutput(id + "\t" + input.get(id));
        }
    }
}
