import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Platform class. This class creates and draws the platforms where the hero can move horizontally.
 */
public class Platform {
    private final Position left;        /** left corner of the platform */
    private final Position right;       /** right corner of the platform */
    private final List<Wall> walls;     /** list of walls that compose the platform */

    /**
     * Constructor. A platform is composed by many wall units.
     * @param left is the position where the left corner of the platform is.
     * @param right is the position where the right corner of the platform is.
     */
    public Platform(Position left, Position right) {
        this.left = left;
        this.right = right;
        walls = createWalls();
    }

    /**
     * Creates wall units that will be used to form a platform.
     * @return wall units that will compose a platform.
     */
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        int n = right.getX() - left.getX() + 1;
        for (int i = 0; i < n; i++)
            walls.add(new Wall(left.getX() + i, left.getY()));

        return walls;
    }

    /**
     * Obtain left corner of the platform.
     * @return left edge.
     */
    public Position getLeft() {
        return left;
    }

    /**
     * Obtain right corner on the platform.
     * @return right edge.
     */
    public Position getRight() {
        return right;
    }

    /**
     * Obtain the list of walls that forms the platforms.
     * @return list of platforms.
     */
    public List<Wall> getWalls() {
        return walls;
    }

    public boolean hasElement(Position position) {
        // to do
        return true;
    }

    /**
     * Draws a platform on the screen.
     * @param screen
     */
    public void draw(TextGraphics screen) {
        for (Wall w : walls)
            w.draw(screen);
    }
}
