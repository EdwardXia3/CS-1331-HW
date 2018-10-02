import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
/**
 * Creates an abstract class for all pieces.
 *
 * @author exia3
 * @version 1.0
 */
public class ChessGame {
    private List<Move> moves;
    private String algname;
    /**
     * Public constructor instantiating moves
     *
     * @param moves a list array of objects of type Move
     */
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }
    /**
     * Gettor method for the moves variable
     *
     * @return a list array of objects of type Move
     */
    public List<Move> getMoves() {
        return moves;
    }
    /**
     * Gettor method for the Move variable
     *
     * @param n the index within the moves array
     *
     * @return the nth Move within the moves array
     */
    public Move getMove(int n) {
        return moves.get(n);
    }
    /**
     * Filters out the list of moves based on a condition
     *
     * @param filter the condition that is being filtered
     *
     * @return the moves that meet the filter condition
     */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> filteredmoves = new ArrayList<>();
        for (Move move : moves) {
            if (filter.test(move)) {
                filteredmoves.add(move);
            }
        }
        return filteredmoves;
    }
    /**
     * A method which filters and returns the moves with comments for either
     * the black player or the white player
     *
     * @return the moves containing a comment in either color
     */
    public List<Move> getMovesWithComment() {
        List<Move> b = filter(it -> it.getWhitePly().getComment().isPresent()
            || it.getBlackPly().getComment().isPresent());
        return b;
    }
    /**
     * A method which filters and returns the moves without comments for both
     * colors.
     *
     * @return the moves not containing a comment in either color
     */
    public List<Move> getMovesWithoutComment() {
        Predicate<Move> it = new Predicate<Move>() {
            public boolean test(Move it) {
                return !(it.getWhitePly().getComment().isPresent())
                    && !(it.getBlackPly().getComment().isPresent());
            }
        };
        List<Move> filteredmoves = new ArrayList<>();
        filteredmoves = filter(it);
        return filteredmoves;

    }
    /**
     * A method which filters and returns the moves with the same
     * piece as the input paramater for either color.
     *
     * @param piece the piece that is being compared in the move
     *
     * @return the moves involving the same piece as the input paramter for
     * either color.
     */
    public List<Move> getMovesWithPiece(Piece piece) {
        algname = piece.algebraicName();
        Predicate<Move> filt = new Inner();
        List<Move> filteredmoves = new ArrayList<>();
        filteredmoves = filter(filt);
        return filteredmoves;
    }
    private class Inner implements Predicate<Move> {
        @Override
        public boolean test(Move it) {
            return it.getWhitePly().getPiece().algebraicName().equals(algname)
                || it.getBlackPly().getPiece().algebraicName().equals(algname);
        }
    }
}