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
        // to do
        return 0;
    }

    @Override
    public int getHeight() {
        // to do
        return 0;
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
        // to do
    }

    @Override
    public void drawWalls(List<Wall> walls) {
        // to do
    }

    @Override
    public void drawPlatform(Platform platform) {
        // to do
    }

    @Override
    public void drawLadder(Ladder ladder) {
        // to do
    }

    @Override
    public void drawCoin(Coin coin) {
        // to do
    }

    @Override
    public void drawKey(Key key) {
        // to do
    }

    @Override
    public void drawDoor(Door door) {
        // to do
    }

    @Override
    public void drawHero(Hero hero, boolean ladder) {
        // to do
    }

    @Override
    public void drawMonster(Monster monster, boolean ladder) {
        // to do
    }

    @Override
    public void drawHealthBar(Hero hero) {
        // to do
    }

    @Override
    public void drawScore(int score) {
        // to do
    }

    @Override
    public void resetBGColour() {
        // to do
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
