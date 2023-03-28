import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args) throws IOException {
        String parts = args[0];
        String items = args[1];
        String tokens = args[2];
        String tasks = args[3];
        String output = args[4];

        // Reading parts ...

        ArrayList<Stack<String>> stacks = new ArrayList<Stack<String>>();

        FileReader reader = new FileReader(parts);
        BufferedReader read = new BufferedReader(reader);

        String line = read.readLine();

        while (line != null) {
            stacks.add(new Stack<String>(line));
            line = read.readLine();
        }

        read.close();
        reader.close();

        // Reading items ...

        reader = new FileReader(items);
        read = new BufferedReader(reader);

        line = read.readLine();
        String[] data = null;

        while (line != null) {
            data = line.split("\\s+");
            for (Stack<String> stack : stacks) {
                if (stack.getName().equals(data[1])) {
                    stack.push(data[0]);
                    break;
                }
            }
            line = read.readLine();
        }

        read.close();
        reader.close();

        // Reading tokens ...

        Queue queue = new Queue();

        reader = new FileReader(tokens);
        read = new BufferedReader(reader);

        line = read.readLine();

        while (line != null) {
            data = line.split("\\s+");
            queue.enqueue(new Node<String>(data[0], data[1], Integer.parseInt(data[2])));
            line = read.readLine();
        }

        read.close();
        reader.close();

        // Reading and executing tasks ...

        reader = new FileReader(tasks);
        read = new BufferedReader(reader);

        line = read.readLine();
        String command = null;

        while (line != null) {
            data = line.split("\\s+");
            command = data[0];

            for (int m = 1; m < data.length; m++) {
                if (command.equals("BUY")) { Buy.buy(data[m].split(","), stacks, queue);}

                else { Put.put(data[m].split(","), stacks);}
            }
            line = read.readLine();
        }

        read.close();
        reader.close();

        // Writing results to output file ...

        FileWriter writer = new FileWriter(output);
        BufferedWriter write = new BufferedWriter(writer);

        for (Stack<String> stack : stacks) {
            write.append(stack.getName() + ":\n");
            Node<String> node = stack.get_first_node();
            if (node == null) { write.append("\n"); }

            while (node != null) {
                write.append(node.get_id() + "\n");
                node = node.get_next_node();
            }
            write.append("---------------\n");
        }

        Collections.reverse(queue.get_tokens());
        write.append("Token Box:\n");

        for (Node node : queue.get_tokens()) {
            write.append(node.get_id() + " " + node.get_name() + " " + node.get_data()+ "\n");
        }

        write.close();
        writer.close();
    }
}
