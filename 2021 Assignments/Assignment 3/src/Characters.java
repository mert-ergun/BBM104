import java.io.IOException;

// This class is the superclass of all characters in game.
// Keeps Hit Point, Attack Power, Max Move Number, Max Hit Point and Attack Type data's.

public class Characters {
    public int HP;
    public int AP;
    public boolean attack_type;  // If the character can attack after every step, attack_type will be true.
    public int max_move;
    private int max_HP;

    Characters (int HP, int AP, int max_move, int max_HP, boolean attack_type) {
        this.HP = HP;
        this.AP = AP;
        this.max_move = max_move;
        this.attack_type = attack_type;
        this.max_HP = max_HP;
    }

    // This method takes all data's from executor and manages all attacks according to given location data.

    public boolean attack (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) throws IOException {

        // Before start to attack, checks the location is valid.

        board.control_index(news[0], news[1]);

        // If this location is null, the character moves there.

        if (check_null(news, board)) {
            board.move(olds, news, character);
            data.move(character, news);

            // If the character can attack after all moving, the character damages all the neighboring enemies.

            if (character.attack_type) {
                damage(character, board, news, Constants.map, character.AP);
            }
            return true;
        }

        // If the location is not null, Attack_V2 method will run.

        else {
            return attack_v2(olds, news, character, board, data);
        }
    }

    public boolean attack_v2 (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) {

        // If there is an enemy, the characters will start to death fight.

        if (check_enemy(news, character, board)) {
            Characters enemy = board.get_character(news[0], news[1]);
            enemy.HP -= character.AP;

            if (enemy.HP <= 0) {
                board.clear(data);
                board.move(olds, news, character);
                data.move(character, news);
                return false;
            }

            else if (character.HP > enemy.HP) {
                character.HP -= enemy.HP;
                board.move(olds, news, character);
                data.move(character, news);
                data.delete_by_character(enemy);
                return false;
            }

            else if (character.HP < enemy.HP) {
                enemy.HP -= character.HP;
                board.move(olds, news, enemy);
                data.delete_by_character(character);
                return false;
            }

            else {
                board.move(olds, news, null);
                data.delete_by_character(enemy);
                data.delete_by_character(character);
                return false;
            }
        }

        else {
            return false;
        }
    }

    // This method is created to manage final attacks more easily. There is a little differences from normally attack,
    // but these are basically the same.

    public void final_attack (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) throws IOException, Exception {

        if (!character.attack_type) {
            character.attack_type = true;
            attack(olds, news, character, board, data);
            character.attack_type = false;
        }

        else {attack(olds, news, character, board, data);}
    }

    // This method is used by attack methods. If there is an enemy, returns true.

    public boolean check_enemy (int[] location, Characters character, Board board) {
        return character instanceof Zorde_Characters != board.get_character(location[0], location[1]) instanceof Zorde_Characters;
    }

    // This method is used by attack methods. If there is a friend, returns true.

    public boolean check_friends (int[] location, Characters character, Board board) {
        Characters c = board.get_character(location[0], location[1]);
        return c != null && character instanceof Zorde_Characters == c instanceof Zorde_Characters;
    }

    // This method is used by attack methods. If this location is null, returns true.

    public boolean check_null (int[] location, Board board) {
        try {
            return board.get_character(location[0], location[1]) == null;
        } catch (BoundaryException m) {return false;}
    }

    // This method takes enemies and decrease HP according to the attack power of enemy.

    public void damage (Characters character, Board board, int[] news, int[][] map, int AP)  {
        Characters c = null;
        for (int[] location : map) {
            int[] checking = {location[0] + news[0], location[1] + news[1]};

            try {
                if (!board.control_location(checking[0], checking[1])) {continue;}
            } catch (BoundaryException ignored) {continue;}


            c = board.get_character(checking[0], checking[1]);
            if (c != null && character instanceof Zorde_Characters != c instanceof Zorde_Characters) {
                c.HP -= AP;

            }
        }
    }

    // This method return current data's of character.

    public String get_data (Characters character, String name) {
        return name + "\t" + character.HP + "\t(" + character.get_max_HP() + ")\n";
    }

    // This method returns Max Hit Point. Used by Ork to check heal operations.

    public int get_max_HP () {
        return max_HP;
    }

    // Returns team name.

    public String get_team () {
        return "Calliance";
    }
}
