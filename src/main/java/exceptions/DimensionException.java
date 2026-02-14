package exceptions;

/**
 * Exception thrown when invalid maze dimensions are provided.
 */
public class DimensionException extends MazeException {

    /**
     * Constructs a new DimensionException with the specified detail message.
     *
     * @param message the detail message explaining the invalid dimensions
     */
    public DimensionException(String message) {
        super(message);
    }

    /**
     * Constructs a new DimensionException with the specified detail message and cause.
     *
     * @param message the detail message explaining the invalid dimensions
     * @param cause   the cause of this exception
     */
    public DimensionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a DimensionException with a standardized message for invalid dimensions.
     *
     * @param width  the invalid width value
     * @param height the invalid height value
     */
    public DimensionException(int width, int height) {
        super(String.format(
                "Invalid maze dimensions: width=%d, height=%d. Both dimensions must be positive.",
                width, height));
    }
}
