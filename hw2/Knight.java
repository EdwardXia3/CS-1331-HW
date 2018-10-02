/**
 * Represents a Knight piece.
 *
 * @author exia3
 * @version 1.0
 */
public class Knight extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the Knight
     */
    public Knight(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of Knight
     */
    public String algebraicName() {
        return "N";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of Knight
     */
    public String fenName() {
        if (getColor().equals(Color.BLACK)) {
            return "n";
        } else {
            return "N";
        }
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the Knight is located
     *
     * @return an array of type Square which includes all the square which the
     * Knight can move from.
     */
    public Square[] movesFrom(Square square) {
        String s = square.toString();
        char row = s.charAt(0);
        char col = s.charAt(1);
        String moves = "";
        Square[] squareout;
        if (row < 'h') {
            if (col < '7') {
                moves = moves + ((char) (row + 1)) + ((char) (col + 2)) + ' ';
            }
            if (col > '2') {
                moves = moves + ((char) (row + 1)) + ((char) (col - 2)) + ' ';
            }
        }
        if (row > 'a') {
            if (col < '7') {
                moves = moves + ((char) (row - 1)) + ((char) (col + 2)) + ' ';
            }
            if (col > '2') {
                moves = moves + ((char) (row - 1)) + ((char) (col - 2)) + ' ';
            }
        }
        if (row < 'g') {
            if (col < '8') {
                moves = moves + ((char) (row + 2)) + ((char) (col + 1)) + ' ';
            }
            if (col > '1') {
                moves = moves + ((char) (row + 2)) + ((char) (col - 1)) + ' ';
            }
        }
        if (row > 'b') {
            if (col < '8') {
                moves = moves + ((char) (row - 2)) + ((char) (col + 1)) + ' ';
            }
            if (col > '1') {
                moves = moves + ((char) (row - 2)) + ((char) (col - 1)) + ' ';
            }
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