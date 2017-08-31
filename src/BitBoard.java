/**
 * Created by Reaper on 8/28/17.
 */
public class BitBoard {
    public long bitboard[][]; // holds all piece locations
    public long masks[];
    private int WHITE = 0;
    public int BLACK = 1;
    // Files
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int G = 6;
    public static final int H = 7;
    private static final long[] RANKS = {
            0xFF00000000000000L, /* 1 */
            0x00FF000000000000L, /* 2 */
            0x0000FF0000000000L, /* 3 */
            0x000000FF00000000L, /* 4 */
            0x00000000FF000000L, /* 5 */
            0x0000000000FF0000L, /* 6 */
            0x000000000000FF00L, /* 7 */
            0x00000000000000FFL, /* 8 */
    };

    /* Bitmaps for board files */
    private static final long[] FILES = {
            0x0101010101010101L, /* A */
            0x0202020202020202L, /* B */
            0x0404040404040404L, /* C */
            0x0808080808080808L, /* D */
            0x1010101010101010L, /* E */
            0x2020202020202020L, /* F */
            0x4040404040404040L, /* G */
            0x8080808080808080L, /* H */
    };

    long WK = 0x1000000000000000L; // White King
    long WQ = 0x0800000000000000L; // White Queen
    long WR = 0x8100000000000000L; // White Rook
    long WB = 0x2400000000000000L; // White Bishop
    long WN = 0x4200000000000000L; // White Knight
    long WP = 0x00FF000000000000L; // White Pawn

    long BK = 0x0000000000000010L;  // Black King
    long BQ = 0x0000000000000008L; // Black Queen
    long BR = 0x0000000000000081L; // Black Rook
    long BB = 0x0000000000000024L; // Black Bishop
    long BN = 0x0000000000000042L; // Black Knight
    long BP = 0x000000000000FF00L; // Black Pawn

    long AW = WK | WQ | WR | WB | WN | WP; // All White
    long AB = BK | BQ | BR | BB | BN | BP; // All Black

    long ALL = AW | AB; // All pieces
    long EMPTY = -ALL;

    long setMask[] = null;
    long clearMask[] = null;


    public BitBoard(){
        initMasks();
    }
    public void initMasks(){
        setMask = new long[64];
        clearMask = new long[64];
        for(int i = 0; i < 64; i++) {
            setMask[i] = 0L;
            clearMask[i] = 0L;
        }
        for(int i = 0; i < 64; i++) {
            setMask[i] |= (1L << i);
            clearMask[i] = ~setMask[i];
        }
    }

    public void initbitBoard() {
        bitboard = new long[6][2]; // White/Black - Pieces

       bitboard[0][WHITE] = WK;
       bitboard[0][BLACK] = BK;

       bitboard[1][WHITE] = WQ;
       bitboard[1][BLACK] = BQ;

       bitboard[2][WHITE] = WR;
       bitboard[2][BLACK] = BR;

       bitboard[3][WHITE] = WB;
       bitboard[3][BLACK] = BB;

       bitboard[4][WHITE] = WN;
       bitboard[4][BLACK] = BN;

       bitboard[5][WHITE] = WP;
       bitboard[5][BLACK] = BP;


       

    }
    public long setBit(long bitmap, int sq64) {
        return bitmap |= setMask[sq64];

    }
    public long clearBit(long bitmap, int sq64) {
        return bitmap &= clearMask[sq64];
    }

    public void show_string(long bitmap){
        System.out.println("********");
        String str = String.format("%64s", Long.toBinaryString(bitmap)).replace(' ', '0');
        StringBuilder row = new StringBuilder();
        for(int i = 0; i < str.length(); i++)
        {
            if (i % 8 == 0 && i > 0){
                System.out.println(row);
                row.setLength(0);
                row.trimToSize();
            }
            row.append(str.charAt(i));
        }
        System.out.println(row);
        System.out.println("********");
    }

}
