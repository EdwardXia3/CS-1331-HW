/**
 * Represents a King piece.
 *
 * @author exia3
 * @version 1.0
 */
public class King extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the King
     */
    public King(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of King
     */
    public String algebraicName() {
        return "K";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of King
     */
    public String fenName() {
        if (getColor().equals(Color.BLACK)) {
            return "k";
        } else {
            return "K";
        }
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the King is located
     *
     * @return an array of type Square which includes all the square which the
     * King can move from.
     */
    public Square[] movesFrom(Square square) {
        String s = square.toString();
        char row = s.charAt(0);
        char col = s.charAt(1);
        Square[] squareout;
        String moves = "";
        if (row < 'h') {
            moves = moves + ((char) (row + 1)) + col + ' ';
            if (col < '8') {
                moves = moves + ((char) (row + 1)) + ((char) (col + 1)) + ' ';
            }
            if (col > '1') {
                moves = moves + ((char) (row + 1)) + ((char) (col - 1)) + ' ';
            }
        }
        if (row > 'a') {
            moves = moves + ((char) (row - 1)) + col + ' ';
            if (col < '8') {
                moves = moves + ((char) (row - 1)) + ((char) (col + 1)) + ' ';
            }
            if (col > '1') {
                moves = moves + ((char) (row - 1)) + ((char) (col - 1)) + ' ';
            }
        }
        if (col < '8') {
            moves = moves + row + ((char) (col + 1)) + ' ';
        }
        if (col > '1') {
            moves = moves + row + ((char) (col - 1)) + ' ';
        }
        String[] out = moves.split(" ");
        squareout = new Square[out.length];
        for (int i = 0; i < (out.length); i++) {
            Square nsq = new Square(out[i]);
            squareout[i] = nsq;
        }
        return squareout;
    }
}