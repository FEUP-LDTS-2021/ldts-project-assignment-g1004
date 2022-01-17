import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public interface GUI {
    Screen getScreen();
    int getWidth();
    int getHeight();

    void drawMenu();
    void drawLevels();
    void drawInstructions();

    void drawBackground();
    void drawWalls(List<Wall> walls);
    void drawPlatform(Platform platform);
    void drawLadder(Ladder ladder);
    void drawCoin(Coin coin);
    void drawHero(Hero hero, boolean ladder);
    void drawMonster(Monster monster, boolean ladder);
    void drawKey(Key key);
    void drawDoor(Door door);
    void drawHealthBar(Hero hero);
    void drawScore(int score);

    void resetBGColour();
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    KeyStroke keyPress() throws IOException;
}
