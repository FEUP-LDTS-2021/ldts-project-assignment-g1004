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

    /**
     * Constructor.
     * It initializes arena and implements Lanterna methods, adding the screen to the arena.
     */
    public Game() {
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

        arena = new Arena();
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
        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    screen.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
                processKey(key);

                if (arena.verifyMonsterCollisions() || arena.leave()) {
                    screen.close();
                    break;
                }

                arena.moveMonsters();

                if (arena.verifyMonsterCollisions()) {
                    screen.close();
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}