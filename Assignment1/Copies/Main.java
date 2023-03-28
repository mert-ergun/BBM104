import java.io.*;

public class Main {
    // Constants for board elements
    public static final char WHITE_BALL = '*';
    public static final char RED_BALL = 'R';
    public static final char YELLOW_BALL = 'Y';
    public static final char BLACK_BALL = 'B';
    public static final char HOLE = 'H';
    public static final char WALL = 'W';
    public static final char EMPTY_SPACE = ' ';
    public static final char MARKED_SPACE = 'X';
    public static final char MARKED_SPACE2 = '?';

    // Constants for points
    public static final int RED_POINTS = 10;
    public static final int YELLOW_POINTS = 5;
    public static final int BLACK_POINTS = -5;

    public static void main(String[] args) throws IOException {
        // Read the input board file
        char[][] board = ReadBoard.readBoard("board.txt");
        char[][] initBoard = ReadBoard.readBoard("board.txt");
        int numRows = ReadBoard.getNumRows(board);  // Call the getNumRows method from ReadBoard class to get the number of rows
        int numCols = ReadBoard.getNumCols(board);  // Call the getNumCols method from ReadBoard class to get the number of columns
        int whiteRow = ReadBoard.getWhiteRow(board);  // Call the getWhiteRow method from ReadBoard class to get the row of the white ball
        int whiteCol = ReadBoard.getWhiteCol(board);  // Call the getWhiteCol method from ReadBoard class to get the column of the white ball
        boolean isMoved = false;  // Check if the white ball is moved
        

        // Read the input move file and execute the moves
        String[] moves = ReadMoves.readMoves("move.txt");
        int score = 0;  

        // Execute the moves
        for (int i = 0; i < moves.length; i++) {  
            if (moves[i] != null) {
                isMoved = false;  // Reset the isMoved variable to false
                int oldWhiteRow = whiteRow;  // Store the old row of the white ball
                int oldWhiteCol = whiteCol;  // Store the old column of the white ball

                // Move the white ball in the specified direction without hitting a wall
                try {
                    if (moves[i].equals("U")) {
                        if (whiteRow > 0 && board[whiteRow - 1][whiteCol] != WALL) {
                            whiteRow--;
                            isMoved = true;
                        } 
                    } else if (moves[i].equals("D")) {
                        if (whiteRow < numRows - 1 && board[whiteRow + 1][whiteCol] != WALL) {
                            whiteRow++;
                            isMoved = true;
                        }
                    } else if (moves[i].equals("L")) {
                        if (whiteCol > 0 && board[whiteRow][whiteCol - 2] != WALL) {
                            whiteCol -= 2;
                            isMoved = true;
                        }
                    } else if (moves[i].equals("R")) {
                        if (whiteCol < numCols - 1 && board[whiteRow][whiteCol + 2] != WALL) {
                            whiteCol += 2;
                            isMoved = true;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ;
                }

                if (!isMoved) {
                    // Move the white ball in the specified direction while hitting a wall
                    if (moves[i].equals("U") && board[whiteRow - 1][whiteCol] == WALL) {
                        whiteRow++;
                        if (whiteRow == numRows) {
                            whiteRow = 0;
                        }
                    } else if (moves[i].equals("D") && board[whiteRow + 1][whiteCol] == WALL) {
                        whiteRow--;
                        if (whiteRow == -1) {
                            whiteRow = numRows - 1;
                        }
                    } else if (moves[i].equals("L") && board[whiteRow][whiteCol - 2] == WALL) {
                        whiteCol += 2;
                        if (whiteCol == numCols + 1) {
                            whiteCol = 0;
                        }
                    } else if (moves[i].equals("R") && board[whiteRow][whiteCol + 2] == WALL) {
                        whiteCol -= 2;
                        if (whiteCol == -1) {
                            whiteCol = numCols - 1;
                        }
                    }
                }

                // Check if the white ball falls from the board
                if (whiteRow < 0 || whiteRow >= numRows || whiteCol < 0 || whiteCol >= numCols) {
                    if (whiteRow < 0) {
                        if (board[numRows - 1][whiteCol] != WALL) {
                            whiteRow = numRows - 1;
                        } else {
                            whiteRow = whiteRow + 1;
                        }
                    } else if (whiteRow >= numRows) {
                        if (board[0][whiteCol] != WALL) {
                            whiteRow = 0;
                        } else {
                            whiteRow = whiteRow - 1;
                        }
                    } else if (whiteCol < 0) {
                        if (board[whiteRow][numCols - 1] != WALL) {
                            whiteCol = numCols - 1;
                        } else {
                            whiteCol = whiteCol + 2;
                        }
                    } else if (whiteCol >= numCols) {
                        if (board[whiteRow][0] != WALL) {
                            whiteCol = 0;
                        } else {
                            whiteCol = whiteCol - 2;
                        }
                    }
                }

                // Check if the white ball falls into a hole
                if (board[whiteRow][whiteCol] == HOLE) {
                    if (moves[i].equals("R")) {
                        board[whiteRow][whiteCol - 2] = EMPTY_SPACE;
                    } else if (moves[i].equals("L")) {
                        board[whiteRow][whiteCol + 2] = EMPTY_SPACE;
                    } else if (moves[i].equals("U")) {
                        board[whiteRow + 1][whiteCol] = EMPTY_SPACE;
                    } else {
                        board[whiteRow - 1][whiteCol] = EMPTY_SPACE;
                    }
                    break;
                }

                // Check if the white ball hits another ball
                if (board[whiteRow][whiteCol] != EMPTY_SPACE && board[whiteRow][whiteCol] != WALL && board[whiteRow][whiteCol] != HOLE) {
                    if (moves[i].equals("R")) {
                        if (board[whiteRow][whiteCol] == RED_BALL) {
                            score += RED_POINTS;
                            board[whiteRow][whiteCol - 2] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == YELLOW_BALL) {
                            score += YELLOW_POINTS;
                            board[whiteRow][whiteCol - 2] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == BLACK_BALL) {
                            score += BLACK_POINTS;
                            board[whiteRow][whiteCol - 2] = MARKED_SPACE;
                        } else {
                            board[whiteRow][whiteCol - 2] = board[whiteRow][whiteCol];
                            board[whiteRow][whiteCol] = MARKED_SPACE2;
                        }
                    } else if (moves[i].equals("L")) {
                        if (board[whiteRow][whiteCol] == RED_BALL) {
                            score += RED_POINTS;
                            board[whiteRow][whiteCol + 2] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == YELLOW_BALL) {
                            score += YELLOW_POINTS;
                            board[whiteRow][whiteCol + 2] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == BLACK_BALL) {
                            score += BLACK_POINTS;
                            board[whiteRow][whiteCol + 2] = MARKED_SPACE;
                        } else {
                            board[whiteRow][whiteCol + 2] = board[whiteRow][whiteCol];
                            board[whiteRow][whiteCol] = MARKED_SPACE2;
                        }
                    } else if (moves[i].equals("U")) {
                        if (board[whiteRow][whiteCol] == RED_BALL) {
                            score += RED_POINTS;
                            board[whiteRow + 1][whiteCol] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == YELLOW_BALL) {
                            score += YELLOW_POINTS;
                            board[whiteRow + 1][whiteCol] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == BLACK_BALL) {
                            score += BLACK_POINTS;
                            board[whiteRow + 1][whiteCol] = MARKED_SPACE;
                        } else {
                            board[whiteRow - 1][whiteCol] = board[whiteRow][whiteCol];
                            board[whiteRow][whiteCol] = MARKED_SPACE2;
                        }
                    } else {
                        if (board[whiteRow][whiteCol] == RED_BALL) {
                            score += RED_POINTS;
                            board[whiteRow - 1][whiteCol] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == YELLOW_BALL) {
                            score += YELLOW_POINTS;
                            board[whiteRow - 1][whiteCol] = MARKED_SPACE;
                        } else if (board[whiteRow][whiteCol] == BLACK_BALL) {
                            score += BLACK_POINTS;
                            board[whiteRow - 1][whiteCol] = MARKED_SPACE;
                        } else {
                            board[whiteRow - 1][whiteCol] = board[whiteRow][whiteCol];
                            board[whiteRow][whiteCol] = MARKED_SPACE2;
                        }
                    }
                }
            }
        }

        // Clear the marked2 spaces
        

        // Print the final board
        OutputWriter.writeOutput("output.txt", score, whiteRow, whiteCol, numRows, numCols , moves, board, initBoard, WHITE_BALL, HOLE);
    }
}
