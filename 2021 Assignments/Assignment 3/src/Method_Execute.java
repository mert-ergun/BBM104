import java.io.*;

// This method takes all data's from Main class and starts to game.

public class Method_Execute {
    public static void executor (String commands, String output, Board board, Data<String, Characters, int[]> data) throws Exception {

        // Firstly writes the board to output file.

        board.changing = true;
        board.printer(data);
        board.changing = false;

        FileReader read = new FileReader(commands);
        BufferedReader reader = new BufferedReader(read);

        String line = reader.readLine();
        String[] elements;

        int length;
        boolean game = true;

        while (line != null && game) {
            elements = line.substring(3).split(";");
            try {
                Characters c = data.get_character(line.substring(0,2));
                // System.out.println(c); // I used this operation to check the program runs correctly.
                board.changing = false;

                // If the user enters wrong number of operations, throws an error.

                if (elements.length / 2 != c.max_move) {throw  new MoveCountException(output);}

                length = 0;
                boolean continuing = true;

                // Takes commands from line and sends to attack method.

                while (continuing && length < elements.length - 2) {
                    int [] loc = data.get_location(c);
                    int[] news = {Integer.parseInt(elements[length + 1]) + loc[0], Integer.parseInt(elements[length]) + loc[1]};
                    continuing = c.attack(loc, news, c, board, data);
                    length += 2;
                }

                // Takes commands from line and sends to final attack method.

                if (continuing) {
                    int [] loc = data.get_location(c);
                    int[] news = {Integer.parseInt(elements[length + 1]) + loc[0], Integer.parseInt(elements[length]) + loc[1]};
                    c.final_attack(loc, news, c, board, data);
                }

            } catch (MoveCountException | BoundaryException ignored) { }

            board.clear(data);
            board.printer(data);

            game = data.check_game(board);

            // If the game isn't end, the loop continues the game.

            line = reader.readLine();
        }
    }
}
