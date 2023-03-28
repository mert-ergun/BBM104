import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        String decimal = args[0];
        String octal = "octal.txt";

        Stack<Integer> stack = new Stack<>();

        FileReader read = new FileReader(decimal);
        BufferedReader reader = new BufferedReader(read);

        FileWriter write = new FileWriter(octal);
        BufferedWriter writer = new BufferedWriter(write);

        String line = reader.readLine();
        String result = null;
        int number = 0;

        while (line != null) {
            number = Integer.parseInt(line);

            while (true) {
                if (number >= 8) {
                    stack.push(number % 8);
                }

                else {
                    stack.push(number);
                    break;
                }

                number = number / 8;
            }

            result = null;

            while (stack.getSize() != 0) {
                result += stack.top();
                stack.pop();
            }

            line = reader.readLine();

            if (line != null) {
                writer.append(result.substring(4) + "\n");
            }

            else {writer.append(result.substring(4));}
        }

        writer.close();
        write.close();

        reader.close();
        read.close();
    }
}
