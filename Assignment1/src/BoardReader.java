// Importing the necessary libraries
import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BoardReader {
    // Read the board from the file and return it as a 2D array of characters.
    public static char[][] readBoard(String filename) throws IOException {
        Scanner boardScanner = new Scanner(new File(filename));
        int numRows = 0;
        int numCols = 0;
        char[][] board = null;
        List<String> lines = new ArrayList<String>();  // Store the lines of the board
        while (boardScanner.hasNextLine()) {  // Read the board
            String line = boardScanner.nextLine();
            if (numCols == 0) {
                numCols = line.length();
            }
            if (!line.trim().isEmpty()) { // Skip empty lines
                lines.add(line);
                numRows++;
            }
        }
        boardScanner.close();
        board = new char[numRows][numCols];  // Create the board
        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < numCols; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        return board;
    }
    
    // Get the number of rows in the board
    public static int getNumRows(char[][] board) {
        return board.length;
    }

    // Get the number of columns in the board
    public static int getNumCols(char[][] board) {
        return board[0].length;
    }

    // Get the row of the white ball
    public static int getWhiteRow(char[][] board) {
        int whiteRow = -1;
        for (int i = 0; i < getNumRows(board); i++) {
            for (int j = 0; j < getNumCols(board); j++) {
                if (board[i][j] == Main.WHITE_BALL) {
                    whiteRow = i;
                    break;
                }
            }
            if (whiteRow != -1) {
                break;
            }
        }
        return whiteRow;
    }

    // Get the column of the white ball
    public static int getWhiteCol(char[][] board) {
        int whiteCol = -1;
        for (int i = 0; i < getNumRows(board); i++) {
            for (int j = 0; j < getNumCols(board); j++) {
                if (board[i][j] == Main.WHITE_BALL) {
                    whiteCol = j;
                    break;
                }
            }
            if (whiteCol != -1) {
                break;
            }
        }
        return whiteCol;
    }
}
