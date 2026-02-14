package hw2;

import hw2.Dense2DMaze;
import hw2.Maze;

public class Dense2DMazeTest extends MazeTest {

    @Override
    public Maze createMaze(int width, int height, boolean defaultValue) {
        return new Dense2DMaze(width, height, defaultValue);
    }

}
