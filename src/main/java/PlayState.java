import com.googlecode.lanterna.input.KeyStroke;

public class PlayState extends GameState {
    private final int numLevel;
    private Arena arena;

    public PlayState(Game game, GUI gui, int numLevel) {
        super(game, gui);
        this.numLevel = numLevel;
        arena = new Arena(gui, numLevel);
    }

    @Override
    public void display() {
        // to do
    }

    @Override
    public void readKey(KeyStroke key) {
        // to do
    }

    @Override
    public void process(int delay) {
        // to do
    }
}
