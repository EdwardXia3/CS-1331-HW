import java.util.Optional;
/**
 * Creates a class which contains the information for
 * the piece and where it moves from and to.
 *
 * @author exia3
 * @version 1.0
 */
public class Ply {
    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;
    /**
     * Gettor method for the piece variable
     *
     * @return the piece being referenced
     */
    public Piece getPiece() {
        return piece;
    }
    /**
     * Gettor method for the from variable
     *
     * @return the Square where the piece moved from
     */
    public Square getFrom() {
        return from;
    }
    /**
     * Gettor method for the to variable
     *
     * @return the Square where the piece moved to
     */
    public Square getTo() {
        return to;
    }
    /**
     * Gettor method for the comment variable
     *
     * @return comments relating to the move
     */
    public Optional<String> getComment() {
        return comment;
    }
    /**
     * Public constructor instantiating piece, from, to, and comment
     *
     * @param piece the piece that was moved
     * @param from the square which the piece moved from
     * @param to the square which the piece moved to
     * @param comment comments relating to the move
     */
    public Ply(Piece piece, Square from, Square to, Optional<String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = comment;
    }
}