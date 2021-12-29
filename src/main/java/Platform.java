import java.util.ArrayList;
import java.util.List;

public class Platform {
    private final Position left;
    private final Position right;
    private final List<Wall> walls;

    public Platform(Position left, Position right) {
        this.left = left;
        this.right = right;
        this.walls = createWalls();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        // to do
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

    public void draw() {
        // to do
    }
}
