package hw2;

import org.junit.jupiter.api.Test;
import hw2.Maze;
import hw2.SparseMaze;

import static org.junit.jupiter.api.Assertions.*;

public class SparseMazeTest extends MazeTest {
    @Override
    public Maze createMaze(int width, int height, boolean defaultValue) {
        return new SparseMaze(width, height, defaultValue);
    }

}