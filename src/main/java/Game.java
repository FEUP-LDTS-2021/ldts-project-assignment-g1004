import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * Game class. This class sets the screen and the arena or level.
 */
public class Game {
    private Screen screen;  /** screen where game will be shown */
    private Arena arena;    /** the arena with game components */
    private final int fps;

    private static Game singleton = null;

    /**
     * Constructor.
     * It initializes arena and implements Lanterna methods, adding the screen to the arena.
     */
    private Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(60, 22);
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

        arena = new Arena();
        fps = 100;
    }

    public static Game getInstance() {
        if (singleton == null)
            singleton = new Game();

        return singleton;
    }

    /**
     * Draws the executable screen,
     * @throws IOException
     */
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    /**
     * When user input is obtained (key), the game handles a response from the game.
     * @param key is the user input.
     */
    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    /**
     * This method shows the main loop of the game.
     * When we run the game, the terminal is drawn and user input is processed.
     * It also checks if hero collided with monsters or, after moving them, if any monster collided with hero.
     */
    public void run() {
        int frameTime = 1000 / fps;
        int friction = 10;

        try {
            while (true) {
                if (friction == 0)
                    friction = 10;

                long startTime = System.currentTimeMillis();

                draw();

                KeyStroke key = screen.pollInput();
                if (key != null) {
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                        screen.close();
                    if (key.getKeyType() == KeyType.EOF)
                        break;
                    processKey(key);

                    if (arena.verifyMonsterCollisions() || arena.leave()) {
                        screen.close();
                        break;
                    }
                }

                if (friction == 10) {
                    arena.moveMonsters();
                    if (arena.verifyMonsterCollisions()) {
                        screen.close();
                        break;
                    }
                }

                long elapsedTime = System.currentTimeMillis() - startTime;
                long sleepTime = frameTime - elapsedTime;

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                }

                friction--;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}