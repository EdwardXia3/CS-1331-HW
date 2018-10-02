/**
 * Represents a Queen piece.
 *
 * @author exia3
 * @version 1.0
 */
public class Queen extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the Queen
     */
    public Queen(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of Queen
     */
    public String algebraicName() {
        return "Q";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of Queen
     */
    public String fenName() {
        if (getColor().equals(Color.BLACK)) {
            return "q";
        } else {
            return "Q";
        }
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the Queen is located
     *
     * @return an array of type Square which includes all the square which the
     * Queen can move from.
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
        row = rowstore;
        while (row < 'h' && col < '8') {
            row = ((char) (row + 1));
            col = ((char) (col + 1));
            moves = moves + row + col + ' ';
        }
        row = rowstore;
        col = colstore;
        while (row < 'h' && col > '1') {
            row = ((char) (row + 1));
            col = ((char) (col - 1));
            moves = moves + row + col + ' ';
        }
        row = rowstore;
        col = colstore;
        while (row > 'a' && col < '8') {
            row = ((char) (row - 1));
            col = ((char) (col + 1));
            moves = moves + row + col + ' ';
        }
        row = rowstore;
        col = colstore;
        while (row > 'a' && col > '1') {
            row = ((char) (row - 1));
            col = ((char) (col - 1));
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