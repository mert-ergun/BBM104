import java.io.IOException;

public class Elf extends Calliance_Characters{

    public final int range_attack = Constants.elfRangedAP;
    Elf (int HP, int AP, int max_move, boolean attack_type) {
        super(HP, AP, max_move, Constants.elfHP, attack_type);
    }

    // The overriding method. Designed for special final attack of Elf.

    public void final_attack (int[] olds, int[] news, Characters character, Board board, Data<String, Characters, int[]> data) throws IOException, Exception{
        board.control_index(news[0], news[1]);

        if (check_null(news, board)) {
            board.move(olds, news, character);
            data.move(character, news);

            if (character.attack_type) {
                damage(character, board, news, Constants.largeMap, range_attack);
            }
        }
        else {
            attack_v2(olds, news, character, board, data);
        }
    }
}
