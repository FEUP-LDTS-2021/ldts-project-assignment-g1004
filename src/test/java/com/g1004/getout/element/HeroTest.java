package com.g1004.getout.element;

import com.g1004.getout.position.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeroTest {
    private Hero hero;

    @BeforeEach
    public void setup() {
        hero = new Hero(10, 10);
    }

    @Test
    public void attributes() {
        Assertions.assertEquals("X", hero.getSymbol());
        Assertions.assertEquals("#FF0000", hero.getColour());
    }

    @Test
    public void move() {
        Position p1 = new Position(10, 9);
        Assertions.assertEquals(p1, hero.moveUp());

        Position p2 = new Position(10, 11);
        Assertions.assertEquals(p2, hero.moveDown());

        Position p3 = new Position(9, 10);
        Assertions.assertEquals(p3, hero.moveLeft());

        Position p4 = new Position(11, 10);
        Assertions.assertEquals(p4, hero.moveRight());
    }

    @Test
    public void hurt() {
        Assertions.assertEquals(20, hero.getHP());
        hero.hurt(4);
        Assertions.assertEquals(16, hero.getHP());
        hero.hurt(13);
        Assertions.assertFalse(hero.isDead());
        hero.hurt(4);
        Assertions.assertTrue(hero.isDead());
    }

    @Test
    public void key(){
        Assertions.assertFalse(hero.hasKey());
        hero.catchKey();
        Assertions.assertTrue(hero.hasKey());
    }
}