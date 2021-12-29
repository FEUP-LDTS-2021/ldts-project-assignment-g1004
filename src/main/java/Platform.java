import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Platform {
    private final Position left;
    private final Position right;
    private final List<Wall> walls;

    public Platform(Position left, Position right) {
        this.left = left;
        this.right = right;
        walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        int n = right.getX() - left.getX() + 1;
        for (int i = 0; i < n; i++)
            walls.add(new Wall(left.getX() + i, left.getY()));

        return walls;
    }

    public Position getLeft() {
        return left;
    }

    public Position getRight() {
        return right;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void draw(TextGraphics screen) {
        for (Wall w : walls)
            w.draw(screen);
    }
}
