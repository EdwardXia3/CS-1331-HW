import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Creates a class which states information of a chess game.
 *
 * @author exia3
 * @version 1.0
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;
    /**
     * Constructor for each parameter and instantiates the moves arraylist
     *
     * @param event Name of the Event where game was held
     * @param site Location of the Event
     * @param date Date when game took place
     * @param white Player who played the white pieces
     * @param black Player who played the black pieces
     * @param result Indicates which player won
     */
    public ChessGame(String event, String site, String date, String white,
        String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }
    /**
     * Adds moves to the moves array list
     *
     * @param move a set of letters and numbers indicating which piece moved and
     * where it moved to
     */
    public void addMove(String move) {
        moves.add(move);
    }
    /**
     * A gettor method which returns a certain move based on the move number
     *
     * @param n the move number
     *
     * @return the nth move
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }
    /**
     * A gettor method for the event of the game
     *
     * @return the event of the game
     */
    public String getEvent() {
        return event.get();
    }
    /**
     * A gettor method for the site of the game
     *
     * @return the site of the game
     */
    public String getSite() {
        return site.get();
    }
    /**
     * A gettor method for the date of the game
     *
     * @return the date of the game
     */
    public String getDate() {
        return date.get();
    }
    /**
     * A gettor method for the white player of the game
     *
     * @return the white player of the game
     */
    public String getWhite() {
        return white.get();
    }
    /**
     * A gettor method for the black player of the game
     *
     * @return the black player of the game
     */
    public String getBlack() {
        return black.get();
    }
    /**
     * A gettor method for the result of the game
     *
     * @return the result of the game
     */
    public String getResult() {
        return result.get();
    }
    /**
     * A gettor method which determines if the sequence of moves matches any
     * standard opener
     *
     * @return the opener of the game or returns an empty string if it does not
     * match any opener
     */
    public String getOpening() {
        if (getMove(1).equals("e4 e5")) {
            if (getMove(2).equals("Nf3 Nc6")) {
                if (getMove(3).equals("Bc4 Bc5")) {
                    return "Giuoco Piano";
                }  else if (getMove(3).substring(0, 3).equals("Bb5")) {
                    return "Ruy Lopez";
                }
            } else if (getMove(2).equals("Nf3 d6")) {
                return "Philidor Defence";
            }
        } else if (getMove(1).equals("e4 c5")) {
            return "Sicilian Defence";
        } else if (getMove(1).equals("d4 d5")
            && getMove(2).substring(0, 2).equals("c4")) {
            return "Queen's Gambit";
        } else if (getMove(1).equals("d4 Nf6")) {
            return "Indian's Defence";
        }
        return "";
    }
}
