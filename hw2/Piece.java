/**
 * Creates an abstract class for all pieces.
 *
 * @author exia3
 * @version 1.0
 */
public abstract class Piece {
    private Color color;
    /**
     * A public constructor which takes a Color parameter and stores it as an
     * instance variable.
     *
     * @param color the color of the piece
     */
    public Piece(Color color) {
        this.color = color;
    }
    /**
     * @return the color of the piece
     */
    public Color getColor() {
        return color;
    }
    /**
     * Creates an abstract class that returns the algebraic name of the piece.
     *
     * @return the algebraicName of the piece
     */
    public abstract String algebraicName();
    /**
     * Creates an abstract class which returns the FEN name of the piece.
     *
     * @return the fenName of the piece
     */
    public abstract String fenName();
    /**
     * Creates an abstract class which returns an array of class Square which
     * contains squares that the piece could have moved from.
     *
     * @param square the square which the piece is currently located
     *
     * @return the array with all the squares where the piece can move from
     */
    public abstract Square[] movesFrom(Square square);
    /**
     * Overrides the default toString method
     *
     * @return the String form of the piece
     */
    public String toString() {
        return color.toString() + " " + this.getClass();
    }
    /**
     * Checks to see if the file and rank are valid.
     *
     * @param char file the file of the piece
     *
     * @param char rank the rank of the piece
     *
     * @return boolean indicating if piece is on the board
     */
    public boolean isInBoard(char file, char rank) {
        return file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8';
    }
}