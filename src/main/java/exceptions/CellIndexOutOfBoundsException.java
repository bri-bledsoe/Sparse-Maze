package exceptions;

/**
 * Exception thrown when invalid cell indices are provided for a maze.
 */
public class CellIndexOutOfBoundsException extends MazeException {

    /**
     * Constructs a new CellIndexOutOfBoundsException with the specified detail message.
     *
     * @param message the detail message explaining the invalid indices
     */
    public CellIndexOutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Constructs a new CellIndexOutOfBoundsException with the specified detail message and cause.
     *
     * @param message the detail message explaining the invalid indices
     * @param cause   the cause of this exception
     */
    public CellIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a CellIndexOutOfBoundsException with a standardized message for invalid indices.
     *
     * @param row    the invalid row index
     * @param col    the invalid column index
     * @param width  the maze width
     * @param height the maze height
     */
    public CellIndexOutOfBoundsException(int row, int col, int width, int height) {
        super(String.format("Invalid cell indices: row=%d, col=%d. Valid rows: 0-%d, cols: 0-%d.", row,
                col, height - 1, width - 1));
    }
}
