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
        gui = new LanternaGUI(60, 22);
    }

    @Test
    public void draw() {
        GUI g = Mockito.spy(GUI.class);
        Arena arena = new Arena(g);

        arena.draw();

        Mockito.verify(g, Mockito.times(1)).drawBackground();
        Mockito.verify(g, Mockito.times(1)).drawWalls(Mockito.anyList());
        Mockito.verify(g, Mockito.atLeastOnce()).drawPlatform(Mockito.any(Platform.class));
        Mockito.verify(g, Mockito.atLeastOnce()).drawLadder(Mockito.any(Ladder.class));
        Mockito.verify(g, Mockito.atLeastOnce()).resetBGColour();
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

        Arena arena = new Arena(gui); // hero spawns at coordinates (1, 18)
        arena.processKey(key);

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
    }

    @Test
    public void moveHero() {
        Arena arena = new Arena(gui); // hero spawns at coordinates (1, 18)

        arena.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'
        // he'll stay in place because there's a wall to his left
        Assertions.assertEquals(1, arena.getHero().getPosition().getX());
        Assertions.assertEquals(18, arena.getHero().getPosition().getY());
        // to check if he stayed in place

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(2);
        Mockito.when(position.getY()).thenReturn(18);

        // direction is set as 'h' so the conditions work properly
        arena.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
        Assertions.assertEquals(18, arena.getHero().getPosition().getY());
    }

    @Test
    public void heroCall() {
        Arena a = new Arena(gui);
        Arena arena = Mockito.spy(a);
        // hero starts at position (1, 18)

        KeyStroke key1 = new KeyStroke(KeyType.ArrowRight);
        Position pos1 = new Position(2, 18);

        Mockito.verify(arena, Mockito.never()).moveHero(pos1);
        arena.processKey(key1);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos1);
        Assertions.assertEquals(pos1, arena.getHero().getPosition());

        KeyStroke key2 = new KeyStroke(KeyType.ArrowDown);
        Position pos2 = new Position(2, 19);
        arena.processKey(key2);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos2);
        // there's a wall in pos2 so the hero won't be able to move to that place
        Assertions.assertEquals(pos1, arena.getHero().getPosition());

        KeyStroke key3 = new KeyStroke(KeyType.ArrowUp);
        Position pos3 = new Position(2, 17);
        arena.processKey(key3);
        Mockito.verify(arena, Mockito.times(1)).moveHero(pos3);
        // while on the ground the hero is only able to move left or right
        Assertions.assertEquals(pos1, arena.getHero().getPosition());
    }

    @Test
    public void jumpOffLadder() {
        Arena arena = new Arena(gui); // hero spawns at coordinates (1, 18)

        // to place hero in the middle of the first ladder in our arena
        for (int i = 0; i < 19; i++)
            arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.processKey(new KeyStroke(KeyType.ArrowUp));
        arena.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'

        // just checking if hero is where we set him
        Assertions.assertEquals(20, arena.getHero().getPosition().getX());
        Assertions.assertEquals(17, arena.getHero().getPosition().getY());

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(21);
        Mockito.when(position.getY()).thenReturn(17);

        // direction is set as 'h' so the conditions work properly
        arena.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        // he shouldn't be authorized to move right as he can't jump off ladders
        Assertions.assertEquals(20, arena.getHero().getPosition().getX());
        Assertions.assertEquals(17, arena.getHero().getPosition().getY());
    }

    @Test
    public void verifyMonsterCollisions() {
        Arena arena = new Arena(gui);

        arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.moveMonsters();

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(19);
        Mockito.when(position.getY()).thenReturn(15);

        arena.moveHero(position);
        Assertions.assertFalse(arena.verifyMonsterCollisions()); // only returns true if our hero dies in the process
        Assertions.assertEquals(18, arena.getHero().getHP()); // hero gets hurt
    }

    @Test
    public void score() {
        Arena arena = new Arena(gui);

        Assertions.assertEquals(0, arena.getScore());

        arena.processKey(new KeyStroke(KeyType.ArrowRight));

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(40);
        Mockito.when(position.getY()).thenReturn(18);
        // coordinates of a coin

        arena.moveHero(position);
        Assertions.assertEquals(1, arena.getScore());
    }

    @Test
    public void leave() {
        Arena arena = new Arena(gui);

        Assertions.assertFalse(arena.leave());
        arena.processKey(new KeyStroke(KeyType.ArrowRight));
        arena.moveHero(new Position(58,3)); // door coordinates
        Assertions.assertFalse(arena.leave());

        arena.moveHero(new Position(2,3)); // key coordinates
        arena.moveHero(new Position(58,3));
        Assertions.assertTrue(arena.leave());
    }
}