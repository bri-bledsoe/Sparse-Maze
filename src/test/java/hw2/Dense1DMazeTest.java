package hw2;

import hw2.Dense1DMaze;
import hw2.Maze;

public class Dense1DMazeTest extends MazeTest {

    @Override
    public Maze createMaze(int width, int height, boolean defaultValue) {
        return new Dense1DMaze(width, height, defaultValue);
    }

}
