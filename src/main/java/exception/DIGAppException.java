package exception;

/**
 *
 * @author martin
 */
public class DIGAppException extends Exception {

    /**
     * Creates a new instance of <code>DIGAppException</code> without detail
     * message.
     */
    public DIGAppException() {
    }

    /**
     * Constructs an instance of <code>DIGAppException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DIGAppException(String msg) {
        super(msg);
    }
    
    public DIGAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public DIGAppException(Throwable cause) {
        super(cause);
    }
}
