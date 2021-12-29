import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PlatformTest {
    private List<Wall> expected;

    @BeforeEach
    public void setup() {
        expected = Arrays.asList(new Wall(1, 5), new Wall(2, 5), new Wall(3, 5), new Wall(4, 5));
    }

    @Test
    public void createWalls() {
        Platform p = new Platform(new Position(1, 5), new Position(4, 5));
        Assertions.assertEquals(expected, p.getWalls());
    }
}