import java.io.*;
import java.util.Scanner;

public class FileReader {
    public static String[] readInput() {
        Scanner inputScanner = new Scanner("input.txt");
        int numInput = 0;
        String[] input = null;
        while (inputScanner.hasNext()) {
            String inputString = inputScanner.next();
            if (numInput == 0) {
                input = new String[100];
            }
            input[numInput] = inputString;
            numInput++;
        }
        inputScanner.close();
        return input;
    }
}
