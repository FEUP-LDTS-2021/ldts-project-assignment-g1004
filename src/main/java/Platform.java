import java.util.ArrayList;
import java.util.List;

public class Platform {
    private final Position left;
    private final Position right;
    private final List<Wall> walls;

    public Platform(Position left, Position right) {
        this.left = left;
        this.right = right;

        List<Wall> walls = new ArrayList<>();
        // to do
        this.walls = walls;
    }

    public Position getLeft() {
        return left;
    }

    public Position getRight() {
        return right;
    }

    public void draw() {
        // to do
    }
}
