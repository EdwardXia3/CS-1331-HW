/**
 * Represents a Rook piece.
 *
 * @author exia3
 * @version 1.0
 */
public class Rook extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the Rook
     */
    public Rook(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of Rook
     */
    public String algebraicName() {
        return "R";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of Rook
     */
    public String fenName() {
        if (getColor().equals(Color.BLACK)) {
            return "r";
        } else {
            return "R";
        }
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the Rook is located
     *
     * @return an array of type Square which includes all the square which the
     * Rook can move from.
     */
    public Square[] movesFrom(Square square) {
        String s = square.toString();
        char row = s.charAt(0);
        char col = s.charAt(1);
        Square[] squareout;
        String moves = "";
        char rowstore = row;
        char colstore = col;
        while (row < 'h') {
            row = ((char) (row + 1));
            moves = moves + row + col + ' ';
        }
        row = rowstore;
        while (col > '1') {
            col = ((char) (col - 1));
            moves = moves + row + col + ' ';
        }
        col = colstore;
        while (col < '8') {
            col = ((char) (col + 1));
            moves = moves + row + col + ' ';
        }
        col = colstore;
        while (row > 'a') {
            row = ((char) (row - 1));
            moves = moves + row + col + ' ';
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