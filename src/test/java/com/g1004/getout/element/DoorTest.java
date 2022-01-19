package com.g1004.getout.element;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoorTest {
    @Test
    public void attributes() {
        Door d = new Door(10, 10);

        Assertions.assertEquals("0", d.getSymbol());
        Assertions.assertEquals("#0000FF", d.getColour());
    }

    @Test
    public void equals() {
        Door d1 = new Door(10, 10);
        Door d2 = new Door(10, 10);

        Assertions.assertEquals(d1, d2);
    }
}
