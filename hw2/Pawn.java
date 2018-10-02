/**
 * Represents a Pawn piece.
 *
 * @author exia3
 * @version 1.0
 */
public class Pawn extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the Pawn
     */
    public Pawn(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of Pawn
     */
    public String algebraicName() {
        return "";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of Pawn
     */
    public String fenName() {
        if (getColor().equals(Color.BLACK)) {
            return "p";
        } else {
            return "P";
        }
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the Pawn is located
     *
     * @return an array of type Square which includes all the square which the
     * Pawn can move from.
     */
    public Square[] movesFrom(Square square) {
        char rank = square.getRank();
        char file = square.getFile();
        if (getColor() == Color.WHITE) {
            if (rank == '8') {
                return new Square[0];
            } else if (rank == '2') {
                return new Square[]{new Square(file, '4'), new Square(file,
                    '3')};
            } else {
                return new Square[]{new Square(file, (char) (rank + 1))};
            }
        } else {
            if (rank == '1') {
                return new Square[0];
            } else if (rank == '7') {
                return new Square[]{new Square(file, '5'), new Square(file,
                    '6')};
            } else {
                return new Square[]{new Square(file, (char) (rank - 1))};
            }
        }
    }
}