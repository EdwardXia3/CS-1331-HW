/**
 * Represents a Bishop piece.
 *
 * @author exia3
 * @version 1.0
 */
public class Bishop extends Piece {
    /**
     * Refers to the Color that is referenced in the Piece class.
     *
     * @param color Color of the bishop
     */
    public Bishop(Color color) {
        super(color);
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return algebraicName of Bishop
     */
    public String algebraicName() {
        return "B";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @return fenName of Bishop
     */
    public String fenName() {
        return getColor() == Color.WHITE ? "B" : "b";
    }
    /**
     * Rewrites the abstract method in the Piece class.
     *
     * @param square Square where the Bishop is located
     *
     * @return an array of type Square which includes all the square which the
     * Bishop can move from.
     */
    public Square[] movesFrom(Square square) {
        Square[] sq = new Square[27];
        int counter = 0;
        char rank = square.getRank();
        char file = square.getFile();
        for (int i = 1; i <= 8; i++) {
            char[] ranks = new char[]{(char) (rank + i), (char) (rank - i)};
            char[] files = new char[]{(char) (file + i), (char) (file - i)};
            if (isInBoard(files[0], ranks[0])) {
                sq[counter++] = new Square(files[0], ranks[0]);
            }
            if (isInBoard(files[1], ranks[0])) {
                sq[counter++] = new Square(files[1], ranks[0]);
            }
            if (isInBoard(files[0], ranks[1])) {
                sq[counter++] = new Square(files[0], ranks[1]);
            }
            if (isInBoard(files[1], ranks[1])) {
                sq[counter++] = new Square(files[1], ranks[1]);
            }
        }

        Square[] full = new Square[counter];
        for (int i = 0; i < counter; i++) {
            full[i] = sq[i];
        }

        return full;
    }
}