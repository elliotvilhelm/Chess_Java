import sun.font.TrueTypeFont;

/**
 * Created by Reaper on 8/28/17.
 */
public class Tile {
    // Variables
    private int tileNumber;
    private Piece piece;
    private boolean attacked;
    private int color;
    // Constructors
    public Tile(int tileNumber) {
        this.tileNumber = tileNumber;
        this.piece = null;
        this.color = -1;
    }
    public Tile(int tileNumber, int color,Piece piece) {
        this.tileNumber = tileNumber;
        this.piece = piece;
        this.color = color;
    }
    // Member Functions
    public boolean isTileOccupied() {
        if (this.piece == null) { return true;}
        else{ return false; }
    }
    // Setters & Getters
    public int getColor(){
        return this.color;
    }
    public void setColor(int color){
        this.color = color;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }
}
