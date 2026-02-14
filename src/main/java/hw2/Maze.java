package hw2;

import exceptions.CellIndexOutOfBoundsException;

/**
 * Core interface for maze representations.
 * <p>
 * The interface extends Iterable to support traversal through all maze cells
 * in row-major order, regardless of the underlying storage implementation.
 */
public interface Maze extends Iterable<Boolean> {

    /**
     * Get the width (number of columns) of the maze.
     *
     * @return the number of columns in the maze
     */
    int getWidth();

    /**
     * Get the height (number of rows) of the maze.
     *
     * @return the number of rows in the maze
     */
    int getHeight();

    /**
     * Check if a cell is open (passable).
     *
     * @param row the row coordinate (0-indexed)
     * @param col the column coordinate (0-indexed)
     * @return true if the cell is open, false if blocked
     * @throws CellIndexOutOfBoundsException if coordinates are invalid
     */
    boolean isOpen(int row, int col);

    /**
     * Set the state of a cell.
     *
     * @param row    the row coordinate (0-indexed)
     * @param col    the column coordinate (0-indexed)
     * @param isOpen true to make the cell open, false to block it
     * @throws CellIndexOutOfBoundsException if coordinates are invalid
     */
    void setCell(int row, int col, boolean isOpen);

    /**
     * Clear the entire maze to a default value.
     *
     * @param defaultValue the value to set for all cells
     */
    void clear(boolean defaultValue);

    /**
     * Get the default value for this maze implementation.
     * This is the value that cells are initialized to and returned to when cleared.
     *
     * @return the default cell value
     */
    boolean getDefaultValue();
}
