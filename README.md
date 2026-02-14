# Sparse Maze

A Java implementation of a sparse data structure for efficient maze representation, demonstrating fundamental computer science concepts like linked lists, iterators, and sparse data organization.

## Project Overview

This assignment introduces sparse data structures through an interactive maze implementation. A **sparse data structure** only stores values that differ from a default value, making it ideal for large datasets that are mostly uniform with scattered exceptions.

### Motivation

Imagine designing a video game with massive worlds (like Minecraft). Most of these worlds are largely empty space or filled with the same default terrain. Storing every single cell would be enormously wasteful! Instead, we can be clever: **assume all cells are open by default, and only store the locations of walls.**

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

#### Getters

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
## Key Concepts Covered

- **Linked Lists**: Singly linked list structure with sentinel node
- **Sparse Data Structures**: Efficient storage of mostly-uniform data
- **Iterators**: Custom iterator implementation for row-major traversal
- **Encapsulation**: Private nested Node class
- **Exception Handling**: DimensionException and CellIndexOutOfBoundsException
- **Asymptotic Analysis**: Understanding iterator efficiency
