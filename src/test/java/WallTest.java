import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {
    @Test
    public void attributes() {
        Wall w = new Wall(10, 10);

        Assertions.assertEquals(" ", w.getSymbol());
        Assertions.assertEquals("#964B00", w.getColour());
    }

    @Test
    public void equals() {
        Wall w1 = new Wall(10, 10);
        Wall w2 = new Wall(10, 10);

        Assertions.assertEquals(w1, w2);
    }
}
