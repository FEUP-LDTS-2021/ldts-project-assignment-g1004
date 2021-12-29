import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LadderTest {
    private List<Bar> expected;

    @BeforeEach
    public void setup() {
        expected = Arrays.asList(new Bar(6, 1), new Bar(6, 2), new Bar(6, 3), new Bar(6, 4));
    }

    @Test
    public void createBars() {
        Ladder l = new Ladder(new Position(6, 1), new Position(6, 4));
        Assertions.assertEquals(expected, l.getBars());
    }
}
