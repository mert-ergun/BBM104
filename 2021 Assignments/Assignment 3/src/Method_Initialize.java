import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// This class manages to record characters. Scan all data from initials file and send data's to Data and Board class.

public class Method_Initialize {
    public static void reader (String initials, Board board, Data<String, Characters, int[]> data) throws IOException {

        FileReader read = new FileReader(initials);
        BufferedReader reader = new BufferedReader(read);

        String line = reader.readLine();
        line = reader.readLine();
        String[] elements;

        int x = Integer.parseInt(line.split("x")[0]);
        int y = Integer.parseInt(line.split("x")[1]);
        board.set_up(x,y);

        while (line != null) {
            elements = line.split("\\s+");
            if (elements.length > 1) {
                Characters c = null;
                if (elements[0].equals("ORK")) {
                    c = new Ork(200, Constants.orkAP, Constants.orkMaxMove, false);
                }
                else if (elements[0].equals("TROLL")) {
                    c = new Troll(150, Constants.trollAP, Constants.trollMaxMove, false);
                }
                else if (elements[0].equals("GOBLIN")) {
                    c = new Goblin(80, Constants.goblinAP, Constants.goblinMaxMove,  true);
                }
                else if (elements[0].equals("HUMAN")) {
                    c = new Human(100, Constants.humanAP, Constants.humanMaxMove,  false);
                }
                else if (elements[0].equals("ELF")) {
                    c = new Elf(70, Constants.elfAP, Constants.elfMaxMove,  true);
                }
                else if (elements[0].equals("DWARF")) {
                    c = new Dwarf(120, Constants.dwarfAP, Constants.dwarfMaxMove,  true);
                }

                int[] location = {Integer.parseInt(elements[3]), Integer.parseInt(elements[2])};
                data.put(elements[1], c, location);
                board.create(location, c);
            }
            line = reader.readLine();
        }

        data.sort();

        reader.close();
        read.close();
    }
}
