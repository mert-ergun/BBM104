import java.io.*;

public class Emirp {
    public static void listEmirpNumbers(BufferedWriter bw, int n) {  // Lists the emirp numbers up to n, takes in the BufferedWriter and the number as parameters
        try {
            bw.write("Emirp numbers up to " + n + ":");  // Writes the emirp numbers up to n to the file
            bw.newLine();
            for (int i = 1; i <= n; i++) {  // Checks if the number is emirp
                if (isEmirp(i)) {
                    bw.write(i + " ");
                }
            }
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            return;
        }
    }

    public static boolean isEmirp(int n) {  // Checks if the number is emirp, takes in the number as a parameter
        int reverse = 0;  // reverse is the reverse of the number
        int temp = n;  // temp is the number that is being reversed
        while (temp != 0) {  // Reverses the number
            int r = temp % 10;
            reverse = reverse * 10 + r;
            temp = temp / 10;
        }

        if (isPrime(n) && isPrime(reverse) && n != reverse) {  // Checks if the number is prime, the reverse is prime, and the number is not the reverse
            return true;
        } else {
            return false;
        }

    }

    public static boolean isPrime(int n) {  // Checks if the number is prime, takes in the number as a parameter
        if (n <= 1) {  // Checks if the number is less than or equal to 1
            return false;
        }
        for (int i = 2; i < n; i++) {  // Checks if the number is divisible by any number less than it
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
