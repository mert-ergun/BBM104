public class WhiteBall {
    // Instance variables, row and col, are the current position of the white ball. BoardRow and boardCol are the number of rows and columns in the board.
    private int row, col, boardRow, boardCol;

    // Constructor
    public WhiteBall(int row, int col, int numRows, int numCols) {
        this.row = row;
        this.col = col;
        this.boardRow = numRows;
        this.boardCol = numCols;
    }

    // Getters and setters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    // Move the white ball in the direction specified by the parameter.
    public void move(String direction) {
        switch (direction) {
            case "U":
                row--;
                break;
            case "D":
                row++;
                break;
            case "L":
                col -= 2;
                break;
            case "R":
                col += 2;
                break;
        }
    }

    // Move the white ball if drops from the board without hitting a wall. Called by the main method in the Main class.
    public void dropBoard(String move) {
        switch (move) {
            case "U":
                WhiteBall.this.row = boardRow - 1;
                break;
            case "D":
                WhiteBall.this.row = 0;
                break;
            case "L":
                WhiteBall.this.col = boardCol - 1;
                break;
            case "R":
                WhiteBall.this.col = 0;
                break;
        }
    }

    // Move the white ball if drops from the board and hits a wall. Called by the main method in the Main class.
    public void dropWall(String move) {
        switch (move) {
            case "U":
                WhiteBall.this.row = 1;
                break;
            case "D":
                WhiteBall.this.row = boardRow - 2;
                break;
            case "L":
                WhiteBall.this.col = 2;
                break;
            case "R":
                WhiteBall.this.col = boardCol - 3;
                break;
        }
    }
}
