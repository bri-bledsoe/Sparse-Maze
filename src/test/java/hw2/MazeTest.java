package hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import hw2.Maze;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MazeTest is an abstract base test class that defines common setup and constants for maze tests.
 * It requires subclasses to implement createMaze(), which is used to initialize the maze field
 * before each test.
 * <p>
 * This structure allows you to write generic tests in MazeTest that will be run
 * for all maze types, ensuring consistency and reducing code duplication. Each subclass can also
 * add its own specific tests if needed. This is a standard and effective approach for testing
 * multiple implementations of a common interface or abstract class.
 * <p>
 * To run these tests, open the subclass test files (e.g., Dense2DMazeTest.java,
 * Dense1DMazeTest.java, SparseMazeTest.java) and run the tests from there.
 */
public abstract class MazeTest {
    protected static final int DEFAULT_WIDTH = 10;
    protected static final int DEFAULT_HEIGHT = 8;
    protected static final boolean DEFAULT_VALUE = true;
    protected Maze maze;

    public abstract Maze createMaze(int width, int height, boolean defaultValue);

    @BeforeEach
    public void setup() {
        maze = createMaze(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VALUE);
    }

    @Test
    public void testConstructorCreatesMazeWithGivenWidthAndHeight() {
        assertEquals(DEFAULT_WIDTH, maze.getWidth());
        assertEquals(DEFAULT_HEIGHT, maze.getHeight());
    }

    @Test
    public void testConstructorPlastersDefaultValues() {
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            for (int col = 0; col < DEFAULT_WIDTH; col++) {
                assertEquals(DEFAULT_VALUE, maze.isOpen(row, col));
            }
        }
    }

    @Test
    public void testConstructorThrowsDimensionExceptionForZeroWidth() {
        assertThrows(exceptions.DimensionException.class, () -> {
            createMaze(0, DEFAULT_HEIGHT, DEFAULT_VALUE);
        });
    }

    @Test
    public void testConstructorThrowsDimensionExceptionForNegativeWidth() {
        assertThrows(exceptions.DimensionException.class, () -> {
            createMaze(-1, DEFAULT_HEIGHT, DEFAULT_VALUE);
        });
    }

    @Test
    public void testConstructorThrowsDimensionExceptionForZeroHeight() {
        assertThrows(exceptions.DimensionException.class, () -> {
            createMaze(DEFAULT_WIDTH, 0, DEFAULT_VALUE);
        });
    }

    @Test
    public void testConstructorThrowsDimensionExceptionForNegativeHeight() {
        assertThrows(exceptions.DimensionException.class, () -> {
            createMaze(DEFAULT_WIDTH, -1, DEFAULT_VALUE);
        });
    }

    @Test
    public void testGetWidthReturnsWidth() {
        assertEquals(DEFAULT_WIDTH, maze.getWidth());
    }

    @Test
    public void testGetHeightReturnsHeight() {
        assertEquals(DEFAULT_HEIGHT, maze.getHeight());
    }

    @Test
    public void testGetDefaultValueReturnsDefaultValue() {
        assertEquals(DEFAULT_VALUE, maze.getDefaultValue());
    }

    @Test
    public void testSetCellUpdatesCellValueToFalse() {
        maze.setCell(2, 3, false);
        assertFalse(maze.isOpen(2, 3));
    }

    @Test
    public void testSetCellUpdatesCellValueToTrue() {
        maze.setCell(2, 3, false);
        maze.setCell(2, 3, true);
        assertTrue(maze.isOpen(2, 3));
    }

    @Test
    public void testSetCellUpdatesCellValueAtOrigin() {
        maze.setCell(0, 0, false);
        assertFalse(maze.isOpen(0, 0));
    }

    @Test
    public void testSetCellUpdatesCellValueAtLastCell() {
        maze.setCell(DEFAULT_HEIGHT - 1, DEFAULT_WIDTH - 1, false);
        assertFalse(maze.isOpen(DEFAULT_HEIGHT - 1, DEFAULT_WIDTH - 1));
    }

    @Test
    public void testSetCellOnlyChangesGivenCell() {
        maze.setCell(1, 1, false);
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            for (int col = 0; col < DEFAULT_WIDTH; col++) {
                if (row == 1 && col == 1) {
                    assertFalse(maze.isOpen(row, col));
                } else {
                    assertEquals(DEFAULT_VALUE, maze.isOpen(row, col));
                }
            }
        }
    }

    @Test
    public void testSetCellThrowsCellIndexOutOfBoundsExceptionForNegativeRow() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.setCell(-1, 0, true));
    }

    @Test
    public void testSetCellThrowsCellIndexOutOfBoundsExceptionForRowTooLarge() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.setCell(DEFAULT_HEIGHT, 0, true));
    }

    @Test
    public void testSetCellThrowsCellIndexOutOfBoundsExceptionForNegativeCol() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.setCell(0, -1, true));
    }

    @Test
    public void testSetCellThrowsCellIndexOutOfBoundsExceptionForColTooLarge() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.setCell(0, DEFAULT_WIDTH, true));
    }

    @Test
    public void testIsOpenReturnsTrueIfCellIsOpen() {
        maze.setCell(3, 4, true);
        assertTrue(maze.isOpen(3, 4));
    }

    @Test
    public void testIsOpenReturnsFalseIfCellIsBlocked() {
        maze.setCell(3, 4, false);
        assertFalse(maze.isOpen(3, 4));
    }

    @Test
    public void testIsOpenThrowsCellIndexOutOfBoundsExceptionForNegativeRow() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class, () -> maze.isOpen(-1, 0));
    }

    @Test
    public void testIsOpenThrowsCellIndexOutOfBoundsExceptionForRowTooLarge() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.isOpen(DEFAULT_HEIGHT, 0));
    }

    @Test
    public void testIsOpenThrowsCellIndexOutOfBoundsExceptionForNegativeCol() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class, () -> maze.isOpen(0, -1));
    }

    @Test
    public void testIsOpenThrowsCellIndexOutOfBoundsExceptionForColTooLarge() {
        assertThrows(exceptions.CellIndexOutOfBoundsException.class,
                () -> maze.isOpen(0, DEFAULT_WIDTH));
    }

    @Test
    public void testClearResetsMazeToDefaultState() {
        maze.setCell(1, 1, !DEFAULT_VALUE);
        maze.setCell(2, 2, !DEFAULT_VALUE);
        maze.clear(DEFAULT_VALUE);
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            for (int col = 0; col < DEFAULT_WIDTH; col++) {
                assertEquals(DEFAULT_VALUE, maze.isOpen(row, col));
            }
        }
    }

    @Test
    public void testIteratorRowMajorOrderAfterConstruction() {
        int count = 0;
        for (Boolean cell : maze) {
            int row = count / DEFAULT_WIDTH;
            int col = count % DEFAULT_WIDTH;
            assertEquals(DEFAULT_VALUE, cell);
            assertEquals(maze.isOpen(row, col), cell);
            count++;
        }
        assertEquals(DEFAULT_WIDTH * DEFAULT_HEIGHT, count);
    }

    @Test
    public void testIteratorAfterSettingFirstRow() {
        for (int col = 0; col < DEFAULT_WIDTH; col++) {
            maze.setCell(0, col, false);
        }
        int count = 0;
        for (Boolean cell : maze) {
            boolean expected = (count / DEFAULT_WIDTH == 0) ? false : DEFAULT_VALUE;
            assertEquals(expected, cell);
            count++;
        }
        assertEquals(DEFAULT_WIDTH * DEFAULT_HEIGHT, count);
    }

    @Test
    public void testIteratorAfterSettingLastColumn() {
        for (int row = 0; row < DEFAULT_HEIGHT; row++) {
            maze.setCell(row, DEFAULT_WIDTH - 1, false);
        }
        int count = 0;
        for (Boolean cell : maze) {
            boolean expected = (count % DEFAULT_WIDTH == DEFAULT_WIDTH - 1) ? false : DEFAULT_VALUE;
            assertEquals(expected, cell);
            count++;
        }
        assertEquals(DEFAULT_WIDTH * DEFAULT_HEIGHT, count);
    }

    @Test
    public void testIteratorAfterClearingMaze() {
        maze.setCell(0, 0, !DEFAULT_VALUE);
        maze.clear(DEFAULT_VALUE);
        int count = 0;
        for (Boolean cell : maze) {
            assertEquals(DEFAULT_VALUE, cell);
            count++;
        }
        assertEquals(DEFAULT_WIDTH * DEFAULT_HEIGHT, count);
    }

    @Test
    public void testIteratorThrowsNoSuchElementException() {
        var it = maze.iterator();
        int total = DEFAULT_WIDTH * DEFAULT_HEIGHT;
        for (int i = 0; i < total; i++) {
            assertTrue(it.hasNext());
            it.next();
        }
        assertFalse(it.hasNext());
        assertThrows(java.util.NoSuchElementException.class, () -> it.next());
    }
}
