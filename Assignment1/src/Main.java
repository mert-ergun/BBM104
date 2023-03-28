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

    // Constants for points
    public static final int RED_POINTS = 10;
    public static final int YELLOW_POINTS = 5;
    public static final int BLACK_POINTS = -5;

    // Wall checker method, checks if the move is valid or not
    public static boolean wallChecker(String move, WhiteBall whiteBall, char[][] board, int numRows, int numCols, boolean checkWall) {
        if (!checkWall){
            switch (move) {
                case "U":
                    return whiteBall.getRow() > 0 && board[whiteBall.getRow() - 1][whiteBall.getCol()] != WALL;
                case "D":
                    return whiteBall.getRow() < numRows - 1 && board[whiteBall.getRow() + 1][whiteBall.getCol()] != WALL;
                case "L":
                    return whiteBall.getCol() > 0 && board[whiteBall.getRow()][whiteBall.getCol() - 2] != WALL;
                case "R":
                    return whiteBall.getCol() < numCols - 1 && board[whiteBall.getRow()][whiteBall.getCol() + 2] != WALL;
                default:
                    return false;
            }
        } else {
            switch (move) {
                case "U":
                    return whiteBall.getRow() > 0 && board[whiteBall.getRow() - 1][whiteBall.getCol()] == WALL;
                case "D":
                    return whiteBall.getRow() < numRows - 1 && board[whiteBall.getRow() + 1][whiteBall.getCol()] == WALL;
                case "L":
                    return whiteBall.getCol() > 0 && board[whiteBall.getRow()][whiteBall.getCol() - 2] == WALL;
                case "R":
                    return whiteBall.getCol() < numCols - 1 && board[whiteBall.getRow()][whiteBall.getCol() + 2] == WALL;
                default:
                    return false;
            }
        }
    }

    // Method to switch the white ball with the ball it is moving to
    public static int ballSwitch(char[][] board, int oldWhiteRow, int oldWhiteCol, WhiteBall whiteBall, int score, boolean[] isOver) {
        int whiteRow = whiteBall.getRow();
        int whiteCol = whiteBall.getCol();
        char ball = board[whiteRow][whiteCol];

        switch (ball) {
            case 'R':
                score += RED_POINTS;
                board[oldWhiteRow][oldWhiteCol] = MARKED_SPACE;
                board[whiteRow][whiteCol] = WHITE_BALL;
                break;
            case 'Y':
                score += YELLOW_POINTS;
                board[oldWhiteRow][oldWhiteCol] = MARKED_SPACE;
                board[whiteRow][whiteCol] = WHITE_BALL;
                break;
            case 'B':
                score += BLACK_POINTS;
                board[oldWhiteRow][oldWhiteCol] = MARKED_SPACE;
                board[whiteRow][whiteCol] = WHITE_BALL;
                break;
            case 'H':
                board[oldWhiteRow][oldWhiteCol] = EMPTY_SPACE;
                isOver[0] = true;
                break;
            default:
                board[oldWhiteRow][oldWhiteCol] = board[whiteRow][whiteCol];
                board[whiteRow][whiteCol] = WHITE_BALL;
                break;
        }
        return score;
    }

    public static void main(String[] args) throws IOException {
        // Read the input board file
        char[][] board = ReadBoard.readBoard("board.txt");
        char[][] initBoard = ReadBoard.readBoard("board.txt");
        int numRows = ReadBoard.getNumRows(board);  // Call the getNumRows method from ReadBoard class to get the number of rows
        int numCols = ReadBoard.getNumCols(board);  // Call the getNumCols method from ReadBoard class to get the number of columns
        int whiteRow = ReadBoard.getWhiteRow(board);  // Call the getwhiteBall.getRow() method from ReadBoard class to get the row of the white ball
        int whiteCol = ReadBoard.getWhiteCol(board);  // Call the getwhiteBall.getCol() method from ReadBoard class to get the column of the white ball
        WhiteBall whiteBall = new WhiteBall(whiteRow, whiteCol, numRows, numCols);  // Create a new white ball object
        boolean isMoved = false;  // Check if the white ball is moved
        boolean isDropped = false;  // Check if the white ball is dropped from the board
        boolean isOver = false;  // Check if the game is over
        

        // Read the input move file and execute the moves
        String[] moves = ReadMoves.readMoves("move.txt");
        int score = 0;  

        // Execute the moves
        for (int i = 0; i < moves.length; i++) {  
            if (moves[i] != null) {
                isMoved = false;  // Reset the isMoved variable to false
                int oldWhiteRow = whiteBall.getRow();  // Store the old row of the white ball
                int oldWhiteCol = whiteBall.getCol();  // Store the old column of the white ball

                // Move the white ball in the specified direction without hitting a wall
                switch (moves[i]) {
                    case "U":
                        if (wallChecker("U", whiteBall, initBoard, numRows, numCols, false)) {
                            whiteBall.move("U");
                            isMoved = true;
                        }
                        break;
                    case "D":
                        if (wallChecker("D", whiteBall, initBoard, numRows, numCols, false)) {
                            whiteBall.move("D");;
                            isMoved = true;
                        }
                        break;
                    case "L":
                        if (wallChecker("L", whiteBall, initBoard, numRows, numCols, false)) {
                            whiteBall.move("L");
                            isMoved = true;
                        }
                        break;
                    case "R":
                        if (wallChecker("R", whiteBall, initBoard, numRows, numCols, false)) {
                            whiteBall.move("R");
                            isMoved = true;
                        }
                        break;
                }

                // Move the white ball in the specified direction while hitting a wall
                if (!isMoved) {
                    switch (moves[i]) {
                        case "U":
                            if (wallChecker("U", whiteBall, initBoard, numRows, numCols, true)) {
                                whiteBall.move("D");
                                if (whiteBall.getRow() == numRows) {
                                    whiteBall.setRow(0);
                                }
                                isMoved = true;
                            }
                            break;
                        case "D":
                            if (wallChecker("D", whiteBall, initBoard, numRows, numCols, true)) {
                                whiteBall.move("U");
                                if (whiteBall.getRow() == -1) {
                                    whiteBall.setRow(numRows - 1);
                                }
                                isMoved = true;
                            }
                            break;
                        case "L":
                            if (wallChecker("L", whiteBall, initBoard, numRows, numCols, true)) {
                                whiteBall.move("R");
                                if (whiteBall.getCol() == numCols + 1) {
                                    whiteBall.setCol(0);
                                }
                                isMoved = true;
                            }
                            break;
                        case "R":
                            if (wallChecker("R", whiteBall, initBoard, numRows, numCols, true)) {
                                whiteBall.move("L");
                                if (whiteBall.getCol() == -1) {
                                    whiteBall.setCol(numCols - 1);
                                }
                                isMoved = true;
                            }
                            break;
                    }
                }

                // Move the white ball in the specified direction while dropping from board
                if (!isMoved) {
                    switch (moves[i]) {
                        case "U":
                            if (whiteBall.getRow() == 0) {
                                whiteBall.dropBoard("U");
                                isDropped = true;
                            }
                            break;
                        case "D":
                            if (whiteBall.getRow() == numRows - 1) {
                                whiteBall.dropBoard("D");
                                isDropped = true;
                            }
                            break;
                        case "L":
                            if (whiteBall.getCol() == 0) {
                                whiteBall.dropBoard("L");
                                isDropped = true;
                            }
                            break;
                        case "R":
                            if (whiteBall.getCol() == numCols - 1) {
                                whiteBall.dropBoard("R");
                                isDropped = true;
                            }
                            break;
                    }
                }
                
                // Move the white ball in the specified direction while dropping from board and hitting a wall
                if (isDropped) {
                    if (board[whiteBall.getRow()][whiteBall.getCol()] == WALL) {
                        switch (moves[i]) {
                            case "U":
                                whiteBall.dropWall("U");
                                break;
                            case "D":
                                whiteBall.dropWall("D");
                                break;
                            case "L":
                                whiteBall.dropWall("L");
                                break;
                            case "R":
                                whiteBall.dropWall("R");
                                break;
                        }
                    } 
                }

                // Check if white ball hits another ball
                if (board[whiteBall.getRow()][whiteBall.getCol()] != WALL) {
                    boolean[] isOverArray = new boolean[1];
                    isOverArray[0] = false;
                    score = ballSwitch(board, oldWhiteRow, oldWhiteCol, whiteBall, score, isOverArray);
                    isOver = isOverArray[0];
                }

                // Check if the white ball dropped to the hole
                if (isOver) {
                    break;
                }

            }
        }

        // Print the final board
        OutputWriter.writeOutput("output.txt", score, whiteBall.getRow(), whiteBall.getCol(), numRows, numCols , moves, board, initBoard, WHITE_BALL, HOLE, isOver);
    }
}
