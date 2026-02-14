package hw2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.CellIndexOutOfBoundsException;
import exceptions.DimensionException;

/**
 * Dense maze implementation using a 1D boolean array with coordinate mapping.
 */
public class Dense1DMaze implements Maze {
    private boolean[] cells;
    private final int width;
    private final int height;
    private boolean defaultValue;

    /**
     * Constructs a Dense1DMaze with specified dimensions and default cell value.
     *
     * @param width        the width of the maze
     * @param height       the height of the maze
     * @param defaultValue the default value for cells in the maze
     *                     true for open, false for blocked
     * @throws DimensionException if width or height are non-positive
     */
    public Dense1DMaze(int width, int height, boolean defaultValue) {
        if (width <= 0 || height <= 0) {
            throw new DimensionException(width, height);
        }
        this.width = width;
        this.height = height;
        this.defaultValue = defaultValue;
        this.cells = new boolean[width * height];
        clear(defaultValue);
    }

    /**
     * Constructs a Dense1DMaze with specified dimensions and default cell value of true (open).
     *
     * @param width  the width of the maze
     * @param height the height of the maze
     * @throws DimensionException if width or height are non-positive
     */
    public Dense1DMaze(int width, int height) {
        this(width, height, true);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean isOpen(int row, int col) {
        validateCoordinates(row, col);
        return cells[getLinearIndex(row, col)];
    }

    @Override
    public void setCell(int row, int col, boolean isOpen) {
        validateCoordinates(row, col);
        cells[getLinearIndex(row, col)] = isOpen;
    }

    @Override
    public void clear(boolean defaultValue) {
        this.defaultValue = defaultValue;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = defaultValue;
        }
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Dense1DMazeIterator();
    }

    // Iterator for traversing the maze in row-major order
    private class Dense1DMazeIterator implements Iterator<Boolean> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < cells.length;
        }

        @Override
        public Boolean next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return cells[currentIndex++];
        }
    }

    // Validate that the provided coordinates are within maze bounds
    private void validateCoordinates(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new CellIndexOutOfBoundsException(row, col, height, width);
        }
    }

    // Convert 2D coordinates to a linear index in the 1D array
    private int getLinearIndex(int row, int col) {
        return row * width + col;
    }
}
