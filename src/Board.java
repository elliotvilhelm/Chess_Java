/**
 * Created by Reaper on 8/28/17.
 */
//import static Def.*;

public class Board {
    public int[] pieces;
    public static final int BOARD_SQUARE_NUM = 120;

    public int turn;
    public int enPassSq;
    public int fiftyMove;

    private static int OFF_BOARD = -1;
    private static int NO_SQUARE = -2;

    int ply;
    int historyPly;
    long posKey; // should not need this rather implement serialiable
    int pieceCount[] = null;
    int bigPieces[] = null;
    int majPieces[] = null;
    int minorPieces[] = null;

    // Colors
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int BOTH = 2;


    long king_squares[] = null; // white black
    boolean castleRights[] = null; // KQkq
    long queen_count[] = null; // WHITE BLACK BOTH
    long rook_count[] = null;  // WHITE BLACK BOTH
    long knight_count[] = null; //WHITE BLACK BOTH
    long bishop_count[] = null;
    long pawn_count[] = null; // WHITE BLACK BOTH
    // Piece IDs
    public static final int EMPTY = 0;
    public static final int KING = 1;
    public static final int QUEEN = 2;
    public static final int ROOK = 3;
    public static final int BISHOP = 4;
    public static final int KNIGHT = 5;
    public static final int PAWN = 6;
    public int sq120toSq64[] = null;
    public int sq64toSq120[] = null;



    public int pieceValue[] = null;

    private long piece_list[][];

    BitBoard bitboard;
    public Board(){
        pieces = new int[BOARD_SQUARE_NUM];
        bitboard = new BitBoard();
        initializeSq64to120();
        initializeBoard();
        piece_char = ".KQRNBPkqrnbp";
        file_char = "ABCDEFGH";
    }

    public void initializeBoard() {
        king_squares = new long[2];
        queen_count = new long[2];
        rook_count = new long[2];
        knight_count = new long[2];
        bishop_count = new long[2];
        pawn_count = new long[2];
        piece_list = new long[13][2]; //
        castleRights = new boolean[4];
        for(int i = 0; i < BOARD_SQUARE_NUM; i++) {
            pieces[i] = OFF_BOARD;
        }
        for(int i = 0; i < 64; i++) {
            pieces[sq64toSq120[i]] = EMPTY;
        }
        for(int i = 0; i < 4; i++){
            castleRights[i] = false;
        }


    }
    String piece_char;
    String file_char;
    // Square Translations
    public void initializeSq64to120(){
        sq64toSq120 = new int[64];
        sq120toSq64 = new int[120];
        int sq64 = 0;
        int sq = FRtoSq120('A', 1);

        for(int i = 0; i < BOARD_SQUARE_NUM; i++) {
            sq120toSq64[i] = 65;
        }
        for(int i = 0; i < 64; i++) {
            sq64toSq120[i] = 120;
        }
        for(int rank = 0; rank < 8; rank++) {
            for(int file = 0; file < 8; file++) {
                sq = FRtoSq120(file, rank);
                sq64toSq120[sq64] = sq;
                sq120toSq64[sq] = sq64;
                sq64++;
            }
        }
    }
    public int FRtoSq120(int file, int rank) {
        return ((21 + file) + (rank * 10));
    }
    public int FRtoSq120(char file, int rank) {
        file = Character.toUpperCase(file);
        String str = "ABCDEFGH";
        int file_i = str.indexOf(file);
        rank--;
        return ((21 + file_i) + (rank * 10));
    }

    public void print_board(){
        int sq, file, rank, piece;
        String row = new String();

        System.out.println("\n*** GAME BOARD ***\n");
        for(rank = 7; rank >= 0; rank--)
        {
            System.out.print(rank+1);
            System.out.print("  ");
            for (file = 0; file < 8; file++) {
                sq = FRtoSq120(file, rank);
                piece = pieces[sq];
                System.out.print(piece_char.charAt(piece));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("   ");
        for(int i = 0; i < 8; i++){
            System.out.print(file_char.charAt(i));
            System.out.print(' ');
        }

        char t;
        if(turn == 0)
            t = 'w';
        else if(turn == 1)
            t = 'b';
        else{
            t = 'x';
        }
        System.out.println("\nTurn: " + t);
        System.out.println(enPassSq);
        if (enPassSq != 0)
            System.out.println("EnPassant Square: " + sq120toSq64[enPassSq]);
    }
}
