/**
 * Creates an class which holds both of the data for the white
 * and black moves.
 *
 * @author exia3
 * @version 1.0
 */
public class Move {
    private Ply whitePly;
    private Ply blackPly;
    /**
     * Gettor method for the Ply variable
     *
     * @return information for the white player for that move
     */
    public Ply getWhitePly() {
        return whitePly;
    }
    /**
     * Gettor method for the Ply variable
     *
     * @return information for the black player for that move
     */
    public Ply getBlackPly() {
        return blackPly;
    }
    /**
     * Public constructor instantiating whitePly and blackPly
     *
     * @param whitePly information for the white player for that move
     * @param blackPly information for the white player for that move
     */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }
}