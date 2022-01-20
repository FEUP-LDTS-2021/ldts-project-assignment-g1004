package com.g1004.getout.MVC;

import com.g1004.getout.structures.Platform;
import com.g1004.getout.position.Position;
import com.g1004.getout.element.monster.Boss;
import com.g1004.getout.element.monster.Goblin;
import com.g1004.getout.gui.GUI;
import com.g1004.getout.gui.LanternaGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaControllerTest {
    private GUI gui;
    private Arena arena;
    private ArenaController controller;

    @BeforeEach
    public void setup() throws IOException {
        gui = new LanternaGUI(75, 27);
        arena = new Arena(1);
        controller = new ArenaController(arena, gui);
    }

    @Test
    public void processKey() {
        ArenaController c = Mockito.spy(controller);

        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        controller.processKey(key); // hero spawns at coordinates (1, 23)

        Assertions.assertEquals(2, arena.hero().getPosition().getX());
    }

    @Test
    public void moveHero() {
        // hero spawns at coordinates (1, 23)
        controller.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'
        // he'll stay in place because there's a wall to his left
        Assertions.assertEquals(1, arena.hero().getPosition().getX());
        Assertions.assertEquals(23, arena.hero().getPosition().getY());
        // to check if he stayed in place

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(2);
        Mockito.when(position.getY()).thenReturn(23);

        // direction is set as 'h' so the conditions work properly
        controller.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        Assertions.assertEquals(2, arena.hero().getPosition().getX());
        Assertions.assertEquals(23, arena.hero().getPosition().getY());
    }

    @Test
    public void moveMonsters() {
        controller.moveMonsters();

        Goblin g = new Goblin(16, 19, new Platform(new Position(15, 20), new Position(55, 20)));
        Assertions.assertEquals(g, arena.monsters().get(0));
    }

    @Test
    public void changeBossSpot() {
        Arena a = new Arena(10);
        ArenaController c = new ArenaController(a, gui);

        Boss b = new Boss(20, 15, new Platform(new Position(20, 16), new Position(52, 16)));
        c.changeBossSpot();

        boolean presence = false;
        int y = a.monsters().get(0).getPosition().getY();

        if (y == 15 || y == 12 || y == 9 || y == 6) // possible heights where the boss can move to
            presence = true;

        Assertions.assertTrue(presence);
    }

    @Test
    public void verifyMonsterCollisions() {
        controller.processKey(new KeyStroke(KeyType.ArrowRight));
        controller.moveMonsters();

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(16);
        Mockito.when(position.getY()).thenReturn(19);

        controller.moveHero(position);
        Assertions.assertFalse(controller.verifyMonsterCollisions()); // only returns true if our hero dies in the process
        Assertions.assertEquals(18, arena.hero().getHP()); // hero gets hurt
    }

    @Test
    public void leave() {
        Assertions.assertFalse(controller.leave());
        controller.processKey(new KeyStroke(KeyType.ArrowRight));
        controller.moveHero(new Position(73,7)); // door coordinates
        Assertions.assertFalse(controller.leave());

        controller.moveHero(new Position(2,7)); // key coordinates
        controller.moveHero(new Position(73,7));
        Assertions.assertTrue(controller.leave());
    }

    @Test
    public void checkScore() {
        Assertions.assertEquals(0, controller.checkScore());

        controller.processKey(new KeyStroke(KeyType.ArrowRight));

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(45);
        Mockito.when(position.getY()).thenReturn(23);
        // coordinates of a coin

        controller.moveHero(position);
        Assertions.assertEquals(1, controller.checkScore());
    }

    @Test
    public void heroCall() {
        ArenaController c = Mockito.spy(controller); // hero starts at position (1, 18)

        KeyStroke key1 = new KeyStroke(KeyType.ArrowRight);
        Position pos1 = new Position(2, 23);

        Mockito.verify(c, Mockito.never()).moveHero(pos1);
        c.processKey(key1);
        Mockito.verify(c, Mockito.times(1)).moveHero(pos1);
        Assertions.assertEquals(pos1, arena.hero().getPosition());

        KeyStroke key2 = new KeyStroke(KeyType.ArrowDown);
        Position pos2 = new Position(2, 24);
        c.processKey(key2);
        Mockito.verify(c, Mockito.times(1)).moveHero(pos2);
        // there's a wall in pos2 so the hero won't be able to move to that place
        Assertions.assertEquals(pos1, arena.hero().getPosition());

        KeyStroke key3 = new KeyStroke(KeyType.ArrowUp);
        Position pos3 = new Position(2, 22);
        c.processKey(key3);
        Mockito.verify(c, Mockito.times(1)).moveHero(pos3);
        // while on the ground the hero is only able to move left or right
        Assertions.assertEquals(pos1, arena.hero().getPosition());
    }

    @Test
    public void jumpOffLadder() {
        // hero spawns at coordinates (1, 23)
        // to place hero in the middle of the first ladder in our arena
        for (int i = 0; i < 34; i++)
            controller.processKey(new KeyStroke(KeyType.ArrowRight));
        controller.processKey(new KeyStroke(KeyType.ArrowUp));
        controller.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'

        // just checking if hero is where we set him
        Assertions.assertEquals(35, arena.hero().getPosition().getX());
        Assertions.assertEquals(22, arena.hero().getPosition().getY());

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(36);
        Mockito.when(position.getY()).thenReturn(22);

        // direction is set as 'h' so the conditions work properly
        controller.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        // he shouldn't be authorized to move right as he can't jump off ladders
        Assertions.assertEquals(35, arena.hero().getPosition().getX());
        Assertions.assertEquals(22, arena.hero().getPosition().getY());
    }
}
