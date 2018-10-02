/**
 * Creates a class which creates a checked exception
 *
 * @author exia3
 * @version 1.0
 */
public class InvalidSquareException extends RuntimeException {
    /**
     * Refers to the message variable that is referenced in the Exception class.
     *
     * Justification for Checked Exception:
     * InvalidSquareException extends Exception and therefore, is an checked
     * exception. The exception should be checked because the square should be
     * valid before execution of other code. Other programmed code is made based
     * on the assumption of a valid square and the error should appear during
     * compile time rather than runtime.
     *
     * @param message text displayed during error
     */
    public InvalidSquareException(String message) {
        super(message);
    }
}