package hw2;

import exceptions.CellIndexOutOfBoundsException;
import exceptions.DimensionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sparse maze implementation using a linked list to store non-default cell values.
 */
public class SparseMaze implements Maze {
  protected Node head; // Sentinel node
  // You should maintain this count of stored (non-default) cells
  // So every time you add or remove a stored cell (i.e., a node), update this count.
  protected int storedCellCount;
  private final int width;
  private final int height;
  private boolean defaultValue;

  /**
   * Node in the sparse linked list storing non-default cell values.
   * Uses linear indexing for ordered storage and efficient traversal.
   */
  protected static class Node {
    public boolean value;
    public int linearIndex;
    public Node next;

    /**
     * Constructs a node storing a non-default value.
     * @param value boolean value stored in cell
     * @param linearIndex row-major linear index of the cell
     */
    public Node(boolean value, int linearIndex) {
      this.value = value;
      this.linearIndex = linearIndex;
    }
  }

  /**
   * Constructs a SparseMaze with specified dimensions and default cell value.
   *
   * @param width the width of the maze
   * @param height the height of the maze
   * @param defaultValue the default value for cells in the maze
   *        true for open, false for blocked
   * @throws DimensionException if width or height are non-positive
   */
  public SparseMaze(int width, int height, boolean defaultValue) {
    if (width <= 0 || height <= 0) {
      throw new DimensionException(width, height);
    }

    this.defaultValue = defaultValue;
    this.width = width;
    this.height = height;
    this.storedCellCount = 0;

    this.head = new Node(defaultValue, -1);
  }

  /**
   * Constructs a SparseMaze with specified dimensions and default cell value of true (open).
   *
   * @param width the width of the maze
   * @param height the height of the maze
   * @throws DimensionException if width or height are non-positive
   */
  public SparseMaze(int width, int height) {
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
    checkBounds(row, col);
    int index = row * width + col;
    Node cur = findNodeAtIndex(index);

    if (cur != null && cur.linearIndex == index) {
      return cur.value;
    }
    return defaultValue;
  }

  @Override
  public void setCell(int row, int col, boolean isOpen) {
    checkBounds(row, col);
    int index = row * width + col;
    Node cur = findNodeAtIndex(index);

    if (cur != null && cur.linearIndex == index) {
      if (isOpen == defaultValue) {
        setCellOpen(cur);
      } else {
        cur.value = isOpen;
      }
    } else {
      if (isOpen != defaultValue) {
        setCellBlocked(cur, isOpen, index);
      }
    }
  }

  private void setCellOpen(Node cur) {
    Node prev = head;
    while (prev.next != cur) {
      prev = prev.next;
    }
    prev.next = cur.next;
    storedCellCount--;
  }

  private void setCellBlocked(Node cur, boolean isOpen, int index) {
    Node prev = head;
    while (prev.next != cur) {
      prev = prev.next;
    }

    Node newNode = new Node(isOpen, index);
    prev.next = newNode;
    newNode.next = cur;
    storedCellCount++;
  }

  private Node findNodeAtIndex(int index) {
    Node cur = head.next;
    while (cur != null && cur.linearIndex < index) {
      cur = cur.next;
    }
    return cur;
  }

  @Override
  public void clear(boolean newDefaultValue) { // parameter name changed to pass checkstyle for hidden field
    this.defaultValue = newDefaultValue;
    head.next = null;
    storedCellCount = 0;
  }

  @Override
  public Iterator<Boolean> iterator() {
    return new SparseMazeIterator();
  }

  private class SparseMazeIterator implements Iterator<Boolean> {
    Node cur = head.next;
    int index; // initialized to zero (checkstyle flagged when initialization was explicit)

    @Override
    public boolean hasNext() {
      return index < width * height;
    }

    @Override
    public Boolean next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      if (cur != null && cur.linearIndex == index) {
        boolean curValue = cur.value;
        cur = cur.next;
        index++;
        return curValue;
      }
      index++;
      return defaultValue;
    }
  }

  private void checkBounds(int row, int col) throws CellIndexOutOfBoundsException {
    if (row < 0 || row >= height || col < 0 || col >= width) {
      throw new CellIndexOutOfBoundsException(row, col, width, height);
    }
  }
}
