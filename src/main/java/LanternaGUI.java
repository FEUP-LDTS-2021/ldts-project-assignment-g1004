import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.List;

public class LanternaGUI implements GUI {
    private final int width;
    private final int height;
    private final Screen screen;
    private final TextGraphics graphics;

    public LanternaGUI(int width, int height) throws IOException {
        this.width = width;
        this.height = height;
        Terminal terminal = createTerminal();
        screen = createScreen(terminal);
        graphics = screen.newTextGraphics();
    }

    private Terminal createTerminal() throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen;
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    @Override
    public Screen getScreen() {
        return screen;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void drawMenu() {
        // to do
    }

    @Override
    public void drawLevels() {
        // to do
    }

    @Override
    public void drawInstructions() {
        // to do
    }

    @Override
    public void drawBackground() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
    }

    @Override
    public void drawWalls(List<Wall> walls) {
        for (Wall wall : walls) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(wall.getColour()));
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), wall.getSymbol());
        }
    }

    @Override
    public void drawPlatform(Platform platform) {
        for (Wall wall : platform.getWalls()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(wall.getColour()));
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), wall.getSymbol());
        }
    }

    @Override
    public void drawLadder(Ladder ladder) {
        for (Bar bar : ladder.getBars()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString(bar.getColour()));
            graphics.putString(new TerminalPosition(bar.getPosition().getX(), bar.getPosition().getY()), bar.getSymbol());
        }
    }

    @Override
    public void drawCoin(Coin coin) {
        graphics.setForegroundColor(TextColor.Factory.fromString(coin.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(coin.getPosition().getX(), coin.getPosition().getY()), coin.getSymbol());
    }

    @Override
    public void drawKey(Key key) {
        graphics.setForegroundColor(TextColor.Factory.fromString(key.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(key.getPosition().getX(), key.getPosition().getY()), key.getSymbol());
    }

    @Override
    public void drawDoor(Door door) {
        graphics.setForegroundColor(TextColor.Factory.fromString(door.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(door.getPosition().getX(), door.getPosition().getY()), door.getSymbol());
    }

    @Override
    public void drawHero(Hero hero, boolean ladder) {
        if (ladder)
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));

        graphics.setForegroundColor(TextColor.Factory.fromString(hero.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), hero.getSymbol());
    }

    @Override
    public void drawMonster(Monster monster, boolean ladder) {
        if (ladder)
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFA500"));

        graphics.setForegroundColor(TextColor.Factory.fromString(monster.getColour()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(monster.getPosition().getX(), monster.getPosition().getY()), monster.getSymbol());
    }

    @Override
    public void drawHealthBar(Hero hero) {
        String HP = "HP[" + Integer.toString(hero.getHP()) + "]";
        graphics.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(1, height - 2), HP);

        resetBGColour();
        for (int c = 8; c <= 28; c++)
            graphics.putString(new TerminalPosition(c, height - 2), " ");

        for (int c = 8; c <= 8 + hero.getHP(); c++) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#00A814"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(c, height - 2), " ");
        }
    }

    @Override
    public void drawScore(int score) {
        String s = "Score: " + Integer.toString(score);
        graphics.setBackgroundColor(TextColor.Factory.fromString("#964B00"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(new TerminalPosition(48, height - 2), s);
    }

    @Override
    public void resetBGColour() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }

    @Override
    public KeyStroke keyPress() throws IOException {
        return screen.pollInput();
    }
}
