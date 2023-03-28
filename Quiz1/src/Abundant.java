import java.io.*;

public class Abundant {  
    public static void listAbundantNumbers(BufferedWriter bw, int n) {  // Lists the abundant numbers up to n, takes in the BufferedWriter and the number as parameters
        try {
            bw.write("Abundant numbers up to " + n + ":");  // Writes the abundant numbers up to n to the file
            bw.newLine();
            for (int i = 1; i <= n; i++) {  // Checks if the number is abundant
                if (isAbundant(i)) {
                    bw.write(i + " ");  // Writes the abundant number to the file
                }
            }
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            return;
        }
    }

    public static boolean isAbundant(int n) {  // Checks if the number is abundant, takes in the number as a parameter
        int sum = 0;  // sum is the sum of the factors of the number
        for (int i = 1; i < n; i++) {  // Finds the factors of the number
            if (n % i == 0) {  // Checks if the number is a factor of the number
                sum = sum + i;  // Adds the factor to the sum
            }
        }
        if (sum > n) {  // Checks if the sum of the factors is greater than the number
            return true;
        } else {
            return false;
        }
    }
}
