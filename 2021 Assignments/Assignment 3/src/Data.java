import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// This class is special Data class that is designed for this game.
// Includes special methods.
// Uses HashMap and ArrayList in the backend.

public class Data <Name, Character, Location> {

    public ArrayList<Name> data = new ArrayList<Name>();
    public HashMap<Name , Character> characters = new HashMap<Name , Character>();
    public HashMap<Name, Location> locations = new HashMap<Name, Location>();
    public HashMap<Character, Name> reverse_characters = new HashMap<Character, Name>();

    // This method records characters.

    public void put (Name name, Character character, Location location) {
        characters.put(name, character);
        locations.put(name, location);
        reverse_characters.put(character, name);
        data.add(name);
    }

    // This method removes character from Data.

    public void delete_by_character (Character character) {
        Name name = reverse_characters.get(character);
        reverse_characters.remove(character);
        characters.remove(name);
        locations.remove(name);
        data.remove(name);
    }

    // This method records new location of given character.

    public void move (Character character, Location location) {
        Name name = reverse_characters.get(character);
        locations.put(name, location);
    }

    // This method sorts all characters according to their names.

    public void sort () {
        data.sort(new Comparator<Name>() {
            @Override
            public int compare(Name o1, Name o2) {
                String data1 = (String) o1;
                String data2 = (String) o2;
                if (data1.compareTo(data2) > 0) {
                    return 1;
                }

                else {
                    return -1;
                }
            }
        });
    }

    // This method is called from Board and writes character information to output file.

    public void printer (String output) throws IOException {

        FileWriter write = new FileWriter(output, true);
        BufferedWriter writer = new BufferedWriter(write);

        for (Name name : data ) {
            Characters c = (Characters) characters.get(name);
            writer.append(c.get_data(c, (String) name));
        }

        writer.append("\n");
        writer.close();
        write.close();
    }

    // This method checks the game is end or continuing.

    public boolean check_game (Board board) throws IOException {
        int Zorde = 0;
        int Calliance = 0;

        for (Map.Entry<Name, Character> data : characters.entrySet()) {
            Character c = data.getValue();
            if (c instanceof Zorde_Characters) {
                Zorde += 1;
            }

            else if (c instanceof Calliance_Characters) {
                Calliance += 1;
            }
        }

        if (Zorde > 0 && Calliance > 0) {
            return true;
        }

        else {
            board.winning_team(((Characters)characters.get(data.get(0))).get_team());
            return false;
        }
    }

    // This method returns the location of given character.

    public Location get_location (Character c) {return locations.get(reverse_characters.get(c));}

    // This method takes the name data and returns the character.

    public Character get_character (Name name) {
        return characters.get(name);
    }

    // This method the reverse version of get_character method. Takes the character and returns the name of given character.

    public Name get_name (Character character) {return reverse_characters.get(character);}
}
