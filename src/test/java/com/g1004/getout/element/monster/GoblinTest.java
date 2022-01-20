package com.g1004.getout.element.monster;

import com.g1004.getout.structures.Platform;
import com.g1004.getout.position.Position;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.strategy.RegularStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoblinTest {
    private Goblin goblin;

    @BeforeEach
    public void setup() {
        goblin = new Goblin(6, 4, new Platform(new Position(1, 5), new Position(8, 5)));
    }

    @Test
    public void attributes() {
        Assertions.assertEquals("g", goblin.getSymbol());
        Assertions.assertEquals("#0E630D", goblin.getColour());
        Assertions.assertEquals(2, goblin.attack());
        Assertions.assertEquals(5, goblin.getPlatform().getLeft().getY());
    }

    @Test
    public void equals() {
        Goblin g2 = new Goblin(6, 4, new Platform(new Position(1, 5), new Position(8, 5)));
        Assertions.assertEquals(goblin, g2);
    }

    @Test
    public void move() {
        MoveStrategy g = goblin.getMovement();
        Assertions.assertTrue(g instanceof RegularStrategy);
        goblin.checkDirection();
        Assertions.assertTrue(goblin.movingForward());

        goblin.move();
        goblin.move();
        goblin.move();
        Assertions.assertFalse(goblin.movingForward());
    }
}
