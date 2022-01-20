package com.g1004.getout.element.monster;

import com.g1004.getout.structures.Platform;
import com.g1004.getout.position.Position;
import com.g1004.getout.strategy.MoveStrategy;
import com.g1004.getout.strategy.SpecialStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BossTest {
    private Boss boss;

    @BeforeEach
    public void setup() {
        boss = new Boss(3, 4, new Platform(new Position(3, 5), new Position(16, 5)));
    }

    @Test
    public void attributes() {
        Assertions.assertEquals("^~{o,,o}~^", boss.getSymbol());
        Assertions.assertEquals("#071669", boss.getColour());
        Assertions.assertEquals(1, boss.attack());
    }

    @Test
    public void move() {
        MoveStrategy b = boss.getMovement();
        Assertions.assertTrue(b instanceof SpecialStrategy);

        boss.move();
        Assertions.assertEquals(6, boss.getPosition().getX());
        boss.move();
        Assertions.assertEquals(3, boss.getPosition().getX());
        boss.switchPlatform(new Platform(new Position(1, 10), new Position(23, 10)));
        Assertions.assertEquals(new Position(3, 9), boss.getPosition());
        boss.switchPlatform(new Platform(new Position(8, 15), new Position(33, 15)));
        Assertions.assertEquals(new Position(8, 14), boss.getPosition());
    }
}