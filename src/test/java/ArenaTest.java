import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ArenaTest {
    @Test
    public void heroCall() {
        Arena arena = Mockito.spy(Arena.class);
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
    public void drawing() {
        Arena arena = new Arena();
        TextGraphics graphics = Mockito.spy(TextGraphics.class);

        arena.draw(graphics);
        Mockito.verify(graphics, Mockito.times(3)).setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        Screen screen = null;
        try {
            TerminalSize terminalSize = new TerminalSize(60, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        assert screen != null;

        Assertions.assertEquals(60, screen.getTerminalSize().getColumns());
        Assertions.assertEquals(20, screen.getTerminalSize().getRows());
    }

    @Test
    public void moveRight() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        Arena arena = new Arena(); // hero spawns at coordinates (1, 18)
        arena.processKey(key);

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
    }

    @Test
    public void moveRightV2() {
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(2);
        Mockito.when(position.getY()).thenReturn(18);

        Arena arena = new Arena(); // hero spawns at coordinates (1, 18)

        arena.processKey(new KeyStroke(KeyType.ArrowLeft)); // just to set direction as 'h'
        // he'll stay in place because there's a wall to his left

        // to prove he stayed in place
        Assertions.assertEquals(1, arena.getHero().getPosition().getX());
        Assertions.assertEquals(18, arena.getHero().getPosition().getY());

        // direction is set as 'h' so the conditions work properly
        arena.moveHero(position); // move right (with position mock)
        // canHeroMove() has calls to position getters

        Assertions.assertEquals(2, arena.getHero().getPosition().getX());
        Assertions.assertEquals(18, arena.getHero().getPosition().getY());
    }

    @Test
    public void jumpOffLadder() {
        Arena arena = new Arena(); // hero spawns at coordinates (1, 18)

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
}

