import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroTest {
    @Test
    public void move() {
        Hero h = new Hero(10, 10);

        Position p1 = new Position(10, 9);
        Assertions.assertEquals(p1, h.moveUp());

        Position p2 = new Position(10, 11);
        Assertions.assertEquals(p2, h.moveDown());

        Position p3 = new Position(9, 10);
        Assertions.assertEquals(p3, h.moveLeft());

        Position p4 = new Position(11, 10);
        Assertions.assertEquals(p4, h.moveRight());

    }
}
