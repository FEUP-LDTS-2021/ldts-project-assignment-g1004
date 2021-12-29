import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroTest {
    @Test
    public void move() {
        Hero h = new Hero(10, 10);

        h.moveUp();
        Position p1 = new Position(10, 9);
        Assertions.assertEquals(p1, h.getPosition());

        h.moveDown();
        Position p2 = new Position(10, 10);
        Assertions.assertEquals(p2, h.getPosition());

        h.moveLeft();
        Position p3 = new Position(9, 10);
        Assertions.assertEquals(p3, h.getPosition());

        h.moveRight();
        Position p4 = new Position(10, 10);
        Assertions.assertEquals(p4, h.getPosition());
    }
}
