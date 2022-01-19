package com.g1004.getout.element.monster;

import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.strategy.TeleportationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GhostTest {
    private Ghost ghost;

    @BeforeEach
    public void setup() {
        ghost = new Ghost(4, 4, new Platform(new Position(1, 5), new Position(8, 5)));
    }

    @Test
    public void attributes() {
        Assertions.assertEquals("h", ghost.getSymbol());
        Assertions.assertEquals("#A9A9A9", ghost.getColour());
        Assertions.assertEquals(4, ghost.attack());
    }

    @Test
    public void equals() {
        Ghost g2 = new Ghost(4, 4, new Platform(new Position(1, 5), new Position(8, 5)));
        Assertions.assertEquals(ghost, g2);
    }

    @Test
    public void move() {
        MoveStrategy h = ghost.getMovement();
        Assertions.assertTrue(h instanceof TeleportationStrategy);

        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        ghost.move();
        Assertions.assertEquals(0, ghost.getSteps());
    }
}
