import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoinTest {
    @Test
    public void attributes() {
        Coin c = new Coin(10, 10);

        Assertions.assertEquals("$", c.getSymbol());
        Assertions.assertEquals("#B5A710", c.getColour());
    }

    @Test
    public void equals() {
        Coin c1 = new Coin(10, 10);
        Coin c2 = new Coin(10, 10);

        Assertions.assertEquals(c1, c2);
    }
}
