import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setup() {
        position = new Position(1, 2);
    }

    @Test
    public void distanceTo() {
        Position p2 = new Position(3, 5);

        double d1 = position.distanceTo(p2);
        double d2 = Math.sqrt(13);

        Assertions.assertEquals(d2, d1);
    }

    @Test
    public void moveTo() {
        Position p2 = new Position(2, 4);
        position.moveTo(p2);

        Assertions.assertEquals(2, position.getX());
        Assertions.assertEquals(4, position.getY());
    }
}
