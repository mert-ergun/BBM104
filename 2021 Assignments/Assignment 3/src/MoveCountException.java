import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// If the user enters wrong number of moving operation, this error is thrown.

public class MoveCountException extends Exception{

    MoveCountException (String output) throws IOException {
        FileWriter write = new FileWriter(output, true);
        BufferedWriter writer = new BufferedWriter(write);

        write.append("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");

        writer.close();
        write.close();
    }
}
