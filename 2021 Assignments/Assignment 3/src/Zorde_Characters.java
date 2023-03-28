// This class is used to determine team of characters.
// Team members: Goblin, Troll, Ork

public class Zorde_Characters extends Characters {
    Zorde_Characters(int HP, int AP, int max_move, int max_HP, boolean attack_type) {
        super(HP, AP, max_move, max_HP, attack_type);
    }

    public String get_team () {
        return "Zorde";
    }
}
