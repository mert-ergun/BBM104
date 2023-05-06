import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class idComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int id1 = Integer.parseInt(o1.split("\t")[0]);
        int id2 = Integer.parseInt(o2.split("\t")[0]);

        if (id1 < id2) {
            return -1;
        } else if (id1 == id2) {
            return 0;
        } else {
            return 1;
        }
    }

    public ArrayList<String> sortArrayList(ArrayList<String> input) {
        Collections.sort(input, this);
        return input;
    }

    public TreeSet<String> sortTreeSet(TreeSet<String> input) {
        TreeSet<String> output = new TreeSet<String>(this);
        output.addAll(input);
        return output;
    }
}

