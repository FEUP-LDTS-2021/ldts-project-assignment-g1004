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
            gui = new LanternaGUI(60, 22);
        }

        @Test
        public void attributes() {
            Assertions.assertEquals(60, gui.getWidth());
            Assertions.assertEquals(60, gui.getScreen().getTerminalSize().getColumns());
            Assertions.assertEquals(22, gui.getHeight());
            Assertions.assertEquals(22, gui.getScreen().getTerminalSize().getRows());
        }

        @Test
        public void drawMenu() {
            // to do
        }

        @Test
        public void drawLevels() {
            // to do
        }

        @Test
        public void drawInstructions() {
            // to do
        }

        @Test
        public void drawBackground() {
            gui.drawBackground();

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
            Assertions.assertEquals(TextColor.Factory.fromString("#CCCC00"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
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
            Assertions.assertEquals(TextColor.Factory.fromString("#00FF00"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
        }

        @Test
        public void drawKey() {
            gui.drawKey(new Key(10, 10));

            Assertions.assertEquals("F", gui.getScreen().getBackCharacter(10,10).getCharacterString());
            Assertions.assertEquals(TextColor.Factory.fromString("#CCCC00"), gui.getScreen().getBackCharacter(10,10).getForegroundColor());
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

            Assertions.assertEquals("P", gui.getScreen().getBackCharacter(2, gui.getHeight() - 2).getCharacterString());
            Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(2,gui.getHeight() - 2).getForegroundColor());

            Assertions.assertEquals(TextColor.Factory.fromString("#00A814"), gui.getScreen().getBackCharacter(8,gui.getHeight() - 2).getBackgroundColor());
            Assertions.assertEquals(TextColor.Factory.fromString("#F5F5DC"), gui.getScreen().getBackCharacter(26,gui.getHeight() - 2).getBackgroundColor());
        }

        @Test
        public void drawScore() {
            gui.drawScore(5);

            Assertions.assertEquals("c", gui.getScreen().getBackCharacter(49, gui.getHeight() - 2).getCharacterString());
            Assertions.assertEquals("5", gui.getScreen().getBackCharacter(55, gui.getHeight() - 2).getCharacterString());
            Assertions.assertEquals(TextColor.Factory.fromString("#000000"), gui.getScreen().getBackCharacter(55,gui.getHeight() - 2).getForegroundColor());
        }

        @Test
        public void resetBGColour() {
            gui.resetBGColour();
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


