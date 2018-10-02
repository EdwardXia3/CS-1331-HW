/**
 * Creates a class which represents a single square position on the board.
 * @author exia3
 * @version 2.0
 */
public class Square {
    private char file;
    private char rank;
    private String name;
    /**
     * A public constructor which initializes and stores the file and rank
     * instance variables and throws an exception in case of an invalid square.
     *
     * @param file column letter which the piece is located
     * @param rank row number which the piece is located
     */
    public Square(char file, char rank) {
        this("" + file + rank);
    }
    /**
     * A public constructor which initializes and stores the file and rank
     * instance variables by full name of the square and throws an exception
     * in case of an invalid square.
     *
     * @param name Square indicating the file and rank of the piece
     */
    public Square(String name) {
        this.name = name;
        if (name != null && name.length() == 2) {
            file = name.charAt(0);
            rank = name.charAt(1);
            if (file >= 'a' && file <= 'h' && rank >= '1' && rank <= '8') {
                this.name = name;
            } else {
                throw new InvalidSquareException(name);
            }
        } else {
            throw new InvalidSquareException(name);
        }
    }
    /**
     * @return the Square name of type String
     */
    public String toString() {
        return "" + file + rank;
    }
    /**
     * Overrides the equals method so objects of the same value or
     * same identity will return true
     *
     * @param o any object or variable
     *
     * @return the boolean value of the equals comparison
     */
    public boolean equals(Object o) {
        if (o instanceof Square) {
            Square that = (Square) o;
            return that.rank == rank && that.file == file;
        } else {
            return false;
        }
    }
    /**
     * A method that returns the file variable
     *
     * @return the file or column the Square is on
     */
    public char getFile() {
        return file;
    }
    /**
     * A method that returns the file variable
     *
     * @return the rank or row the Square is on
     */
    public char getRank() {
        return rank;
    }
}