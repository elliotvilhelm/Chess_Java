/**
 * Created by Reaper on 8/29/17.
 */

public class FENParser {
    String input;
    int bK = 7;
    int bQ = 8;
    int bR = 9;
    int bN = 10;
    int bB = 11;
    int bP = 12;

    int wK = 1;
    int wQ = 2;
    int wR = 3;
    int wN = 4;
    int wB = 5;
    int wP = 6;
    int EMPTY = 0;

    String sample;


    public FENParser() {
        input = new String();
        sample = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";
        input = sample;
    }

    public FENParser(String in) {
        input = in;
    }

    public void parse(Board b) {
        int i = 0;
        int piece = -1;
        int count = 1;
        int sq64 = 0;
        int sq120 = 0;
        int rank = 7;
        int file = 0;
        boolean end_board = false;
//        boolean end_turn = false;
//        boolean
//        int i = 0;

        while (i < input.length()) {
            count  = 1;
            char cursor = input.charAt(i);
//            System.out.println(cursor);
            if(!end_board) {
                switch (cursor) {
                    case 'p':
                        piece = bP;
                        break;
                    case 'r':
                        piece = bR;
                        break;
                    case 'n':
                        piece = bN;
                        break;
                    case 'b':
                        piece = bB;
                        break;
                    case 'k':
                        piece = bK;
                        break;
                    case 'q':
                        piece = bQ;
                        break;
                    case 'P':
                        piece = wP;
                        break;
                    case 'R':
                        piece = wR;
                        break;
                    case 'N':
                        piece = wN;
                        break;
                    case 'B':
                        piece = wB;
                        break;
                    case 'K':
                        piece = wK;
                        break;
                    case 'Q':
                        piece = wQ;
                        break;

                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                        piece = EMPTY;
                        count = cursor - '0';
                        break;

                    case '/':
//                case ' ':
                        rank--;
                        file = 0;
                        count = 0;
                        break;
                    case ' ':
                        count = 0;
                        end_board = true;
                        break;

                    default:

                        System.out.println("error : " + cursor);
                        break;

                }

//            System.out.println(cursor);
                if (rank >= 0) {
                    for (int j = 0; j < count; j++) {
                        sq64 = rank * 8 + file;
//                    System.out.println("sq 64: " + sq64 + "   piece: " + piece);
//                System.out.println(sq64);
                        sq120 = b.sq64toSq120[sq64];
                        if (piece != EMPTY) {
                            b.pieces[sq120] = piece;
                        }
                        file++;
                    }
                }
            }
            else{
                break;
//                if(cursor == 'w')
//                    b.turn = 0;
//                if()
//                System.out.println("cursor : "+cursor);
            }
            i++;

        }
        System.out.println("i: "+ i + "  cursor: " + input.charAt(i));
        char cursor = input.charAt(i);
        if(cursor == 'w'){
            b.turn = 0;
            i += 2;
        }
        else if (cursor == 'b'){
            b.turn = 1;
            i += 2;
        }
        for(int k = 0; k < 4; k++)
        {
            cursor = input.charAt(i);
//            if(cursor == '-'){
//                b.castleRights[k] = false;
//                i++;
//            }

            if (cursor == 'K')
                b.castleRights[0] = true;
            else if (cursor == 'Q')
                b.castleRights[1] = true;
            else if (cursor == 'k')
                b.castleRights[2] = true;
            else if (cursor == 'q')
                b.castleRights[3] = true;
            else if (cursor == ' ')
                break;
            i++;
        }

        i++; // space
        cursor = input.charAt(i);
        if(cursor == '-')
            i+=2;
        else {
//            System.out.println("en passat " + input.charAt(i) +"   i+1 " +input.charAt(i + 1));
//            System.out.println(b.FRtoSq120(input.charAt(i), input.charAt(i + 1)-'0'));
            b.enPassSq = b.FRtoSq120(input.charAt(i), input.charAt(i + 1)-'0');
            i+=3;
        }
        cursor = input.charAt(i);
        b.fiftyMove = cursor;
        i += 2;
        cursor = input.charAt(i);
        b.ply = cursor; // i think
    }
}


