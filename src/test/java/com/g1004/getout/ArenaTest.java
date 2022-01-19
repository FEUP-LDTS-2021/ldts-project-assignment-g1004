package com.g1004.getout;

import com.g1004.getout.element.Coin;
import com.g1004.getout.element.Door;
import com.g1004.getout.element.Hero;
import com.g1004.getout.element.Key;
import com.g1004.getout.element.monster.Monster;
import com.g1004.getout.gui.GUI;
import com.g1004.getout.gui.LanternaGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaTest {
    private GUI gui;

    @BeforeEach
    public void setup() throws IOException {
        gui = new LanternaGUI(75, 27);
    }

    @Test
    public void draw() {
        GUI g = Mockito.spy(GUI.class);
        Arena arena = new Arena(g, 1);

        arena.draw();

        Mockito.verify(g, Mockito.times(1)).drawBackground(Mockito.anyString());
        Mockito.verify(g, Mockito.times(1)).drawWalls(Mockito.anyList());
        Mockito.verify(g, Mockito.atLeastOnce()).drawPlatform(Mockito.any(Platform.class));
        Mockito.verify(g, Mockito.atLeastOnce()).drawLadder(Mockito.any(Ladder.class));
        Mockito.verify(g, Mockito.atLeastOnce()).setBGColour(Mockito.anyString());
        Mockito.verify(g, Mockito.atMostOnce()).drawKey(Mockito.any(Key.class));
        Mockito.verify(g, Mockito.times(1)).drawDoor(Mockito.any(Door.class));
        Mockito.verify(g, Mockito.atLeastOnce()).drawCoin(Mockito.any(Coin.class));
        Mockito.verify(g, Mockito.times(1)).drawHero(Mockito.any(Hero.class), Mockito.anyBoolean());
        Mockito.verify(g, Mockito.atLeastOnce()).drawMonster(Mockito.any(Monster.class), Mockito.anyBoolean());
        Mockito.verify(g, Mockito.times(1)).drawHealthBar(Mockito.any(Hero.class));
        Mockito.verify(g, Mockito.times(1)).drawScore(Mockito.anyInt());
    }

    @Test
    public void processKey() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        Arena arena = new Arena(gui, 1); // hero spawns at coordinates (1, 23)
        arena.processKey(key);

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
    }

    @Test
    public void moveHero() {
        Arena arena = new Arena(gui, 1); // hero spawns at coordinates (1, 23)

        arena.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'
        // he'll stay in place because there's a wall to his left
        Assertions.assertEquals(1, arena.getHero().getPosition().getX());
        Assertions.assertEquals(23, arena.getHero().getPosition().getY());
        // to check if he stayed in place

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(2);
        Mockito.when(position.getY()).thenReturn(23);

        // direction is set as 'h' so the conditions work properly
        arena.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
        Assertions.assertEquals(23, arena.getHero().getPosition().getY());
    }

    @Test
    public void heroCall() {
        Arena a = new Arena(gui, 1);
        Arena arena = Mockito.spy(a);
        // hero starts at position (1, 18)

        KeyStroke key1 = new KeyStroke(KeyType.ArrowRight);
        Position pos1 = new Position(2, 23);

        Mockito.verify(arena, Mockito.never()).moveHero(pos1);
        arena.processKey(key1);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos1);
        Assertions.assertEquals(pos1, arena.getHero().getPosition());

        KeyStroke key2 = new KeyStroke(KeyType.ArrowDown);
        Position pos2 = new Position(2, 24);
        arena.processKey(key2);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos2);
        // there's a wall in pos2 so the hero won't be able to move to that place
        Assertions.assertEquals(pos1, arena.getHero().getPosition());

        KeyStroke key3 = new KeyStroke(KeyType.ArrowUp);
        Position pos3 = new Position(2, 22);
        arena.processKey(key3);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos3);
        // while on the ground the hero is only able to move left or right
        Assertions.assertEquals(pos1, arena.getHero().getPosition());
    }

    @Test
    public void jumpOffLadder() {
        Arena arena = new Arena(gui, 1); // hero spawns at coordinates (1, 23)

        // to place hero in the middle of the first ladder in our arena
        for (int i = 0; i < 34; i++)
            arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.processKey(new KeyStroke(KeyType.ArrowUp));
        arena.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'

        // just checking if hero is where we set him
        Assertions.assertEquals(35, arena.getHero().getPosition().getX());
        Assertions.assertEquals(22, arena.getHero().getPosition().getY());

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(36);
        Mockito.when(position.getY()).thenReturn(22);

        // direction is set as 'h' so the conditions work properly
        arena.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        // he shouldn't be authorized to move right as he can't jump off ladders
        Assertions.assertEquals(35, arena.getHero().getPosition().getX());
        Assertions.assertEquals(22, arena.getHero().getPosition().getY());
    }

    @Test
    public void verifyMonsterCollisions() {
        Arena arena = new Arena(gui, 1);

        arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.moveMonsters();

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(16);
        Mockito.when(position.getY()).thenReturn(19);

        arena.moveHero(position);
        Assertions.assertFalse(arena.verifyMonsterCollisions()); // only returns true if our hero dies in the process
        Assertions.assertEquals(18, arena.getHero().getHP()); // hero gets hurt
    }

    @Test
    public void score() {
        Arena arena = new Arena(gui, 1);

        Assertions.assertEquals(0, arena.getScore());

        arena.processKey(new KeyStroke(KeyType.ArrowRight));

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(45);
        Mockito.when(position.getY()).thenReturn(23);
        // coordinates of a coin

        arena.moveHero(position);
        Assertions.assertEquals(1, arena.getScore());
    }

    @Test
    public void leave() {
        GUI g = Mockito.spy(GUI.class);
        Arena arena = new Arena(g, 1);

        Assertions.assertFalse(arena.leave());
        arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.moveHero(new Position(73,7)); // door coordinates
        Assertions.assertFalse(arena.leave());

        arena.moveHero(new Position(2,7)); // key coordinates
        arena.moveHero(new Position(73,7));
        Assertions.assertTrue(arena.leave());

        Mockito.verify(g, Mockito.times(1)).drawVictory();
    }
}

