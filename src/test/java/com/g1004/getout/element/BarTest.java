package com.g1004.getout.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BarTest {
    @Test
    public void attributes() {
        Bar b = new Bar(10, 10);

        Assertions.assertEquals(" ", b.getSymbol());
        Assertions.assertEquals("#FFA500", b.getColour());
    }

    @Test
    public void equals() {
        Bar b1 = new Bar(10, 10);
        Bar b2 = new Bar(10, 10);

        Assertions.assertEquals(b1, b2);
    }
}
