import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) throws Exception {

        String initials = args[0];
        String commands = args[1];
        String output = args[2];

        // This operation clears output file before starting game.

        FileWriter write = new FileWriter(output, false);
        BufferedWriter writer = new BufferedWriter(write);

        writer.close();
        write.close();

        // The program creates new board class ( to play ) and data class ( to store all information about characters and to data management).

        Board board = new Board(output);
        Data<String, Characters, int[]> data = new Data<String, Characters, int[]>();

        // After that, this operation reads all commands given.

        Method_Initialize.reader(initials, board, data);

        // The game is ready to start. All files and classes are sent to executors.

        Method_Execute.executor(commands, output, board, data);
    }
}
