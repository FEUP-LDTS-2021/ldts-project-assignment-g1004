import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyTest {
    @Test
    public void attributes() {
        Key k = new Key(10, 10);

        Assertions.assertEquals("F", k.getSymbol());
        Assertions.assertEquals("#B57B0E", k.getColour());
    }

    @Test
    public void equals() {
        Key k1 = new Key(10, 10);
        Key k2 = new Key(10, 10);

        Assertions.assertEquals(k1, k2);
    }
}
