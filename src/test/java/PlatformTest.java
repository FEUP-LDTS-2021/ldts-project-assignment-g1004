import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class PlatformTest {
    private Platform platform;

    @BeforeEach
    public void setup() {
        platform = new Platform(new Position(1, 5), new Position(4, 5));
    }

    @Test
    public void createWalls() {
        List<Wall> expected = Arrays.asList(new Wall(1, 5), new Wall(2, 5), new Wall(3, 5), new Wall(4, 5));
        Assertions.assertEquals(expected, platform.getWalls());
    }

    @Test
    public void tips() {
        Assertions.assertEquals(new Position(1, 5), platform.getLeft());
        Assertions.assertEquals(new Position(4, 5), platform.getRight());
    }

    @Test
    public void hasElement() {
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(2);
        Mockito.when(position.getY()).thenReturn(4);

        Assertions.assertTrue(platform.hasElement(position));
    }
}