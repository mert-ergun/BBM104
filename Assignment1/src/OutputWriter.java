import java.io.*;

public class OutputWriter {
    // Write the output to the file.
    public static void writeOutput(String filename, int score, int whiteRow, int whiteCol, int numRows, 
    int numCols, String[] mainMoves, char[][] board, char[][] initBoard, char WHITE_BALL, char HOLE, boolean gameOver) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8");
        

        writer.write("Game Board:\n");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++ ) {
                writer.write(initBoard[i][j]);
            }
            writer.write("\n");
            writer.flush();
        }

        writer.write("\nYour movement is:\n");
        String [] moves = mainMoves;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != null) {
                writer.write(moves[i] + " ");
                writer.flush();
            }
        }

        writer.write("\n\nYour output is:\n");

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == whiteRow && j == whiteCol && board[i][j] != HOLE) {
                    writer.write(WHITE_BALL);
                    writer.flush();
                } else {
                    writer.write(board[i][j]);
                    writer.flush();
                }
            }
            writer.write("\n");
            writer.flush();
        }
        if (gameOver) {
            writer.write("\nGame Over!");
            writer.flush();
        }
        writer.write("\nScore: " + score);
        writer.flush();
        writer.close();
    }
}
