public class Move {
    private Ply whitePly;
    private Ply blackPly;
    public Ply getWhitePly() {
        return whitePly;
    }
    public Ply getBlackPly() {
        return blackPly;
    }
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }
}