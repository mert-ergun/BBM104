import java.io.IOException;

public class Ork extends Zorde_Characters{
    public final int heal = Constants.orkHealPoints;
    Ork (int HP, int AP, int max_move, boolean attack_type) {
        super(HP, AP, max_move, Constants.orkHP, attack_type);
    }

    // Ork has a special talent. It is healing. So, I need to design new final attack method for Ork.

    public void final_attack (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) throws Exception {
        healing(olds, news, character, board, data);
        super.final_attack(olds, news, character, board, data);
    }

    // If there is a neighboring friend, Ork will heal it.

    public void healing (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) throws IOException {
        heal_myself();
        for (int[] location : Constants.map) {
            int[] checking = {location[0] + olds[0], location[1] + olds[1]};
            if (checking[0] < 0 || checking[1] < 0) {continue;}
            Characters c = board.get_character(checking[0], checking[1]);

            if (super.check_friends(checking, character, board)) {
                c.HP += this.heal;
                if (c.HP > c.get_max_HP()) {
                    c.HP = c.get_max_HP();
                }
            }
        }
    }

    // And of course, Ork can heal itself.

    public void heal_myself () {
        HP += heal;
        if (HP > get_max_HP()) {
            HP = get_max_HP();
        }
    }
}
