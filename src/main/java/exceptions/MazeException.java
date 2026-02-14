package exceptions;

/**
 * Base exception class for maze-related errors.
 */
public class MazeException extends RuntimeException {

    /**
     * Constructs a new MazeException with the specified detail message.
     *
     * @param message the detail message explaining the exception
     */
    public MazeException(String message) {
        super(message);
    }

    /**
     * Constructs a new MazeException with the specified detail message and cause.
     *
     * @param message the detail message explaining the exception
     * @param cause   the cause of this exception
     */
    public MazeException(String message, Throwable cause) {
        super(message, cause);
    }
}
