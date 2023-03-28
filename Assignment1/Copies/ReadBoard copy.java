import java.io.*;
import java.util.Scanner;

public class ReadBoard {
    private int numRows, numCols;

    public ReadBoard(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public ReadBoard() {
        this.numRows = 0;
        this.numCols = 0;
    }



    public char[][] readBoard(String filename) throws IOException {
        Scanner boardScanner = new Scanner(new File(filename));
        char[][] board = null;
        while (boardScanner.hasNextLine()) {
            String line = boardScanner.nextLine();
            if (numCols == 0) {
                numCols = line.length();
                board = new char[20][20];
            }
            for (int j = 0; j < numCols; j++) {
                board[numRows][j] = line.charAt(j);
            }
            numRows++;
        }
        boardScanner.close();
        return board;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getWhiteRow(char[][] board) {
        int whiteRow = -1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
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

    public int getWhiteCol(char[][] board) {
        int whiteCol = -1;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
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
