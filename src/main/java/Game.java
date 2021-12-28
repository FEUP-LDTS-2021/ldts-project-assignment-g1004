import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static java.lang.System.exit;

public class Game {

    Arena arena = new Arena(40, 20);

    private final TerminalScreen screen;

    public Game(int width, int height) throws IOException {
        //hero = new Hero(10, 10);
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

    }

    private void draw() throws IOException {
        this.screen.clear();
        this.screen.setCharacter(10, 10, TextCharacter.fromCharacter('X')[0]);
        arena.draw(screen);
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key);
    }

    public void run() throws IOException {
        while(true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType()== KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
            }
        }

    }


}
