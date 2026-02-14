# Sparse Maze

A Java implementation of a sparse data structure for efficient maze representation, demonstrating fundamental computer science concepts like linked lists, iterators, and sparse data organization.

## Project Overview

This assignment introduces sparse data structures through an interactive maze implementation. A **sparse data structure** only stores values that differ from a default value, making it ideal for large datasets that are mostly uniform with scattered exceptions.

### Motivation

Imagine designing a video game with massive worlds (like Minecraft). Most of these worlds are largely empty space or filled with the same default terrain. Storing every single cell would be enormously wasteful! Instead, we can be clever: **assume all cells are open by default, and only store the locations of walls.**

In practice, sparse data structures are fundamental in:
- Computer graphics
- Game development
- Scientific computing
- Any large dataset with mostly uniform values and scattered exceptions

## Implementation Requirements

### SparseMaze Class

Implement a `SparseMaze` class that implements the `Maze` interface using a sparse linked list structure:

- **Storage**: Singly linked list with a sentinel (dummy head) node
- **Default Assumption**: Most cells have the same default value (true for open, false for blocked)
- **Nodes Store**:
  - Linear index of the cell (calculated as `row * width + col`)
  - The value at that position (true/false)
  - Reference to the next node
- **Constraint**: No Java Collections (ArrayList, LinkedList, etc.) or advanced data structures

### Key Methods

#### Constructor
```java
SparseMaze(int width, int height, boolean defaultValue)
```
- Initializes dimensions and default value
- Creates sentinel node
- Throws `DimensionException` for invalid dimensions

#### Core Operations

```java
boolean isOpen(int row, int col)
```
- Searches linked list for the cell's linear index
- Returns stored value if found, default value if not found

```java
void setCell(int row, int col, boolean value)
```
- If setting to non-default: add or update node
- If setting to default: remove node (if exists) to maintain sparsity
- Updates `storedCellCount` accordingly
- Throws `CellIndexOutOfBoundsException` for invalid coordinates

```java
void clear()
```
- Resets maze to default state
- Clears all stored nodes
- Resets `storedCellCount` to 0

#### Accessors

```java
int getWidth()
int getHeight()
b boolean getDefaultValue()
```

### Iterator Implementation

The `Maze` interface extends `Iterable<Boolean>`, requiring an iterator implementation:

- **Traversal Order**: Row-major (row 0 left-to-right, then row 1, etc.)
- **Complete View**: Iterator must present the entire logical maze
- **Smart Lookup**: Return stored values from linked list, default values for cells not stored
- **Efficiency**: Must be efficient with few operations per `next()` call (similar to array-based implementations)

#### Example Iterator Behavior
```
Maze: 5×4, default = true (open)
Current stored nodes: [index=7, value=false], [index=14, value=false]

Iterator returns: T T T T T T T F T T T T T T F T ...
                 (logical view of all 20 cells in row-major order)
```

## Sparse Maze Example

### Initial Maze
```
Maze maze = new SparseMaze(5, 4, true); // 5×4 maze, default = open (true)

Logical view:
T T T T T
T T T T T
T T T T T
T T T T T

Internal state: head (sentinel) -> null
```

### After Setting a Wall
```
maze.setCell(1, 2, false); // Block cell at row 1, column 2

Linear index = 1 * 5 + 2 = 7

Logical view:
T T T T T
T T F T T
T T T T T
T T T T T

Internal state: head -> [index=7, value=false] -> null
```

### Multiple Walls
```
maze.setCell(2, 4, false); // Block cell at row 2, column 4

Linear index = 2 * 5 + 4 = 14

Logical view:
T T T T T
T T F T T
T T T T F
T T T T T

Internal state: head -> [index=7, value=false] -> [index=14, value=false] -> null
```

### Removing a Wall (Back to Default)
```
maze.setCell(1, 2, true); // Open cell at row 1, column 2 (back to default)

Node is removed since value equals default!

Logical view:
T T T T T
T T T T T
T T T T F
T T T T T

Internal state: head -> [index=14, value=false] -> null
```

## Grading Rubric (18 points total)

| Spec # | Requirement | Points |
|--------|-------------|--------|
| 1 | Constructor initialization, sentinel node, DimensionException | 2 |
| 2 | getWidth() and getHeight() correctness | 1 |
| 3 | getDefaultValue() correctness and consistency | 1 |
| 4 | isOpen() correct and efficient search | 2 |
| 5 | setCell() add, update, remove, and sparsity maintenance | 3 |
| 6 | setCell() maintains storedCellCount correctly | 1 |
| 7 | setCell() throws CellIndexOutOfBoundsException | 0.5 |
| 8 | clear() resets state and clears nodes | 1.5 |
| 9 | Iterator traverses all cells in row-major order | 2 |
| 10 | Iterator is efficient (few operations per next()) | 2 |
| 11 | Singly linked list with sentinel, no Java Collections | 1 |
| 12 | Good code style, encapsulation, nested private Node | 1 |

## Getting Started

### Prerequisites
- Java 8 or higher
- IntelliJ IDEA (recommended)

### Setup
1. Download and unzip the starter code (`hw2-starter.zip`)
2. Open the `hw2-starter` folder as an IntelliJ project
3. Review the `Maze` interface and dense implementations for reference

### Testing
- Use provided test cases to validate your implementation
- Run checkstyle to ensure code compliance
- Test sparsity: verify nodes are only stored for non-default values

## Important Notes

⚠️ **Refactoring for Efficiency**: After implementing a working solution, you may need to refactor to make the iterator efficient. Always commit your working version before attempting optimizations.

⚠️ **Maintain Sparsity**: It is **required** to remove nodes when values are set back to default!

⚠️ **No Modifications**: Do not modify the `Maze` interface or provided example implementations.

## Submission Checklist

- [ ] All methods implemented and tested
- [ ] Code passes checkstyle compliance
- [ ] Iterator is efficient (minimal operations per cell)
- [ ] Sparsity is maintained (no default values stored)
- [ ] Create zip file containing `src` folder
- [ ] Submit to Gradescope before deadline (Wed, Feb 11 at 5:00 PM ET)

## Key Concepts Covered

- **Linked Lists**: Singly linked list structure with sentinel node
- **Sparse Data Structures**: Efficient storage of mostly-uniform data
- **Iterators**: Custom iterator implementation for row-major traversal
- **Encapsulation**: Private nested Node class
- **Exception Handling**: DimensionException and CellIndexOutOfBoundsException
- **Asymptotic Analysis**: Understanding iterator efficiency

## References

- JHU Data Structures & Algorithms Course
- Starter code includes Dense1DMaze and Dense2DMaze as examples
- Maze interface specification in provided starter code

---

**Course**: JHU DSA | **Assignment**: Homework 2 | **Due**: Wed, Feb 11, 5:00 PM ET
