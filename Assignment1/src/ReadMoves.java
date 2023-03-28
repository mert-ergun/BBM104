import java.io.*;
import java.util.Scanner;

public class ReadMoves {
    // Read the moves from the file and return them as an array of strings.
    public static String[] readMoves(String filename) throws IOException {
        Scanner moveScanner = new Scanner(new File(filename));
        int numMoves = 0;
        String[] moves = null;
        while (moveScanner.hasNext()) {
            String move = moveScanner.next();
            if (numMoves == 0) {
                moves = new String[100];
            }
            moves[numMoves] = move;
            numMoves++;
        }
        moveScanner.close();
        return moves;
    }
}
