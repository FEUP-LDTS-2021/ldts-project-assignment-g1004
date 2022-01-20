package com.g1004.getout.builder;

import com.g1004.getout.MVC.Arena;
import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.gui.GUI;
import com.g1004.getout.gui.LanternaGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GoblinLairTest {
    private Arena arena;

    @BeforeEach
    public void setup() throws IOException {
        GUI gui = new LanternaGUI(75, 27);
        arena = new Arena(gui, 1);
    }

    @Test
    public void createBackground() {
        Assertions.assertEquals("#90EE90", arena.background());
    }

    @Test
    public void createPlatforms() {
        int size = arena.platforms().size();

        Assertions.assertEquals(8, size);
        Assertions.assertEquals(new Position(15, 20), arena.platforms().get(0).getLeft());
        Assertions.assertEquals(new Position(73, 8), arena.platforms().get(size - 1).getRight());
    }

    @Test
    public void createLadders() {
        int size = arena.ladders().size();

        Assertions.assertEquals(9, size);
        Assertions.assertEquals(new Position(35, 20), arena.ladders().get(0).getTop());
        Assertions.assertEquals(new Position(60, 11), arena.ladders().get(size - 1).getBottom());
    }

    @Test
    public void createCoins() {
        int size = arena.coins().size();

        Assertions.assertEquals(10, size);
        Assertions.assertEquals(new Coin(45, 23), arena.coins().get(0));
        Assertions.assertEquals(new Coin(56, 7), arena.coins().get(size - 1));
    }

    @Test
    public void createKey() {
        Assertions.assertEquals(new Key(2, 7), arena.key());
    }

    @Test
    public void createDoor() {
        Assertions.assertEquals(new Door(73, 7), arena.door());
    }

    @Test
    public void createMonster() {
        int size = arena.monsters().size();
        Assertions.assertEquals(8, arena.monsters().size());

        Goblin g1 = new Goblin(15, 19, new Platform(new Position(15, 20), new Position(55, 20)));
        Assertions.assertEquals(g1, arena.monsters().get(0));

        Goblin g2 = new Goblin(54, 7, new Platform(new Position(54, 8), new Position(73, 8)));
        Assertions.assertEquals(g2, arena.monsters().get(size - 1));
    }
}