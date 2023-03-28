import java.io.*;

public class Armstrong {
    public static void listArmstrongNumbers(BufferedWriter bw, int n) {  // Lists the armstrong numbers up to n, takes in the BufferedWriter and the number as parameters
        try {
            bw.write("Armstrong numbers up to " + n + ":");  // Writes the armstrong numbers up to n to the file
            bw.newLine();
            for (int i = 1; i <= n; i++) {  // Checks if the number is armstrong
                if (isArmstrong(i)) {
                    bw.write(i + " ");
                }
            }
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            return;
        }
    }

    public static boolean isArmstrong(int n) {  // Checks if the number is armstrong, takes in the number as a parameter
        int numDigits = String.valueOf(n).length();  // numDigits is the number of digits in the number
        int sum = 0;  // sum is the sum of the digits of the number raised to the power of the number of digits
        int temp = n;  // temp is the number that is being checked
        if (numDigits == 1) {  // Checks if the number is a single digit, if it is, it is not armstrong
            return false;
        }
        while (temp != 0) {  // Finds the sum of the digits of the number raised to the power of the number of digits
            int r = temp % 10;  // r is the digit that is being raised to the power of the number of digits
            sum = sum + (int)Math.pow(r, numDigits);  // Adds the digit raised to the power of the number of digits to the sum
            temp = temp / 10;
        }
        if (n == sum) {  // Checks if the number is equal to the sum of the digits of the number raised to the power of the number of digits
            return true;
        } else {
            return false;
        }
    }
}
