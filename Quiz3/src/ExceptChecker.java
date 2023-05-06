import java.util.ArrayList;

public class ExceptChecker {
    public static void checkException(ArrayList<String> input) {
        try {
            if (input.size() == 0) {
                throw new EmptyInput("The input file should not be empty");
            }
            for (int i = 0; i < input.size(); i++) {
                String line = input.get(i);
                for (int j = 0; j < line.length(); j++) {
                    if (!line.matches("[a-zA-Z ]+")) {
                        throw new InvalidChar("The input file should not contains unexpected characters");
                    }
                }
            }
            for (int i = 0; i < input.size(); i++) {
                String line = input.get(i);
                Main.ow.writeOutput(line);
            }
        } catch (Exception e) {
            Main.ow.writeOutput(e.getMessage());
            System.exit(0);
        }
    }
}
