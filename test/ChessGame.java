import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;
public class ChessGame {
    private List<Move> moves;
    private String algname;
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public Move getMove(int n) {
        return moves.get(n);
    }
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> filteredmoves = new ArrayList<>();
        for (Move move : moves) {
            if (filter.test(move)) {
                filteredmoves.add(move);
            }
        }
        return filteredmoves;
    }
    public List<Move> getMovesWithComment() {
        List<Move> b = filter(it -> it.getWhitePly().getComment().isPresent() ||
            it.getBlackPly().getComment().isPresent());
        return b;
    }
    public List<Move> getMovesWithoutComment() {
        Predicate<Move> it = new Predicate<Move>() {
            public boolean test(Move it) {
                return !(it.getWhitePly().getComment().isPresent()) &&
                    !(it.getBlackPly().getComment().isPresent());
            }
        };
        List<Move> filteredmoves = new ArrayList<>();
        filteredmoves = filter(it);
        return filteredmoves;

    }
    public List<Move> getMovesWithPiece(Piece p) {
        algname = p.algebraicName();
        Predicate<Move> filt = new inner();
        List<Move> filteredmoves = new ArrayList<>();
        filteredmoves = filter(filt);
        return filteredmoves;
    }
    private class inner implements Predicate<Move>{
        public boolean test(Move it) {
            return it.getWhitePly().getPiece().algebraicName().equals(algname)
                || it.getBlackPly().getPiece().algebraicName().equals(algname);
        }
    }
}