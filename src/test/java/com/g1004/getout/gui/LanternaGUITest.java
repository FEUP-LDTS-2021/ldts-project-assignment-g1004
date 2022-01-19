package com.g1004.getout.gui;

import com.g1004.getout.Ladder;
import com.g1004.getout.Platform;
import com.g1004.getout.Position;
import com.g1004.getout.element.*;
import com.g1004.getout.element.monster.Goblin;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LanternaGUITest {
    private GUI gui;

    @BeforeEach
    public void setup() throws IOException {
        gui = new LanternaGUI(75, 27);
    }

    @Test
    public void attributes() {
        Assertions.assertEquals(75, gui.getWidth());
        Assertions.assertEquals(75, gui.getScreen().getTerminalSize().getColumns());
        Assertions.assertEquals(27, gui.getHeight());
        Assertions.assertEquals(27, gui.getScreen().getTerminalSize().getRows());
    }

    @Test
    public void drawMenu() {
        gui.drawMenu();

        Assertions.assertEquals(TextColor.Factory.fromString("#58181F"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
        Assertions.assertEquals("G", gui.getScreen().getBackCharacter(33, 6).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFFF00"), gui.getScreen().getBackCharacter(33,6).getForegroundColor());
        Assertions.assertEquals("!", gui.getScreen().getBackCharacter(40, 6).getCharacterString());
        Assertions.assertEquals("1", gui.getScreen().getBackCharacter(34, 13).getCharacterString());
        Assertions.assertEquals("2", gui.getScreen().getBackCharacter(30, 16).getCharacterString());
        Assertions.assertEquals("0", gui.getScreen().getBackCharacter(34, 19).getCharacterString());
    }

    @Test
    public void drawLevels() {
        gui.drawLevels(2, Arrays.asList(6, 7, 0, 0, 0, 0, 0, 0, 0, 0));

        Assertions.assertEquals(TextColor.Factory.fromString("#301934"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
        Assertions.assertEquals("L", gui.getScreen().getBackCharacter(34, 3).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFA500"), gui.getScreen().getBackCharacter(34,3).getForegroundColor());
        Assertions.assertEquals("1", gui.getScreen().getBackCharacter(7, 9).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFA500"), gui.getScreen().getBackCharacter(7,9).getForegroundColor());
        Assertions.assertEquals("7", gui.getScreen().getBackCharacter(24, 11).getCharacterString());
        Assertions.assertEquals("0", gui.getScreen().getBackCharacter(45, 17).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#F5F5DC"), gui.getScreen().getBackCharacter(45,17).getForegroundColor());
    }

    @Test
    public void drawInstructions() {
        gui.drawInstructions();

        Assertions.assertEquals(TextColor.Factory.fromString("#ADD8E6"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
        Assertions.assertEquals("I", gui.getScreen().getBackCharacter(31, 2).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(31,2).getForegroundColor());
        Assertions.assertEquals("a", gui.getScreen().getBackCharacter(7, 11).getCharacterString());
    }

    @Test
    public void drawBackground() {
        gui.drawBackground("#F5F5DC");

        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(0,0).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#F5F5DC"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
    }

    @Test
    public void drawWalls() {
        List<Wall> walls = Arrays.asList(new Wall(0, 1), new Wall(0, 0), new Wall(1, 0));
        gui.drawWalls(walls);

        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(0,1).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(0,0).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(1,0).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#964B00"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
    }

    @Test
    public void drawPlatform() {
        gui.drawPlatform(new Platform(new Position(1, 5), new Position(4, 5)));

        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(1,5).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(3,5).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(4,5).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#964B00"), gui.getScreen().getBackCharacter(3,5).getBackgroundColor());
    }

    @Test
    public void drawLadder() {
        gui.drawLadder(new Ladder(new Position(6, 1), new Position(6, 4)));

        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(6,1).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(6,3).getCharacterString());
        Assertions.assertEquals(" ", gui.getScreen().getBackCharacter(6,4).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFA500"), gui.getScreen().getBackCharacter(6,3).getBackgroundColor());
    }

    @Test
    public void drawCoin() {
        gui.drawCoin(new Coin(10, 10));

        Assertions.assertEquals("$", gui.getScreen().getBackCharacter(10,10).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#B5A710"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
    }

    @Test
    public void drawHero() {
        gui.drawHero(new Hero(10, 10), false);

        Assertions.assertEquals("X", gui.getScreen().getBackCharacter(10,10).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FF0000"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
    }

    @Test
    public void drawMonster() {
        Platform platform = Mockito.mock(Platform.class);
        gui.drawMonster(new Goblin(10, 10, platform), true);

        Assertions.assertEquals("g", gui.getScreen().getBackCharacter(10,10).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFA500"), gui.getScreen().getBackCharacter(10,10).getBackgroundColor());
        Assertions.assertEquals(TextColor.Factory.fromString("#0E630D"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
    }

    @Test
    public void drawKey() {
        gui.drawKey(new Key(10, 10));

        Assertions.assertEquals("F", gui.getScreen().getBackCharacter(10,10).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#B57B0E"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
    }

    @Test
    public void drawDoor() {
        gui.drawDoor(new Door(10, 10));

        Assertions.assertEquals("0", gui.getScreen().getBackCharacter(10,10).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#0000FF"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
    }

    @Test
    public void drawHealthBar() {
        Hero hero = new Hero(10, 10);
        hero.hurt(4);

        gui.drawHealthBar(hero);

        Assertions.assertEquals("P", gui.getScreen().getBackCharacter(3, gui.getHeight() - 2).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(3,gui.getHeight() - 2).getForegroundColor());

        Assertions.assertEquals(TextColor.Factory.fromString("#00A814"), gui.getScreen().getBackCharacter(9,gui.getHeight() - 2).getBackgroundColor());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFFFFF"), gui.getScreen().getBackCharacter(27,gui.getHeight() - 2).getBackgroundColor());
    }

    @Test
    public void drawScore() {
        gui.drawScore(5);

        Assertions.assertEquals("c", gui.getScreen().getBackCharacter(65, gui.getHeight() - 2).getCharacterString());
        Assertions.assertEquals("5", gui.getScreen().getBackCharacter(71, gui.getHeight() - 2).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(71,gui.getHeight() - 2).getForegroundColor());
    }

    @Test
    public void drawVictory() {
        gui.drawVictory();

        Assertions.assertEquals(TextColor.Factory.fromString("#C7C72A"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
        Assertions.assertEquals("Y", gui.getScreen().getBackCharacter(17, 13).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(17,13).getForegroundColor());
    }

    @Test
    public void drawDefeat() {
        gui.drawDefeat();

        Assertions.assertEquals(TextColor.Factory.fromString("#0D455E"), gui.getScreen().getBackCharacter(0,0).getBackgroundColor());
        Assertions.assertEquals("Y", gui.getScreen().getBackCharacter(28, 13).getCharacterString());
        Assertions.assertEquals(TextColor.Factory.fromString("#FFFFFF"), gui.getScreen().getBackCharacter(28,13).getForegroundColor());
    }

    @Test
    public void setBGColour() {
        gui.setBGColour("#F5F5DC");
        gui.drawKey(new Key(10, 10));

        Assertions.assertEquals(TextColor.Factory.fromString("#F5F5DC"), gui.getScreen().getBackCharacter(10,10).getBackgroundColor());
    }

    @Test
    public void clear() {
        gui.drawWalls(Arrays.asList(new Wall(0, 0), new Wall(0, 1)));
        gui.clear();

        Assertions.assertEquals(TextColor.ANSI.DEFAULT, gui.getScreen().getBackCharacter(0, 0).getBackgroundColor());
    }
}
