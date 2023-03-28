import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;

public class Board<String> {

    // This is a special class that is designed for board game. Board class includes a 2D board, some true/false statements
    // to direct application and an output file statement to keep output file.

    public Characters[][] board;
    public boolean changing = false;
    public String output_file;
    public boolean boundary_error = false;

    // Constructor takes the output file.

    Board (String output_file) {
        this.output_file = output_file;
    }

    // Set Up method takes size data of board and create new board.

    public void set_up (int x, int y) {
        board = new Characters[x][y];
    }

    // Create method takes location information of new character with character and record it to board.

    public void create (int[] news, Characters c) {
        board[news[0]][news[1]] = c;
    }

    // Get Character method returns character in given coordinate.

    public Characters get_character (int x, int y) {
        return board[x][y];
    }

    // Move method moves the character to new location.

    public void move (int[] olds, int[] news, Characters c) {
        board[olds[0]][olds[1]] = null;
        board[news[0]][news[1]] = c;
        changing = true;
    }

    // Printer method writes changes in board to output file after all command.

    public void printer (Data<String, Characters, int[]> data) throws IOException {
        if (changing) {

            // Firstly prints board.

            FileWriter write = new FileWriter(output_file, true);
            BufferedWriter writer = new BufferedWriter(write);

            //create a string made up of n copies of string s
            //String.join("", Collections.nCopies(n, s));

            String star = String.join("", Collections.nCopies(board.length * 2 + 2, "*"));

            writer.append(star + "\n");

            for (int m = 0; m < board.length; m++) {
                writer.append("*");
                for (int n = 0; n < board.length; n++) {
                    if (board[m][n] == null) {
                        writer.append("  ");
                    }

                    else {
                        writer.append(data.get_name(board[m][n]));
                    }
                }
                writer.append("*\n");
            }

            writer.append(star + "\n\n");

            writer.close();
            write.close();

            // Secondly, calls Printer method of Data class and prints current status of all characters.

            data.printer(output_file);
        }

        // This is a small part of printer. If there is an Boundary Error, it calls writing method and prints
        // error message to output file.

        if (boundary_error) {
            writing();
            boundary_error = false;
        }
    }

    // Control Location method checks given location data is valid or invalid. Used by damage and heal operations.

    public boolean control_location (int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board.length;
    }

    // Control Index method checks given location data is valid or invalid. Used by attack operations. If location is invalid,
    // throws an error.

    public boolean control_index (int x, int y) {
        if ( x >= 0 && y >= 0 && x < board.length && y < board.length) {
            return true;
        }

        else {
            boundary_error = true;
            throw new BoundaryException();
        }
    }

    // Clear method clears eliminated characters in board.

    public void clear (Data<String, Characters, int[]> data) {
        for (Characters[] list : board) {
            for (int i = 0; i < list.length; i++) {
                try {
                    if (list[i].HP <= 0) {
                        data.delete_by_character(list[i]);
                        list[i] = null;
                    }
                } catch (NullPointerException ignored) {}
            }
        }
    }

    // This method writes the team name of winner time to end of the output file.

    public void winning_team (String team) throws IOException {

        FileWriter write = new FileWriter(output_file, true);
        BufferedWriter writer = new BufferedWriter(write);

        writer.append("Game Finished\n" + team  + " Wins");

        writer.close();
        write.close();
    }

    // This method writes error messages to output file.

    public void writing () throws IOException {
        FileWriter write = new FileWriter(output_file, true);
        BufferedWriter writer = new BufferedWriter(write);

        write.append(  "Error : Game board boundaries are exceeded. Input line ignored.\n\n");

        writer.close();
        write.close();
    }
}
