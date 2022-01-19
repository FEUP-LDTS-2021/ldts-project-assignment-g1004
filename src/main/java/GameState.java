import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public abstract class GameState {
    protected final Game game;
    protected final GUI gui;

    public GameState(Game game, GUI gui) {
        this.game = game;
        this.gui = gui;
    }

    public abstract void display() throws IOException;
    public abstract void readKey(KeyStroke key);
    public abstract void process(int delay) throws IOException;
}
