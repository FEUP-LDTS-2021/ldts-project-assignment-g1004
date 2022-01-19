package com.g1004.getout.element.monster;

import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.strategy.ConfusedStrategy;
import com.g1004.getout.strategy.MoveStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZombieTest {
    private Zombie zombie;

    @BeforeEach
    public void setup() {
        zombie = new Zombie(3, 4, new Platform(new Position(1, 5), new Position(8, 5)));
    }

    @Test
    public void attributes() {
        Assertions.assertEquals("Z", zombie.getSymbol());
        Assertions.assertEquals("#964B00", zombie.getColour());
        Assertions.assertEquals(3, zombie.attack());
    }

    @Test
    public void equals() {
        Zombie z2 = new Zombie(3, 4, new Platform(new Position(1, 5), new Position(8, 5)));
        Assertions.assertEquals(zombie, z2);
    }

    @Test
    public void move() {
        MoveStrategy z = zombie.getMovement();
        Assertions.assertTrue(z instanceof ConfusedStrategy);
        zombie.step();
        Assertions.assertEquals(1, zombie.getSteps());
        zombie.reset();
        Assertions.assertEquals(0, zombie.getSteps());

        zombie.move();
        zombie.move();
        zombie.move();
        Position p = new Position(4, 4);
        Assertions.assertEquals(p, zombie.getPosition());
        Assertions.assertTrue(zombie.movingForward());
    }
}
