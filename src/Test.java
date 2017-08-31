/**
 * Created by Reaper on 8/28/17.
 */
import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();
        FENParser f = new FENParser("r1b2rk1/1p4pp/p1n2n2/3p2q1/4p3/PBP5/1P4PP/RN1QK2R w KQ - 32 17");
        f.parse(board);
        board.print_board();
    }

    public static void showClearMasks() {
        BitBoard board = new BitBoard();
        for(int i = 0; i < 64; i++) {
            board.show_string(board.clearMask[i]);
        }
    }
    public static void showSetMasks() {
        BitBoard board = new BitBoard();
        for(int i = 0; i < 64; i++) {
            board.show_string(board.setMask[i]);
        }
    }
}


//        long KING_ON_B2 = 1L | 1L << 1  | 1L << 2  |
//                               1L << 7  | 1L << 9  |
//                               1L << 15 | 1L << 16 | 1L << 17;
//        board.show_string(KING_ON_B2);