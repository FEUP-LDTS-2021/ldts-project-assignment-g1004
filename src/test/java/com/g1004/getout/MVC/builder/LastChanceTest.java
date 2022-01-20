package com.g1004.getout.MVC.builder;

import com.g1004.getout.MVC.Arena;
import com.g1004.getout.structures.Platform;
import com.g1004.getout.position.Position;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Boss;
import com.g1004.getout.gui.GUI;
import com.g1004.getout.gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LastChanceTest {
    private Arena arena;

    @BeforeEach
    public void setup() throws IOException {
        GUI gui = new LanternaGUI(75, 27);
        arena = new Arena(10);
    }

    @Test
    public void createBackground() {
        Assertions.assertEquals("#C99C99", arena.background());
    }

    @Test
    public void createPlatforms() {
        int size = arena.platforms().size();

        Assertions.assertEquals(7, size);
        Assertions.assertEquals(new Position(20, 16), arena.platforms().get(0).getLeft());
        Assertions.assertEquals(new Position(22, 4), arena.platforms().get(size - 1).getRight());
    }

    @Test
    public void createLadders() {
        int size = arena.ladders().size();

        Assertions.assertEquals(7, size);
        Assertions.assertEquals(new Position(33, 20), arena.ladders().get(0).getTop());
        Assertions.assertEquals(new Position(34, 9), arena.ladders().get(size - 1).getBottom());
    }

    @Test
    public void createCoins() {
        // to do
    }

    @Test
    public void createKey() {
        Assertions.assertEquals(new Key(7, 3), arena.key());
    }

    @Test
    public void createDoor() {
        Assertions.assertEquals(new Door(73, 20), arena.door());
    }

    @Test
    public void createMonster() {
        int size = arena.monsters().size();
        Assertions.assertEquals(1, arena.monsters().size());

        Boss b = new Boss(20, 15, new Platform(new Position(20, 16), new Position(52, 16)));
        Assertions.assertEquals(b.getPosition(), arena.monsters().get(0).getPosition());
    }
}
